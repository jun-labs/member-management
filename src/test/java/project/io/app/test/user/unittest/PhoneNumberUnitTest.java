package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] 사용자 전화번호 단위 테스트")
class PhoneNumberUnitTest {

    @Test
    @DisplayName("올바른 전화번호를 입력하면 전화번호 객체가 생성된다.")
    void validPhoneNumberTest() {
        assertDoesNotThrow(() -> new PhoneNumber("01012345678"));
    }

    @Test
    @DisplayName("null 값을 입력하면 IllegalArgumentException이 발생한다.")
    void phoneNumberIsNullTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new PhoneNumber(null)
        );
        assertEquals("올바른 핸드폰 번호를 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("빈 값을 입력하면 IllegalArgumentException이 발생한다.")
    void phoneNumberIsEmptyTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new PhoneNumber("")
        );
        assertEquals("올바른 핸드폰 번호를 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("올바르지 않은 전화번호 형식을 사용하면 IllegalArgumentException이 발생한다.")
    void phoneNumberFormatIsInvalidTest() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("010-1234-5678"), "올바른 핸드폰 번호를 입력해주세요."),
            () -> assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("02012345678"), "올바른 핸드폰 번호를 입력해주세요."),
            () -> assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("1234567890"), "올바른 핸드폰 번호를 입력해주세요.")
        );
    }
}
