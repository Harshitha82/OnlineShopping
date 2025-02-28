package com.Online.Shopping.shoppingCart.JWT;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JWTUtil {
	
	private String SECRET_KEY="";

	public JWTUtil() {
		KeyGenerator generator;
		try {
			generator=KeyGenerator.getInstance("HmacSHA256");
			Key key=generator.generateKey();
			SECRET_KEY=Base64.getEncoder().encodeToString(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String genereteToken(String username)
	{
		Map<String, Object> claims=new HashMap<>();
		
		return Jwts.builder().claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.and()
				.signWith(getKey())
				.compact();
				
	}
	private SecretKey getKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String extractUserName(String token)
	{
		return extractClaim(token,Claims::getSubject);
	}
	private <T> T extractClaim(String token,Function<Claims,T> claimResolver) {

		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public  boolean validateToken(String Token,UserDetails details)
	{
		final String username=extractUserName(Token);
		return (username.equals(details.getUsername())&& !isTokenExpired(Token));
	}
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	

}
