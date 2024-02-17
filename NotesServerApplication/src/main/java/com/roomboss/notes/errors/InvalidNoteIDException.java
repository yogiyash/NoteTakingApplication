package com.roomboss.notes.errors;

public class InvalidNoteIDException extends Exception {

    private Long id;
    public InvalidNoteIDException(Long id) {
        this.id=id;
    }

    public Long getNoteId(){
        return id;
    }

}
