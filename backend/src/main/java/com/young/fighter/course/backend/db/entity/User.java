package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    private Long userId;
    private String login;
    private String pass;
    private String email;
    private String fullName;
    private LocalDate birthDate;
}
