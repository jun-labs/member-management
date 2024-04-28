package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] 사용자 비밀번호 단위 테스트")
class PasswordUnitTest {

    private static final String TEST_PASSWORD = "TestPassword123!";
    private static final String EXPECTED_HASHED
        = "83c7aa7112f45734dbe71fbe69234c537949cda41f7b118f87adb077008ac32a901b90ccc83895b70376d3c4beb08fe71d0b3330b035bd8bc01e8d95084a7480";

    @Test
    @DisplayName("비밀번호 해싱은 일관되게 수행된다.")
    void passwordHashingConsistencyTest() {
        Password password = new Password(TEST_PASSWORD);
        Password samePassword = new Password(TEST_PASSWORD);

        assertEquals(password, samePassword);
        assertEquals(EXPECTED_HASHED, password.getPassword());
    }

    @Test
    @DisplayName("해싱된 비밀번호는 원래의 비밀번호와 다르다.")
    void passwordHashedDifferentThanOriginalTest() {
        Password password = new Password(TEST_PASSWORD);
        assertNotEquals(TEST_PASSWORD, password.getPassword());
    }
}

