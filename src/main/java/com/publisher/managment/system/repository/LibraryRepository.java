package com.publisher.managment.system.repository;

import com.publisher.managment.system.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Integer>, JpaSpecificationExecutor<Library> {
    Optional<Library> findByLibrary(String library);
    List<Library> findByDeleted(boolean isDeleted);
}
