package com.Online.Shopping.shoppingCart.JWT;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Online.Shopping.shoppingCart.UserService.FetchUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String header=request.getHeader("Authorization");
		String token=null;
		String username=null;
		String jwt=null;
		
		if(request.getCookies()!=null)
		{
			for(Cookie cookie:request.getCookies())
			{
				if(cookie.getName().equals("jwt"))
				{
					jwt=cookie.getValue();
				}
			}
		}
		
		if(jwt!=null)
		{
			token=jwt;
			username=jwtUtil.extractUserName(token);
			System.out.println("token used");
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails details=context.getBean(FetchUserDetails.class).loadUserByUsername(username);
			if(jwtUtil.validateToken(token, details))
			{
				UsernamePasswordAuthenticationToken token2=new UsernamePasswordAuthenticationToken(details,null, details.getAuthorities());
				token2.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token2);
				System.out.println("token used");

			}
		}
		filterChain.doFilter(request, response);
		
	}

}
