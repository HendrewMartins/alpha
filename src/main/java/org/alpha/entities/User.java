package org.alpha.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.alpha.enums.Role;

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
@Table(name="tb_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//generate id automatically
    
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name",unique = true)
    @NotNull
    @Size(min=10, max=40)
    private String user_name;

    @Column(name="user_email",unique = true)
    @NotNull
    @Size(min=5, max=100)
    private String user_email;

    @NotNull
    @Column(name="password")
    private String user_password;

    @NotNull
    @Column(name="roles")
    @Enumerated(EnumType.STRING)
    private Role roles;

}
