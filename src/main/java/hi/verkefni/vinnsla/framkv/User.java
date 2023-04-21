package hi.verkefni.vinnsla.framkv;

public class User {
    private String name;
    private boolean isLoggedIn;

    public User(String name, boolean isLoggedIn) {
        this.name = name;
        this.isLoggedIn = isLoggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

