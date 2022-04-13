package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@NamedEntityGraph(name = "address-graph", attributeNodes = { @NamedAttributeNode(value = "address") })
public class User extends BaseEntity {

	@NotEmpty
	@Column(length = 20)
	private String firstName;
	@NotEmpty
	@Column(length = 20)
	private String lastName;
	@Column(length = 30, unique = true)
	private String email;
	@Column(length = 250, nullable = false)
	private String password;
	@Pattern(regexp = "(^[0-9]{10})")
	private String mobile;
	@Min(18)
	private int age;
	@ToString.Exclude
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Address> address = new ArrayList<>();
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> order = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private UserRole role;
}
