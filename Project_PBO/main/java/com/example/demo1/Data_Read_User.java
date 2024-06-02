package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_Read_User {
    public Data_Read_User(){}

    public Data_Read_User(String id, String Jasa, String Peliharaan, String Tgl_pesan, String tgl_ambil, String Harga, String Status ){
        this.setid(id);
        this.setJasa(Jasa);
        this.setPeliharaan(Peliharaan);
        this.setTgl_pesan(Tgl_pesan);
        this.settgl_ambil(tgl_ambil);
        this.setHarga(Harga);
        this.setStatus(Status);
    }

    private StringProperty id;
    public void setid(String value) { idProperty().set(value); }
    public String getNRP() { return idProperty().get(); }
    public StringProperty idProperty() {
        if (id == null) id = new SimpleStringProperty(this, "id");
        return id;
    }

    private StringProperty Jasa;
    public void setJasa(String value) { JasaProperty().set(value); }
    public String getJasa() { return JasaProperty().get(); }
    public StringProperty JasaProperty() {
        if (Jasa == null) Jasa = new SimpleStringProperty(this, "Jasa");
        return Jasa;
    }

    private StringProperty Tgl_pesan;
    public void setTgl_pesan(String value) { Tgl_pesanProperty().set(value); }
    public String getTgl_pesan() { return Tgl_pesanProperty().get(); }
    public StringProperty Tgl_pesanProperty() {
        if (Tgl_pesan == null) Tgl_pesan = new SimpleStringProperty(this, "Tgl_pesan");
        return Tgl_pesan;
    }

    private StringProperty Peliharaan;
    public void setPeliharaan(String value) { PeliharaanProperty().set(value); }
    public String getPeliharaan() { return PeliharaanProperty().get(); }
    public StringProperty PeliharaanProperty() {
        if (Peliharaan == null) Peliharaan = new SimpleStringProperty(this, "Peliharaan");
        return Peliharaan;
    }

    private StringProperty tgl_ambil;
    public void settgl_ambil(String value) { tgl_ambilProperty().set(value); }
    public String gettgl_ambil() { return tgl_ambilProperty().get(); }
    public StringProperty tgl_ambilProperty() {
        if (tgl_ambil == null) tgl_ambil = new SimpleStringProperty(this, "tgl_ambil");
        return tgl_ambil;
    }

    private StringProperty Harga;
    public void setHarga(String value) { HargaProperty().set(value); }
    public String getHarga() { return HargaProperty().get(); }
    public StringProperty HargaProperty() {
        if (Harga == null) Harga = new SimpleStringProperty(this, "Harga");
        return Harga;
    }

    private StringProperty Status;
    public void setStatus(String value) { StatusProperty().set(value); }
    public String getStatus() { return StatusProperty().get(); }
    public StringProperty StatusProperty() {
        if (Status == null) Status = new SimpleStringProperty(this, "Status");
        return Status;
    }




}
