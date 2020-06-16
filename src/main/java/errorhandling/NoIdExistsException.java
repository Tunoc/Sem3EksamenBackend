package errorhandling;

/**
 *
 * @author rando
 */
public class NoIdExistsException extends Exception{

    public NoIdExistsException(String message) {
        super(message);
    }

    public NoIdExistsException() {
        super("Requested recepie dosn't exist");
    }  
    
}
