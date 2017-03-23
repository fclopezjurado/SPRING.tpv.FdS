package api.exceptions;

public class NotFoundUserEmailException extends ApiException {

    public static final String DESCRIPTION = "No se encuentra ninguno usuario en el sistema, a partir del correo electrónico utilizado";

    public static final int CODE = 13;

    private static final long serialVersionUID = -1344640670884805385L;

    public NotFoundUserEmailException() {
        this("");
    }

    public NotFoundUserEmailException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
