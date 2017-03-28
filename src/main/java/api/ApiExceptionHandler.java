package api;

import api.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundUserIdException.class, NotFoundReferenceVoucherException.class, NotFoundTicketReferenceException.class,
            NotFoundUserMobileException.class, NotFoundUserEmailException.class, NotFoundInvoiceIdException.class, NotFoundProviderIdException.class })
    @ResponseBody
    public ErrorMessage notFoundRequest(ApiException exception) { 
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public void unauthorized(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        LogManager.getLogger(this.getClass()).info("  ERROR: UNAUTHORIZED, " + apiErrorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MalformedHeaderException.class, InvalidUserFieldException.class, InvalidNewVoucherException.class,
            InvalidVoucherReferenceException.class, MalformedFieldxception.class})
    @ResponseBody
    public ErrorMessage badRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({AlreadyExistUserFieldException.class, AlreadyExistUserFieldException.class,
            AlreadyExistProviderFieldException.class, ProviderWithArticlesException.class})
    @ResponseBody
    public ErrorMessage conflictRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // @ExceptionHandler({Exception.class})
    // @ResponseBody
    // public ErrorMessage exception(Exception exception) {
    // ErrorMessage apiErrorMessage = new ErrorMessage(exception);
    // return apiErrorMessage;
    // }

}
