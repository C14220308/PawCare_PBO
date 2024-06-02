package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_read_search {
    public Data_read_search(String count, String untung, String hitung) {
        this.setcount(count);
        this.setuntung(untung);
        this.setHitung(hitung);
    }

    private StringProperty count;
    public void setcount(String value) { countProperty().set(value); }
    public String getcount() { return countProperty().get(); }
    public StringProperty countProperty() {
        if (count == null) count = new SimpleStringProperty(this, "count");
        return count;
    }

    private StringProperty untung;
    public void setuntung(String value) { untungProperty().set(value); }
    public String getuntung() { return untungProperty().get(); }
    public StringProperty untungProperty() {
        if (untung == null) untung = new SimpleStringProperty(this, "untung");
        return untung;
    }

    private StringProperty Hitung;
    public void setHitung(String value) { HitungProperty().set(value); }
    public String getHitung() { return HitungProperty().get(); }
    public StringProperty HitungProperty() {
        if (Hitung == null) Hitung = new SimpleStringProperty(this, "Hitung");
        return Hitung;
    }
}
