package api.exceptions;

public class NotFoundReferenceVoucherException extends ApiException {

    private static final long serialVersionUID = 425949732950172963L;

    public static final String DESCRIPTION = "No se ha encontrado un vale con la referencia indicada, su periodo de valided a expirado o ya ha sido consumido";

    public static final int CODE = 9;

    public NotFoundReferenceVoucherException() {
        this("");
    }

    public NotFoundReferenceVoucherException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
