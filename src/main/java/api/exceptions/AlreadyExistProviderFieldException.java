package api.exceptions;

public class AlreadyExistProviderFieldException extends ApiException {

    private static final long serialVersionUID = -3642480446627523359L;
    
    public static final String DESCRIPTION = "Provider already exists in DB";

    public static final int CODE = 1;

    public AlreadyExistProviderFieldException() {
        this("");
    }

    public AlreadyExistProviderFieldException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
