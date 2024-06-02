package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.*;
import java.time.temporal.ChronoUnit;

public class User_Reservation_DayCareCont extends LoginCont implements Return{



    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_pesan;

    @FXML
    private ImageView img;

    @FXML
    private DatePicker tgl_ambil;

    @FXML
    private DatePicker tgl_pesan;

    @FXML
    private TextField txt_jenis_pel;

    @FXML
    private TextField txt_nama_pel;

    @FXML
    protected void onclickPesan() throws SQLException {
        if(tgl_pesan == null || tgl_ambil==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field Kosong");
            alert.setHeaderText("Harap isi semua isian!");
            alert.showAndWait();
        }

        else {
            Connection con = connections.GetConnection();
            String Query = "Select harga_jasa from harga_jasa where nama_jasa like 'Daycare'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            int column_count = rs.getMetaData().getColumnCount();
            int harga = 0;

            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    harga = rs.getInt(1);
                }

            }
            String jasa = "Daycare";


            long daysbetween = ChronoUnit.DAYS.between(tgl_pesan.getValue(),tgl_ambil.getValue() );
            if (daysbetween < 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("Tanggal invalid");
            }
            else {
                int total = (int) (harga * daysbetween);

                String Insert = "INSERT INTO `pesanan`(`Waktu_pesan`, `Waktu_diambil`, `Nama_peliharaan`, `Total_harga`, `Jenis_anjing`, `username`, `id_jasa`, `Status`) " +
                        "VALUES (?,?,?,?,?,?,5,0)";
                PreparedStatement pe = con.prepareStatement(Insert);
                pe.setString(1, String.valueOf(tgl_pesan.getValue()));
                pe.setString(2, String.valueOf(tgl_ambil.getValue()));
                pe.setString(3, txt_nama_pel.getText());
                pe.setInt(4, total);
                pe.setString(5, txt_jenis_pel.getText());
                pe.setString(6, username);


                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Konfirmasi");
                alert2.setHeaderText("Total pesanan: " + total);
                alert2.setContentText("Apakah semua isian sudah benar?");
                alert2.showAndWait();
                if (alert2.getResult() == ButtonType.OK) {
                    pe.executeUpdate();
                    StartUp start = StartUp.getApplicationInstance();
                    Stage MainStage = start.getMainStage();
                    Scene pesan = start.getUserHome();
                    MainStage.setScene(pesan);
                    clear();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene Reservasi = start.getUserReservation();
        MainStage.setScene(Reservasi);
    }

    public void clear (){
       txt_jenis_pel.clear();
       txt_nama_pel.clear();
       tgl_ambil.getEditor().clear();
       tgl_pesan.getEditor().clear();
    }
}
