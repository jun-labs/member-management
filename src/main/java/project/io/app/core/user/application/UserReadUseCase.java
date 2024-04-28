package project.io.app.core.user.application;

import project.io.app.core.user.domain.*;

public interface UserReadUseCase {
    User findById(Long userId);
}
