package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_read_admin_view extends Data_read_search {
    private StringProperty nama;

    public Data_read_admin_view(String count, String untung, String hitung,String nama) {
        super(count, untung, hitung);
        this.setnama(nama);
    }

    public void setnama(String value) { namaProperty().set(value); }
    public String getnama() { return namaProperty().get(); }
    public StringProperty namaProperty() {
        if (nama == null) nama = new SimpleStringProperty(this, "nama");
        return nama;
    }
}
