package project.io.app.common.response;

import lombok.*;

@Getter
public class Payload<T> {

    private T data;
    private final int code;
    private final String message;

    public Payload(
        T data,
        int code,
        String message
    ) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
