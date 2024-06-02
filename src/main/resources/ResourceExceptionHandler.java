import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
        StandardError error = new StandardError(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                e.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}