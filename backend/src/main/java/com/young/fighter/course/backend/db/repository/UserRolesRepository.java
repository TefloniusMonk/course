package com.young.fighter.course.backend.db.repository;

import com.young.fighter.course.backend.db.entity.relations.UserRoles;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRolesRepository extends ReactiveCrudRepository<UserRoles, Long> {
}
