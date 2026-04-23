package org.umg.compilerjava.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementación en memoria pensada para demostración y orientación.
 */
public final class InMemoryAuthService implements AuthService {

    private final Map<String, String> passwordsByEmail = new HashMap<String, String>();
    private final Map<String, String> rolesByEmail = new HashMap<String, String>();

    public InMemoryAuthService() {
        register("admin@umg.local", "admin123", "LEADER");
        register("student@umg.local", "student123", "STUDENT");
    }

    private void register(String email, String password, String role) {
        passwordsByEmail.put(normalize(email), password);
        rolesByEmail.put(normalize(email), role);
    }

    @Override
    public AuthResult authenticate(String email, String password) {
        String normalized = normalize(email);
        if (!passwordsByEmail.containsKey(normalized)) {
            return new AuthResult(false, "", "Usuario no encontrado");
        }

        if (!passwordsByEmail.get(normalized).equals(password)) {
            return new AuthResult(false, "", "Contraseña inválida");
        }

        return new AuthResult(true, rolesByEmail.get(normalized), "Autenticación exitosa");
    }

    private String normalize(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }
}
