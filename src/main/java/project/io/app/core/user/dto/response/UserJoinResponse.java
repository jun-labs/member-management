package project.io.app.core.user.dto.response;

import lombok.*;

@Getter
public class UserJoinResponse {

    private Long id;

    private UserJoinResponse() {
    }

    public UserJoinResponse(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id:%s", id);
    }
}
