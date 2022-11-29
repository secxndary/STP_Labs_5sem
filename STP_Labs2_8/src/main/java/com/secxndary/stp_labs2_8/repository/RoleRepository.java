package com.secxndary.stp_labs2_8.repository;
import com.secxndary.stp_labs2_8.entity.Role;
import com.secxndary.stp_labs2_8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
