package com.example.assess.dto;

import lombok.Data;

@Data

public class StudentDTO {
	private int studentId;
	private String name;
	private long pno;
	private String email;
	private String gender;

	private String doorNo;
	private String streetName;
	private String city;
	private String pincode;

	private String courseName;
	private String coursePercentage;

}
