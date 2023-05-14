package com.publisher.managment.system.service.impl;

import com.publisher.managment.system.dto.LibraryDTO;
import com.publisher.managment.system.entity.Library;
import com.publisher.managment.system.exception.BadRequestException;
import com.publisher.managment.system.exception.ResourceNotFoundException;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LibraryServiceImplTest {

    @MockBean
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryService toTest;

    @Test
    public void test_addLibrary_ok() {

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        when(libraryRepository.save(Mockito.<Library>any())).thenReturn(library);

    }

    @Test
    public void test_addLibrary_ko() {

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        Optional<Library> ofResult = Optional.of(library);
        when(libraryRepository.save(Mockito.<Library>any())).thenReturn(library);
        when(libraryRepository.findByLibrary(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(BadRequestException.class, () -> toTest.addLibrary(new LibraryDTO()));
        verify(libraryRepository).findByLibrary(Mockito.<String>any());
    }

    @Test
    public void test_getLibraries_ok() {

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        ArrayList<Library> libraryList = new ArrayList<>();
        libraryList.add(library);
        when(libraryRepository.findByDeleted(anyBoolean())).thenReturn(libraryList);
        assertEquals(1, toTest.getLibrariesByStatus(false).size());

    }

    @Test
    public void test_getLibraryById_ko() {
        when(libraryRepository.findByDeleted(anyBoolean())).thenThrow(new ResourceNotFoundException("library not found"));
        assertThrows(ResourceNotFoundException.class, () -> toTest.getLibrariesByStatus(false));
        verify(libraryRepository).findByDeleted(anyBoolean());
    }

    @Test
    public void test_getLibraryById_ok() {

        Library library = new Library();
        library.setId(1);
        library.setLibrary("Library");
        library.setAddress("test");
        library.setEmail("test@test.com");
        library.setPhoneNumber("12345678");
        library.setDeleted(false);
        library.setCreatedAt(LocalDate.of(2023, 1, 1).atStartOfDay());
        library.setModifiedAt(LocalDate.of(2023, 1, 1).atStartOfDay());

        ArrayList<Library> libraryList = new ArrayList<>();
        libraryList.add(library);
        when(libraryRepository.findByDeleted(anyBoolean())).thenReturn(libraryList);
        assertEquals(1, toTest.getLibrariesByStatus(false).size());
        verify(libraryRepository).findByDeleted(anyBoolean());

    }

    @Test
    public void test_deleteLibrary_ok() {

        Optional<Library> result = Optional.of(new Library());
        doNothing().when(libraryRepository).delete(Mockito.<Library>any());
        when(libraryRepository.findById(Mockito.<Integer>any())).thenReturn(result);
        toTest.deleteLibraryById(anyInt());

    }
}
