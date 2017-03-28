package api.exceptions;

public class AlreadyExistProviderFieldException extends ApiException {

    private static final long serialVersionUID = -3642480446627523359L;
    
    public static final String DESCRIPTION = "Ya existe el proveedor en la base de datos";

    public static final int CODE = 1;

    public AlreadyExistProviderFieldException() {
        this("");
    }

    public AlreadyExistProviderFieldException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
