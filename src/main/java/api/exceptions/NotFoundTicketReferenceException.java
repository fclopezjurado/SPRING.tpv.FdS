package api.exceptions;

public class NotFoundTicketReferenceException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra la referencia de ticket utilizado";

    public static final int CODE = 10;

    public NotFoundTicketReferenceException() {
        this("");
    }

    public NotFoundTicketReferenceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
