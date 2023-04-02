package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.entity.Library;

import java.util.List;

public interface LibraryService {
    LibraryDTO addLibrary(LibraryDTO libraryDTO);
    List<LibraryDTO> getLibraries();
    LibraryDTO getLibraryById(Integer id);
    LibraryDTO updateLibrary(Integer id, LibraryDTO libraryDTO);
    void deleteLibraryById(Integer id);
}
