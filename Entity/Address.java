package com.example.assess.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	@Column(name = "student_id", insertable = false, updatable = false)
	private int studentId;
	@Column(name = "door_no")
	private String doorNo;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "city")
	private String city;
	@Column(name = "pin_code")
	private String pinCode;
	


}
