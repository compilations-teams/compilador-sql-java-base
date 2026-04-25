package org.umg.compilerjava.compiler;

import java.util.List;

public final class Parser {

    private final List<Token> tokens;
    private int position;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
        this.currentToken = tokens.isEmpty() ? new Token(TokenType.END_OF_FILE, "", 1, 1) : tokens.get(0);
    }

    public StatementNode parse() {
        if (check(TokenType.SELECT)) {
            return parseSelect();
        }
        if (check(TokenType.CREATE)) {
            return parseCreateTable();
        }
        error("Se esperaba SELECT o CREATE TABLE");
        return null;
    }

    private StatementNode parseSelect() {
        SelectNode selectNode = new SelectNode();
        expect(TokenType.SELECT);
        parseColumns(selectNode);
        expect(TokenType.FROM);
        Token tableToken = expect(TokenType.IDENTIFIER);
        selectNode.setTableName(tableToken.getValue());

        if (check(TokenType.WHERE)) {
            advance();
            selectNode.setWhereCondition(parseCondition());
        }

        if (check(TokenType.SEMICOLON)) {
            advance();
        }

        if (!check(TokenType.END_OF_FILE)) {
            error("Se esperaba fin de archivo después de la consulta");
        }

        return selectNode;
    }

    private StatementNode parseCreateTable() {
        expect(TokenType.CREATE);
        expect(TokenType.TABLE);
        Token tableToken = expect(TokenType.IDENTIFIER);
        String tableName = tableToken.getValue();

        expect(TokenType.LEFT_PAREN);

        CreateTableNode createTableNode = new CreateTableNode();
        createTableNode.setTableName(tableName);

        parseColumnDefinitions(createTableNode);

        expect(TokenType.RIGHT_PAREN);

        if (check(TokenType.SEMICOLON)) {
            advance();
        }

        return createTableNode;
    }

    private void parseColumnDefinitions(CreateTableNode createTableNode) {
        Token columnNameToken = expect(TokenType.IDENTIFIER);
        String columnName = columnNameToken.getValue();

        DataType columnType = tokenToDataType(expectDataTypeToken());
        createTableNode.addColumn(columnName, columnType);

        while (check(TokenType.COMMA)) {
            advance();
            Token nextColumnName = expect(TokenType.IDENTIFIER);
            String nextName = nextColumnName.getValue();
            DataType nextType = tokenToDataType(expectDataTypeToken());
            createTableNode.addColumn(nextName, nextType);
        }
    }

    private DataType tokenToDataType(TokenType type) {
        switch (type) {
            case INT:
                return DataType.INT;
            case FLOAT:
                return DataType.FLOAT;
            case VARCHAR:
                return DataType.VARCHAR;
            default:
                error("Se esperaba un tipo de dato válido (INT, FLOAT, VARCHAR)");
                return DataType.VARCHAR;
        }
    }

    private TokenType expectDataTypeToken() {
        TokenType type = currentToken.getType();
        if (type != TokenType.INT && type != TokenType.FLOAT && type != TokenType.VARCHAR) {
            error("Se esperaba INT, FLOAT o VARCHAR pero se encontró " + type);
        }
        Token consumed = currentToken;
        advance();
        return consumed.getType();
    }

    private void parseColumns(SelectNode selectNode) {
        if (check(TokenType.ASTERISK)) {
            selectNode.setSelectAll(true);
            advance();
            return;
        }

        Token firstColumn = expect(TokenType.IDENTIFIER);
        selectNode.addColumn(firstColumn.getValue());

        while (check(TokenType.COMMA)) {
            advance();
            Token nextColumn = expect(TokenType.IDENTIFIER);
            selectNode.addColumn(nextColumn.getValue());
        }
    }

    private ConditionNode parseCondition() {
        ExpressionNode left = parseExpression();
        CompOperator operator = tokenTypeToCompOperator(currentToken.getType());
        advance();
        ExpressionNode right = parseExpression();
        return new ConditionNode(left, operator, right);
    }

    private ExpressionNode parseExpression() {
        if (check(TokenType.IDENTIFIER)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.IDENTIFIER, value);
        }
        if (check(TokenType.NUMBER)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.NUMBER, value);
        }
        if (check(TokenType.STRING)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.STRING, value);
        }
        error("Se esperaba una expresión");
        return null;
    }

    private CompOperator tokenTypeToCompOperator(TokenType tokenType) {
        switch (tokenType) {
            case EQUAL:
                return CompOperator.EQUAL;
            case GREATER:
                return CompOperator.GREATER;
            case LESS:
                return CompOperator.LESS;
            case GREATER_EQUAL:
                return CompOperator.GREATER_EQUAL;
            case LESS_EQUAL:
                return CompOperator.LESS_EQUAL;
            case NOT_EQUAL:
                return CompOperator.NOT_EQUAL;
            default:
                error("Se esperaba un operador de comparación válido");
                return CompOperator.EQUAL;
        }
    }

    private boolean check(TokenType type) {
        return currentToken.getType() == type;
    }

    private Token expect(TokenType type) {
        if (!check(type)) {
            error("Se esperaba " + type + " pero se encontró " + currentToken.getType());
        }
        Token consumed = currentToken;
        advance();
        return consumed;
    }

    private void advance() {
        position++;
        currentToken = position < tokens.size() ? tokens.get(position) : new Token(TokenType.END_OF_FILE, "", currentToken.getLine(), currentToken.getColumn());
    }

    private void error(String message) {
        throw new CompilerException(message, currentToken.getLine(), currentToken.getColumn());
    }
}
