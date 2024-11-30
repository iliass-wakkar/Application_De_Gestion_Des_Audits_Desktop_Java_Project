package model;

public class User {
	private static int counter=0;
    private int id;
    private String username;
    private String password;
    private String role; 
    private boolean isActive;

    public User() {
        this.id = counter++;
        this.username = "UNKOWN";
        this.password = "UNKOWN";
        this.role = "UNKOWN";
        this.isActive = false;
    }
    public User(String username, String password, String role, boolean isActive) {
        this.id = counter++;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

