package com.hostshw.demohosts.model;
//allow us to connect to the DB
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Integer> {

}
