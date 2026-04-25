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
}