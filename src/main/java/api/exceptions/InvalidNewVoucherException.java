package api.exceptions;

public class InvalidNewVoucherException extends ApiException {

    private static final long serialVersionUID = 425949732950172963L;

    public static final String DESCRIPTION = "Los datos para la creación del vale no son correctos";

    public static final int CODE = 7;

    public InvalidNewVoucherException() {
        this("");
    }

    public InvalidNewVoucherException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
