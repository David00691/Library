package pl.java.dawid.library.model;

public class User {
    private String login;
    private String password;
    private int id;
    private Role role;

    public User(String login, String password, int id ,Role role) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.role = role;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }
    public void showUser(){
        System.out.println(this.login + this.role );
    }
}
