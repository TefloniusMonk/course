package com.young.fighter.course.backend.db.entity.relations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "org.user_roles")
public class UserRoles {
    @Column("user_id")
    private Long userId;
    @Column("role_id")
    private Long roleId;
}
