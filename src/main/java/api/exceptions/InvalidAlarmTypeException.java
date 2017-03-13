package api.exceptions;

public class InvalidAlarmTypeException extends ApiException {

    
    private static final long serialVersionUID = 425949732950172963L;
    
    public static final String DESCRIPTION = "El tipo de alarma es incorrecto";

    public static final int CODE = 7;

    public InvalidAlarmTypeException() {
        this("");
    }
    public InvalidAlarmTypeException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
