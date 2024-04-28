package project.io.app.core.user.domain;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE u.id =:id AND u.deleted =:deleted")
    Optional<User> findUserByIdAndDeleted(@Param("id") Long id, @Param("deleted") Boolean deleted);
}
