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
    @ApiResponse(responseCode = "201", description = "사용자가 성공적으로 등록되었습니다.",
        content = @Content(schema = @Schema(implementation = UserJoinResponse.class))),
    @ApiResponse(responseCode = "400", description = "올바르지 않은 파라미터가 입력되었습니다.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class),
            examples = {
                @io.swagger.v3.oas.annotations.media.ExampleObject(
                    name = "InvalidParamExample",
                    value = "{\"code\":400, \"message\":\"올바르지 않은 파라미터입니다.\"}")
            })),
    @ApiResponse(responseCode = "422", description = "사용자 ID, 닉네임, 또는 전화번호가 중복됩니다.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했습니다.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class),
            examples = {
                @io.swagger.v3.oas.annotations.media.ExampleObject(
                    name = "ServerErrorExample",
                    value = "{\"code\":500, \"message\":\"서버 내부 오류입니다.\"}")
            }))
})
public @interface JoinApiSpec {
}
