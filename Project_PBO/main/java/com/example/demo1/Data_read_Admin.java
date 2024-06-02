package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data_read_Admin extends Data_Read_User {

    public Data_read_Admin(String id, String User, String Jasa, String Peliharaan, String Tgl_pesan, String tgl_ambil, String Harga, String Status){
        this.setid(id);
        this.setuser(User);
        this.setJasa(Jasa);
        this.setPeliharaan(Peliharaan);
        this.setTgl_pesan(Tgl_pesan);
        this.settgl_ambil(tgl_ambil);
        this.setHarga(Harga);
        this.setStatus(Status);

    }

    private StringProperty user;
    public void setuser(String value) { userProperty().set(value); }
    public String getuser() { return userProperty().get(); }
    public StringProperty userProperty() {
        if (user == null) user = new SimpleStringProperty(this, "user");
        return user;
    }


}
