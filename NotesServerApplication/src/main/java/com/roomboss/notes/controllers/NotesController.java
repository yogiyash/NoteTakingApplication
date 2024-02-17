package com.roomboss.notes.controllers;

import com.roomboss.notes.dto.NotesDTO;
import com.roomboss.notes.dto.NotesResponse;
import com.roomboss.notes.errors.InvalidNoteIDException;
import com.roomboss.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(@Autowired NotesService notesService) {
        this.notesService = notesService;
    }


    @PostMapping
    public ResponseEntity<NotesResponse> createNote(@RequestBody @Validated NotesDTO note) {

        NotesDTO result = notesService.saveNote(note);
        return ResponseEntity.ok(
                NotesResponse.builder().notes(Arrays.asList(result))
                        .build());
    }

    @GetMapping
    public ResponseEntity<NotesResponse> getNote(@RequestParam(value = "id" ,required = false) Long id) throws InvalidNoteIDException {
        NotesResponse body = NotesResponse.builder().notes(
                Objects.nonNull(id) ? Arrays.asList(notesService.getNote(id)) : notesService.getAllNotes()).build();
        return ResponseEntity.ok(
                body);
    }



}
