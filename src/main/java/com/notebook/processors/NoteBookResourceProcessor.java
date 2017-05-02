package com.notebook.processors;

import com.notebook.controller.NoteBookController;
import com.notebook.domain.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static com.notebook.domain.NoteBook.Status.NEW;
import static com.notebook.domain.NoteBook.Status.PUBLISHED;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class NoteBookResourceProcessor implements ResourceProcessor<Resource<NoteBook>> {

    @Autowired
    private RepositoryEntityLinks entityLinks;

    @Override
    public Resource<NoteBook> process(final Resource<NoteBook> resource) {
        final NoteBook noteBook = resource.getContent();

        if (NEW == noteBook.getStatus()) {
            resource.add(linkTo(methodOn(NoteBookController.class).publish(noteBook.getId())).withRel("publishing"));
            resource.add(entityLinks.linkToSingleResource(NoteBook.class, noteBook.getId()).withRel("update"));
            resource.add(entityLinks.linkToSingleResource(NoteBook.class, noteBook.getId()).withRel("deletion"));
        }

        if (PUBLISHED == noteBook.getStatus()) {
            resource.add(linkTo(methodOn(NoteBookController.class).expire(noteBook.getId())).withRel("expiration"));
        }

        return resource;
    }
}
