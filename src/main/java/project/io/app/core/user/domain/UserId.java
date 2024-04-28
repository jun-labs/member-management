package project.io.app.core.user.domain;

import javax.persistence.*;
import java.util.*;
import java.util.regex.*;

@Embeddable
public class UserId {

    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{5,13}$");

    @Column(name = "user_id")
    private String userId;

    protected UserId() {
    }

    public UserId(String userId) {
        validate(userId);
        this.userId = userId;
    }

    private void validate(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디를 입력해주세요.");
        }
        if (!match(userId).matches()) {
            throw new IllegalArgumentException("사용자 아이디는 13자리의 영문자 또는 숫자여야 합니다.");
        }
    }

    private static Matcher match(String userId) {
        return pattern.matcher(userId);
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId1 = (UserId) o;
        return Objects.equals(userId, userId1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
