package com.hdsupply.xmi.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestControllerExceptionHandler {
	
	private static final String FIELD_ERRORS 	= "fieldErrors";
	private static final String JSON_ERROR 		= "jsonError";

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected Map<String, String> handleInvalidJson(HttpMessageNotReadableException ex) {
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put(JSON_ERROR, ex.getMostSpecificCause().getMessage().split("\\n")[0]);

		return errorMap;
	} 	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected Map<String, List<FieldErrorInfo>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		
		List<FieldErrorInfo> errorList = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().stream().forEach(e -> {
			errorList.add(new FieldErrorInfo(e.getField(), e.getDefaultMessage()));
		});
	  	
		Map<String, List<FieldErrorInfo>> errorMap = new HashMap<>();
		errorMap.put(FIELD_ERRORS, errorList);

		return errorMap;
	} 
  
	public class FieldErrorInfo {

		String field;
		String message;
		
		public FieldErrorInfo(String field, String message) {
			
			this.field = field;
			this.message = message;
		}
	  
		public String getField() {
			return field;
		}
		
		public void setField(String field) {
			this.field = field;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
