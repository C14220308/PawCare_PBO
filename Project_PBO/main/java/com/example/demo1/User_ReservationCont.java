package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class User_ReservationCont implements Return{
    @FXML
    private Button btn_daycr;

    @FXML
    private Button btn_salon;

    @FXML
    private ImageView img_dog;
    Image myImage  = new Image("Dog.png");
    public void displaydog(){
        img_dog.setImage(myImage);
    }

    @FXML
    private ImageView img_house;
    Image myImage2  = new Image("House.png");
    public void displayhouse(){
        img_house.setImage(myImage2);
    }

    @FXML
    protected void onclickDayCare() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene pesan = start.getUserReservationDaycare();
        MainStage.setScene(pesan);
    }

    @FXML
    protected void onclickSalon() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene pesan = start.getUserReservationSalon();
        MainStage.setScene(pesan);
    }

    @Override
    public void onBackPressed() {

    }
}
