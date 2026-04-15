package com.hostshw.demohosts.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//create table called hosts
@Entity 
public class Host {
	@Id //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //host id
	
	private int pe; //proccesing element
	private int ram; //host ram
	private int availablePe;
	private int availableRam;
	
	public Host() {
	}
	
	//constructor
	public Host(int pe, int ram) {
		super();
		//this.id = id;
		this.pe = pe;
		this.ram = ram;
		//new vars for the vm
	    this.availablePe  = pe; // starts fully available
	    this.availableRam = ram;
	}
	
	//VM list relationship
	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<VM> vms = new ArrayList<>();
	
	//getter and setter for id
	public int getId() {
		return	id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//getter and setter for pe
	public int getPe() {
		return	pe;
	}
	public void setPe(int pe) {
		this.pe = pe;
	}
	
	//getter and setter for ram
	public int getRam() {
		return	ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	//getter and setter for the new variebles for vm allocation
	//pe
	public int getAvailablePe() { 
		return availablePe; 
	}
	
	public void setAvailablePe(int availablePe) { 
		this.availablePe = availablePe; 
	}

	//ram
	public int getAvailableRam() { 
		return availableRam; 
	}
	
	public void setAvailableRam(int availableRam) { 
		this.availableRam = availableRam; 
	}
	
	//vm
	public List<VM> getVms() { 
		return vms; 
	}
	
	public void setVms(List<VM> vms) { 
		this.vms = vms; 
	}
	
}
