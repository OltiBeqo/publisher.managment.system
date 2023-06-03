package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.BookDTO;
import com.publisher.managment.system.service.BookService;
import com.publisher.managment.system.dto.request.AppConstants;
import com.publisher.managment.system.dto.response.PageResponse;
import com.publisher.managment.system.dto.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<PageResponse<BookDTO>> getBooksPaginated(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PageResponse<BookDTO> response = new PageResponse<>();
        response.setPageStats(bookService.getBooksPaginated(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<PageResponse<BookDTO>> search(@RequestBody SearchRequest request) {
        PageResponse<BookDTO> response = new PageResponse<>();
        response.setPageStats(bookService.searchBook(request));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<List<BookDTO>> getBooksByStatus(@RequestParam boolean isDeleted) {
        return ResponseEntity.ok(bookService.getBooksByStatus(isDeleted));
    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @TrackExecutionTime
    @GetMapping("/find/{title}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @TrackExecutionTime
    @GetMapping("/category/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBooksByCategoryId(id));
    }

    @TrackExecutionTime
    @PostMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @TrackExecutionTime
    @PutMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(bookDTO));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
