package com.Online.Shopping.shoppingCart.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name="user_id",nullable = false)
	private Users user;
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<CartItem> cartItems=new ArrayList<>();
	
	
	public Cart(Users users) {
		// TODO Auto-generated constructor stub
		this.user=users;
		this.cartItems=new ArrayList<>();
	}
	
	
}
