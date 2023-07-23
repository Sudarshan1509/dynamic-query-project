package com.authorization.config;

import com.authorization.Main;
import com.authorization.service.CustomerDetailsService;
import com.authorization.service.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * For any incoming request, this Filter class gets executed. It checks if the
 * request has a valid JWT token. If it has a valid JWT Token, then it sets the
 * authentication in context to specify that the current user is authenticated.
 * 
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomerDetailsService custDetailsService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("START");
		log.debug("REQUEST {}", request);
		log.debug("RESPONSE {}", response);
		log.debug("FILTER CHAIN {}", filterChain);
		final String authHeader = request.getHeader("Authorization");
		log.debug("AUTH HEADER {}:", authHeader);
		String username = null;
		String jwt = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwt = authHeader.substring(7);
			log.debug("JWT TOKEN {}:", jwt);
			username = jwtUtil.extractUsername(jwt);
			log.debug("USERNAME {}:", username);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.custDetailsService.loadUserByUsername(username);
			log.debug("USER DETAILS {}:", userDetails);
			if (jwtUtil.validateToken(jwt, userDetails)) {
				log.info("TOKEN IS VALID");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				log.debug("USERNAME PASSWORD AUTHENTICATION TOKEN {}:", usernamePasswordAuthenticationToken);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		log.info("END");
	}

}
