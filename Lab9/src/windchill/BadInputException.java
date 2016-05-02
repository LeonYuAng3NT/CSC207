package windchill;

public class BadInputException  extends RuntimeException {
    public BadInputException() { }
    public BadInputException(String msg){
    super(msg);
    }
 }
