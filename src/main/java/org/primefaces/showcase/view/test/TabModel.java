package org.primefaces.showcase.view.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabModel {
    private String name;
    private List<Row> rows;

    public TabModel(String name, Row... rows) {
        this.name = name;
        this.rows = new ArrayList<Row>(rows.length);
        Collections.addAll(this.rows, rows);
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }

}
