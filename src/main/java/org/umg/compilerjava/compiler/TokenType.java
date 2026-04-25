package org.umg.compilerjava.compiler;

/** Tipos de token soportados por el lexer. */
public enum TokenType {
    SELECT,
    FROM,
    WHERE,
    CREATE,
    TABLE,
    IDENTIFIER,
    NUMBER,
    STRING,
    INT,
    FLOAT,
    VARCHAR,
    EQUAL,
    GREATER,
    LESS,
    GREATER_EQUAL,
    LESS_EQUAL,
    NOT_EQUAL,
    ASTERISK,
    COMMA,
    LEFT_PAREN,
    RIGHT_PAREN,
    SEMICOLON,
    END_OF_FILE,
    INVALID
}
