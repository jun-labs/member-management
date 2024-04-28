package project.io.app.common.fixture;

import project.io.app.core.user.domain.*;

import java.time.*;
import java.util.*;

public final class UserFixture {

    private UserFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static User createFixture() {
        return new User(
            null,
            "Ziyan",
            "010-1234-5555",
            "hello-world@gmail.com",
            "Ziyan",
            "hello-world",
            "hello-world",
            LocalDateTime.now(),
            null,
            false
        );
    }

    public static User createFixture(int number) {
        return new User(
            null,
            String.valueOf(number),
            "010-1234-5555",
            String.format("hello-world%s@gmail.com", number),
            String.format("User%s", number),
            String.format("User%s", number),
            "hello-world",
            LocalDateTime.now(),
            null,
            false
        );
    }

    public static List<User> createUsersFixture(int count) {
        List<User> users = new ArrayList<>();
        for (int index = 1; index <= count; index++) {
            users.add(createFixture(index));
        }
        return users;
    }
}
