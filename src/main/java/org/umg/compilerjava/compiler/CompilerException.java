package org.umg.compilerjava.compiler;

/** Excepción controlada del compilador. */
public final class CompilerException extends RuntimeException {

    private final int line;
    private final int column;

    public CompilerException(String message, int line, int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " (línea " + line + ", columna " + column + ")";
    }
}
