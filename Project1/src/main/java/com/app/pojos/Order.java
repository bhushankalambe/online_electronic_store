package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="ordertbl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order extends BaseEntity{
	@Min(value = 100)
	private int amount;
	@Column(name="delievery_date")
	@JsonFormat(pattern="M/dd/yyyy")
	private LocalDate deliveryDate;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToMany
	@JoinTable(name = "Order_Products",joinColumns = @JoinColumn(name ="order_id"),
	inverseJoinColumns = @JoinColumn(name="product_id"))
	private List<Product> list = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private Status stat;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	public Order(int amount, LocalDate dod, User u, Address a)
	{
		this.amount=amount;
		this.deliveryDate=dod;
		this.user=u;
		this.address=a;
	}
}
