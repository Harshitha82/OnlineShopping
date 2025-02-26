package com.Online.Shopping.shoppingCart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Online.Shopping.shoppingCart.Entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
	
	public Optional<Users> findByEmail(String email);
	

}
