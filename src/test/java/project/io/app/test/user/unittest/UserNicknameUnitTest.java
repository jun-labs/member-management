package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] 사용자 닉네임 단위 테스트")
class UserNicknameUnitTest {

    @Test
    @DisplayName("올바른 닉네임을 입력하면 닉네임 객체가 생성된다.")
    void validNicknameTest() {
        assertDoesNotThrow(() -> new Nickname("Username"));
    }

    @Test
    @DisplayName("null 값을 입력하면 IllegalArgumentException이 발생한다.")
    void nicknameIsNullTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Nickname(null)
        );
        assertEquals("닉네임을 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("빈 값을 입력하면 IllegalArgumentException이 발생한다.")
    void nicknameIsEmptyTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Nickname("")
        );
        assertEquals("닉네임을 입력해주세요.", exception.getMessage());
    }

    @Test
    @DisplayName("영문자 이외의 문자를 포함하는 닉네임은 IllegalArgumentException이 발생한다.")
    void nicknameContainsInvalidCharactersTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Nickname("사용자123")
        );
        assertEquals("닉네임은 영문자로만 구성되어야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("최대 입력 가능한 닉네임 길이를 초과하면 IllegalArgumentException이 발생한다.")
    void nicknameTooLongTest() {
        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> new Nickname("TooLongNicknameForYou")
        );
        assertEquals("닉네임은 최대 15자까지 가능합니다.", exception.getMessage());
    }
}
