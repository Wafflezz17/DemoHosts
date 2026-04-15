package com.hostshw.demohosts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VM {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int pe;
	private int ram;
	
	//the relationship between vm and host
	//one host can have many vms
    @ManyToOne
    @JoinColumn(name = "host_id")
    @JsonIgnore  // prevents infinite loop in JSON response
    private Host host;

    //empty constructor 
    public VM() {
    	
    }

    public VM(int pe, int ram, Host host) {
        this.pe = pe;
        this.ram = ram;
        this.host = host;
    }
    
    //getters and setters for each variable
    //id
    public int getId() { 
    		return id; 
    	}
    public void setId(int id) { 
    		this.id = id; 
    	}
    
    //PE
    public int getPe() { 
    		return pe; 
    	}
    
    public void setPe(int pe) { 
    		this.pe = pe;
    	}

    //ram
    public int getRam() { 
    		return ram; 
    	}
    
    public void setRam(int ram) { 
    		this.ram = ram; 
    	}

    //host
    public Host getHost() { 
    		return host; 
    	}
    
    public void setHost(Host host) { 
    		this.host = host; 
    	}
    
}
