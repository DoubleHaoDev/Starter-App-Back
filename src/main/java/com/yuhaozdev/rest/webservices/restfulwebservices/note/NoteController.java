package com.yuhaozdev.rest.webservices.restfulwebservices.note;

import com.yuhaozdev.rest.webservices.restfulwebservices.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    @Autowired
    NoteJpaRepository noteJpaRepository;

    @GetMapping(path="/users/{username}/notes")
    public List<Note> getAllNotes(@PathVariable String username){
        List<Note> noteList = noteJpaRepository.findByUsername(username);
        return noteList;
    }
    @DeleteMapping(path="/users/{username}/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String username, @PathVariable long id){
        System.out.println("DELETING");
        noteJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path="/users/{username}/notes")
    public ResponseEntity<Void> postNote(@PathVariable String username, @RequestBody Note note){
        note.setUsername(username);
        Note postNote = noteJpaRepository.save(note);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postNote.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}


