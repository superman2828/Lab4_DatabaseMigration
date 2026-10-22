package com.earlnt.mydb.controllers;

import com.earlnt.mydb.dtos.ChangePasswordRequest;
import com.earlnt.mydb.dtos.RegisterUserRequest;
import com.earlnt.mydb.dtos.UpdateUserRequest;
import com.earlnt.mydb.dtos.UserDto;
import com.earlnt.mydb.entities.User;
import com.earlnt.mydb.mappers.UserMapper;
import com.earlnt.mydb.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController

@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping()
    public Iterable<UserDto> getAllUsers(
            @RequestHeader(name = "x-auth-token", required = false) String authToken,
            @RequestParam(required = false, defaultValue = "name") String sort) {
        System.out.println("Auth Token: " + authToken);
        return userRepository.findAll(Sort.by(sort)).stream()
                .map(user -> userMapper.toDto(user))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        //var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping()
    public UserDto createUser(@RequestBody RegisterUserRequest data){
        var user = userMapper.toEntity(data);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name="id") Long id,
            @RequestBody UpdateUserRequest data){

        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(data, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }

    if (!user.getPassword().equals(request.getOldPassword())) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    user.setPassword(request.getNewPassword());
    userRepository.save(user);
    return ResponseEntity.noContent().build();
    }
}

