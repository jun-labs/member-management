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
            "김지혁",
            "01012345555",
            "helloworld@gmail.com",
            "Ziyan",
            "helloworld1",
            "hello-world",
            LocalDateTime.now(),
            null,
            false
        );
    }

    public static User createFixture(int number) {
        String koreanName = generateKoreanName();
        return new User(
            null,
            koreanName,
            "01012345555",
            String.format("helloworld%s@gmail.com", number),
            String.format("User%s", number),
            String.format("User%s", number),
            "helloworld",
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

    private static final String[] surnames = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"};
    private static final String[] nameCharacters = {
        "민", "지", "수", "은", "현", "영", "우", "서", "준", "호", "솔", "하", "나", "다", "라", "마", "건", "율", "리", "아"
    };

    private static final Random random = new Random();

    public static String generateKoreanName() {
        String surname = surnames[random.nextInt(surnames.length)];
        int nameLength = 1 + random.nextInt(2);
        StringBuilder nameBuilder = new StringBuilder(surname);
        for (int i = 0; i < nameLength; i++) {
            nameBuilder.append(nameCharacters[random.nextInt(nameCharacters.length)]);
        }
        return nameBuilder.toString();
    }

}
