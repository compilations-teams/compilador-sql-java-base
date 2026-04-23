package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tabla de símbolos con schema fijo para el ejercicio educativo.
 */
public final class SymbolTable {

    private final List<Table> tables = new ArrayList<Table>();

    public SymbolTable() {
        Table usuarios = new Table("usuarios");
        usuarios.addColumn("id", DataType.INT);
        usuarios.addColumn("nombre", DataType.VARCHAR);
        usuarios.addColumn("edad", DataType.INT);
        usuarios.addColumn("ciudad", DataType.VARCHAR);
        tables.add(usuarios);

        Table productos = new Table("productos");
        productos.addColumn("id", DataType.INT);
        productos.addColumn("nombre", DataType.VARCHAR);
        productos.addColumn("precio", DataType.FLOAT);
        productos.addColumn("categoria", DataType.VARCHAR);
        tables.add(productos);
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public Table findTable(String tableName) {
        for (Table table : tables) {
            if (table.getName().equalsIgnoreCase(tableName)) {
                return table;
            }
        }
        return null;
    }
}
