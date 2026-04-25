package org.umg.compilerjava.compiler;

import java.util.Objects;

public final class ConditionNode {

    private final ExpressionNode left;
    private final CompOperator operator;
    private final ExpressionNode right;

    public ConditionNode(ExpressionNode left, CompOperator operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public ExpressionNode getLeft() {
        return left;
    }

    public CompOperator getOperator() {
        return operator;
    }

    public ExpressionNode getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionNode that = (ConditionNode) o;
        return Objects.equals(left, that.left)
                && operator == that.operator
                && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, operator, right);
    }

    @Override
    public String toString() {
        return "ConditionNode{"
                + "left=" + left
                + ", operator=" + operator
                + ", right=" + right
                + '}';
    }
}