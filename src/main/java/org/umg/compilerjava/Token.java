package org.umg.compilerjava;

/**
 * Representa un Token del analizador léxico
 * Migrado de: struct Token en Token.h (C++)
 */
public class Token {
    public TokenType type;
    public String value;
    public int line;
    public int column;

    public Token() {
        this.type = TokenType.INVALID;
        this.value = "";
        this.line = 0;
        this.column = 0;
    }

    public Token(TokenType type, String value, int line, int column) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    public String typeToString() {
        return type.toString();
    }

    public void print() {
        System.out.print("[" + typeToString() + "] ");
        if (!value.isEmpty()) {
            System.out.print("'" + value + "' ");
        }
        System.out.println("(L" + line + ":C" + column + ")");
    }
}