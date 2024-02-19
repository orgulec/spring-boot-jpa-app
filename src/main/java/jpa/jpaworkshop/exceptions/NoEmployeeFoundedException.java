package jpa.jpaworkshop.exceptions;

public class NoEmployeeFoundedException extends RuntimeException{
    public NoEmployeeFoundedException(String employee){
        super("There is no such employee founded "+ employee);
    }
}
