package org.umg.compilerjava.tests;

/** Utilidad mínima de aserciones para pruebas sin JUnit. */
public final class Assert {

    private Assert() {
    }

    public static void equals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertionError(message + " | esperado=" + expected + " actual=" + actual);
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}
