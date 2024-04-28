package project.io.app.core.user.domain;

import javax.persistence.*;
import java.util.*;
import java.util.regex.*;

@Embeddable
public class Email {

    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    @Column(name = "email")
    private String email;

    protected Email() {
    }

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("이메일 주소를 입력해주세요.");
        }
        if (!match(email).matches()) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }

    private static Matcher match(String email) {
        return pattern.matcher(email);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

