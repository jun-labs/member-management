package project.io.app.core.user.domain;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Getter
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "deleted")
    private Boolean deleted;

    protected User() {
    }

    public void update(
        String nickname,
        String email
    ) {
        this.nickname = nickname;
        this.email = email;
    }
}
