package com.pdf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Hetasvi.Ahir
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstname;
	private String lastname;
	private String number;
	private String email;
	private String gender;
	private String address1;
	private String address2;
	private String state;
	private String district;
	private String taluka;
	private String city;
	private String department;

}
