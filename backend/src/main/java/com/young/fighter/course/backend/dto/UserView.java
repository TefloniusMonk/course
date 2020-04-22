package com.young.fighter.course.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView implements Serializable {
    private Long userId;
    private String login;
    private String pass;
    private String email;
    private String fullName;
    private LocalDate birthDate;
}
