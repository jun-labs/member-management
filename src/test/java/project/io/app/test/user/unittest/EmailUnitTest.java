package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] Email 단위 테스트")
class EmailUnitTest {

    @Test
    @DisplayName("올바른 값을 입력하면 메일 객체가 생성된다.")
    void validEmailTest() {
        assertDoesNotThrow(() -> new Email("example@example.com"));
    }

    @Test
    @DisplayName("null 값을 입력하면 IllegalArgumentException이 발생한다.")
    void emailIsNullTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Email(null)
        );
        assertEquals("이메일 주소를 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("빈 값을 입력하면 IllegalArgumentException이 발생한다.")
    void emailIsEmptyTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Email("")
        );

        assertEquals("이메일 주소를 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("올바르지 않은 값을 입력하면 IllegalArgumentException이 발생한다.")
    void emailFormatIsInvalidTest() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Email("example@.com"), "올바른 이메일 형식이 아닙니다."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Email("example.com"), "올바른 이메일 형식이 아닙니다."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Email("example@@example.com"), "올바른 이메일 형식이 아닙니다."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Email("@example.com"), "올바른 이메일 형식이 아닙니다."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Email("example@com"), "올바른 이메일 형식이 아닙니다.")
        );
    }

    @Test
    @DisplayName("올바르지 않은 양식을 입력하면 IllegalArgumentException이 발생한다.")
    void emailHasInvalidCharactersTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Email("ex!ample@example.com")
        );
        assertEquals("올바른 이메일 형식이 아닙니다.", exception.getMessage());
    }
}

