package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class SymbolTable {

    private final Map<String, Table> tables = new TreeMap<String, Table>();

    public SymbolTable() {
        addDefaultTables();
    }

    private void addDefaultTables() {
        Table usuarios = new Table("usuarios");
        usuarios.addColumn("id", DataType.INT);
        usuarios.addColumn("nombre", DataType.VARCHAR);
        usuarios.addColumn("edad", DataType.INT);
        usuarios.addColumn("ciudad", DataType.VARCHAR);
        tables.put("usuarios", usuarios);

        Table productos = new Table("productos");
        productos.addColumn("id", DataType.INT);
        productos.addColumn("nombre", DataType.VARCHAR);
        productos.addColumn("precio", DataType.FLOAT);
        productos.addColumn("categoria", DataType.VARCHAR);
        tables.put("productos", productos);
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(new ArrayList<Table>(tables.values()));
    }

    public Table findTable(String tableName) {
        if (tableName == null) return null;
        return tables.get(tableName.toLowerCase());
    }

    public boolean addTable(Table table) {
        if (table == null || table.getName() == null) {
            return false;
        }
        String key = table.getName().toLowerCase();
        if (tables.containsKey(key)) {
            return false;
        }
        tables.put(key, table);
        return true;
    }

    public boolean addTable(String tableName) {
        if (tableName == null || tableName.trim().isEmpty()) {
            return false;
        }
        String key = tableName.toLowerCase();
        if (tables.containsKey(key)) {
            return false;
        }
        tables.put(key, new Table(tableName));
        return true;
    }

    public boolean containsTable(String tableName) {
        if (tableName == null) return false;
        return tables.containsKey(tableName.toLowerCase());
    }

    // 🔥 NUEVO: agregar columna a una tabla
    public boolean addColumn(String tableName, String columnName, DataType type) {
        if (tableName == null || columnName == null || type == null) {
            return false;
        }
        Table table = findTable(tableName);
        if (table == null) {
            return false;
        }
        return table.addColumn(columnName, type);
    }

    // 🔥 NUEVO: verificar columna
    public boolean containsColumn(String tableName, String columnName) {
        if (tableName == null || columnName == null) {
            return false;
        }
        Table table = findTable(tableName);
        if (table == null) {
            return false;
        }
        return table.containsColumn(columnName);
    }
}