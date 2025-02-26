package com.Online.Shopping.shoppingCart.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	public CartItem(Cart cart, Product product) {
		
	this.cart=cart;
	this.product=product;
	this.quantity=quantity;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="cart_id",nullable = false)
	private Cart cart;
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	private int quantity;

}
