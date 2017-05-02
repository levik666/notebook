package com.notebook.domain;

import com.notebook.exception.InvalidAdStateTransitionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "note_book")
public class NoteBook extends BaseAttributes {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "published_ts")
    public LocalDateTime publishedTs;

    public void publish() {
        if (status == Status.NEW) {
            publishedTs = LocalDateTime.now();
            status = Status.PUBLISHED;
        } else {
            throw new InvalidAdStateTransitionException("NoteBook is already published");
        }
    }

    public void expire() {
        if (status == Status.PUBLISHED) {
            status = Status.EXPIRED;
        } else {
            throw new InvalidAdStateTransitionException(
                    "Ad can be finished only when it is in the " + Status.PUBLISHED + " state");
        }
    }

    public enum Status {
        NEW, PUBLISHED, EXPIRED;
    }

}
