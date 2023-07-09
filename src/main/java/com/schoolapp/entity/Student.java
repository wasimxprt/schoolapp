package com.schoolapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student", uniqueConstraints = @UniqueConstraint(name = "emailId_unique", columnNames = "emailId"))
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String guardianName;
	private String guardianEmail;
	private String guardianMobile;
}
