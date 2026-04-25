package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.ConditionNode;
import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Parser;
import org.umg.compilerjava.compiler.SelectNode;
import org.umg.compilerjava.compiler.StatementNode;

public final class ParserTests {

    public void run() {
        parsesSelectWithColumns();
        parsesWhereCondition();
    }

    private void parsesSelectWithColumns() {
        StatementNode stmt = new Parser(new Lexer("SELECT nombre, edad FROM usuarios;").tokenize()).parse();
        SelectNode ast = (SelectNode) stmt;
        Assert.isFalse(ast.isSelectAll(), "No debe marcar selectAll en lista explícita");
        Assert.equals("usuarios", ast.getTableName(), "Debe extraer tabla");
        Assert.equals(2, ast.getColumns().size(), "Debe extraer dos columnas");
    }

    private void parsesWhereCondition() {
        StatementNode stmt = new Parser(new Lexer("SELECT * FROM usuarios WHERE edad >= 18;").tokenize()).parse();
        SelectNode ast = (SelectNode) stmt;
        ConditionNode condition = ast.getWhereCondition();
        Assert.isTrue(ast.isSelectAll(), "SELECT * debe marcar selectAll");
        Assert.equals("edad", condition.getLeft().getValue(), "Debe extraer identificador izquierdo");
        Assert.equals("18", condition.getRight().getValue(), "Debe extraer literal derecho");
    }
}
