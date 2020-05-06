package com.young.fighter.course.backend.db.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
public abstract class BusinessEntity {
    @Column("created")
    final protected LocalDateTime created = LocalDateTime.now();
    @Column("updated")
    protected LocalDateTime updated;

//    @PrePersist
//    public void prePersist() {
//        this.updated = this.created;
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//
//        this.updated = LocalDateTime.now();
//    }
}
