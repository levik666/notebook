package com.notebook.repository;

import com.notebook.domain.NoteBook;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteBookRepository extends PagingAndSortingRepository<NoteBook, Long> {

}
