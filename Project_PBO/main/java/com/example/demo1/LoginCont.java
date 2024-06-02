package com.example.demo1;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.*;

//600, 400
public class LoginCont {

    Admin_HomeCont AH = new Admin_HomeCont();

    @FXML
    private ImageView img;
    Image myImage  = new Image("logo.png");
    public void displayImage(){
        img.setImage(myImage);
    }

    @FXML
    private Button log_btn;

    @FXML
    private PasswordField pass_input;

    @FXML
    private Button sign_btn;

    @FXML
    private TextField user_input;

    public static String username;

    @FXML
    protected void onclickSignUP() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene signup = start.getSignUpScene();
        MainStage.setScene(signup);
    }
    @FXML
    protected void onclickLogIn() throws SQLException {
        Login();
    }

    public void Login() throws SQLException {
        String password="";
        String tipe_users="";
        Connection con = connections.GetConnection();
        String querry = "Select password, tipe_user from users where Username = '" + user_input.getText() + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(querry);

        while (rs.next()){
            password=rs.getString(1);
            tipe_users=rs.getString(2);
        }

        System.out.println(password);
        System.out.println(tipe_users);

        if (password.equals(pass_input.getText()) && tipe_users.equalsIgnoreCase("Admin")){

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Welcome");
            alert2.setContentText("Selamat Datang!" + user_input.getText() );
            alert2.showAndWait();

            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene home = start.getAdminHome();
            MainStage.setScene(home);

            clear();

        }
        else if(password.equals(pass_input.getText()) && tipe_users.equalsIgnoreCase("Member")){
            username = user_input.getText();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Welcome");
            alert2.setContentText("Selamat Datang " + user_input.getText() + " !"  );
            alert2.showAndWait();

            System.out.println(username);



            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene home = start.getUserHome();
            MainStage.setScene(home);

            clear();



        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("INVALID");
            alert2.setContentText("Username atau password salah");
            alert2.showAndWait();
        }
    }
    public void clear(){
        pass_input.clear();
        user_input.clear();
    }


}