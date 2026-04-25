package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ErrorHandler {

    private final List<CompilerException> errors;
    private final List<String> warnings;

    public ErrorHandler() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public void addError(CompilerException error) {
        errors.add(error);
    }

    public void addError(String message, int line, int column) {
        errors.add(new CompilerException(message, line, column));
    }

    public void addError(String message, int line, int column, CompilerException.ErrorType errorType) {
        errors.add(new CompilerException(message, line, column, errorType));
    }

    public void addWarning(String message) {
        warnings.add(message);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }

    public List<CompilerException> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public List<String> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    public int getErrorCount() {
        return errors.size();
    }

    public int getWarningCount() {
        return warnings.size();
    }

    public String getFormattedErrors() {
        if (errors.isEmpty()) {
            return "Sin errores";
        }
        StringBuilder sb = new StringBuilder();
        for (CompilerException error : errors) {
            sb.append(error.getMessage()).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        errors.clear();
        warnings.clear();
    }
}