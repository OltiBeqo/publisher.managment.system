package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.entity.Library;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ExceptionMessage;
import com.publisher.managment.system.exception.ExceptionResponse;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.mapper.LibraryMapper;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl extends ExceptionMessage implements LibraryService {
    @Autowired private LibraryRepository libraryRepository;
    @Override
    @Transactional
    public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
        Library library = libraryRepository.findByLibrary(libraryDTO.getLibrary()).orElse(null);
        if (library != null){
            throw new BadRequestException(String.format(LIBRARY_EXISTS, libraryDTO.getLibrary()));
        }
        return LibraryMapper.toDto(libraryRepository.save(LibraryMapper.toEntity(libraryDTO)));
    }
    @Override
    public List<LibraryDTO> getLibrariesByStatus(boolean isDeleted) {
        return libraryRepository.findByDeleted(isDeleted).stream().map(LibraryMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public LibraryDTO getLibraryById(Integer id) {
        return libraryRepository.findById(id)
                .map(LibraryMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(String.format(LIBRARY_NOT_FOUND, id)));
    }
    @Override
    @Transactional
    public LibraryDTO updateLibrary(LibraryDTO libraryDTO) {
        Library library = libraryRepository.findById(libraryDTO.getId())
                .orElseThrow(()-> new ResourceNotFoundException(String.format(LIBRARY_NOT_FOUND, libraryDTO.getId())));
        return LibraryMapper.toDto(libraryRepository.save(LibraryMapper.toEntityForUpdate(library, libraryDTO)));
    }
    @Override
    @Transactional
    public void deleteLibraryById(Integer id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(LIBRARY_NOT_FOUND, id)));
        libraryRepository.delete(library);
    }
}
