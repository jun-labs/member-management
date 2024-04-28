package project.io.app.common.response;


import org.springframework.http.*;
import project.io.app.common.codeandmessage.*;

public class ApiResponse<T> extends ResponseEntity<Payload<T>> {

    public ApiResponse(
        CodeAndMessage codeAndMessage,
        T data
    ) {
        super(
            new Payload<>(data, codeAndMessage.getCode(), codeAndMessage.getCustomMessage()),
            HttpStatus.valueOf(codeAndMessage.getCode())
        );
    }

    public static <T> ApiResponse<T> from(CodeAndMessage codeAndMessage) {
        return new ApiResponse<>(
            codeAndMessage,
            null
        );
    }

    public static <T> ApiResponse<T> from(
        CodeAndMessage codeAndMessage,
        T data
    ) {
        return new ApiResponse<>(
            codeAndMessage,
            data
        );
    }
}
