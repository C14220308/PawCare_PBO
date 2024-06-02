package com.example.demo1;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Admin_Edit_HargaCont implements Return, Initializable {
    @FXML
    private Button btn_cancel;

    @FXML
    private TextField txt_jenis_pel;

    @FXML
    private Button btn_pesan;

    @FXML
    private ImageView img;

    @FXML
    private ChoiceBox<String> List_jasa;

    @FXML
    private TextField txt_nama_pel;
    @FXML
    protected void onclickEdit() throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi!");
        alert.setHeaderText("Apakah Anda Yakin Ingin Mengganti Harga?");

        if(alert.showAndWait().get()== ButtonType.OK){
            Connection con = connections.GetConnection();

            int harga = Integer.parseInt(txt_jenis_pel.getText());

            String query = "UPDATE harga_jasa set harga_jasa = ? where nama_jasa like ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, harga);
            ps.setString(2,List_jasa.getValue());
            int row_inserted = ps.executeUpdate();

            if (row_inserted > 0) {
                System.out.println("Hapus sukses");
            } else {
                System.out.println("Insert gagal");
            }
            con.close();

            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene cari = start.getAdminHome();
            MainStage.setScene(cari);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Harga Diganti");
            alert.setHeaderText("Pergantian Harga Sukses!");
            alert.showAndWait();

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
        return status;
    }

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminHome();
        MainStage.setScene(cari);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List_jasa.getItems().addAll(getlistStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
