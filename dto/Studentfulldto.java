package com.example.assess.dto;

import java.util.List;

import com.example.assess.Entity.Address;
import com.example.assess.Entity.Qualification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studentfulldto {
	private int studentId;
	private String studentName;
	private long pno;
	private String email;
	private String gender;

	private List<Address> address;
	private List<Qualification> qualification;

}
