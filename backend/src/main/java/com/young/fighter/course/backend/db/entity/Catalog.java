package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "course", name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long catalogId;
}
