package com.secxndary.stp_labs2_8.service;
import com.secxndary.stp_labs2_8.dto.UserDto;
import com.secxndary.stp_labs2_8.entity.Role;
import com.secxndary.stp_labs2_8.entity.User;
import com.secxndary.stp_labs2_8.mapper.UserMapper;
import com.secxndary.stp_labs2_8.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User register(UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDTO(userDto);
        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser == user) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User is exist");
        }

//        Role roleUser = roleRepository.findByName(ROLE_USER);
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sendMessageRegistration(user);
        return userRepository.save(user);
    }
}
