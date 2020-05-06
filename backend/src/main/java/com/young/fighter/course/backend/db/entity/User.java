package com.young.fighter.course.backend.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("org.user")
public class User extends BusinessEntity {
    @Id
    @Column("user_id")
    private Long userId;

    @Column("login")
    private String login;

    @Column("password")
    private String password;

    @Column("email")
    private String email;

    @Column("customer_id")
    private Long customerId;
}
