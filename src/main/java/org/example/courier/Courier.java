package org.example.courier;

public class Courier {
    private String login;
    private String firstName;
    private String password;

    public Courier(String login, String firstName, String password) {
        this.login = login;
        this.firstName = firstName;
        this.password = password;
    }

    public Courier() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}




