package api.exceptions;

public class AlreadyExistCashierBalanceDayException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Ya existe un cierre de caja para el dia solicitado";

    public static final int CODE = 11;

    public AlreadyExistCashierBalanceDayException() {
        this("");
    }

    public AlreadyExistCashierBalanceDayException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
