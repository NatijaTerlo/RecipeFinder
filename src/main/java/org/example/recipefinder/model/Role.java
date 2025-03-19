package org.example.recipefinder.model;

/**
 * Enum representing user roles.
 * Used to define the role of a user in the system, e.g., regular user or admin.
 */
public enum Role {
    USER,   // Regular user with standard privileges
    ADMIN;  // Admin user with elevated privileges

    /**
     * Converts a string to a Role enum value.
     *
     * @param role The string representing the role.
     * @return The corresponding Role enum value.
     * @throws IllegalArgumentException if no matching role is found.
     */
    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No matching role for: " + role);
    }
}
