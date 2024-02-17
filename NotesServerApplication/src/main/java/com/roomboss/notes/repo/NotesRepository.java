package com.roomboss.notes.repo;

import com.roomboss.notes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note,Long> {
}
