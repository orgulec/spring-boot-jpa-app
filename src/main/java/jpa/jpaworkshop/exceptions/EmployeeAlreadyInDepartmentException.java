package jpa.jpaworkshop.exceptions;

public class EmployeeAlreadyInDepartmentException extends RuntimeException {
    public EmployeeAlreadyInDepartmentException(String name){
        super("Employee is already in department "+name);
    }
}
