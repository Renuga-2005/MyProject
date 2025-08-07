package com.example.assess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification,Integer> {
	List<Qualification> findByStudentId(int studentId);

}
