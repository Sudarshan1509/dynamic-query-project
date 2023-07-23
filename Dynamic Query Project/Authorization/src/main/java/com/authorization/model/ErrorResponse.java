package com.authorization.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * This is a model class used for custom error handling which have fields like
 * time stamp, status, reason and a message.
 */
@Data
@ToString
public class ErrorResponse {

	private LocalDateTime timestamp;

	private HttpStatus status;

	private String reason;

	private String message;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponse(LocalDateTime timestamp, HttpStatus status, String reason, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.reason = reason;
		this.message = message;
	}
	

}