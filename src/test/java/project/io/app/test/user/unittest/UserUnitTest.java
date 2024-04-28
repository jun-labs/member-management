package project.io.app.test.user.unittest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import static project.io.app.common.fixture.UserFixture.*;
import project.io.app.core.user.domain.*;

@DisplayName("[UnitTest] 사용자 단위 테스트")
class UserUnitTest {

    @Test
    @DisplayName("사용자 정보를 변경하면, 변경 사항이 반영된다.")
    void userInfoUpdateTest() {
        User user = createFixture();
        String newNickname = "JohnHarry";
        String newEmail = "john-harry@gmail.com";

        user.update(newNickname, newEmail);

        assertAll(
            () -> assertEquals(newNickname, user.getNickname()),
            () -> assertEquals(newEmail, user.getEmail())
        );
    }
}
