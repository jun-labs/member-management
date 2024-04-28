package project.io.app.core.user.application.service;

import org.springframework.stereotype.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.exception.*;
import static project.io.app.core.user.exception.UserCodeAndMessage.*;

@Component
public class UserValidator {

    private final UserJpaRepository userRepository;

    public UserValidator(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(UserJoinRequest request) {
        UserId userId = new UserId(request.getUserId());
        if (userRepository.findByUserIdAndDeleted(userId, false).isPresent()) {
            throw new DuplicatedUserException(DUPLICATED_USER_ID);
        }

        PhoneNumber phoneNumber = new PhoneNumber(request.getPhoneNumber());
        if (userRepository.findByPhoneNumberAndDeleted(phoneNumber, false).isPresent()) {
            throw new DuplicatedUserException(DUPLICATED_PHONE_NUMBER);
        }

        Nickname nickname = new Nickname(request.getNickname());
        if (userRepository.findByNicknameAndDeleted(nickname, false).isPresent()) {
            throw new DuplicatedUserException(DUPLICATED_NICKNAME);
        }

    }
}
