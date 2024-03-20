package com.example.RegAuthSystem.ExceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.common.config.HttpBody;
import com.example.common.config.MessageCode;
import com.example.common.config.ResponseResult;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
		ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
		String defaultMessage = constraintViolation.getMessage();
		return ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, defaultMessage));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return ResponseResult.badRequest(HttpBody.build(MessageCode.VALIDATE_ARGS_FAILED, defaultMessage));
	}

}
