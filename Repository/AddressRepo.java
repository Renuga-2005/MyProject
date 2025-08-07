package com.example.assess.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.assess.Entity.Address;

@Repository

public interface AddressRepo extends JpaRepository<Address,Integer> {
	List<Address> findByStudentId(int studentId);

}
