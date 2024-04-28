package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] 사용자 이름 단위 테스트")
class UserNameUnitTest {

    @Test
    @DisplayName("올바른 이름을 입력하면 이름 객체가 생성된다.")
    void validNameTest() {
        assertDoesNotThrow(() -> new Name("철수"));
    }

    @Test
    @DisplayName("null 값을 입력하면 IllegalArgumentException이 발생한다.")
    void nameIsNullTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Name(null)
        );
        assertEquals("올바른 이름을 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("빈 값을 입력하면 IllegalArgumentException이 발생한다.")
    void nameIsEmptyTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Name("")
        );
        assertEquals("올바른 이름을 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("올바르지 않은 이름 양식을 사용하면 IllegalArgumentException이 발생한다.")
    void nameContainsInvalidCharactersTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Name("John")
        );
        assertEquals("올바른 이름을 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("최대 입력 가능한 이름 길이를 초과하면 IllegalArgumentException이 발생한다.")
    void nameTooLongTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Name("김철박수민호")
        );
        assertEquals("올바른 이름을 입력해주세요.", exception.getMessage());
    }
}

