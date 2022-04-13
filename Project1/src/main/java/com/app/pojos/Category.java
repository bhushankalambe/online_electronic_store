package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category extends BaseEntity{
 
	@Column(length=20)
	private String name;
	@Column(name="img_loc")
	private String catImage;
	@Column(length=350)
	private String description;
	@OneToMany( mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Product> product = new ArrayList<>();
 
}
