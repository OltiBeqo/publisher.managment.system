package com.publisher.managment.system.repository.impl;

import com.publisher.managment.system.entity.Library;
import com.publisher.managment.system.repository.LibraryRepository;
import com.publisher.managment.system.repository.impl.queries.NamedQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class LibraryRepositoryImpl extends NamedQueries implements LibraryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Library addLibrary(Library library) {
        entityManager.persist(library);
        return library;
    }

    @Override
    public List<Library> getLibraries() {
        return entityManager.createNamedQuery(GET_LIBRARIES, Library.class).getResultList();
    }

    @Override
    public Library getLibraryById(Integer id) {
        return entityManager.createNamedQuery(GET_LIBRARY_BY_ID, Library.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Library updateLibrary(Integer id) {
        Library library = getLibraryById(id);
        return entityManager.merge(library);
    }

    @Override
    public void deleteLibraryById(Integer id) {
        Library library = getLibraryById(id);
        entityManager.remove(id);
    }
}
