package hi.verkefni.vinnsla.framkv;


/**
 The User class represents a user with a name and a boolean indicating whether the user is currently logged in or not.
 */
public class User {
    private String name;
    private boolean isLoggedIn;

    /**
     Constructs a new User object with the given name and login status.
     @param name the name of the user
     @param isLoggedIn the login status of the user
     */
    public User(String name, boolean isLoggedIn) {
        this.name = name;
        this.isLoggedIn = isLoggedIn;
    }

    /**
     Returns the name of the user.
     @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     Sets the name of the user to the given name.
     @param name the new name for the user
     */
    public void setName(String name) {
        this.name = name;
    }
}

