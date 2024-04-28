package project.io.app.test.user.integrationtest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import static project.io.app.common.fixture.UserFixture.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.test.*;

import java.util.*;

@DisplayName("[IntegrationTest] 사용자 조회 통합 테스트")
class UserSearchIntegrationTest extends IntegrationTestBase {

    @Autowired
    private UserReadUseCase userReadUseCase;

    @Test
    @DisplayName("사용자 데이터가 존재하면 페이징을 할 수 있다.")
    void searchUsersPagingTest() {
        List<User> users = createUsersFixture(100);
        userJpaRepository.saveAll(users);

        Pageable pageable = PageRequest.of(0, 10);
        Page<User> findUsers = userReadUseCase.findUsers(pageable);

        assertAll(
            () -> assertEquals(10, findUsers.getTotalPages()),
            () -> assertEquals(100, findUsers.getTotalElements()),
            () -> assertEquals(pageable.getPageNumber(), findUsers.getNumber()),
            () -> assertEquals(pageable.getPageSize(), findUsers.getSize())
        );
    }
}
