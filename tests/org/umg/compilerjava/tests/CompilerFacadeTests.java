package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.CompilerFacade;
import org.umg.compilerjava.compiler.CompilerReport;

public final class CompilerFacadeTests {

    public void run() {
        buildsSuccessfulReport();
        reportsSemanticError();
    }

    private void buildsSuccessfulReport() {
        CompilerReport report = new CompilerFacade().compile("SELECT nombre FROM usuarios;");
        Assert.isTrue(report.isSuccess(), "La consulta simple debe compilar");
        Assert.isTrue(report.getTokens().size() >= 5, "Debe producir tokens");
    }

    private void reportsSemanticError() {
        CompilerReport report = new CompilerFacade().compile("SELECT salario FROM usuarios;");
        Assert.isFalse(report.isSuccess(), "La consulta inválida debe fallar");
        Assert.isTrue(!report.getDiagnostics().isEmpty(), "Debe exponer diagnósticos");
    }
}
