package com.example.objects;

public class IPNamesData {
    private String name;

    private boolean selected;

    public IPNamesData(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }
    
    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public boolean isSelected() {
            return selected;
    }

    public void setSelected(boolean selected) {
            this.selected = selected;
    }
}
