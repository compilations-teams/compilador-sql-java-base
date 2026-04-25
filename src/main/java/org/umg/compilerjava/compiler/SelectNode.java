package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** AST raíz para una consulta SELECT. */
/**
 * Nodo raíz del AST para una consulta SELECT.
 * Almacena toda la información estructural de la consulta:
 * columnas seleccionadas, tabla origen y condición WHERE opcional.
 *
 * Ejemplo de consulta representada:
 *   SELECT nombre, edad FROM clientes WHERE edad >= 18
 */
public final class SelectNode {

    /** Indica si la consulta usa SELECT * (todas las columnas). */
    private boolean selectAll;

    /** Nombre de la tabla indicada en la cláusula FROM. */
    private String tableName = "";

    /**
     * Lista de columnas explícitas indicadas en el SELECT.
     * Solo se usa cuando selectAll es false.
     * Ejemplo: SELECT nombre, edad → ["nombre", "edad"]
     */
    private final List<String> columns = new ArrayList<String>();

    /**
     * Condición de filtrado de la cláusula WHERE.
     * Es null si la consulta no tiene WHERE.
     * Ejemplo: WHERE edad >= 18 → ConditionNode(edad, >=, 18)
     */
    private ConditionNode whereCondition;

    /**
     * Retorna true si la consulta selecciona todas las columnas (SELECT *).
     * El SemanticAnalyzer lo usa para saltar la validación de columnas.
     */
    public boolean isSelectAll() {
        return selectAll;

    }

    /**
     * Establece si la consulta usa SELECT *.
     * Lo llama el Parser cuando encuentra el token ASTERISK.
     */
    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;

    }

    /**
     * Retorna el nombre de la tabla de la cláusula FROM.
     * El SemanticAnalyzer lo usa para buscar la tabla en el SymbolTable.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Establece el nombre de la tabla.
     * Lo llama el Parser cuando consume el IDENTIFIER después de FROM.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Retorna la lista de columnas como una vista de solo lectura.
     * Así se evita que código externo modifique la lista directamente.
     * El SemanticAnalyzer la recorre para validar que cada columna exista en el schema.
     */
    public List<String> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    /**
     * Agrega una columna a la lista de columnas seleccionadas.
     * Lo llama el Parser cada vez que encuentra un IDENTIFIER en la lista del SELECT.
     * Ejemplo: SELECT nombre, edad → se llama dos veces, con "nombre" y "edad".
     */
    public void addColumn(String column) {
        columns.add(column);
    }

    /**
     * Retorna el nodo de condición WHERE, o null si no hay cláusula WHERE.
     * El SemanticAnalyzer lo usa para validar tipos en la comparación.
     */
    public ConditionNode getWhereCondition() {
        return whereCondition;
    }

    /**
     * Establece la condición WHERE del nodo.
     * Lo llama el Parser cuando encuentra el token WHERE seguido de una expresión válida.
     * Si la consulta no tiene WHERE, este método nunca se llama y queda como null.
     */
    public void setWhereCondition(ConditionNode whereCondition) {
        this.whereCondition = whereCondition;
    }
}
