package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("org.roles")
public class Role {
    @Id
    @Column("role_id")
    private Long roleId;

    @Column("role")
    private String role;

    @Column("role_desc")
    private String roleDesc;
}
