package com.secxndary.stp_labs2_8.repository;
import com.secxndary.stp_labs2_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
