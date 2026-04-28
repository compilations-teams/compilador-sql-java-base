package org.umg.compilerjava.compiler;

import java.util.List;

/**
 * Parser descendente recursivo para la gramática básica SELECT-FROM-WHERE.
 */
public final class Parser {

    private final List<Token> tokens;
    private int position;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
        this.currentToken = tokens.isEmpty() ? new Token(TokenType.END_OF_FILE, "", 1, 1) : tokens.get(0);
    }

    // Parser Para Querys del tipo: SELECT columnas FROM tabla [WHERE condición];
    // T-17

    public SelectNode parse() {
        SelectNode selectNode = new SelectNode();
        // Select
        expect(TokenType.SELECT);
        // Columnas
        parseColumns(selectNode);
        // From
        expect(TokenType.FROM);
        // Tabla
        Token tableToken = expect(TokenType.IDENTIFIER);
        selectNode.setTableName(tableToken.getValue());

        // Where (opcional)
        if (check(TokenType.WHERE)) {
            advance();
            selectNode.setWhereCondition(parseCondition());
        }

        // Punto y coma opcional al final
        if (check(TokenType.SEMICOLON)) {
            advance();
        }

        // Debe ser fin de archivo después de la consulta
        if (!check(TokenType.END_OF_FILE)) {
            error("Se esperaba fin de archivo después de la consulta");
        }

        return selectNode;
    }

    // Parser Para Querys del tipo: SELECT * FROM tabla [WHERE condición]; T-17

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

    /**
     * T-15: Parseo de la condición WHERE simple.
     */
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
        if (check(TokenType.NUMBER) || check(TokenType.FLOAT)) {
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
        currentToken = position < tokens.size() ? tokens.get(position)
                : new Token(TokenType.END_OF_FILE, "", currentToken.getLine(), currentToken.getColumn());
    }

    private void error(String message) {
        throw new CompilerException(message, currentToken.getLine(), currentToken.getColumn(), CompilerException.ErrorType.SYNTACTIC);
    }
}
