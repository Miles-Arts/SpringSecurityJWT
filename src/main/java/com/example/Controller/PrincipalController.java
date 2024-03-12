package com.example.Controller;

import com.example.Controller.Request.CreateUserDTO;
import com.example.Repositories.UserRepository;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;



@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Word Not Secureted";
    }

    @GetMapping("/helloSecured")
    public String helloSecured()
    {
        return "Hello Word Yes Secureted";
    }

    @PatchMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO)
    {

     /*   Set<RoleEntity> roles = createUserDTO.getRoles().stream().map(role -> RoleEntity.builder()
                .name(ERole.valueOf(role))
                .build())
        collect(Collectors.toSet());*/

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                        .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);

    }

    @DeleteMapping("/deleteUser")
    public String delete(@RequestBody String id)
    {
        userRepository.deleteById(Long.parseLong(id));
        return "User Has Been Deleted ";
    }

}
