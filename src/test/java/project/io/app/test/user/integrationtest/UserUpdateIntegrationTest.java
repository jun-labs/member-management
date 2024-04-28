package project.io.app.test.user.integrationtest;


import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import static project.io.app.common.fixture.UserFixture.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.exception.*;
import project.io.app.test.*;

@DisplayName("[IntegrationTest] 사용자 수정 통합 테스트")
class UserUpdateIntegrationTest extends IntegrationTestBase {

    @Autowired
    private UserWriteUseCase userWriteUseCase;

    @Autowired
    private UserReadUseCase userReadUseCase;

    @Test
    @DisplayName("사용자 정보를 변경하면, 변경 사항이 반영된다.")
    void updateInfoTest() {
        User newUser = userJpaRepository.save(createFixture());
        UserUpdateRequest request = new UserUpdateRequest("Assignment", "assignment@gmail.com");
        userWriteUseCase.updateInfo(newUser.getId(), request);

        User findUser = userReadUseCase.findById(newUser.getId());

        assertAll(
            () -> assertEquals(request.getNickname(), findUser.getNickname()),
            () -> assertEquals(request.getEmail(), findUser.getEmail())
        );
    }

    @Test
    @DisplayName("사용자 정보를 수정할 때, 존재하지 않는 사용자라면 UserNotFoundException이 발생한다.")
    void userUpdateNotFoundTest() {
        Long invalidUserId = Long.MAX_VALUE;
        UserUpdateRequest request = new UserUpdateRequest("Assignment", "assignment@gmail.com");

        assertThatThrownBy(() -> userWriteUseCase.updateInfo(invalidUserId, request))
            .isExactlyInstanceOf(UserNotFoundException.class);
    }
}
