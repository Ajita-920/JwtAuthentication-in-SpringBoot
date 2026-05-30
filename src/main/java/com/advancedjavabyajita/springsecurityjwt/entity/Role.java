package com.advancedjavabyajita.springsecurityjwt.entity;

import lombok.Getter;

import java.util.Set;

public enum Role {

    USER(Set.of(Permissions.USER_READ)),
    ADMIN(Set.of(Permissions.USER_READ, Permissions.USER_WRITE, Permissions.USER_DELETE, Permissions.USER_UPDATE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions>permissions){
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
