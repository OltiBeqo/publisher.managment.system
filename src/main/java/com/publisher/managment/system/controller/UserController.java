package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RolesAllowed("ADMIN")
    @GetMapping("/admin")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
    @RolesAllowed("ADMIN")
    @PostMapping("/admin")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }
    @RolesAllowed("ADMIN")
    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }
    @RolesAllowed("ADMIN")
    @DeleteMapping("admin//{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
