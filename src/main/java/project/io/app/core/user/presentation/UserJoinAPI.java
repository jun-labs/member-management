package project.io.app.core.user.presentation;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import org.springframework.web.bind.annotation.*;
import static project.io.app.common.codeandmessage.CommonCodeAndMessage.*;
import project.io.app.common.exception.*;
import project.io.app.common.response.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.dto.response.*;
import project.io.app.core.user.presentation.spec.*;

import javax.validation.*;

@RestController
@RequestMapping("/api/users")
public class UserJoinAPI {

    private final UserWriteUseCase userWriteUseCase;

    public UserJoinAPI(UserWriteUseCase userWriteUseCase) {
        this.userWriteUseCase = userWriteUseCase;
    }

    @JoinApiSpec
    @PostMapping("/join")
    public ApiResponse<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest request) {
        Long userId = userWriteUseCase.join(request);
        UserJoinResponse payload = new UserJoinResponse(userId);
        return ApiResponse.from(CREATED, payload);
    }
}
