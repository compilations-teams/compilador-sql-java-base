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

        acceptsIntComparedToInt();
        acceptsIntComparedToFloat();
        acceptsFloatComparedToInt();
        rejectsVarcharComparedToInt();
        rejectsIntComparedToVarchar();
        acceptsSelectAllWithValidTable();
        rejectsUnknownTable();
    }

    // ----------------------------------------------------------------
    // Tests originales
    // ----------------------------------------------------------------

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

    // ----------------------------------------------------------------
    // Tests de compatibilidad de tipos en WHERE
    // ----------------------------------------------------------------

    /* INT (edad) comparado con literal INT (18)*/
    private void acceptsIntComparedToInt() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE edad = 18;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "T-21: INT vs INT debe ser válido");
    }

    /* INT (edad) comparado con literal FLOAT (18.5)*/
    private void acceptsIntComparedToFloat() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE edad > 18.5;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "T-21: INT vs FLOAT debe ser válido (promoción numérica)");
    }

    /* FLOAT (precio) comparado con literal INT (100)*/
    private void acceptsFloatComparedToInt() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM productos WHERE precio > 100;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "T-21: FLOAT vs INT debe ser válido (promoción numérica)");
    }

    /* VARCHAR (nombre) comparado con literal INT (42)*/
    private void rejectsVarcharComparedToInt() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE nombre = 42;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "T-21: VARCHAR vs INT debe ser inválido");
    }

    /* INT (edad) comparado con literal STRING ('joven')*/
    private void rejectsIntComparedToVarchar() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE edad = 'joven';").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "T-21: INT vs VARCHAR debe ser inválido");
    }

    /* SELECT * sin WHERE sobre tabla válida*/
    private void acceptsSelectAllWithValidTable() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM productos;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isTrue(analyzer.analyze(ast), "T-21: SELECT * de tabla válida debe pasar");
    }

    /* Tabla inexistente*/
    private void rejectsUnknownTable() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM inventario;").tokenize()).parse();
        SemanticAnalyzer analyzer = new SemanticAnalyzer(new SymbolTable());
        Assert.isFalse(analyzer.analyze(ast), "T-21: tabla inexistente debe fallar");
    }
}