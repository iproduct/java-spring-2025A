package course.spring.web;

import course.spring.dto.ErrorResponse;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonexistingEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerCotrollerAdvice {
    @ExceptionHandler(NonexistingEntityException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NonexistingEntityException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(404, exception.getMessage(), null)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRequetData(InvalidEntityDataException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getViolations()));
    }

}
