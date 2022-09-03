package com.yuhaozdev.rest.webservices.restfulwebservices.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {

    List<Note> findByUsername(String username);
}
