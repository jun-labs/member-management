package project.io.app.core.user.presentation;

import org.springframework.web.bind.annotation.*;
import static project.io.app.common.codeandmessage.CommonCodeAndMessage.*;
import project.io.app.common.response.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.dto.request.*;
import project.io.app.core.user.dto.response.*;
import project.io.app.core.user.presentation.spec.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserUpdateAPI {

    private final UserWriteUseCase userWriteUseCase;

    public UserUpdateAPI(UserWriteUseCase userWriteUseCase) {
        this.userWriteUseCase = userWriteUseCase;
    }

    @UpdateUserApiSpec
    @PutMapping("/{userId}")
    public ApiResponse<List<UserResponse>> updateInfo(
        @PathVariable Long userId,
        @Valid @RequestBody UserUpdateRequest request
    ) {
        userWriteUseCase.updateInfo(userId, request);
        return ApiResponse.from(OK);
    }
}
