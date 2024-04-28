package project.io.app.core.user.presentation;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;
import org.springframework.data.domain.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import static project.io.app.common.codeandmessage.CommonCodeAndMessage.*;
import static project.io.app.common.codeandmessage.MessageConst.*;
import project.io.app.common.response.*;
import project.io.app.core.user.application.*;
import project.io.app.core.user.domain.*;
import project.io.app.core.user.dto.response.*;
import project.io.app.core.user.presentation.spec.*;

import javax.validation.constraints.*;
import java.util.*;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserSearchAPI {

    private final UserReadUseCase userReadUseCase;

    public UserSearchAPI(UserReadUseCase userReadUseCase) {
        this.userReadUseCase = userReadUseCase;
    }

    @SearchUsersApiSpec
    @GetMapping
    public ApiResponse<PageResponse<UserResponse>> searchUsers(
        @Min(value = 0, message = PAGE_INDEX_VALIDATION_MESSAGE)
        @RequestParam(value = "page", defaultValue = "0") int page,
        @Min(value = 10, message = PAGE_SIZE_VALIDATION_MESSAGE)
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt") String sort,
        @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort orderBy = createOrderBy(sort, direction);
        Pageable pageable = PageRequest.of(page, size, orderBy);
        Page<User> findUsers = userReadUseCase.findUsers(pageable);
        PageResponse<UserResponse> payload = createPayload(findUsers, pageable);
        return ApiResponse.from(OK, payload);
    }

    private Sort createOrderBy(
        String sort,
        String direction
    ) {
        return "asc".equalsIgnoreCase(direction) ?
            Sort.by(sort).ascending() :
            Sort.by(sort).descending();
    }

    private PageResponse<UserResponse> createPayload(
        Page<User> findUsers,
        Pageable pageable
    ) {
        if (findUsers.isEmpty()) {
            return new PageResponse<>(emptyList(), 0, 0, pageable);
        }
        List<UserResponse> data = findUsers.getContent().stream()
            .map(UserResponse::new)
            .collect(toList());
        return new PageResponse<>(data, findUsers.getTotalPages(), findUsers.getTotalElements(), pageable);
    }
}
