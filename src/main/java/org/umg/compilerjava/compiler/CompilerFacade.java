package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.List;

/** Orquestador del pipeline: lexer -> parser -> semantic analyzer. */
public final class CompilerFacade {

    public CompilerReport compile(String sql) {
        List<Token> tokens = new Lexer(sql).tokenize();
        List<String> diagnostics = new ArrayList<String>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.INVALID) {
                diagnostics.add("Token inválido '" + token.getValue() + "' en línea " + token.getLine());
                return new CompilerReport(false, tokens, diagnostics, null);
            }
        }

        try {
            SelectNode ast = new Parser(tokens).parse();
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(new SymbolTable());
            boolean success = semanticAnalyzer.analyze(ast);
            diagnostics.addAll(semanticAnalyzer.getErrors());
            return new CompilerReport(success, tokens, diagnostics, ast);
        } catch (CompilerException ex) {
            diagnostics.add(ex.getMessage());
            return new CompilerReport(false, tokens, diagnostics, null);
        }
    }
}
