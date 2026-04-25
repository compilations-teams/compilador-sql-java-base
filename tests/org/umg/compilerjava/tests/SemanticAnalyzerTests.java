package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Parser;
import org.umg.compilerjava.compiler.SelectNode;
import org.umg.compilerjava.compiler.SemanticAnalyzer;
import org.umg.compilerjava.compiler.SymbolTable;

public final class SemanticAnalyzerTests {

    public void run() {
        acceptsValidQuery();
        rejectsUnknownColumn();
        acceptsSelectAll();
        rejectsUnknownTable();
        rejectsUnknownColumnInWhere();
        rejectsIncompatibleTypesInCondition();
        acceptsCompatibleIntAndFloat();
        acceptsQueryOnProductos();
        rejectsNullAst();
        acceptsSelectAllWithWhereCondition();
    }

    private void acceptsValidQuery() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE edad > 18;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "La consulta válida debe pasar el análisis semántico");
    }

    private void rejectsUnknownColumn() {
        SelectNode ast = new Parser(new Lexer("SELECT salario FROM usuarios;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "Una columna inexistente debe fallar");
    }

    private void acceptsSelectAll() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "SELECT * sobre tabla existente debe pasar");
    }

    private void rejectsUnknownTable() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM clientes;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "Una tabla inexistente debe fallar");
        Assert.isTrue(!analyzer.getErrors().isEmpty(), "Debe reportar al menos un error");
    }

    private void rejectsUnknownColumnInWhere() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE sueldo > 1000;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "Columna inexistente en WHERE debe fallar");
    }

    private void rejectsIncompatibleTypesInCondition() {
        // nombre es VARCHAR, compararlo con numero es incompatible
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE nombre > 100;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "Tipos incompatibles en WHERE deben fallar");
    }

    private void acceptsCompatibleIntAndFloat() {
        // edad es INT, 3.5 es FLOAT — deben ser compatibles
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE edad > 3.5;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "INT y FLOAT deben ser tipos compatibles");
    }

    private void acceptsQueryOnProductos() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM productos WHERE precio > 50;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "Consulta válida sobre tabla productos debe pasar");
    }

    private void rejectsNullAst() {
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(null), "AST nulo debe fallar");
        Assert.isTrue(!analyzer.getErrors().isEmpty(), "AST nulo debe generar un error");
    }

    private void acceptsSelectAllWithWhereCondition() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM productos WHERE precio > 10;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "SELECT * con WHERE válido debe pasar");
    }
}
