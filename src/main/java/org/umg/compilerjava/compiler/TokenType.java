package org.umg.compilerjava.compiler;

/** Tipos de token soportados por el lexer. */
public enum TokenType {
    SELECT,
    FROM,
    WHERE,
    IDENTIFIER,
    NUMBER,
    FLOAT,
    STRING,
    EQUAL,
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    NOT_EQUAL,
    ASTERISK,
    COMMA,
    SEMICOLON,
    END_OF_FILE,
    INVALID
}
