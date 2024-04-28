package project.io.app.core.user.domain;

import javax.persistence.*;
import java.time.*;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Email email;

    @Embedded
    private Nickname nickname;

    @Embedded
    private UserId userId;

    @Embedded
    private Password password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "deleted")
    private Boolean deleted;

    protected User() {
    }

    public User(
        Long id,
        String name,
        String phoneNumber,
        String email,
        String nickname,
        String userId,
        String password,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt,
        Boolean deleted
    ) {
        this.id = id;
        this.name = new Name(name);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.email = new Email(email);
        this.nickname = new Nickname(nickname);
        this.userId = new UserId(userId);
        this.password = new Password(password);
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId.getUserId();
    }

    public String getName() {
        return name.getName();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getNickname() {
        return nickname.getNickname();
    }

    public String getPhoneNumber() {
        return phoneNumber.getPhoneNumber();
    }

    public void update(
        String nickname,
        String email
    ) {
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
    }
}
