package com.hostshw.demohosts.model;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //it interfaces with the repo
public class HostServices {
	
	//no need for it we will use database now
	//private Map<Integer, Host>hosts; //hash map that has key value pair where key is int and value is host
	
	@Autowired //creates instance auto
	private HostRepository hostrepo;
		
	public List<Host> getHosts() {
		
		return hostrepo.findAll();
	}
	
	public Host addHost(Host ahost) {
		
		//this will let us forcefully set available resources to match what we currently have / allocate
	    ahost.setAvailablePe(ahost.getPe());
	    ahost.setAvailableRam(ahost.getRam());
		return hostrepo.save(ahost);
	}
	
	public Host getHost(int id) {
		
		return hostrepo.findById(id).orElse(null); //if it doesnt find it return null
	}
	
	public Host updateHost(Host ahost) {
	    if (hostrepo.existsById(ahost.getId())) {
	        Host existing = hostrepo.findById(ahost.getId()).orElse(null);

	        // Calculate how much consumed by VMs
	        int usedPe  = existing.getPe()  - existing.getAvailablePe();
	        int usedRam = existing.getRam() - existing.getAvailableRam();

	        // Apply new totls
	        ahost.setAvailablePe(ahost.getPe()   - usedPe);
	        ahost.setAvailableRam(ahost.getRam() - usedRam);
	        return hostrepo.save(ahost);
	    }
	    return null;
	}
	
	public String removeHost(int id) {
		
		if (!hostrepo.existsById(id)) {
			
			return "Error...!";
		}
		hostrepo.deleteById(id);
		return "Hosts Removed!";
	}
	

}
