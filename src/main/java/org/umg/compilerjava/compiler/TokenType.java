package org.umg.compilerjava.compiler;

/** Tipos de token soportados por el lexer. */
public enum TokenType {
    SELECT,
    FROM,
    WHERE,
    IDENTIFIER,
    NUMBER,
    STRING,
    EQUAL,
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    NOT_EQUAL,
    AND,
    OR,
    ASTERISK,
    COMMA,
    SEMICOLON,
    END_OF_FILE,
    INVALID
}
