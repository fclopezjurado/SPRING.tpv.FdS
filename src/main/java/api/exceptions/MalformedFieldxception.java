package api.exceptions;

public class MalformedFieldxception extends ApiException {

    private static final long serialVersionUID = -7732775194577036979L;

    public static final int CODE = 14;

    public static final String DESCRIPTION = "Error en los campos pasados en la API por formato erroneo ";

    public MalformedFieldxception() {
        this("");
    }

    public MalformedFieldxception(String detail) {

        super(DESCRIPTION + ". " + detail, CODE);
       
    }

}
