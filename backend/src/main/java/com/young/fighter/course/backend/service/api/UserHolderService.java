package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.security.entity.CurrentUser;

public interface UserHolderService {
    CurrentUser getUserFromContext();
}
