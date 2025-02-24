package com.Online.Shopping.shoppingCart.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role=Role.ROLE_USER;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Cart cart;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Order> order=new ArrayList<>();
}
