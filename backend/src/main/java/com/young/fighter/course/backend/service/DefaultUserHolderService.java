//package com.young.fighter.course.backend.service;
//
//import com.young.fighter.course.backend.security.entity.CurrentUser;
//import com.young.fighter.course.backend.service.api.UserHolderService;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.HashSet;
//
//@Service
//public class DefaultUserHolderService implements UserHolderService {
//    @Override
//    public CurrentUser getUserFromContext() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            return new CurrentUser(authentication.getPrincipal().toString(), "", authentication.getAuthorities());
//        }
//        return new CurrentUser("0", null, new HashSet<>(Collections.singleton(new SimpleGrantedAuthority("UNAUTHORIZED"))));
//    }
//
//}
