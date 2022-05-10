package com.example.couriermobile.exception;

import com.example.couriermobile.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
    @Value("${spring.servlet.multipart.max-file-size:1MB}")
    private String maxFileSize="1MB";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return validationException(ex.getBindingResult());
    }

    private HttpEntity<?> validationException(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public HttpEntity<?> handleValidationExceptions1(BindException ex) {
        return validationException(ex.getBindingResult());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public HttpEntity<?> handleSqlError(SQLException e){
        Map<String, Object> errors=new LinkedHashMap<>();
        switch (e.getSQLState()){
            case "23503"->{
                String message=e.getMessage();
                message=message.substring(0, message.length()-2);
                String obj=message.substring(message.lastIndexOf("\"")+1);
                String id=message.substring(message.lastIndexOf("(")+1, message.lastIndexOf(")"));
                errors.put("message", obj+" with id=(" +id+ ") not found");
            }
            default -> {
                errors.put("message", e.getCause()!=null?e.getCause().getMessage():e.getMessage());
            }
        }
        errors.put("success", false);
        return ResponseEntity.status(500).body(errors);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public HttpEntity<?> handleSizeException(){
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                        .message("File size must be less than "+maxFileSize)
                .build());
    }
}
