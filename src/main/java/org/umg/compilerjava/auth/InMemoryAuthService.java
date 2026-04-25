package org.umg.compilerjava.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementacion en memoria pensada para demostracion y orientacion.
 */
public final class InMemoryAuthService implements AuthService {

    private static final String MESSAGE_EMAIL_REQUIRED = "El correo es obligatorio";
    private static final String MESSAGE_PASSWORD_REQUIRED = "La contrasena es obligatoria";
    private static final String MESSAGE_USER_NOT_FOUND = "Usuario no encontrado";
    private static final String MESSAGE_INVALID_PASSWORD = "Contrasena invalida";
    private static final String MESSAGE_SUCCESS = "Autenticacion exitosa";

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
        if (normalized.isEmpty()) {
            return AuthResult.failure(MESSAGE_EMAIL_REQUIRED);
        }

        if (isBlank(password)) {
            return AuthResult.failure(MESSAGE_PASSWORD_REQUIRED);
        }

        if (!passwordsByEmail.containsKey(normalized)) {
            return AuthResult.failure(MESSAGE_USER_NOT_FOUND);
        }

        if (!passwordsByEmail.get(normalized).equals(password)) {
            return AuthResult.failure(MESSAGE_INVALID_PASSWORD);
        }

        return AuthResult.success(rolesByEmail.get(normalized), MESSAGE_SUCCESS);
    }

    private String normalize(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
