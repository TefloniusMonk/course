package com.young.fighter.course.backend.security;

import com.young.fighter.course.backend.security.entity.JwtUser;
import com.young.fighter.course.backend.security.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JwtTest {
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    private List<String> authorities = Arrays.asList("CATALOG_WRITE",
            "CATALOG_VIEW",
            "BILL_VIEW",
            "PROFILE_VIEW",
            "PROFILE_WRITE",
            "PRODUCT_WRITE",
            "PRODUCT_VIEW",
            "BASKET_VIEW",
            "BASKET_WRITE");

    @Test
    void shouldGenerate() {
        System.out.println(jwtTokenUtil.generateToken(new JwtUser(3L, 1L, authorities)));
    }
}

