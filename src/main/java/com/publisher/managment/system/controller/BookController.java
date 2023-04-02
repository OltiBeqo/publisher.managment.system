package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }
    @RolesAllowed({"ADMIN", "SALESFORCE"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
