package com.Online.Shopping.shoppingCart.UserService;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Online.Shopping.shoppingCart.Entity.Users;
import com.Online.Shopping.shoppingCart.Repository.UserRepository;
@Service
public class FetchUserDetails implements UserDetailsService {
	@Autowired
	private UserRepository repository;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users users=repository.findByEmail(email).get();
		return new UserDetails() {
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return users.getEmail();
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return users.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				System.out.println(users.getRole());
				
				return Collections.singleton(new SimpleGrantedAuthority(users.getRole()));
			}
		};
	}
}