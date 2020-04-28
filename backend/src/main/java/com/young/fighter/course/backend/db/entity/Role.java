package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "org", name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_desc")
    private String roleDesc;
}
