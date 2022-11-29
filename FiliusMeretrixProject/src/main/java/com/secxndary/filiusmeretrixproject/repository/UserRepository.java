package com.secxndary.filiusmeretrixproject.repository;
import com.secxndary.filiusmeretrixproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
}
