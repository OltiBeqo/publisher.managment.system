package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.entity.Library;
import com.publisher.managment.system.mapper.LibraryMapper;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    @Override
    @Transactional
    public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
        return LibraryMapper.toDto(libraryRepository.save(LibraryMapper.toEntity(libraryDTO)));
    }

    @Override
    public List<LibraryDTO> getLibraries() {
        return libraryRepository.findAll().stream().map(library -> LibraryMapper.toDto(library)).collect(Collectors.toList());
    }

    @Override
    public LibraryDTO getLibraryById(Integer id) {
        return libraryRepository.findById(id).map(library -> LibraryMapper.toDto(library)).orElseThrow(()-> new RuntimeException());
    }

    @Override
    @Transactional
    public LibraryDTO updateLibrary(Integer id, LibraryDTO libraryDTO) {
        Library library = libraryRepository.findById(id).orElseThrow(()-> new RuntimeException());
        return LibraryMapper.toDto(libraryRepository.save(LibraryMapper.toEntityForUpdate(library, libraryDTO)));
    }

    @Override
    @Transactional
    public void deleteLibraryById(Integer id) {
        Library library = libraryRepository.findById(id).orElseThrow(()-> new RuntimeException());
        libraryRepository.delete(library);
    }
}
