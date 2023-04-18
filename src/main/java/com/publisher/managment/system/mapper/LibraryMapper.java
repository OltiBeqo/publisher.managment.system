package com.publisher.managment.system.mapper;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.entity.Library;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class LibraryMapper {
    public static LibraryDTO toDto(Library library) {
        return LibraryDTO.builder()
                .id(library.getId())
                .library(library.getLibrary())
                .address(library.getAddress())
                .email(library.getEmail())
                .phoneNumber(library.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Library toEntity(LibraryDTO libraryDTO) {
        return Library.builder()
                .id(libraryDTO.getId())
                .library(libraryDTO.getLibrary())
                .address(libraryDTO.getAddress())
                .email(libraryDTO.getEmail())
                .phoneNumber(libraryDTO.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Library toEntityForUpdate(Library library, LibraryDTO libraryDTO) {
        library.setLibrary(libraryDTO.getLibrary());
        library.setAddress(libraryDTO.getAddress());
        library.setEmail(libraryDTO.getEmail());
        library.setPhoneNumber(libraryDTO.getPhoneNumber());
        library.setModifiedAt(LocalDateTime.now());
        return library;
    }
}
