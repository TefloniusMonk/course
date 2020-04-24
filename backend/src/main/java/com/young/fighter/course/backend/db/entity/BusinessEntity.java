package com.young.fighter.course.backend.db.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BusinessEntity {
    @Column(name = "created")
    final protected LocalDateTime created = LocalDateTime.now();
    @Column(name = "updated")
    protected LocalDateTime updated;

    @PrePersist
    public void prePersist() {
        this.updated = this.created;
    }

    @PreUpdate
    public void preUpdate() {

        this.updated = LocalDateTime.now();
    }
}
