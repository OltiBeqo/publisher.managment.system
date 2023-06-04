package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibraryService {
    LibraryDTO addLibrary(LibraryDTO libraryDTO);

    List<LibraryDTO> getLibrariesByStatus(boolean isDeleted);

    LibraryDTO getLibraryById(Integer id);

    LibraryDTO updateLibrary(LibraryDTO libraryDTO);

    void deleteLibraryById(Integer id);

    Page<LibraryDTO> getLibrariesPaginated(Pageable pageable);

    Page<LibraryDTO> searchLibrary(SearchRequest request);
}
