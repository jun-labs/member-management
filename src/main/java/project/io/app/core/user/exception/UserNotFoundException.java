package project.io.app.core.user.exception;

import lombok.*;
import project.io.app.common.codeandmessage.*;
import project.io.app.common.exception.*;

@Getter
public class UserNotFoundException extends BusinessException {

    private final CodeAndMessage codeAndMessage;

    public UserNotFoundException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
        this.codeAndMessage = codeAndMessage;
    }
}
