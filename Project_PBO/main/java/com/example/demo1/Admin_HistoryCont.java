package com.example.demo1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Admin_HistoryCont implements Return, Logout, Initializable {
    ObservableList<Data_read_Admin> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Data_read_Admin, String> clm_Harga;
    @FXML
    private TableView<Data_read_Admin> User_table;

    @FXML
    private Button btn_back;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_nam_pel;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_no_order;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_status;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_tgl;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_tgl_take;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_user;

    @FXML
    private ImageView logo;

    @FXML
    private Menu menu_logout;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_jasa;

    @Override
    public void Logging_out() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Anda Akan Logout");
        alert.setHeaderText("Apakah Anda Yakin ingin Logout?");


        if(alert.showAndWait().get()== ButtonType.OK){
            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene Home = start.getLoginScene();
            MainStage.setScene(Home);

        }
    }

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminHome();
        MainStage.setScene(cari);
    }

    public void Load() throws SQLException {
        Connection con = connections.GetConnection();
        String query = "SELECT no_order,username,nama_jasa,nama_peliharaan, waktu_pesan,COALESCE(waktu_diambil,'-'),Total_harga,Status FROM history_pesan p join harga_jasa j on(j.id_jasa=p.id_jasa);";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int column_count = rs.getMetaData().getColumnCount();

        if(column_count > 0) // ada data
        {
            while (rs.next())
            {
                String no_order = rs.getString(1);
                String username = rs.getString(2);
                String jasa = rs.getString(3);
                String Nama_peliharaan = rs.getString(4);
                String Tgl_pesan = rs.getString(5);
                String tgl_ambil = rs.getString(6);
                String harga = rs.getString(7);
                String Status = rs.getString(8);
                data.add(new Data_read_Admin(no_order, username,jasa,Nama_peliharaan,Tgl_pesan,tgl_ambil,harga,Status));
                User_table.setItems(data);
            }
            con.close();
        }
    }

    public void refresh() throws SQLException {
        Load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            clm_no_order.setCellValueFactory(new PropertyValueFactory<>("id"));
            clm_user.setCellValueFactory(new PropertyValueFactory<>("user"));
            clm_jasa.setCellValueFactory(new PropertyValueFactory<>("Jasa"));
            clm_nam_pel.setCellValueFactory(new PropertyValueFactory<>("Peliharaan"));
            clm_tgl.setCellValueFactory(new PropertyValueFactory<>("Tgl_pesan"));
            clm_tgl_take.setCellValueFactory(new PropertyValueFactory<>("tgl_ambil"));
            clm_Harga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
            clm_status.setCellValueFactory(new PropertyValueFactory<>("Status"));


        try {
            Load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    protected void onClickrefresh() throws SQLException {
        for (int i = 0; i < User_table.getItems().size(); i++) {
            User_table.getItems().clear();
        }
        Load();
    }
}
