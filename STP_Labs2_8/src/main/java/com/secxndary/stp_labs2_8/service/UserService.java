package com.secxndary.stp_labs2_8.service;
import com.secxndary.stp_labs2_8.dto.UserDto;
import com.secxndary.stp_labs2_8.entity.Role;
import com.secxndary.stp_labs2_8.entity.User;
//import com.secxndary.stp_labs2_8.mapper.UserMapper;
import com.secxndary.stp_labs2_8.repository.RoleRepository;
import com.secxndary.stp_labs2_8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // TODO: passwordEncoder
//    private final PasswordEncoder passwordEncoder;



    // TODO: interface + override
    public User findByUsername(String username) {
        log.info("UserService : findByUsername");
        return userRepository.findByUserName(username);
    }


    // TODO: mapper
    public User register(UserDto userDto) {
//        UserMapper.INSTANCE.fromDTO(userDto);
        Role roleUser = roleRepository.findByRoleName("USER");
        System.out.println(roleUser.getRoleName());
        List<Role> roleList = new ArrayList<Role>();
        roleList.add(roleUser);

        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(roleList);

        User existUser = findByUsername(userDto.getUsername());
//        System.out.println(existUser.getUsername());
//        System.out.println(user.getUsername());
        if (existUser != null && Objects.equals(existUser.getUserName(), user.getUserName())) {
            System.out.println("yra");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        // TODO: Add roles enum and List<roles> to user

//        Role roleUser = roleRepository.findByName(ROLE_USER);
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        user.setRoles(userRoles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
