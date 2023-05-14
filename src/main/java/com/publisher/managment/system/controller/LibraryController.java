package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<List<LibraryDTO>> getLibrariesByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(libraryService.getLibrariesByStatus(isDeleted));
    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Integer id) {
        return ResponseEntity.ok(libraryService.getLibraryById(id));
    }

    @TrackExecutionTime
    @PostMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<LibraryDTO> addLibrary(@RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok(libraryService.addLibrary(libraryDTO));
    }

    @TrackExecutionTime
    @PutMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<LibraryDTO> updateLibrary(@RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok(libraryService.updateLibrary(libraryDTO));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> deleteLibraryById(@PathVariable Integer id) {
        libraryService.deleteLibraryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
