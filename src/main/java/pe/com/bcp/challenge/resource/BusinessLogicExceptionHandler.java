package pe.com.bcp.challenge.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.com.bcp.challenge.domain.ApiError;
import pe.com.bcp.challenge.exception.ExchangeException;

@ControllerAdvice
public class BusinessLogicExceptionHandler {

    @ExceptionHandler(ExchangeException.class)
    public ResponseEntity<Object> exchangeExceptionHandler(ExchangeException ex) {
        String error = "Currency type request is equal than currency type to exchange";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
