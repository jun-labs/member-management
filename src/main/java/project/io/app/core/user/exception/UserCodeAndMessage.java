package project.io.app.core.user.exception;

import project.io.app.common.codeandmessage.*;

public enum UserCodeAndMessage implements CodeAndMessage {
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다.");

    private final int code;
    private final String message;

    UserCodeAndMessage(
        int code,
        String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getCustomMessage() {
        return message;
    }
}
