package project.io.app.common.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import project.io.app.common.codeandmessage.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> resolveBusinessException(BusinessException exception) {
        CodeAndMessage codeAndMessage = exception.getCodeAndMessage();
        return ResponseEntity.status(codeAndMessage.getCode())
            .body(new ErrorResponse(codeAndMessage));
    }
}
