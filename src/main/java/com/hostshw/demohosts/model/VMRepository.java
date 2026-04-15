package com.hostshw.demohosts.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VMRepository extends JpaRepository<VM, Integer> {
	List<VM> findByHostId(int hostId);
	//add this method so that we can list all vms in one host by its ID
}