package org.umg.compilerjava.compiler;

/** Columna del schema de base de datos. */
public final class Column {
    private final String name;
    private final DataType type;
    private final boolean nullable;

    public Column(String name, DataType type) {
        this.name = name;
        this.type = type;
        this.nullable = true;
    }

    public Column(String name, DataType type, boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public String getName() {
        return name;
    }

    public DataType getType() {
        return type;
    }

    public boolean isNullable() {
        return nullable;
    }

    @Override
    public String toString() {
        return "Column{name='" + name + "', type=" + type + ", nullable=" + nullable + "}";
    }
}
