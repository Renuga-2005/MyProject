package com.example.assess;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="qualification")

public class Qualification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="qualification_id")
	private int qualificationId;
	@Column(name = "student_id", insertable = false, updatable = false)
	private int studentId;
	@Column(name="course_name")
	private String courseName;
	@Column(name="course_percentage")
	private String coursePercentage;
}
