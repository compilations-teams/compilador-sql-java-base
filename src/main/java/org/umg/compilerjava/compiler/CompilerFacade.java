package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.List;

public final class CompilerFacade {

    private final SymbolTable symbolTable;

    public CompilerFacade() {
        this.symbolTable = new SymbolTable();
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public CompilerReport compile(String sql) {
        List<Token> tokens = new Lexer(sql).tokenize();
        List<String> diagnostics = new ArrayList<String>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.INVALID) {
                diagnostics.add("Token inválido '" + token.getValue() + "' en línea " + token.getLine());
                return new CompilerReport(false, tokens, diagnostics, null);
            }
        }

        try {
            Parser parser = new Parser(tokens);
            StatementNode ast = parser.parse();

            if (ast instanceof CreateTableNode) {
                return handleCreateTable((CreateTableNode) ast, tokens, diagnostics);
            }

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(symbolTable);
            boolean success = semanticAnalyzer.analyze((SelectNode) ast);
            diagnostics.addAll(semanticAnalyzer.getErrors());
            return new CompilerReport(success, tokens, diagnostics, ast);
        } catch (CompilerException ex) {
            diagnostics.add(ex.getMessage());
            return new CompilerReport(false, tokens, diagnostics, null);
        }
    }

    private CompilerReport handleCreateTable(CreateTableNode createTableNode, List<Token> tokens, List<String> diagnostics) {
        String tableName = createTableNode.getTableName();

        if (symbolTable.containsTable(tableName)) {
            diagnostics.add("La tabla '" + tableName + "' ya existe");
            return new CompilerReport(false, tokens, diagnostics, createTableNode);
        }

        Table table = new Table(tableName);
        for (CreateTableNode.ColumnDefinition colDef : createTableNode.getColumns()) {
            if (table.containsColumn(colDef.getName())) {
                diagnostics.add("Columna duplicada '" + colDef.getName() + "' en la tabla '" + tableName + "'");
                return new CompilerReport(false, tokens, diagnostics, createTableNode);
            }
            table.addColumn(colDef.getName(), colDef.getType());
        }

        symbolTable.addTable(table);
        return new CompilerReport(true, tokens, diagnostics, createTableNode);
    }
}
