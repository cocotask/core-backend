package com.cocotask.core.user.repository;

import com.cocotask.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByUserEmail(String userEmail);
}
