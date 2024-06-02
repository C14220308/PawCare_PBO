package com.example.demo1;


import javafx.event.ActionEvent;
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

public class Admin_Update_Status implements Return, Initializable {
    @FXML
    private Button btn_cancel;
    @FXML
    private ChoiceBox<String> list_status;

    @FXML
    private Button btn_pesan;

    @FXML
    private ImageView img;

    @FXML
    private TextField txt_jenis_pel;

    @FXML
    private TextField txt_nama_pel;

    @FXML
    protected void onclickEdit() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi!");
        alert.setHeaderText("Apakah Anda Yakin Ingin Mengganti Harga?");


        if (alert.showAndWait().get() == ButtonType.OK) {
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

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminHome();
        MainStage.setScene(cari);
    }

    protected ArrayList<String> getlistStatus() throws SQLException {
        ArrayList<String> status = new ArrayList<>();
        Connection con = connections.GetConnection();
        String Query = "Select nama_status from status";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(Query);

        while (rs.next()) {
            status.add(rs.getString(1));
        }
            con.close();
        return status;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list_status.getItems().addAll(getlistStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void Update(ActionEvent e) throws SQLException {
        Connection con = connections.GetConnection();
        if(e.getSource()==btn_pesan) {
            String check = list_status.getValue();
            int value;

            if (check.equalsIgnoreCase("Pesanan Diterima")) {
                value = 0;
            } else if (check.equalsIgnoreCase("Hewan Peliharaan Sedang Dirawat")) {
                value = 1;
            } else if (check.equalsIgnoreCase("Hewan Peliharaan Siap dijemput")) {
                value = 2;
            } else {
                value = 3;
            }

            String query = "UPDATE pesanan set status = ? where no_order = ?" ;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, value);
            ps.setString(2,txt_nama_pel.getText());
            ps.executeUpdate();



                StartUp start = StartUp.getApplicationInstance();
                Stage MainStage = start.getMainStage();
                Scene cari = start.getAdminHome();
                MainStage.setScene(cari);

        }
    }
}


