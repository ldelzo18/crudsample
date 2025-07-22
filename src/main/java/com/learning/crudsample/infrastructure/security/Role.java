package com.learning.crudsample.infrastructure.security;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@IdClass(RoleId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @Column(name = "email", length = 50)
    private String email;

    @Id
    @Column(name = "role", length = 50)
    private String role;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private User user;
}
