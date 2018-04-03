package exceptions;

public class MemberNotExistsException extends Exception{
    public MemberNotExistsException(String message) {
        super(message);
    }
}
