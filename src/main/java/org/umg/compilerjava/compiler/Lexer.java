package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Analizador léxico manual, inspirado directamente en la versión C++ educativa.
 */
public final class Lexer {

    private static final Map<String, TokenType> KEYWORDS = createKeywords();

    private final String source;
    private int position;
    private int line;
    private int column;
    private char currentChar;

    public Lexer(String source) {
        this.source = source == null ? "" : source;
        this.position = 0;
        this.line = 1;
        this.column = 1;
        this.currentChar = this.source.isEmpty() ? '\0' : this.source.charAt(0);
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<Token>();
        Token token;
        do {
            token = getNextToken();
            tokens.add(token);
        } while (token.getType() != TokenType.END_OF_FILE);
        return tokens;
    }

    public Token getNextToken() {
        skipWhitespace();

        if (currentChar == '\0') {
            return new Token(TokenType.END_OF_FILE, "", line, column);
        }

        int startLine = line;
        int startColumn = column;

        if (isIdentifierStart(currentChar)) {
            return readIdentifierOrKeyword();
        }

        if (Character.isDigit(currentChar)) {
            return readNumberToken();
        }

        if (currentChar == '\'') {
            return readString();
        }

        if (currentChar == '>') {
            advance();
            if (currentChar == '=') {
                advance();
                return new Token(TokenType.GREATER_EQUAL, ">=", startLine, startColumn);
            }
            return new Token(TokenType.GREATER, ">", startLine, startColumn);
        }

        if (currentChar == '<') {
            advance();
            if (currentChar == '=') {
                advance();
                return new Token(TokenType.LESS_EQUAL, "<=", startLine, startColumn);
            }
            return new Token(TokenType.LESS, "<", startLine, startColumn);
        }

        if (currentChar == '!') {
            advance();
            if (currentChar == '=') {
                advance();
                return new Token(TokenType.NOT_EQUAL, "!=", startLine, startColumn);
            }
            return new Token(TokenType.INVALID, "!", startLine, startColumn);
        }

        char consumed = currentChar;
        advance();

        switch (consumed) {
            case '=':
                return new Token(TokenType.EQUAL, "=", startLine, startColumn);
            case '*':
                return new Token(TokenType.ASTERISK, "*", startLine, startColumn);
            case ',':
                return new Token(TokenType.COMMA, ",", startLine, startColumn);
            case ';':
                return new Token(TokenType.SEMICOLON, ";", startLine, startColumn);
            default:
                return new Token(TokenType.INVALID, String.valueOf(consumed), startLine, startColumn);
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }

        if (currentChar == '-' && peek() == '-') {
            while (currentChar != '\0' && currentChar != '\n') {
                advance();
            }
            if (currentChar == '\n') {
                advance();
            }
            skipWhitespace();
        }
    }

    private Token readIdentifierOrKeyword() {
        int startLine = line;
        int startColumn = column;
        StringBuilder builder = new StringBuilder();

        while (isIdentifierPart(currentChar)) {
            builder.append(currentChar);
            advance();
        }

        String value = builder.toString();
        TokenType keywordType = KEYWORDS.get(value.toUpperCase(Locale.ROOT));
        if (keywordType != null) {
            return new Token(keywordType, value, startLine, startColumn);
        }
        return new Token(TokenType.IDENTIFIER, value, startLine, startColumn);
    }

    private Token readNumberToken() {
        int startLine = line;
        int startColumn = column;
        StringBuilder builder = new StringBuilder();

        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            builder.append(currentChar);
            advance();
        }

        if (currentChar == '.') {
            builder.append(currentChar);
            advance();
            while (currentChar != '\0' && Character.isDigit(currentChar)) {
                builder.append(currentChar);
                advance();
            }
        }

        return new Token(TokenType.NUMBER, builder.toString(), startLine, startColumn);
    }

    private Token readString() {
        int startLine = line;
        int startColumn = column;
        StringBuilder builder = new StringBuilder();

        advance();
        while (currentChar != '\0' && currentChar != '\'') {
            builder.append(currentChar);
            advance();
        }

        if (currentChar != '\'') {
            return new Token(TokenType.INVALID, builder.toString(), startLine, startColumn);
        }

        advance();
        return new Token(TokenType.STRING, builder.toString(), startLine, startColumn);
    }

    private void advance() {
        if (currentChar == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }

        position++;
        currentChar = position >= source.length() ? '\0' : source.charAt(position);
    }

    private char peek() {
        int nextPosition = position + 1;
        if (nextPosition >= source.length()) {
            return '\0';
        }
        return source.charAt(nextPosition);
    }

    private static boolean isIdentifierStart(char value) {
        return Character.isLetter(value) || value == '_';
    }

    private static boolean isIdentifierPart(char value) {
        return Character.isLetterOrDigit(value) || value == '_';
    }

    private static Map<String, TokenType> createKeywords() {
        Map<String, TokenType> keywords = new HashMap<String, TokenType>();
        keywords.put("SELECT", TokenType.SELECT);
        keywords.put("FROM", TokenType.FROM);
        keywords.put("WHERE", TokenType.WHERE);
        return keywords;
    }
}
