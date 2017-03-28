package api.exceptions;

public class NotFoundProviderIdException extends ApiException {

	private static final long serialVersionUID = 9187485023618814427L;

	public static final String DESCRIPTION = "No se encuentra el identificador del proveedor utilizado";

    public static final int CODE = 4;

    public NotFoundProviderIdException() {
        this("");
    }

    public NotFoundProviderIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
