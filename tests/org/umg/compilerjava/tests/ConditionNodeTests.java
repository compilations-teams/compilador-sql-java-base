package org.umg.compilerjava.tests;

import org.umg.compilerjava.compiler.CompOperator;
import org.umg.compilerjava.compiler.ConditionNode;
import org.umg.compilerjava.compiler.ExpressionNode;
import org.umg.compilerjava.compiler.ExpressionNode.ExprType;

public final class ConditionNodeTests {

    public static void run() {
        createsConditionWithGetters();
        equalsReturnsTrueForSameValues();
        equalsReturnsFalseForDifferentLeft();
        equalsReturnsFalseForDifferentOperator();
        equalsReturnsFalseForDifferentRight();
        hashCodeIsConsistent();
        toStringContainsAllFields();
    }

    private static void createsConditionWithGetters() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "edad");
        ExpressionNode right = new ExpressionNode(ExprType.NUMBER, "18");
        ConditionNode condition = new ConditionNode(left, CompOperator.GREATER_EQUAL, right);

        Assert.equals("edad", condition.getLeft().getValue(), "Left value");
        Assert.equals(CompOperator.GREATER_EQUAL, condition.getOperator(), "Operator");
        Assert.equals("18", condition.getRight().getValue(), "Right value");
    }

    private static void equalsReturnsTrueForSameValues() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "nombre");
        ExpressionNode right = new ExpressionNode(ExprType.STRING, "'juan'");
        ConditionNode c1 = new ConditionNode(left, CompOperator.EQUAL, right);
        ConditionNode c2 = new ConditionNode(left, CompOperator.EQUAL, right);

        Assert.isTrue(c1.equals(c2), "Same values should be equal");
    }

    private static void equalsReturnsFalseForDifferentLeft() {
        ExpressionNode left1 = new ExpressionNode(ExprType.IDENTIFIER, "edad");
        ExpressionNode left2 = new ExpressionNode(ExprType.IDENTIFIER, "nombre");
        ExpressionNode right = new ExpressionNode(ExprType.NUMBER, "18");

        ConditionNode c1 = new ConditionNode(left1, CompOperator.EQUAL, right);
        ConditionNode c2 = new ConditionNode(left2, CompOperator.EQUAL, right);

        Assert.isFalse(c1.equals(c2), "Different left should not be equal");
    }

    private static void equalsReturnsFalseForDifferentOperator() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "edad");
        ExpressionNode right = new ExpressionNode(ExprType.NUMBER, "18");

        ConditionNode c1 = new ConditionNode(left, CompOperator.GREATER, right);
        ConditionNode c2 = new ConditionNode(left, CompOperator.LESS, right);

        Assert.isFalse(c1.equals(c2), "Different operator should not be equal");
    }

    private static void equalsReturnsFalseForDifferentRight() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "edad");
        ExpressionNode right1 = new ExpressionNode(ExprType.NUMBER, "18");
        ExpressionNode right2 = new ExpressionNode(ExprType.NUMBER, "21");

        ConditionNode c1 = new ConditionNode(left, CompOperator.EQUAL, right1);
        ConditionNode c2 = new ConditionNode(left, CompOperator.EQUAL, right2);

        Assert.isFalse(c1.equals(c2), "Different right should not be equal");
    }

    private static void hashCodeIsConsistent() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "id");
        ExpressionNode right = new ExpressionNode(ExprType.NUMBER, "5");
        ConditionNode condition = new ConditionNode(left, CompOperator.NOT_EQUAL, right);

        int hash1 = condition.hashCode();
        int hash2 = condition.hashCode();

        Assert.equals(hash1, hash2, "HashCode should be consistent");
    }

    private static void toStringContainsAllFields() {
        ExpressionNode left = new ExpressionNode(ExprType.IDENTIFIER, "status");
        ExpressionNode right = new ExpressionNode(ExprType.NUMBER, "1");
        ConditionNode condition = new ConditionNode(left, CompOperator.NOT_EQUAL, right);

        String str = condition.toString();

        Assert.isTrue(str.contains("ConditionNode"), "toString contains class name");
        Assert.isTrue(str.contains("status"), "toString contains left value");
        Assert.isTrue(str.contains("NOT_EQUAL"), "toString contains operator");
    }
}