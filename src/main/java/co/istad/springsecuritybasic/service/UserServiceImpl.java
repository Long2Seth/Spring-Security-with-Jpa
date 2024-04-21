package co.istad.springsecuritybasic.service;


import co.istad.springsecuritybasic.mapper.UserMapper;
import co.istad.springsecuritybasic.model.Role;
import co.istad.springsecuritybasic.model.User;
import co.istad.springsecuritybasic.model.dto.UserRequest;
import co.istad.springsecuritybasic.model.dto.UserResponse;
import co.istad.springsecuritybasic.repository.RoleRepository;
import co.istad.springsecuritybasic.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // check email
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        Set<Role> roles = new HashSet<>();
        userRequest.roles().forEach(role -> {
            var roleOptional = roleRepository.findByName(role)
                    .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));
            roles.add(roleOptional);
        });

        User newUser = userMapper.toUser(userRequest, roles);
        newUser.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }
}
