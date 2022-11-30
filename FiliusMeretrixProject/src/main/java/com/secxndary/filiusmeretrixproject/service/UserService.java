package com.secxndary.filiusmeretrixproject.service;
import com.secxndary.filiusmeretrixproject.dto.UserDto;
import com.secxndary.filiusmeretrixproject.entity.Role;
import com.secxndary.filiusmeretrixproject.entity.User;
import com.secxndary.filiusmeretrixproject.repository.RoleRepository;
import com.secxndary.filiusmeretrixproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        if (existUser != null && Objects.equals(existUser.getUserName(), user.getUserName())) {
            log.error("User with username " + existUser.getUserName() + " already exists.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }


//        Role roleUser = roleRepository.findByName(ROLE_USER);
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        user.setRoles(userRoles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


    public void login(UserDto userDto) {

    }

}
