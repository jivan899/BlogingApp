package com.blog.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//1 . get token
		
		String requestToken = request.getHeader("Authorization");
		
		//Bearer 
		System.out.println("Token "+requestToken);
		String userName = null;
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			
			try {
				userName = this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e){
				System.out.println(e);
				System.out.println("Unable to get JWT token");
			}
			catch (ExpiredJwtException e) {
				System.out.println(e);
				System.out.println("Jwt Token is Expired");
			}
			catch (MalformedJwtException e) {
				System.out.println(e);
				System.out.println("invalid JWT Token");
			}
		}else {
			System.out.println("JWT token does not start with Bearer");
		}
		//once we get token now validate
		
		//validate Token
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				//do authontication
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid JWT token");
			}
		}else {
			System.out.println("User name is null and context is not null");
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
 