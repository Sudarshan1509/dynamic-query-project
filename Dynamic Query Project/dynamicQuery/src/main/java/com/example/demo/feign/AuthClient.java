package com.example.demo.feign;

import com.example.demo.domain.JwtResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="authorization-service",url="http://localhost:8084")
	public interface AuthClient {

		@GetMapping("/api/auth/validate")
			public JwtResponse verifyToken(@RequestHeader(name="authorization",required = true)String token) ;

}
