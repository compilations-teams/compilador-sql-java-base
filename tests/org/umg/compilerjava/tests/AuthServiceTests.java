package org.umg.compilerjava.tests;

import org.umg.compilerjava.auth.AuthResult;
import org.umg.compilerjava.auth.InMemoryAuthService;

public final class AuthServiceTests {

    public void run() {
        authenticatesKnownUser();
        authenticatesKnownUserWithNormalizedEmail();
        rejectsInvalidPassword();
        rejectsUnknownUser();
        rejectsEmptyEmail();
        rejectsEmptyPassword();
    }

    private void authenticatesKnownUser() {
        AuthResult result = new InMemoryAuthService().authenticate("admin@umg.local", "admin123");
        Assert.isTrue(result.isSuccess(), "Debe autenticar usuario demo");
        Assert.equals("LEADER", result.getRole(), "Debe devolver rol correcto");
        Assert.equals("Autenticacion exitosa", result.getMessage(), "Debe devolver mensaje de exito");
    }

    private void authenticatesKnownUserWithNormalizedEmail() {
        AuthResult result = new InMemoryAuthService().authenticate("  STUDENT@UMG.LOCAL  ", "student123");
        Assert.isTrue(result.isSuccess(), "Debe autenticar correo normalizado");
        Assert.equals("STUDENT", result.getRole(), "Debe conservar el rol del estudiante");
    }

    private void rejectsInvalidPassword() {
        AuthResult result = new InMemoryAuthService().authenticate("student@umg.local", "bad-password");
        Assert.isFalse(result.isSuccess(), "Debe rechazar contrasena invalida");
        Assert.equals("Contrasena invalida", result.getMessage(), "Debe informar password invalida");
    }

    private void rejectsUnknownUser() {
        AuthResult result = new InMemoryAuthService().authenticate("ghost@umg.local", "secret");
        Assert.isFalse(result.isSuccess(), "Debe rechazar usuario inexistente");
        Assert.equals("Usuario no encontrado", result.getMessage(), "Debe informar usuario inexistente");
    }

    private void rejectsEmptyEmail() {
        AuthResult result = new InMemoryAuthService().authenticate("   ", "student123");
        Assert.isFalse(result.isSuccess(), "Debe rechazar correo vacio");
        Assert.equals("El correo es obligatorio", result.getMessage(), "Debe informar correo obligatorio");
    }

    private void rejectsEmptyPassword() {
        AuthResult result = new InMemoryAuthService().authenticate("student@umg.local", "   ");
        Assert.isFalse(result.isSuccess(), "Debe rechazar password vacia");
        Assert.equals("La contrasena es obligatoria", result.getMessage(), "Debe informar password obligatoria");
    }
}
