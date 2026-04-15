package com.hostshw.demohosts.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VMServices {

    @Autowired
    private VMRepository vmrepo;

    @Autowired
    private HostRepository hostrepo;

    // Create VM + allocate resources from host
    public VM addVM(int hostId, VM avm) {

        Host host = hostrepo.findById(hostId).orElse(null);
        if (host == null) return null;

        // Check if host has enough resources pe and ram
        if (host.getAvailablePe() < avm.getPe() || host.getAvailableRam() < avm.getRam()) {
            return null; // not enough we return null
        }

        // Deduct resources from host hence allowing us to keep allocating and keep track
        host.setAvailablePe(host.getAvailablePe() - avm.getPe());
        host.setAvailableRam(host.getAvailableRam() - avm.getRam());
        hostrepo.save(host);

        // Link VM to host and save
        avm.setHost(host);
        return vmrepo.save(avm);
    }

    // Get all VMs on a host
    public List<VM> getVMs(int hostId) {
        return vmrepo.findByHostId(hostId);
    }

    // Get single VM on a host
    public VM getVM(int hostId, int vmId) {
        VM vm = vmrepo.findById(vmId).orElse(null);
        if (vm == null || vm.getHost().getId() != hostId) return null;
        return vm;
    }
    
    // update vm and return if its succesfull or not
    public String updateVM(int hostId, int vmId, VM updatedVM) {
        VM existing = vmrepo.findById(vmId).orElse(null);
        if (existing == null || existing.getHost().getId() != hostId) return "Error: VM not found!";

        Host host = existing.getHost();
        int restoredPe  = host.getAvailablePe()  + existing.getPe();
        int restoredRam = host.getAvailableRam() + existing.getRam();

        if (restoredPe < updatedVM.getPe() || restoredRam < updatedVM.getRam()) {
            return "Error: Not enough resources on host!"; 
        }

        host.setAvailablePe(restoredPe   - updatedVM.getPe());
        host.setAvailableRam(restoredRam - updatedVM.getRam());
        hostrepo.save(host);

        existing.setPe(updatedVM.getPe());
        existing.setRam(updatedVM.getRam());
        vmrepo.save(existing);
        return "VM Updated Successfully!";
    }

    //remove vm and return a string if its succeful or not
    public String removeVM(int hostId, int vmId) {
        VM vm = vmrepo.findById(vmId).orElse(null);
        if (vm == null || vm.getHost().getId() != hostId) return "Error: VM not found!";

        //return the resoruces to the host and remv the vm from host
        Host host = vm.getHost();
        host.setAvailablePe(host.getAvailablePe()   + vm.getPe());
        host.setAvailableRam(host.getAvailableRam() + vm.getRam());
        host.getVms().remove(vm);
        //save new changeds
        hostrepo.save(host);

        vmrepo.deleteById(vmId);
        return "VM Deleted Successfully!";
    }
}