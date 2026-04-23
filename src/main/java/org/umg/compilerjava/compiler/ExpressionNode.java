package org.umg.compilerjava.compiler;

/** Nodo de expresión simple. */
public final class ExpressionNode {

    public enum ExprType {
        IDENTIFIER,
        NUMBER,
        STRING
    }

    private final ExprType type;
    private final String value;

    public ExpressionNode(ExprType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ExprType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
