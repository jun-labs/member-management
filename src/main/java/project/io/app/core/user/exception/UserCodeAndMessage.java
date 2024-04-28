package project.io.app.core.user.exception;

import project.io.app.common.codeandmessage.*;

public enum UserCodeAndMessage implements CodeAndMessage {
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    DUPLICATED_PHONE_NUMBER(422, "이미 존재하는 핸드폰 번호 입니다"),
    DUPLICATED_NICKNAME(422, "이미 존재하는 닉네임 입니다"),
    DUPLICATED_USER_ID(422, "이미 존재하는 사용자 아이디 입니다");

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
