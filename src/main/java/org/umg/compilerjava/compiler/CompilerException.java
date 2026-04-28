package org.umg.compilerjava.compiler;

/** Excepción controlada del compilador. */
public final class CompilerException extends RuntimeException {

    public enum ErrorType {
        LEXICAL,
        SYNTACTIC,
        SEMANTIC,
        UNKNOWN
    }

    private final int line;
    private final int column;
    private final ErrorType errorType;

    public CompilerException(String message, int line, int column) {
        this(message, line, column, ErrorType.UNKNOWN);
    }

    public CompilerException(String message, int line, int column, ErrorType errorType) {
        super(message);
        this.line = line;
        this.column = column;
        this.errorType = errorType;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String getMessage() {
        String typePrefix = errorType != ErrorType.UNKNOWN ? "[" + errorType.name() + "] " : "";
        return typePrefix + super.getMessage() + " (línea " + line + ", columna " + column + ")";
    }
}
