package org.umg.compilerjava.auth;

/** Servicio simple de autenticación para la base del examen. */
public interface AuthService {

    AuthResult authenticate(String email, String password);
}
