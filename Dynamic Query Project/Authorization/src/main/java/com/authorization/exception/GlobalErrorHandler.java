package com.authorization.exception;

import com.authorization.Main;
import com.authorization.model.ErrorResponse;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is used to handle all the errors. All the methods in the class
 * corresponds to an Exception and returns an object of ErrorResponse class with
 * fields like time stamp, message etc.
 *
 */

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllMedicineStockErrors(Exception ex) {
		log.info("START");
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setReason("BAD REQUEST");
		log.debug("ERROR RESPONSE {}:", response);
		log.info("END");
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ErrorResponse> handleInternalAuthenticationServiceException(
			InternalAuthenticationServiceException ex) {
		log.info("START");
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.FORBIDDEN);
		response.setReason("ENTERED CREDENTIALS MIGHT BE WRONG");
		log.debug("ERROR RESPONSE{}:", response);
		log.info("END");
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
	}

}
