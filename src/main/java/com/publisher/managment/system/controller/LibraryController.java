package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping
    public ResponseEntity<List<LibraryDTO>> getLibraries(){
        return ResponseEntity.ok(libraryService.getLibraries());
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Integer id){
        return ResponseEntity.ok(libraryService.getLibraryById(id));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PostMapping
    public ResponseEntity<LibraryDTO> addLibrary(@RequestBody LibraryDTO libraryDTO){
        return ResponseEntity.ok(libraryService.addLibrary(libraryDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Integer id, @RequestBody LibraryDTO libraryDTO){
        return ResponseEntity.ok(libraryService.updateLibrary(id, libraryDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryById(@PathVariable Integer id){
        libraryService.deleteLibraryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
