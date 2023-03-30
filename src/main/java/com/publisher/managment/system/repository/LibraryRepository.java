package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Library;

import java.util.List;

public interface LibraryRepository {
    Library addLibrary(Library library);
    List<Library> getLibraries();
    Library getLibraryById(Integer id);
    Library updateLibrary(Integer id);
    void deleteLibraryById(Integer id);

}
