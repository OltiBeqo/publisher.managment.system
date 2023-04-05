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
    @GetMapping
    public ResponseEntity<List<LibraryDTO>> getLibraries(){
        return ResponseEntity.ok(libraryService.getLibraries());
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Integer id){
        return ResponseEntity.ok(libraryService.getLibraryById(id));
    }
    @PostMapping
    public ResponseEntity<LibraryDTO> addLibrary(@RequestBody LibraryDTO libraryDTO){
        return ResponseEntity.ok(libraryService.addLibrary(libraryDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Integer id, @RequestBody LibraryDTO libraryDTO){
        return ResponseEntity.ok(libraryService.updateLibrary(id, libraryDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryById(@PathVariable Integer id){
        libraryService.deleteLibraryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
