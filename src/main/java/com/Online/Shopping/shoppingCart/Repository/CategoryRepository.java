package com.Online.Shopping.shoppingCart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Online.Shopping.shoppingCart.Entity.Category;

import jakarta.transaction.Transactional;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

	public Optional<Category> findByName(String name);
	@Transactional
	public void deleteByName(String name);
}
