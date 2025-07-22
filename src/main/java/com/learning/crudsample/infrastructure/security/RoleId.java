package com.learning.crudsample.infrastructure.security;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {

    private String email;
    private String role;

    public RoleId() {}

    public RoleId(String email, String role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId)) return false;
        RoleId that = (RoleId) o;
        return Objects.equals(email, that.email) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }
}
