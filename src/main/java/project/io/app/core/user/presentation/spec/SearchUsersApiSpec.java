package project.io.app.core.user.presentation.spec;

import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import project.io.app.common.exception.*;
import project.io.app.core.user.dto.response.*;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "사용자 검색이 성공적으로 완료되었습니다.",
        content = @Content(schema = @Schema(implementation = UserResponse.class))),
    @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터입니다.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
public @interface SearchUsersApiSpec {
}

