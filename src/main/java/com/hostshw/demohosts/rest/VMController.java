package com.hostshw.demohosts.rest;

import com.hostshw.demohosts.model.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hosts/{hostid}/vms") //add the main path to get to vms
public class VMController {

    @Autowired
    private VMServices vmservice;

    @PostMapping
    public VM addVM(@PathVariable("hostid") int hostId, @RequestBody VM avm) {
        return vmservice.addVM(hostId, avm); // returns null if not enough resources
    }

    @GetMapping
    public List<VM> getVMs(@PathVariable("hostid") int hostId) {
        return vmservice.getVMs(hostId);
    }

    @GetMapping(path = "{vmid}")
    public VM getVM(@PathVariable("hostid") int hostId, @PathVariable("vmid") int vmId) {
        return vmservice.getVM(hostId, vmId); 
        // returns null if not found
    }

    @PutMapping(path = "{vmid}")
    public String updateVM(@PathVariable("hostid") int hostId, @PathVariable("vmid") int vmId, @RequestBody VM avm) {
        return vmservice.updateVM(hostId, vmId, avm);
    }

    @DeleteMapping(path = "{vmid}")
    public String removeVM(@PathVariable("hostid") int hostId, @PathVariable("vmid") int vmId) {
        return vmservice.removeVM(hostId, vmId);
    }
}