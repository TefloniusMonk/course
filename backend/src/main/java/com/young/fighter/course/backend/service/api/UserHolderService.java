package com.young.fighter.course.backend.service.api;

import com.young.fighter.course.backend.security.entity.CurrentUser;
import reactor.core.publisher.Mono;

public interface UserHolderService {
    Mono<CurrentUser> getUserFromContext();
}
