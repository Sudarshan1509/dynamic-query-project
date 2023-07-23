package com.authorization.controller;

import com.authorization.Main;
import com.authorization.model.JwtResponse;
import com.authorization.model.UserLoginCredential;
import com.authorization.model.UserToken;
import com.authorization.repository.MyUserRepository;
import com.authorization.service.CustomerDetailsService;
import com.authorization.service.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller class for creating and validating JWT Tokens.
 */


@RestController
@CrossOrigin
public class JwtAuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private CustomerDetailsService custdetailservice;
 
	@Autowired
	private MyUserRepository userservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * This method takes userId and Password as parameters and checks if its correct
	 * by invoking authenticate method of {@link AuthenticationManager} class and
	 * throws exception if found incorrect.
	 * 
	 * @param userid
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String userid, String password) throws Exception {
		log.info("START");
		log.debug("USERID AND PASSWORD {}:", userid, password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
		} catch (DisabledException e) {
			log.error("EXCEPTION: NOT A VALID USER");
			throw new Exception("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("EXCEPTION: NOT A VALID USER");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * This method is responsible for checking whether the user credentials are
	 * correct or not by calling the authenticate method. If the user credentials
	 * are correct the token will be generated and if the credentials are wrong
	 * Exception will be thrown.Uses JwtUtil class to generate token.
	 * 
	 * @param userlogincredentials
	 * @return UserToken Class with UserId and password with token and status as OK.
	 * @throws Exception
	 */
		@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential userlogincredentials) throws Exception {
		log.info("START");
		log.debug("USERLOGINCREDENTIALS {}:", userlogincredentials);
		authenticate(userlogincredentials.getUserid(), userlogincredentials.getPassword());
		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		log.debug("USERDETAILS {}:", userdetails);
		log.info("END");
		return new ResponseEntity<>(new UserToken(userlogincredentials.getUserid(), jwtutil.generateToken(userdetails)),
				HttpStatus.OK);

	}

	/**
	 * This Method is responsible for validating the token provided as parameter by
	 * every request. It uses the JwtUtil class for that.
	 * 
	 * @param token
	 * @return JwtToken class and status as OK for valid token.
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") final String token) {
		log.debug("TOKEN {}:", token);
		String newToken = token.substring(7);
		log.debug("TOKEN AFTER REMOVING BEARER {}:", newToken);
		JwtResponse jwtResponse = new JwtResponse();
		if (jwtutil.validateToken(newToken)) {
			log.info("TOKEN IS VALID");
			jwtResponse.setUserid(jwtutil.extractUsername(newToken));
			jwtResponse.setValid(true);
			jwtResponse
					.setUsername((userservice.findById(jwtutil.extractUsername(newToken)).orElse(null).getUsername()));
			log.debug("JWTRESPONSE {}:", jwtResponse);
		} else {
			log.error("TOKEN VALIDATION FAILED");
			jwtResponse.setValid(false);
		}
		log.info("END");
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}

}


