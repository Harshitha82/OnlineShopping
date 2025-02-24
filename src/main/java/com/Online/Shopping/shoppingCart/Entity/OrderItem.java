package com.Online.Shopping.shoppingCart.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="order_id",nullable = false)
	private Order order;
	@ManyToOne
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	private int quantity;
	@Positive
	private BigDecimal price;
}
