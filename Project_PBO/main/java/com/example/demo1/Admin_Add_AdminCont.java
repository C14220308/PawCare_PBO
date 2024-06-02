package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin_Add_AdminCont implements Return {
    @FXML
    private Button Cancel;

    @FXML
    private TextField Nama_input;

    @FXML
    private Button daftar_btn;

    @FXML
    private ImageView img;

    @FXML
    private PasswordField new_pass_input;

    @FXML
    private TextField new_user_input;

    @FXML
    private TextField telp_input;

    @FXML
    protected void onclickAdd() throws SQLException {
        String Nama = Nama_input.getText();
        String telp = telp_input.getText();
        String username = new_user_input.getText();
        String pass = new_pass_input.getText();

        Connection con = connections.GetConnection();
        String query = "INSERT INTO `users`(`Username`, `NoTelp`, `Password`, `Tipe_user`, `Nama`) " +
                "VALUES (?,?,?,'admin',?)";
        PreparedStatement pe = con.prepareStatement(query);
        pe.setString(1,username);
        pe.setString(2,telp);
        pe.setString(3,pass);
        pe.setString(4,Nama);




        int row_inserted = pe.executeUpdate();
        if(row_inserted>0){
            System.out.println("Insert sukses");
        }
        else{
            System.out.println("Insert gagal");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi!");
        alert.setHeaderText("Apakah Anda Yakin ingin menambahkan admin?");


        if(alert.showAndWait().get()== ButtonType.OK){
            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene cari = start.getAdminHome();
            MainStage.setScene(cari);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Admin Ditambahkan");
            alert.setHeaderText("Admin berhasil ditambahkan");
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
}
