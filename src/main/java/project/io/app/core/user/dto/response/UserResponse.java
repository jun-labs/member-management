package project.io.app.core.user.dto.response;

import lombok.*;
import project.io.app.core.user.domain.*;

@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String nickname;
    private String email;

    private UserResponse() {
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }

    @Override
    public String toString() {
        return String.format(
            "id:%s, name:%s, nickname:%s, email:%s", id, name, nickname, email)
            ;
    }
}
