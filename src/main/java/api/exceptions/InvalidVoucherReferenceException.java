package api.exceptions;

public class InvalidVoucherReferenceException extends ApiException {

    private static final long serialVersionUID = 425949732950172963L;

    public static final String DESCRIPTION = "La referencia del vale introducido no es válida, el formato es un string de 27 caracteres";

    public static final int CODE = 8;

    public InvalidVoucherReferenceException() {
        this("");
    }

    public InvalidVoucherReferenceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
