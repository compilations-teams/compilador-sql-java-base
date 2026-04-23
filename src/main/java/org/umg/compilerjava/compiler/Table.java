package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Tabla del schema fijo. */
public final class Table {

    private final String name;
    private final List<Column> columns = new ArrayList<Column>();

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addColumn(String columnName, DataType type) {
        columns.add(new Column(columnName, type));
    }

    public List<Column> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public Column findColumn(String columnName) {
        for (Column column : columns) {
            if (column.getName().equalsIgnoreCase(columnName)) {
                return column;
            }
        }
        return null;
    }
}
