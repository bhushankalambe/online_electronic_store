package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address extends BaseEntity {

	@Column(length=20)
	private String state;
	@Column(length=20)
	private String city;
	@Min(value = 100000)
	@Max(value = 999999)
	private int zipcode;
	@Column(length=50)
	private String address;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_id", nullable=false )
	private User userId;
}
