package org.primefaces.showcase.view.test;

public class Row {
    private String name;
    private boolean checkbox;

    public Row(String name, boolean checkbox) {
        this.name = name;
        this.checkbox = checkbox;
    }

    public String getName() {
        return name;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
