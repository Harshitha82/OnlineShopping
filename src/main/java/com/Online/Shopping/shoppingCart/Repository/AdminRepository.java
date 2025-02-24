package com.Online.Shopping.shoppingCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Online.Shopping.shoppingCart.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByEmail(String email);

}
