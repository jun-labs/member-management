package project.io.app.core.user.dto.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
public class UserUpdateRequest {

    @NotBlank
    private String nickname;

    @Email
    private String email;

    private UserUpdateRequest() {
    }

    public UserUpdateRequest(
        String nickname,
        String email
    ) {
        this.nickname = nickname;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("nickname:%s, email:%s", nickname, email);
    }
}
