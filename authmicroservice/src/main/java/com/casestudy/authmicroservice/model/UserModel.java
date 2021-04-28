package com.casestudy.authmicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
	private int userId;
	@Column(name="name")
	private String name;
	@Column(name="mobile_no")
	private String mobileNo;
	@Column(name="email")
	private String email;
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
}
