package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Validador semántico contra el schema fijo de tablas y columnas.
 */
public final class SemanticAnalyzer {

    private final SymbolTable symbolTable;
    private final List<String> errors = new ArrayList<>();

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public boolean analyze(SelectNode ast) {
        errors.clear();

        if (ast == null) {
            errors.add("Error Semántico: El AST está vacío.");
            return false;
        }

        try {
            // 1. Validar que la tabla exista en la SymbolTable
            Table table = symbolTable.findTable(ast.getTableName());
            
            if (table == null) {
                errors.add("Error Semántico: La tabla '" + ast.getTableName() + "' no existe.");
                return false;
            }

            validateColumns(ast, table);
            validateCondition(ast.getWhereCondition(), table);

            System.out.println("Análisis semántico completado.");

        } catch (Exception e) {
            errors.add("Error: " + e.getMessage());
            return false;
        }

        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }


    private void validateColumns(SelectNode ast, Table table) {
        if (ast.isSelectAll()) {
            return;
        }
        for (String columnName : ast.getColumns()) {
            if (table.findColumn(columnName) == null) {
                errors.add("Columna '" + columnName + "' no existe en la tabla '" + table.getName() + "'");
            }
        }
    }

    private void validateCondition(ConditionNode condition, Table table) {
        if (condition == null) {
            return;
        }

        DataType left = getExpressionType(condition.getLeft(), table);
        DataType right = getExpressionType(condition.getRight(), table);
        if (!areCompatible(left, right)) {
            errors.add("Tipos incompatibles en comparación: " + left + " " + condition.getOperator().getSymbol() + " " + right);
        }
    }

    private DataType getExpressionType(ExpressionNode expression, Table table) {
    if (expression == null) {
        return DataType.VARCHAR;
    }

    switch (expression.getType()) {
        case NUMBER:
            return expression.getValue().contains(".") ? DataType.FLOAT : DataType.INT;

        case STRING:
            return DataType.VARCHAR;

        case IDENTIFIER:
            Column column = table.findColumn(expression.getValue());
            if (column == null) {
                errors.add("Columna '" + expression.getValue() + "' no existe en la tabla '" + table.getName() + "'");
                return DataType.VARCHAR;
            }
            return column.getType();

        default:
            return DataType.VARCHAR;
    }
}

    private boolean areCompatible(DataType left, DataType right) {
        if (left == right) {
            return true;
        }
        return (left == DataType.FLOAT && right == DataType.INT)
            || (left == DataType.INT && right == DataType.FLOAT);
    }
}


