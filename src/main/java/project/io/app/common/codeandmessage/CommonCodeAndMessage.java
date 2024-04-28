package project.io.app.common.codeandmessage;

import lombok.*;

@Getter
public enum CommonCodeAndMessage implements CodeAndMessage {
    OK(200, "OK"),
    BAD_REQUEST(400, "올바른 파라미터를 입력해주세요.");

    private final int code;
    private final String message;

    CommonCodeAndMessage(
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
