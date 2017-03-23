package api.exceptions;

public class NotFoundUserMobileException extends ApiException {

    public static final String DESCRIPTION = "No se encuentra ninguno usuario en el sistema, a partir del número de teléfono utilizado";

    public static final int CODE = 11;

    private static final long serialVersionUID = -1344640670884805385L;

    public NotFoundUserMobileException() {
        this("");
    }

    public NotFoundUserMobileException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
