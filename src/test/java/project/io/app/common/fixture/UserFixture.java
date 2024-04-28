package project.io.app.common.fixture;

import project.io.app.core.user.domain.*;

import java.time.*;

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
}
