package jpa.jpaworkshop.exceptions;

public class DepartmentAlreadyExistException extends RuntimeException {
    public DepartmentAlreadyExistException(String departmentName) {
        super("Department "+departmentName+" is already exist!");
    }
}
