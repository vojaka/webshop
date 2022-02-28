package ee.kim.webshop.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

        //ResponseEntity kogu vastuse alumine osa
    @ExceptionHandler()
    public ResponseEntity <ErrorResponse> handleException(NoSuchElementException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Otsitud kirjet ei leitud");
        response.setTimeStamp(new Date());
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity <ErrorResponse> handleException(MethodArgumentTypeMismatchException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage("Sisestati vale funktsiooni argument");
        response.setTimeStamp(new Date());
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler()
//    public ResponseEntity <ErrorResponse> handleException(DataIntegrityViolationException e){
//        ErrorResponse response = new ErrorResponse();
//        response.setMessage("Ribakood peab olema unikaalne");
//        response.setTimeStamp(new Date());
//        response.setHttpStatus(HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler()
    public ResponseEntity <ErrorResponse> handleException(ConstraintViolationException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage("N6utud v2ljad on t2itmata");
        response.setTimeStamp(new Date());
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
    }
    //(Exception e) v]tab k]ik
}
