package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import com.publisher.managment.system.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<Page<CategoryDTO>> getCategoriesPaginated(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getCategoriesPaginated(pageable));
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<Page<CategoryDTO>> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(categoryService.searchCategory(request));
    }

    @TrackExecutionTime
    @GetMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @TrackExecutionTime
    @PostMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @TrackExecutionTime
    @PutMapping
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDTO));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "EMPLOYEE"})
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
