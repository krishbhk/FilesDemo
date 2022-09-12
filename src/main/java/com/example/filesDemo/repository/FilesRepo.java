package com.example.filesDemo.repository;

import com.example.filesDemo.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilesRepo extends JpaRepository<Doc,Integer> {

    Optional<Doc> findByDocName(String fileName);

    void deleteById(Integer id);
}
