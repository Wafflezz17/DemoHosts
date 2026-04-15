package com.hostshw.demohosts.rest;

import com.hostshw.demohosts.model.*;

import java.util.List;

//REST API needed libs
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/hosts") //the root resource
public class HostController {
	
	@Autowired
	private HostServices hservice;
	
	@GetMapping
	public List<Host> getallHosts() {
		
		return hservice.getHosts();
	}
	
	@GetMapping(path= "{hostid}") //this is a variable
	public Host getHost(@PathVariable("hostid") int id) { //use the variable here
		
		return hservice.getHost(id);
	}
	
	@PostMapping
	public Host addHost(@RequestBody Host ahost) { //it will be specified in the body req
		
		return hservice.addHost(ahost);
	}
	
	@PutMapping(path= "{hostid}")
	public Host updateHost(@PathVariable("hostid") int id, @RequestBody Host ahost) {
		
		ahost.setId(id);
		return hservice.updateHost(ahost);
	}
	
	@DeleteMapping(path= "{hostid}")
	public String removeHost(@PathVariable("hostid") int id) {
		
		return hservice.removeHost(id);	
	}
	
	
}



