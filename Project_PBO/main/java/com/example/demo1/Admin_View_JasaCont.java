package com.example.demo1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.ActionEvent;
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

public class Admin_View_JasaCont implements Logout, Return, Initializable {
    ObservableList<Data_read_search> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Data_read_search> User_table;


    @FXML
    private Button btn_back;

    @FXML
    private TableColumn<Data_read_search, String> clm_nama_jasa;

    @FXML
    private TableColumn<Data_read_search, String> clm_sum;

    @FXML
    private TableColumn<Data_read_search, String> clm_untung;

    @FXML
    private ImageView logo;

    @FXML
    private Menu menu_logout;

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
        String query = "SELECT * from harga_jasa;";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int column_count = rs.getMetaData().getColumnCount();

        if(column_count > 0) // ada data
        {
            while (rs.next())
            {
                String Hitung = rs.getString(1);
                String count = rs.getString(2);
                String untung = rs.getString(3);
                data.add(new Data_read_search(Hitung,count,untung));
                User_table.setItems(data);
            }
            con.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clm_nama_jasa.setCellValueFactory(new PropertyValueFactory<>("count"));
        clm_sum.setCellValueFactory(new PropertyValueFactory<>("untung"));
        clm_untung.setCellValueFactory(new PropertyValueFactory<>("hitung"));

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
