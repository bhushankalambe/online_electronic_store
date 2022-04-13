package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart extends BaseEntity{
	
	private int quantity;
	private double price;
	private String name;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	@JsonIgnore
	private User userId;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	@JsonIgnore
	private Product products ;
	
//	public Cart(int qty, int price, String name, User u)
//	{
//		quantity=qty;
//		this.price=price;
//		this.name=name;
//		userId = u;
//	}
}
