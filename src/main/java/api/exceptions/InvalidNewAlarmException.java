package api.exceptions;

public class InvalidNewAlarmException extends ApiException {

    private static final long serialVersionUID = 425949732950172963L;

    
    public static final String DESCRIPTION = "La alarma no está correctamente formada";

    public static final int CODE = 6;

    public InvalidNewAlarmException() {
        this("");
    }
    public InvalidNewAlarmException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
