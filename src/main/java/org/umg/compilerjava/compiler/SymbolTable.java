package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tabla de símbolos con schema fijo para el ejercicio educativo.
 * T-20 - SymbolTable Schema
 * @author Josué David Morales Ramírez
 */
public final class SymbolTable {

    private final List<Table> tables = new ArrayList<Table>();

    public SymbolTable() {

        // Tabla: usuarios
        Table usuarios = new Table("usuarios");
        usuarios.addColumn("id",     DataType.INT);
        usuarios.addColumn("nombre", DataType.VARCHAR);
        usuarios.addColumn("edad",   DataType.INT);
        usuarios.addColumn("ciudad", DataType.VARCHAR);
        tables.add(usuarios);

        // Tabla: productos
        Table productos = new Table("productos");
        productos.addColumn("id",        DataType.INT);
        productos.addColumn("nombre",    DataType.VARCHAR);
        productos.addColumn("precio",    DataType.FLOAT);
        productos.addColumn("categoria", DataType.VARCHAR);
        tables.add(productos);

        // Tabla: pedidos (T-20 - tabla adicional al schema)
        Table pedidos = new Table("pedidos");
        pedidos.addColumn("id",          DataType.INT);
        pedidos.addColumn("id_usuario",  DataType.INT);
        pedidos.addColumn("id_producto", DataType.INT);
        pedidos.addColumn("cantidad",    DataType.INT);
        pedidos.addColumn("total",       DataType.FLOAT);
        tables.add(pedidos);
    }

    /**
     * Retorna lista inmutable de todas las tablas del schema.
     */
    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    /**
     * Busca una tabla por nombre de forma case-insensitive.
     * Retorna null si no existe.
     */
    public Table findTable(String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            return null;
        }
        for (Table table : tables) {
            if (table.getName().equalsIgnoreCase(tableName)) {
                return table;
            }
        }
        return null;
    }

    /**
     * Verifica si una tabla existe en el schema.
     */
    public boolean hasTable(String tableName) {
        return findTable(tableName) != null;
    }

    /**
     * Representación del schema completo para diagnóstico.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Schema del compilador SQL ===\n");
        for (Table table : tables) {
            sb.append("Tabla: ").append(table.getName()).append("\n");
            for (Column col : table.getColumns()) {
                sb.append("  - ")
                  .append(col.getName())
                  .append(" : ")
                  .append(col.getType())
                  .append("\n");
            }
        }
        return sb.toString();
    }
}