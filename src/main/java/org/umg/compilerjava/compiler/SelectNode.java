package org.umg.compilerjava.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SelectNode extends StatementNode {

    private boolean selectAll;
    private String tableName = "";
    private final List<String> columns = new ArrayList<String>();
    private ConditionNode whereCondition;

    public boolean isSelectAll() {
        return selectAll;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public void addColumn(String column) {
        columns.add(column);
    }

    public ConditionNode getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(ConditionNode whereCondition) {
        this.whereCondition = whereCondition;
    }
}
