package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_read_all extends Data_Read_User{

    public Data_read_all(String id, String Jasa, String Peliharaan, String Tgl_pesan, String tgl_ambil, String Harga, String Status, String jenis, String Berat ){
        this.setid(id);
        this.setJasa(Jasa);
        this.setPeliharaan(Peliharaan);
        this.setTgl_pesan(Tgl_pesan);
        this.settgl_ambil(tgl_ambil);
        this.setjenis(jenis);
        this.setberat(Berat);
        this.setHarga(Harga);
        this.setStatus(Status);
    }
    private StringProperty berat;
    public void setberat(String value) { beratProperty().set(value); }
    public String getberat() { return beratProperty().get(); }
    public StringProperty beratProperty() {
        if (berat == null) berat = new SimpleStringProperty(this, "berat");
        return berat;
    }
    private StringProperty jenis;
    public void setjenis(String value) { jenisProperty().set(value); }
    public String getjenis() { return jenisProperty().get(); }
    public StringProperty jenisProperty() {
        if (jenis == null) jenis = new SimpleStringProperty(this, "jenis");
        return jenis;
    }
}
