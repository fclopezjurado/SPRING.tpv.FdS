package api.exceptions;

public class NotFoundInvoiceIdException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra el identificador de la factura utilizado";

    public static final int CODE = 13;

    public NotFoundInvoiceIdException() {
        this("");
    }

    public NotFoundInvoiceIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
