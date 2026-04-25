package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CreateTableNode extends StatementNode {

    private String tableName = "";
    private final List<ColumnDefinition> columns = new ArrayList<ColumnDefinition>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnDefinition> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public void addColumn(String columnName, DataType type) {
        columns.add(new ColumnDefinition(columnName, type));
    }

    public static final class ColumnDefinition {
        private final String name;
        private final DataType type;

        public ColumnDefinition(String name, DataType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public DataType getType() {
            return type;
        }
    }
}