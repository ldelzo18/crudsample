package com.learning.crudsample.infrastructure.security;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name= "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;
}
