package com.Online.Shopping.shoppingCart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Online.Shopping.shoppingCart.Entity.Cart;
import com.Online.Shopping.shoppingCart.Entity.Users;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Optional<Cart> findByUserId(long userId);

}
