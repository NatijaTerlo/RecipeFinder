package org.example.recipefinder.model;

/**
 * Enum for different dietary preferences.
 * Represents various diet restrictions or preferences.
 */
public enum DietaryPreference {
    VEGAN,          // No animal products at all
    VEGETARIAN,     // No meat, but animal products like dairy and eggs are allowed
    GLUTEN_FREE,    // Excludes gluten-containing foods
    DAIRY_FREE,     // Excludes dairy products
    NONE;           // No dietary restrictions

    /**
     * This method is used to convert a string into a DietaryPreference enum.
     *
     * @param preference The string representing a dietary preference.
     * @return The corresponding DietaryPreference enum value.
     * @throws IllegalArgumentException if the provided string does not match any value.
     */
    public static DietaryPreference fromString(String preference) {
        for (DietaryPreference diet : DietaryPreference.values()) {
            if (diet.name().equalsIgnoreCase(preference)) {
                return diet;
            }
        }
        throw new IllegalArgumentException("No matching dietary preference for: " + preference);
    }
}
