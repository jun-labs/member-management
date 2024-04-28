package project.io.app.common.exception;

import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import project.io.app.common.codeandmessage.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> resolveBusinessException(BusinessException exception) {
        CodeAndMessage codeAndMessage = exception.getCodeAndMessage();
        return ResponseEntity.status(codeAndMessage.getCode())
            .body(new ErrorResponse(codeAndMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> resolveMethodArgumentNotValidException(
        MethodArgumentNotValidException exception
    ) {
        CodeAndMessage codeAndMessage = CommonCodeAndMessage.BAD_REQUEST;
        StringBuilder builder = createMessage(exception);
        log.error("errors:{}", builder);
        return ResponseEntity.status(codeAndMessage.getCode())
            .body(new ErrorResponse(codeAndMessage));
    }

    private StringBuilder createMessage(MethodArgumentNotValidException exception) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        BindingResult bindingResult = exception.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            builder.append("[")
                .append(field)
                .append("]")
                .append(" Input:")
                .append(fieldError.getRejectedValue())
                .append(" ");
        }
        builder.append("}");
        return builder;
    }
}
