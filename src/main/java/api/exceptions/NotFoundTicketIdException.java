package api.exceptions;

public class NotFoundTicketIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra el identificador de ticket utilizado";

    public static final int CODE = 10;

    public NotFoundTicketIdException() {
        this("");
    }

    public NotFoundTicketIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
