package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CompilerReport {

    private final boolean success;
    private final List<Token> tokens;
    private final List<String> diagnostics;
    private final StatementNode ast;

    public CompilerReport(boolean success, List<Token> tokens, List<String> diagnostics, StatementNode ast) {
        this.success = success;
        this.tokens = new ArrayList<Token>(tokens);
        this.diagnostics = new ArrayList<String>(diagnostics);
        this.ast = ast;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    public List<String> getDiagnostics() {
        return Collections.unmodifiableList(diagnostics);
    }

    public StatementNode getAst() {
        return ast;
    }

    public String toMultilineString() {
        StringBuilder builder = new StringBuilder();
        builder.append(success ? "✅ Compilación exitosa" : "❌ Compilación con errores").append('\n');
        builder.append("Tokens:").append('\n');
        for (Token token : tokens) {
            builder.append("- ").append(token.toString()).append('\n');
        }
        if (!diagnostics.isEmpty()) {
            builder.append("Diagnósticos:").append('\n');
            for (String diagnostic : diagnostics) {
                builder.append("- ").append(diagnostic).append('\n');
            }
        }
        return builder.toString();
    }
}
