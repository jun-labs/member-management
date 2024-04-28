package project.io.app.core.user.domain;

import javax.persistence.*;
import java.util.*;
import java.util.regex.*;

@Embeddable
public class Nickname {

    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,15}$");

    @Column(name = "nickname")
    private String nickname;

    protected Nickname() {
    }

    public Nickname(String nickname) {
        validate(nickname);
        this.nickname = nickname;
    }

    private void validate(String nickname) {
        if (nickname == null || nickname.isEmpty()) {
            throw new IllegalArgumentException("닉네임을 입력해주세요.");
        }
        if (nickname.length() > 15) {
            throw new IllegalArgumentException("닉네임은 최대 15자까지 가능합니다.");
        }
        if (!match(nickname).matches()) {
            throw new IllegalArgumentException("닉네임은 영문자로만 구성되어야 합니다.");
        }
    }

    private static Matcher match(String nickname) {
        return pattern.matcher(nickname);
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nickname nickname1 = (Nickname) o;
        return Objects.equals(nickname, nickname1.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}
