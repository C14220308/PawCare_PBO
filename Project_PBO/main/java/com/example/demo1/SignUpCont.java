package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpCont implements Return{
    @FXML
    private ImageView img;

    Image myImage  = new Image("logo.png");
    public void displayImage(){
        img.setImage(myImage);
    }

    @FXML
    private Button Cancel;

    @FXML
    private TextField Nama_input;

    @FXML
    private Button daftar_btn;

    @FXML
    private PasswordField new_pass_input;

    @FXML
    private TextField new_user_input;

    @FXML
    private TextField telp_input;

    @FXML
    protected void onclickSignUp() throws SQLException {
        Connection con = null;
        try {
            con = connections.GetConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String Insert = "INSERT INTO `users`(`Username`, `NoTelp`, `Password`,  `Nama`,`Tipe_user`) " +
                "VALUES (?,?,?,?,'member')";
        PreparedStatement pe = con.prepareStatement(Insert);
        pe.setString(4, Nama_input.getText());
        pe.setString(2, telp_input.getText());
        pe.setString(1, new_user_input.getText());
        pe.setString(3, new_pass_input.getText());


        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Konfirmasi");
        alert2.setContentText("Apakah semua isian sudah benar?");
        alert2.showAndWait();
        if(alert2.getResult()== ButtonType.OK) {
            pe.executeUpdate();
            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene login = start.getLoginScene();
//        Scene home = start.getAdminHome();
            MainStage.setScene(login);
            clear();
        }

    }

    @Override
    public void onBackPressed() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene login = start.getLoginScene();
//        Scene home = start.getAdminHome();
        MainStage.setScene(login);
    }


    public void clear(){
        Nama_input.clear();
        telp_input.clear();
        new_pass_input.clear();
        new_pass_input.clear();
    }
}
