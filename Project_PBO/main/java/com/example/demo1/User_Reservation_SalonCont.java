package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class User_Reservation_SalonCont extends LoginCont implements Return, Initializable {

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_pesan;

    @FXML
    private ChoiceBox<String> choice_jasa;

    @FXML
    private ImageView img;

    @FXML
    private DatePicker tgl_pesan;

    @FXML
    private TextField txt_berat_pel;

    @FXML
    private TextField txt_jenis_pel;

    @FXML
    private TextField txt_nama_pel;

    @FXML
    protected void onclickPesan() throws SQLException {
        if(tgl_pesan == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Field Kosong");
            alert.setHeaderText("Harap isi semua isian!");
            alert.showAndWait();
        }

        else {
            Connection con = connections.GetConnection();
            String Query = "Select harga_jasa,id_jasa from harga_jasa where nama_jasa = '" + choice_jasa.getValue()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            int column_count = rs.getMetaData().getColumnCount();
            int harga = 0;
            int id=0;

            if (column_count > 0) // ada data
            {
                while (rs.next()) {
                    harga = rs.getInt(1);
                    id = rs.getInt(2);
                }

            }

            int berat = Integer.parseInt(txt_berat_pel.getText());

            Check_berat();

            int total = (int) (harga + bonus);
            System.out.println(username);

            String Insert = "INSERT INTO `pesanan`(`Waktu_pesan`,`Nama_peliharaan`,`Total_harga`,`Berat_anjing`, `Jenis_anjing`,`username`, `id_jasa`, `Status`) " +
                    "VALUES (?,?,?,?,?,?,?,0)";
            PreparedStatement pe = con.prepareStatement(Insert);
            pe.setString(1, String.valueOf(tgl_pesan.getValue()));
            pe.setString(2, txt_nama_pel.getText());
            pe.setInt(3, total);
            pe.setInt(4, Integer.parseInt(txt_berat_pel.getText()));
            pe.setString(5, txt_jenis_pel.getText());
            pe.setString(6, username);
            pe.setInt(7,id);



            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Konfirmasi");
            alert2.setHeaderText("Total pesanan: " + total);
            alert2.setContentText("Apakah semua isian sudah benar?");
            alert2.showAndWait();
            if(alert2.getResult()== ButtonType.OK) {
                pe.executeUpdate();
                StartUp start = StartUp.getApplicationInstance();
                Stage MainStage = start.getMainStage();
                Scene pesan = start.getUserHome();
                MainStage.setScene(pesan);
                clear();
            }
        }
    }
    protected ArrayList<String> getlistStatus() throws SQLException {
        ArrayList<String> status = new ArrayList<>();
        Connection con = connections.GetConnection();
        String Query = "Select nama_jasa from harga_jasa";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(Query);

        while (rs.next()) {
            status.add(rs.getString(1));
        }
        con.close();
        status.remove(4);
        return status;
    }

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene Reservasi = start.getUserReservation();
        MainStage.setScene(Reservasi);
    }
    protected int bonus;
    protected void Check_berat(){
        if(Integer.parseInt(txt_berat_pel.getText())< 10){
            bonus = 0;
        }
        else if(Integer.parseInt(txt_berat_pel.getText())>=10 ||Integer.parseInt(txt_berat_pel.getText())<22 ){
            bonus = 10000;
        }
        else{
            bonus = 15000;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            choice_jasa.getItems().addAll(getlistStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear (){
        txt_jenis_pel.clear();
        txt_nama_pel.clear();
        txt_berat_pel.clear();
        tgl_pesan.getEditor().clear();
    }
}
