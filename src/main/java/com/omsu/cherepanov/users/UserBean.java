package com.omsu.cherepanov.users;

/**
 * Created by Павел on 24.05.2014.
 */
public class UserBean {
    private String username;
    private String password;
    private boolean isValid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
