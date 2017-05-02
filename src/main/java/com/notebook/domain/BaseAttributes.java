package com.notebook.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseAttributes implements Identifiable<Long> {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
    @GenericGenerator(
            name = "hilo_sequence_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "hilo_seqeunce"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "3"),
                    @Parameter(name = "optimizer", value = "hilo")
            })
    @Id
    protected Long id;
    @Column(name = "create_ts")
    protected LocalDateTime createdTs;
    @Column(name = "update_ts")
    protected LocalDateTime updatedTs;

    @PrePersist
    public void beforeCreate() {
        if (createdTs == null) {
            createdTs = LocalDateTime.now();
        }
        updatedTs = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        updatedTs = LocalDateTime.now();
    }
}
