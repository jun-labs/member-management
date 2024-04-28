package project.io.app.core.user.dto.request;

import lombok.*;
import project.io.app.core.user.domain.*;

import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import java.time.*;

@Getter
public class UserJoinRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    private UserJoinRequest() {
    }

    public UserJoinRequest(
        String name,
        String phoneNumber,
        String email,
        String nickname,
        String userId,
        String password
    ) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }

    public User toEntity() {
        return new User(
            null,
            name,
            phoneNumber,
            email,
            nickname,
            userId,
            password,
            LocalDateTime.now(),
            null,
            false
        );
    }

    @Override
    public String toString() {
        return String.format(
            "name:%s, phoneNumber:%s, email:%s, nickname:%s, userId:%s", name, phoneNumber, email, nickname, userId
        );
    }
}
