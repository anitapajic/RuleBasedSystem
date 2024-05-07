package com.ftn.sbnz.util;


import com.ftn.sbnz.model.dto.ResponseMessageDTO;
import com.ftn.sbnz.util.exceptions.AlreadyExistisException;
import com.ftn.sbnz.util.exceptions.InvalidOperationException;
import com.ftn.sbnz.util.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessageDTO> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessageDTO> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ResponseMessageDTO("Incorrect username or password."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ResponseMessageDTO> handleInvalidOperationException(InvalidOperationException e) {
        return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistisException.class)
    public ResponseEntity<ResponseMessageDTO> handleAlreadyExistisException(AlreadyExistisException e) {
        return new ResponseEntity<>(new ResponseMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
