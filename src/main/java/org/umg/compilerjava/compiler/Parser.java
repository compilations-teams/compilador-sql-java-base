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

    public SelectNode parse() {
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

    /**
     * T-14: Implementación del análisis de expresiones.
     * Una expresión en SQL básico puede ser un nombre de columna (IDENTIFIER),
     * un número (NUMBER) o un texto (STRING).
     */
    private ExpressionNode parseExpression() {
        // Caso 1: La expresión es un nombre de columna o identificador
        if (check(TokenType.IDENTIFIER)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.IDENTIFIER, value);
        }
        
        // Caso 2: La expresión es un número literal
        if (check(TokenType.NUMBER)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.NUMBER, value);
        }
        
        // Caso 3: La expresión es una cadena de texto (ej. 'Guatemala')
        if (check(TokenType.STRING)) {
            String value = currentToken.getValue();
            advance();
            return new ExpressionNode(ExpressionNode.ExprType.STRING, value);
        }
        
        // Si no es ninguno de los anteriores, hay un error sintáctico
        error("Error de sintaxis: Se esperaba un Identificador, Número o Cadena en la expresión.");
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
