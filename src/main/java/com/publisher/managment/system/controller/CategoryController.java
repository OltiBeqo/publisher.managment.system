package com.publisher.managment.system.controller;

import com.publisher.managment.system.aspect.TrackExecutionTime;
import com.publisher.managment.system.dto.CategoryDTO;
import com.publisher.managment.system.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @TrackExecutionTime
    @GetMapping("/page")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<PageResponse<CategoryDTO>> getCategoriesPaginated(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PageResponse<CategoryDTO> response = new PageResponse<>();
        response.setPageStats(categoryService.getCategoriesPaginated(pageNo, pageSize, sortBy, sortDir));
        return ResponseEntity.ok(response);
    }

    @TrackExecutionTime
    @PostMapping("/search")
    @RolesAllowed({"ADMIN", "EMPLOYEE", "COURIER"})
    public ResponseEntity<PageResponse<CategoryDTO>> search(@RequestBody SearchRequest request) {
        PageResponse<CategoryDTO> response = new PageResponse<>();
        response.setPageStats(categoryService.searchCategory(request));
        return ResponseEntity.ok(response);
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
