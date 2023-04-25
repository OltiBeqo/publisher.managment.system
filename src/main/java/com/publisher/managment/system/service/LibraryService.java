package com.publisher.managment.system.service;

import com.publisher.managment.system.dto.LibraryDTO;

import java.util.List;

public interface LibraryService {
    LibraryDTO addLibrary(LibraryDTO libraryDTO);

    List<LibraryDTO> getLibrariesByStatus(boolean isDeleted);

    LibraryDTO getLibraryById(Integer id);

    LibraryDTO updateLibrary(LibraryDTO libraryDTO);

    void deleteLibraryById(Integer id);
}
