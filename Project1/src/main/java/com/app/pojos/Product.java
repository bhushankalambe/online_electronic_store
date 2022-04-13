package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity{

	@Column(name="product_name",length=30)
	private String name;
	@Column(name="company_name",length=20)
	private String cname;
	@Min(value = 0)
	private int qty;
	@Min(value = 10)
	private int price;
	@Column(length=50)
	private String description;
	@Column(name="img_location")
	private String location;
	@Column(name="manufacture_date")
	private LocalDate manufactureDate;
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name="category_id",nullable = false)
	private Category category;
}
