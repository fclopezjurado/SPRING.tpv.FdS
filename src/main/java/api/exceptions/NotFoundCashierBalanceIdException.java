package api.exceptions;

public class NotFoundCashierBalanceIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se ha encontrado un cierre de caja para el dia solicitado";

    public static final int CODE = 12;

    public NotFoundCashierBalanceIdException() {
        this("");
    }

    public NotFoundCashierBalanceIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}

