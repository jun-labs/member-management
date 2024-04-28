package project.io.app.test.user.integrationtest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.exception.*;
import project.io.app.test.*;

@DisplayName("[IntegrationTest] 사용자 가입 통합 테스트")
class UserJoinIntegrationTest extends IntegrationTestBase {

    @Autowired
    private UserWriteUseCase userWriteUseCase;

    @Autowired
    private UserReadUseCase userReadUseCase;

    @Test
    @DisplayName("새로운 사용자를 등록하면, 데이터베이스에 사용자가 추가된다.")
    void joinNewUserTest() {
        UserJoinRequest request = new UserJoinRequest(
            "홍길동",
            "01012345678",
            "hong@example.com",
            "honggildong",
            "hong123",
            "password123"
        );

        Long userId = userWriteUseCase.join(request);
        assertNotNull(userId);

        User newUser = userReadUseCase.findById(userId);
        assertAll(
            () -> assertEquals(request.getName(), newUser.getName()),
            () -> assertEquals(request.getPhoneNumber(), newUser.getPhoneNumber()),
            () -> assertEquals(request.getEmail(), newUser.getEmail()),
            () -> assertEquals(request.getNickname(), newUser.getNickname()),
            () -> assertEquals(request.getUserId(), newUser.getUserId())
        );
    }

    @Test
    @DisplayName("사용자 ID가 중복되는 경우, DuplicatedUserException 예외가 발생한다.")
    void joinWithDuplicatedUserIdTest() {
        UserJoinRequest firstRequest = new UserJoinRequest(
            "김철수",
            "01098765432",
            "kim@example.com",
            "kimchulsoo",
            "kim123",
            "password321"
        );
        userWriteUseCase.join(firstRequest);

        UserJoinRequest secondRequest = new UserJoinRequest(
            "박민수",
            "01087654321",
            "park@example.com",
            "parkminsu",
            "kim123",
            "password432"
        );
        assertThatThrownBy(() -> userWriteUseCase.join(secondRequest))
            .isExactlyInstanceOf(DuplicatedUserException.class);
    }

    @Test
    @DisplayName("전화번호가 중복되는 경우, DuplicatedUserException 예외가 발생한다.")
    void joinWithDuplicatedPhoneNumberTest() {
        UserJoinRequest firstRequest = new UserJoinRequest(
            "최영희",
            "01011223344",
            "choi@example.com",
            "choiyh",
            "choi123",
            "password123"
        );
        userWriteUseCase.join(firstRequest);

        UserJoinRequest secondRequest = new UserJoinRequest(
            "정민수",
            "01011223344",
            "jung@example.com",
            "jungms",
            "jung123",
            "password234"
        );
        assertThatThrownBy(() -> userWriteUseCase.join(secondRequest))
            .isExactlyInstanceOf(DuplicatedUserException.class);
    }

    @Test
    @DisplayName("닉네임이 중복되는 경우, DuplicatedUserException 예외가 발생한다.")
    void joinWithDuplicatedNicknameTest() {
        UserJoinRequest firstRequest = new UserJoinRequest(
            "김태희",
            "01022223333",
            "kimth@example.com",
            "kimtaehee",
            "kimt123",
            "password321"
        );
        userWriteUseCase.join(firstRequest);

        UserJoinRequest secondRequest = new UserJoinRequest(
            "이민호",
            "01033334444",
            "lee@example.com",
            "kimtaehee",
            "lee123",
            "password432"
        );
        assertThatThrownBy(() -> userWriteUseCase.join(secondRequest))
            .isExactlyInstanceOf(DuplicatedUserException.class);
    }
}
