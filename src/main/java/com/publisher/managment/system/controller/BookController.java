package com.publisher.managment.system.controller;

import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.dto.CategoryDTO;
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
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("/{id}") @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @GetMapping("/find/{title}") @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }
    @PostMapping @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }
    @PutMapping("/{id}") @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }
    @DeleteMapping("/{id}") @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
