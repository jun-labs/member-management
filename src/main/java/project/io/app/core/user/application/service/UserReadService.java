package project.io.app.core.user.application.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.exception.*;

@Service
public class UserReadService implements UserReadUseCase {

    private final UserJpaRepository userRepository;

    public UserReadService(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(UserCodeAndMessage.USER_NOT_FOUND));
    }

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
