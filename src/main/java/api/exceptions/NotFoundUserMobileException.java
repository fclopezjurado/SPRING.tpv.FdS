package api.exceptions;

public class NotFoundUserMobileException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra ninguno usuario en el sistema, a partir del número de teléfono utilizado";

    public static final int CODE = 11;

    public NotFoundUserMobileException() {
        this("");
    }

    public NotFoundUserMobileException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
