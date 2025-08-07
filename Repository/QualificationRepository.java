package com.example.assess.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.assess.Entity.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
	List<Qualification> findByStudentId(int studentId);

}
