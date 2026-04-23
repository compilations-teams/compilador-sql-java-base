package org.umg.compilerjava.tests;

import org.umg.compilerjava.auth.AuthResult;
import org.umg.compilerjava.auth.InMemoryAuthService;

public final class AuthServiceTests {

    public void run() {
        authenticatesKnownUser();
        rejectsInvalidPassword();
    }

    private void authenticatesKnownUser() {
        AuthResult result = new InMemoryAuthService().authenticate("admin@umg.local", "admin123");
        Assert.isTrue(result.isSuccess(), "Debe autenticar usuario demo");
        Assert.equals("LEADER", result.getRole(), "Debe devolver rol correcto");
    }

    private void rejectsInvalidPassword() {
        AuthResult result = new InMemoryAuthService().authenticate("student@umg.local", "bad-password");
        Assert.isFalse(result.isSuccess(), "Debe rechazar contraseña inválida");
    }
}
