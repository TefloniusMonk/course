package com.young.fighter.course.backend.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {
    private Long customerId;
    private Long userId;
    private List<String> authorities;
}
