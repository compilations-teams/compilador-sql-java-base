package org.umg.compilerjava.compiler;

/** Condición WHERE simple con expresión izquierda, operador y expresión derecha. */
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
}
