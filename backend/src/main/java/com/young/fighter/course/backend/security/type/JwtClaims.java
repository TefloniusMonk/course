package com.young.fighter.course.backend.security.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtClaims {
    AUTHORITIES("authorities"),
    USER_ID("userId"),
    CUSTOMER_ID("customerId");
    private String key;
}

