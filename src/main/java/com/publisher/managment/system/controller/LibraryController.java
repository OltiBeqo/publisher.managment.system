package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.dto.response.PageResponse;
import com.publisher.managment.system.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<Page<LibraryDTO>> getLibrariesPaginated(Pageable pageable) {
        return ResponseEntity.ok(libraryService.getLibrariesPaginated(pageable));
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<Page<LibraryDTO>> searchLibrary(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(libraryService.searchLibrary(request));
    }

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
