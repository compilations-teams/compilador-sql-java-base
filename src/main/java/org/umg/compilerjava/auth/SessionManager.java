package org.umg.compilerjava.auth;

public class SessionManager {

    private static SessionManager instance;
    private String currentUser;

    private SessionManager() {
        currentUser = null;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Usuario inválido");
        }
        this.currentUser = username;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
