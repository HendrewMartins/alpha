package org.alpha.entities;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//generate id automatically
    
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name",unique = true)
    @NotNull
    @Size(min=10, max=40)
    private String name;

    @Column(name="user_email",unique = true)
    @NotNull
    @Size(min=5, max=100)
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="roles")
    @Enumerated(EnumType.STRING)
    
    private Role roles;

}
