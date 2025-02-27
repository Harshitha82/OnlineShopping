package com.Online.Shopping.shoppingCart.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private BigDecimal totalPrice;
	private int phnumber;
	@Enumerated(EnumType.STRING)
	private OrderStatus Orderstatus;
	private LocalDateTime orderTime;
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	@OneToMany(mappedBy ="order",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem> items=new ArrayList<>();

}
