package project.io.app.core.user.application;

import org.springframework.data.domain.*;
import project.io.app.core.user.domain.*;

public interface UserReadUseCase {
    User findById(Long userId);

    Page<User> findUsers(Pageable pageable);
}
