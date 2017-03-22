package api.exceptions;

public class NotFoundUserEmailException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra ninguno usuario en el sistema, a partir del correo electrónico utilizado";

    public static final int CODE = 13;

    public NotFoundUserEmailException() {
        this("");
    }

    public NotFoundUserEmailException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
