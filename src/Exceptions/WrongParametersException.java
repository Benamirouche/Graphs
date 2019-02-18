package Exceptions;

public class WrongParametersException extends Exception {
    public WrongParametersException(){
        super();
    }

    public WrongParametersException(String s){
        super(s);
    }

}
