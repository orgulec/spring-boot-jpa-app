package jpa.jpaworkshop.controller.handlers;

import jpa.jpaworkshop.exceptions.EmployeeAlreadyInDepartmentException;
import jpa.jpaworkshop.exceptions.NoDepartmentFoundedException;
import jpa.jpaworkshop.exceptions.NoEmployeeFoundedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoDepartmentFoundedException.class)
    public ResponseEntity<String> handleNoDepartmentFoundedException(NoDepartmentFoundedException ex){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
    @ExceptionHandler(NoEmployeeFoundedException.class)
    public ResponseEntity<String> handleNoEmployeeFoundedException(NoEmployeeFoundedException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EmployeeAlreadyInDepartmentException.class)
    public ResponseEntity<String> handleEmployeeAlreadyInDepartmentException(EmployeeAlreadyInDepartmentException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}