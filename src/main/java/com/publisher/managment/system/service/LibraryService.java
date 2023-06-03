package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.dto.request.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LibraryService {
    LibraryDTO addLibrary(LibraryDTO libraryDTO);

    List<LibraryDTO> getLibrariesByStatus(boolean isDeleted);

    LibraryDTO getLibraryById(Integer id);

    LibraryDTO updateLibrary(LibraryDTO libraryDTO);

    void deleteLibraryById(Integer id);

    Page<LibraryDTO> getLibrariesPaginated(int pageNo, int pageSize, String sortBy, String sortDir);

    Page<LibraryDTO> searchLibrary(SearchRequest request);
}
