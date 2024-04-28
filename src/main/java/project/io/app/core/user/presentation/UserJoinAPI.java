package project.io.app.core.user.presentation;

import org.springframework.web.bind.annotation.*;
import static project.io.app.common.codeandmessage.CommonCodeAndMessage.*;
import project.io.app.common.response.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.dto.response.*;

import javax.validation.*;

@RestController
@RequestMapping("/api/users")
public class UserJoinAPI {

    private final UserWriteUseCase userWriteUseCase;

    public UserJoinAPI(UserWriteUseCase userWriteUseCase) {
        this.userWriteUseCase = userWriteUseCase;
    }

    @PostMapping("/join")
    public ApiResponse<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest request) {
        Long userId = userWriteUseCase.join(request);
        UserJoinResponse payload = new UserJoinResponse(userId);
        return ApiResponse.from(CREATED, payload);
    }
}
