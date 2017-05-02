package com.notebook.repository.handler;

import com.notebook.domain.NoteBook;
import com.notebook.repository.NoteBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class NoteBookEventHandler {

    private final NoteBookRepository noteBookRepository;

    @Autowired
    public NoteBookEventHandler(final NoteBookRepository noteBookRepository) {
        this.noteBookRepository = noteBookRepository;
    }

    @HandleBeforeCreate
    public void setStatus(NoteBook noteBook) {
        noteBook.setStatus(NoteBook.Status.NEW);
        noteBookRepository.save(noteBook);
    }
}
