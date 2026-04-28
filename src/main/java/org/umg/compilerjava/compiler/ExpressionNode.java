package org.umg.compilerjava.compiler;

/**
 * Nodo del AST que representa una expresión simple en una condición WHERE.
 *
 * <p>Una expresión puede ser un identificador (nombre de columna), un número
 * entero o decimal, o una cadena de texto entre comillas simples.
 *
 * <p>Ejemplos de expresiones válidas:
 * <ul>
 *   <li>{@code edad}    → IDENTIFIER</li>
 *   <li>{@code 18}      → NUMBER</li>
 *   <li>{@code 'Roma'}  → STRING</li>
 * </ul>
 */
public final class ExpressionNode {

    /** Tipo de expresión reconocido por el parser. */
    public enum ExprType {
        /** Nombre de columna o tabla, por ejemplo {@code edad}. */
        IDENTIFIER,
        /** Literal numérico entero o decimal, por ejemplo {@code 18} o {@code 19.99}. */
        NUMBER,
        /** Literal de cadena entre comillas simples, por ejemplo {@code 'Roma'}. */
        STRING
    }

    private final ExprType type;
    private final String value;

    /**
     * Crea un nodo de expresión con tipo y valor dados.
     *
     * @param type  tipo de la expresión (no puede ser {@code null})
     * @param value valor textual de la expresión (no puede ser {@code null})
     */
    public ExpressionNode(ExprType type, String value) {
        if (type == null) {
            throw new IllegalArgumentException("El tipo de expresión no puede ser nulo");
        }
        if (value == null) {
            throw new IllegalArgumentException("El valor de la expresión no puede ser nulo");
        }
        this.type = type;
        this.value = value;
    }

    /** Devuelve el tipo de esta expresión. */
    public ExprType getType() {
        return type;
    }

    /** Devuelve el valor textual de esta expresión. */
    public String getValue() {
        return value;
    }

    /** Retorna {@code true} si esta expresión es un identificador (nombre de columna). */
    public boolean isIdentifier() {
        return type == ExprType.IDENTIFIER;
    }

    /** Retorna {@code true} si esta expresión es un literal numérico. */
    public boolean isNumber() {
        return type == ExprType.NUMBER;
    }

    /** Retorna {@code true} si esta expresión es un literal de cadena. */
    public boolean isString() {
        return type == ExprType.STRING;
    }

    /**
     * Representación legible del nodo, útil para diagnóstico y pruebas.
     *
     * <p>Formato: {@code ExpressionNode[type=NUMBER, value=18]}
     */
    @Override
    public String toString() {
        return "ExpressionNode[type=" + type + ", value=" + value + "]";
    }
}