package org.umg.compilerjava;

import org.umg.compilerjava.auth.InMemoryAuthService;
import org.umg.compilerjava.compiler.CompilerFacade;
import org.umg.compilerjava.ui.LoginFrame;
import org.umg.compilerjava.ui.ResultFrame;

/**
 * Coordina la inicialización y el flujo entre login, compilador y vistas.
 */
public final class MainController {

    private final InMemoryAuthService authService;
    private final CompilerFacade compilerFacade;
    private final ResultFrame resultFrame;
    private final LoginFrame loginFrame;

    public MainController() {
        authService = new InMemoryAuthService();
        compilerFacade = new CompilerFacade();
        resultFrame = new ResultFrame(compilerFacade);
        loginFrame = new LoginFrame(authService, resultFrame);
    }

    public void start() {
        loginFrame.setVisible(true);
    }
}
