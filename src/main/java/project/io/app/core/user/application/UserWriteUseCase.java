package project.io.app.core.user.application;

import project.io.app.core.user.dto.request.*;

public interface UserWriteUseCase {
    void updateInfo(Long userId, UserUpdateRequest request);
}
