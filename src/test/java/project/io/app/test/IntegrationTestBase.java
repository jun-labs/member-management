package project.io.app.test;

import lombok.extern.slf4j.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import project.io.app.core.user.domain.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @Autowired
    protected UserJpaRepository userJpaRepository;

    @BeforeEach
    void afterEach() {
        userJpaRepository.deleteAll();
    }
}
