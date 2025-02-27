package com.Online.Shopping.shoppingCart.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Online.Shopping.shoppingCart.Dto.CartDto;
import com.Online.Shopping.shoppingCart.Dto.OrderDto;
import com.Online.Shopping.shoppingCart.Entity.Cart;
import com.Online.Shopping.shoppingCart.Entity.CartItem;
import com.Online.Shopping.shoppingCart.Entity.Order;
import com.Online.Shopping.shoppingCart.Entity.OrderItem;
import com.Online.Shopping.shoppingCart.Entity.OrderStatus;
import com.Online.Shopping.shoppingCart.Entity.Product;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.Mapper.CartMapper;
import com.Online.Shopping.shoppingCart.Mapper.OrderMapper;
import com.Online.Shopping.shoppingCart.Repository.CartRepository;
import com.Online.Shopping.shoppingCart.Repository.OrderRepository;
import com.Online.Shopping.shoppingCart.Repository.ProductRepository;
import com.Online.Shopping.shoppingCart.Repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductRepository productRepository;
	
	public OrderDto createOrder(String email,String address,int phno)
	{
		Users users=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		CartDto cartDto=cartService.getAllCartItems(email);
		Cart cart=CartMapper.toEntity(cartDto);
		if(cart.getCartItems().isEmpty())
		{
			throw new RuntimeException("Cannot place order for empty cart");
		}
		Users users2=new Users(users.getId());
		Order order=new Order();
		order.setAddress(address);
		order.setPhnumber(phno);
		order.setOrderstatus(OrderStatus.PLACED);
		order.setOrderTime(LocalDateTime.now());
		List<OrderItem> item=createOrderItem(cart,order);
		BigDecimal totalprice=item.stream().map(price->price.getPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
		order.setTotalPrice(totalprice);
		order.setItems(item);
		order.setUser(users2);
		orderRepository.save(order);
		cartService.deleteAllitems(email);
		return OrderMapper.dto(order);
				
	}
	
	private List<OrderItem> createOrderItem(Cart cart, Order order) {
		// TODO Auto-generated method stub
		return cart.getCartItems().stream().map(item->{
			Product product=productRepository.findById(item.getProduct().getId()).orElseThrow(()->new RuntimeException("Product not found"));
		return new OrderItem(0,order, product,item.getQuantity(),product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
		}).collect(Collectors.toList());
		
	}

	public List<OrderDto> getAll(){
		List<Order> list=orderRepository.findAll();
		return list.stream().map(OrderMapper::dto).collect(Collectors.toList());	
	}
	
	public List<OrderDto> getAllByUser(String email)
	{
		Users users=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		List<Order> list=orderRepository.findByUserId(users.getId());
		return list.stream().map(OrderMapper::dto).collect(Collectors.toList());
		
	}
	public OrderDto updateStatus(String email,long id,OrderStatus orderStatus)
	{
		Order order=orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not found"));
		order.setOrderstatus(orderStatus);
		orderRepository.save(order);
		return OrderMapper.dto(order);
	}

}
