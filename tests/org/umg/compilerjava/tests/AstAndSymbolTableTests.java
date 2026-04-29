package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.Column;
import org.umg.compilerjava.compiler.CompOperator;
import org.umg.compilerjava.compiler.ConditionNode;
import org.umg.compilerjava.compiler.DataType;
import org.umg.compilerjava.compiler.ExpressionNode;
import org.umg.compilerjava.compiler.SelectNode;
import org.umg.compilerjava.compiler.SymbolTable;
import org.umg.compilerjava.compiler.Table;

/** Pruebas minimas directas para AST y tabla de simbolos. */
public final class AstAndSymbolTableTests {

    public void run() {
        buildsAstNodes();
        exposesFixedSchema();
    }

    private void buildsAstNodes() {
        SelectNode select = new SelectNode();
        select.setTableName("usuarios");
        select.addColumn("nombre");
        select.addColumn("edad");

        ExpressionNode left = new ExpressionNode(ExpressionNode.ExprType.IDENTIFIER, "edad");
        ExpressionNode right = new ExpressionNode(ExpressionNode.ExprType.NUMBER, "18");
        ConditionNode condition = new ConditionNode(left, CompOperator.GREATER_EQUAL, right);
        select.setWhereCondition(condition);

        Assert.equals("usuarios", select.getTableName(), "Debe guardar nombre de tabla");
        Assert.equals(2, select.getColumns().size(), "Debe guardar columnas agregadas");
        Assert.equals(ExpressionNode.ExprType.IDENTIFIER, select.getWhereCondition().getLeft().getType(),
                "Debe guardar tipo de expresion izquierda");
        Assert.equals(">=", select.getWhereCondition().getOperator().getSymbol(), "Debe conservar operador");
    }

    private void exposesFixedSchema() {
        SymbolTable symbolTable = new SymbolTable();

        Table usuarios = symbolTable.findTable("USUARIOS");
        Assert.isTrue(usuarios != null, "Debe encontrar tabla sin importar mayusculas");

        Column edad = usuarios.findColumn("Edad");
        Assert.isTrue(edad != null, "Debe encontrar columna sin importar mayusculas");
        Assert.equals(DataType.INT, edad.getType(), "Debe conservar tipo de datos esperado");

        Table productos = symbolTable.findTable("productos");
        Assert.isTrue(productos != null, "Debe incluir tabla productos");
        Assert.equals(DataType.FLOAT, productos.findColumn("precio").getType(), "Precio debe ser FLOAT");
    }
}
