package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class Table {

    private final String name;
    private final Map<String, Column> columns = new TreeMap<String, Column>();

    public Table(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addColumn(String columnName, DataType type) {
        if (columnName == null || columnName.trim().isEmpty() || type == null) {
            return false;
        }
        String key = columnName.toLowerCase();
        if (columns.containsKey(key)) {
            return false;
        }
        columns.put(key, new Column(columnName, type));
        return true;
    }

    public boolean addColumn(Column column) {
        if (column == null || column.getName() == null) {
            return false;
        }
        String key = column.getName().toLowerCase();
        if (columns.containsKey(key)) {
            return false;
        }
        columns.put(key, column);
        return true;
    }

    public List<Column> getColumns() {
        return Collections.unmodifiableList(new ArrayList<Column>(columns.values()));
    }

    public Column findColumn(String columnName) {
        if (columnName == null) return null;
        return columns.get(columnName.toLowerCase());
    }

    public boolean containsColumn(String columnName) {
        if (columnName == null) return false;
        return columns.containsKey(columnName.toLowerCase());
    }

    public int getColumnCount() {
        return columns.size();
    }
}