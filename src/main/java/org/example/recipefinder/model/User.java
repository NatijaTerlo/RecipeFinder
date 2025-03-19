package org.example.recipefinder.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * Entity class representing a user in the system.
 * Implements Spring Security's UserDetails interface.
 */
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    /**
     * The role of the user, stored as an enum for better type safety.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    // Default constructor
    public User() {}

    // Copy constructor for creating a new user from an existing one
    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    // Getters and setters

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.name()); // Prefixing with "ROLE_" as required by Spring Security
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Tjek om kontoen er udløbet
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Tjek om kontoen er låst
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Tjek om credentials (adgangskode) er udløbet
    }

    @Override
    public boolean isEnabled() {
        return true; // Tjek om kontoen er aktiveret
    }
}
