package com.notebook.controller;import com.notebook.domain.NoteBook;import com.notebook.repository.NoteBookRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RestController;@RestControllerpublic class NoteBookController {    @Autowired    private NoteBookRepository noteBookRepository;    @RequestMapping(value = "/noteBook/{id}/publishing", method = RequestMethod.POST)    public NoteBook publish(@PathVariable("id") Long id) {        NoteBook noteBook = noteBookRepository.findOne(id);        noteBook.publish();        return noteBookRepository.save(noteBook);    }    @RequestMapping(value = "/noteBook/{id}/expiration", method = RequestMethod.POST)    public NoteBook expire(@PathVariable("id") Long id) {        NoteBook noteBook = noteBookRepository.findOne(id);        noteBook.expire();        return noteBookRepository.save(noteBook);    }}