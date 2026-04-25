package org.umg.compilerjava.compiler;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressionNode that = (ExpressionNode) o;
        return type == that.type && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "ExpressionNode{" + type + ", " + value + "}";
    }
}