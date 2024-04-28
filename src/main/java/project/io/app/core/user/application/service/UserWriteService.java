package project.io.app.core.user.application.service;

import org.springframework.stereotype.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.dto.request.*;
import static project.io.app.core.user.exception.UserCodeAndMessage.*;
import project.io.app.core.user.exception.*;

import javax.transaction.*;

@Service
public class UserWriteService implements UserWriteUseCase {

    private final UserJpaRepository userRepository;

    public UserWriteService(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void updateInfo(
        Long userId,
        UserUpdateRequest request
    ) {
        User findUser = userRepository.findUserByIdAndDeleted(userId, false)
            .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        findUser.update(request.getNickname(), request.getEmail());
    }
}
