package py.com.mcs.tweet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TweetExceptionHandler {
    @ExceptionHandler(value = {TweetException.class, ValidationException.class})
    public ResponseEntity<TweetExceptionRes> handleException(TweetException e) {
        return new ResponseEntity<>(new TweetExceptionRes(e.getMessage(), e.getCode(), e.getCode().value()), e.getCode());
    }

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<TweetExceptionRes> handleExceptionBind(BindException bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<String> errorsMessage = new ArrayList<>();
        // Construye un mensaje de error personalizado
        StringBuilder messageError = new StringBuilder();
        for (FieldError error : errors) {
            messageError.append(error.getDefaultMessage());
            messageError.append(" - ");
        }
        return new ResponseEntity<>(new TweetExceptionRes(messageError.toString(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
