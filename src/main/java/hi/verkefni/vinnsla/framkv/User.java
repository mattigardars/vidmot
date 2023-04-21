package hi.verkefni.vinnsla.framkv;

/**
 * The User class represents a user of the system.
 */

public class User {
    private String name;
    private boolean isLoggedIn;

    /**
     * Constructs a new User object with the given name and login status.
     * @param name the name of the user
     * @param isLoggedIn the login status of the user (true if logged in, false otherwise)
     */
    public User(String name, boolean isLoggedIn) {
        this.name = name;
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Returns the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
}

