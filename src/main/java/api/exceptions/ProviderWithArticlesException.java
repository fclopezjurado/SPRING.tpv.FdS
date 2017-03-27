package api.exceptions;

public class ProviderWithArticlesException extends ApiException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3660348750099931905L;

	public static final String DESCRIPTION = "Proveedor con articulos activos ";

    public static final int CODE = 1;

    public ProviderWithArticlesException() {
        this("");
    }

    public ProviderWithArticlesException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
