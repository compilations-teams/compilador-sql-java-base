package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.CompilerException;
import org.umg.compilerjava.compiler.ConditionNode;
import org.umg.compilerjava.compiler.ExpressionNode;
import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Parser;
import org.umg.compilerjava.compiler.SelectNode;

public final class ParserTests {

    public void run() {
        parsesSelectWithColumns();
        parsesWhereCondition();
        parsesSelectAllWithoutWhere();
        parsesWhereWithStringLiteral();
        parsesWhereWithEqualOperator();
        parsesQueryWithoutSemicolon();
        throwsErrorWhenMissingFrom();
        throwsErrorWhenInvalidExpression();
    }

    private void parsesSelectWithColumns() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre, edad FROM usuarios;").tokenize()).parse();
        Assert.isFalse(ast.isSelectAll(), "No debe marcar selectAll en lista explicita");
        Assert.equals("usuarios", ast.getTableName(), "Debe extraer tabla");
        Assert.equals(2, ast.getColumns().size(), "Debe extraer dos columnas");
    }

    private void parsesWhereCondition() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE edad >= 18;").tokenize()).parse();
        ConditionNode condition = ast.getWhereCondition();
        Assert.isTrue(ast.isSelectAll(), "SELECT * debe marcar selectAll");
        Assert.equals("edad", condition.getLeft().getValue(), "Debe extraer identificador izquierdo");
        Assert.equals("18", condition.getRight().getValue(), "Debe extraer literal derecho");
    }

    private void parsesSelectAllWithoutWhere() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM productos;").tokenize()).parse();
        Assert.isTrue(ast.isSelectAll(), "Debe marcar selectAll con asterisco");
        Assert.equals("productos", ast.getTableName(), "Debe extraer tabla productos");
        Assert.isTrue(ast.getWhereCondition() == null, "No debe tener condicion WHERE");
    }

    private void parsesWhereWithStringLiteral() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios WHERE ciudad = 'Roma';").tokenize()).parse();
        ConditionNode condition = ast.getWhereCondition();
        Assert.equals("ciudad", condition.getLeft().getValue(), "Lado izquierdo debe ser ciudad");
        Assert.equals(ExpressionNode.ExprType.STRING, condition.getRight().getType(), "Lado derecho debe ser STRING");
        Assert.equals("Roma", condition.getRight().getValue(), "Valor derecho debe ser Roma");
    }

    private void parsesWhereWithEqualOperator() {
        SelectNode ast = new Parser(new Lexer("SELECT * FROM usuarios WHERE id = 1;").tokenize()).parse();
        ConditionNode condition = ast.getWhereCondition();
        Assert.equals("id", condition.getLeft().getValue(), "Identificador izquierdo debe ser id");
        Assert.equals("1", condition.getRight().getValue(), "Literal derecho debe ser 1");
        Assert.equals(ExpressionNode.ExprType.NUMBER, condition.getRight().getType(), "Lado derecho debe ser NUMBER");
    }

    private void parsesQueryWithoutSemicolon() {
        SelectNode ast = new Parser(new Lexer("SELECT nombre FROM usuarios").tokenize()).parse();
        Assert.equals("usuarios", ast.getTableName(), "Debe parsear sin punto y coma final");
        Assert.equals(1, ast.getColumns().size(), "Debe extraer una columna");
    }

    private void throwsErrorWhenMissingFrom() {
        boolean lanzo = false;
        try {
            new Parser(new Lexer("SELECT nombre usuarios;").tokenize()).parse();
        } catch (CompilerException ex) {
            lanzo = true;
        }
        Assert.isTrue(lanzo, "Debe lanzar CompilerException si falta FROM");
    }

    private void throwsErrorWhenInvalidExpression() {
        boolean lanzo = false;
        try {
            new Parser(new Lexer("SELECT * FROM usuarios WHERE = 5;").tokenize()).parse();
        } catch (CompilerException ex) {
            lanzo = true;
        }
        Assert.isTrue(lanzo, "Debe lanzar CompilerException con expresion invalida");
    }
}