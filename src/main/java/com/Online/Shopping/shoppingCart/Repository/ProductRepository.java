package com.Online.Shopping.shoppingCart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Online.Shopping.shoppingCart.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	public Product findByName(String categoryName);

}
