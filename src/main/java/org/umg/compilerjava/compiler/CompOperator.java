package org.umg.compilerjava.compiler;

/** Operadores de comparación soportados. */
public enum CompOperator {
    EQUAL("="),
    GREATER(">"),
    LESS("<"),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    NOT_EQUAL("!=");

    private final String symbol;

    CompOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
