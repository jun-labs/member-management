package project.io.app.core.user.domain;

import org.springframework.data.jpa.repository.*;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
