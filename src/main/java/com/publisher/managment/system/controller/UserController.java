package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.UserDTO;
import com.publisher.managment.system.dto.request.AppConstants;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.response.PageResponse;
import com.publisher.managment.system.entity.enums.Role;
import com.publisher.managment.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<PageResponse<UserDTO>> getUsersPaginated (
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PageResponse<UserDTO> response = new PageResponse<>();
        response.setPageStats(userService.getUsersPaginated(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @GetMapping("/search")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<PageResponse<UserDTO>> searchUser (@RequestBody SearchRequest request){
        PageResponse<UserDTO> response = new PageResponse<>();
        response.setPageStats(userService.searchUser(request));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());

    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @TrackExecutionTime
    @GetMapping("/role/{role}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<UserDTO>> getUsersByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @TrackExecutionTime
    @PutMapping
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
