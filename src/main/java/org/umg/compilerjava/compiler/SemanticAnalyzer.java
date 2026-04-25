package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SemanticAnalyzer {

    private final SymbolTable symbolTable;
    private final List<String> errors = new ArrayList<>();

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    // Punto de entrada principal
    /**
     * Analiza el AST de una sentencia SELECT.
     *
     * @param ast nodo raíz producido por el Parser
     * @return true si no se encontraron errores semánticos
     */
    public boolean analyze(SelectNode ast) {
        errors.clear();

        if (ast == null) {
            errors.add("AST vacío");
            return false;
        }

        // Verificar que la tabla exista en el schema
        Table table = symbolTable.findTable(ast.getTableName());
        if (table == null) {
            errors.add("Tabla '" + ast.getTableName() + "' no existe en el schema");
            return false;
        }

        validateColumns(ast, table);

        validateConditionTypes(ast.getWhereCondition(), table);

        return errors.isEmpty();
    }

    /** Retorna la lista de errores semánticos encontrados (inmutable). */
    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    // ------------------------------------------------------------------
    // Validación de columnas del SELECT
    // ------------------------------------------------------------------

    private void validateColumns(SelectNode ast, Table table) {
        if (ast.isSelectAll()) {
            return;
        }
        for (String columnName : ast.getColumns()) {
            if (table.findColumn(columnName) == null) {
                errors.add("Columna '" + columnName
                        + "' no existe en la tabla '" + table.getName() + "'");
            }
        }
    }

    // ------------------------------------------------------------------
    // Validación de tipos en la condición WHERE 
    // ------------------------------------------------------------------

    private void validateConditionTypes(ConditionNode condition, Table table) {
        if (condition == null) {
            return;
        }

        DataType leftType  = resolveExpressionType(condition.getLeft(),  table);
        DataType rightType = resolveExpressionType(condition.getRight(), table);

        if (!areTypesCompatible(leftType, rightType)) {
            errors.add(
                "Error de tipos en condición WHERE: "
                + "no se puede comparar " + leftType
                + " con " + rightType
                + " usando el operador '" + condition.getOperator().getSymbol() + "'"
            );
        }
    }
    // ------------------------------------------------------------------
    // Resolución del tipo de una expresión
    // ------------------------------------------------------------------

    private DataType resolveExpressionType(ExpressionNode expression, Table table) {
        switch (expression.getType()) {

            case NUMBER:
                return expression.getValue().contains(".") ? DataType.FLOAT : DataType.INT;

            case STRING:
                return DataType.VARCHAR;

            case IDENTIFIER:
                Column column = table.findColumn(expression.getValue());
                if (column == null) {
                    errors.add("Columna '" + expression.getValue()
                            + "' no existe en la tabla '" + table.getName() + "'");
                    return DataType.VARCHAR;
                }
                return column.getType();

            default:
                return DataType.VARCHAR;
        }
    }

    // ------------------------------------------------------------------
    // Reglas de compatibilidad de tipos 
    // ------------------------------------------------------------------

    private boolean areTypesCompatible(DataType left, DataType right) {
        if (left == right) {
            return true;
        }

        boolean leftIsNumeric  = (left  == DataType.INT || left  == DataType.FLOAT);
        boolean rightIsNumeric = (right == DataType.INT || right == DataType.FLOAT);
        if (leftIsNumeric && rightIsNumeric) {
            return true;
        }

        return false;
        //prueba
    }
}