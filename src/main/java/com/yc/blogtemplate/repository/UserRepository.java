package com.yc.blogtemplate.repository;

import com.yc.blogtemplate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUsername(String username);
}
