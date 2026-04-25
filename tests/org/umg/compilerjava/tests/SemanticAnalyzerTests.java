package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Parser;
import org.umg.compilerjava.compiler.SelectNode;
import org.umg.compilerjava.compiler.SemanticAnalyzer;
import org.umg.compilerjava.compiler.StatementNode;
import org.umg.compilerjava.compiler.SymbolTable;

public final class SemanticAnalyzerTests {

    public void run() {
        acceptsValidQuery();
        rejectsUnknownColumn();
    }

    private void acceptsValidQuery() {
        StatementNode stmt = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE edad > 18;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(stmt), "La consulta válida debe pasar el análisis semántico");
    }

    private void rejectsUnknownColumn() {
        StatementNode stmt = new Parser(new Lexer("SELECT salario FROM usuarios;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(stmt), "Una columna inexistente debe fallar");
    }
}
