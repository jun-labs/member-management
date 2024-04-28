package project.io.app.core.user.exception;

import lombok.*;
import project.io.app.common.codeandmessage.*;

@Getter
public class BusinessException extends RuntimeException {

    private final CodeAndMessage codeAndMessage;

    public BusinessException(CodeAndMessage codeAndMessage) {
        this.codeAndMessage = codeAndMessage;
    }
}
