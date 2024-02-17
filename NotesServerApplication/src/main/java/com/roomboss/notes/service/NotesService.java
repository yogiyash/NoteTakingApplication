package com.roomboss.notes.service;

import com.roomboss.notes.dto.NotesDTO;
import com.roomboss.notes.entities.Content;
import com.roomboss.notes.entities.Note;
import com.roomboss.notes.errors.InvalidNoteIDException;
import com.roomboss.notes.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesService {


    private NotesRepository notesRepository;

    public NotesService(@Autowired NotesRepository notesRepository){
        this.notesRepository = notesRepository;
    }

    public NotesDTO saveNote(NotesDTO notesDTO){
        Note note  = dtoToObject(notesDTO);
        return objectToDto(notesRepository.save(note));
    }

    public NotesDTO getNote(Long id) throws InvalidNoteIDException {
        Optional<Note> result = notesRepository.findById(id);
        if(!result.isPresent()){
            throw new InvalidNoteIDException(id);
        }
        return  objectToDto(result.get());
    }

    public List<NotesDTO> getAllNotes( ){
         return notesRepository.findAll().stream().map(this::objectToDto).collect(Collectors.toList());
    }


    private Note dtoToObject(NotesDTO notesDTO) {
        return Note.builder().title(notesDTO.getTitle())
                .createdOn(new Date())
                .content(Content.builder().text(notesDTO.getContent()).build()).build();
    }

    private NotesDTO objectToDto(Note note) {

        return NotesDTO.builder().title(note.getTitle())
                .createdOn(note.getCreatedOn())
                .content(note.getContent().getText()).build();
    }


}
