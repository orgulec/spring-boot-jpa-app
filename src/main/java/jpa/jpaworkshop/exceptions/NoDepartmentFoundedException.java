package jpa.jpaworkshop.exceptions;

public class NoDepartmentFoundedException extends RuntimeException {
    public NoDepartmentFoundedException(){
        super("There is no such department founded.");
    }
}
