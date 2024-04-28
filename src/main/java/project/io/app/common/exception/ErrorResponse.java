package project.io.app.common.exception;

import lombok.*;
import project.io.app.common.codeandmessage.*;

@Getter
public class ErrorResponse {

    private int code;
    private String message;

    private ErrorResponse() {
    }

    public ErrorResponse(CodeAndMessage codeAndMessage) {
        this.code = codeAndMessage.getCode();
        this.message = codeAndMessage.getCustomMessage();
    }
}
