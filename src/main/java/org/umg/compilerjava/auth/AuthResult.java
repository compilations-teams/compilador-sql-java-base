package org.umg.compilerjava.auth;

/**
 * Resultado de una autenticación.
 */
public final class AuthResult {

    private final boolean success;
    private final String role;
    private final String message;

    public AuthResult(boolean success, String role, String message) {
        this.success = success;
        this.role = role;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}
