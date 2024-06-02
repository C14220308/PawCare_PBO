package com.example.demo1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StartUp extends Application {

    private Stage MainStage;
    private Scene LoginScene;
    private LoginCont loginCont;

    private Scene UpdateScene;
    private Admin_Update_Status adminUpdateStatus;

    private Scene SignUpScene;
    private SignUpCont signUpCont;

    private Scene UserHome;
    private User_HomeCont userhomeCont;

    private Scene UserHistory;
    private User_HistoryCont historyCont;

    private Scene UserReservation;
    private User_ReservationCont userReservationCont;

    private Scene UserReservationDaycare;
    private User_Reservation_DayCareCont userReservationDayCareCont;

    private Scene UserReservationSalon;
    private User_Reservation_SalonCont reservationSalonCont;

    private Scene UserConfirmation;


    private Scene AdminHome;
    private Admin_HomeCont adminHomeCont;

    private Scene AdminHistory;
    private Admin_HistoryCont admin_historyCont;

    private Scene AdminAddAdmin;
    private Admin_Add_AdminCont adminAddAdminCont;

    private Scene AdminEditHarga;
    private Admin_Edit_HargaCont adminEditHargaCont;

    private Scene AdminSearchBulan;
    private Admin_Search_BulanCont adminSearchBulanCont;

    private Scene AdminSearchJasa;
    private Admin_Search_JasaCont adminSearchJasaCont;

    private Scene AdminSearchMember;
    private Admin_Search_MemberCont adminSearchMemberCont;

    private Scene AdminViewJasa;
    private Admin_View_JasaCont admin_jasa;

    private Scene adminViewMember;
    private Admin_View_MemberCont admin_member;

    public StartUp(){applicationInstance = this;}

    private static StartUp applicationInstance;
    public static  StartUp getApplicationInstance(){return  applicationInstance;}

    public Scene getAdminViewJasa() {
        return AdminViewJasa;
    }

    public Scene getAdminViewMember() {
        return adminViewMember;
    }



    public Admin_View_JasaCont getAdmin_jasa() {
        return admin_jasa;
    }

    public Stage getMainStage() {
        return MainStage;
    }

    public Scene getLoginScene() {
        return LoginScene;
    }

    public LoginCont getLoginCont() {
        return loginCont;
    }

    public Scene getSignUpScene() {
        return SignUpScene;
    }

    public SignUpCont getSignUpCont() {
        return signUpCont;
    }

    public Scene getUserHome() {
        return UserHome;
    }

    public User_HomeCont getUserhomeCont() {
        return userhomeCont;
    }

    public Scene getUserHistory() {
        return UserHistory;
    }

    public User_HistoryCont getHistoryCont() {
        return historyCont;
    }

    public Scene getUserReservation() {
        return UserReservation;
    }

    public User_ReservationCont getUserReservationCont() {
        return userReservationCont;
    }

    public Scene getUserReservationDaycare() {
        return UserReservationDaycare;
    }

    public User_Reservation_DayCareCont getUserReservationDayCareCont() {
        return userReservationDayCareCont;
    }

    public Scene getUserReservationSalon() {
        return UserReservationSalon;
    }

    public User_Reservation_SalonCont getReservationSalonCont() {
        return reservationSalonCont;
    }

    public Scene getUserConfirmation() {
        return UserConfirmation;
    }



    public Scene getAdminHome() {
        return AdminHome;
    }

    public Admin_HomeCont getAdminHomeCont() {
        return adminHomeCont;
    }

    public Scene getAdminHistory() {
        return AdminHistory;
    }

    public Admin_HistoryCont getAdmin_historyCont() {
        return admin_historyCont;
    }

    public Scene getAdminAddAdmin() {
        return AdminAddAdmin;
    }

    public Admin_Add_AdminCont getAdminAddAdminCont() {
        return adminAddAdminCont;
    }

    public Scene getAdminEditHarga() {
        return AdminEditHarga;
    }

    public Admin_Edit_HargaCont getAdminEditHargaCont() {
        return adminEditHargaCont;
    }

    public Scene getAdminSearchBulan() {
        return AdminSearchBulan;
    }

    public Admin_Search_BulanCont getAdminSearchBulanCont() {
        return adminSearchBulanCont;
    }

    public Scene getAdminSearchJasa() {
        return AdminSearchJasa;
    }

    public Admin_Search_JasaCont getAdminSearchJasaCont() {
        return adminSearchJasaCont;
    }

    public Scene getUpdateScene() {
        return UpdateScene;
    }

    public Admin_Update_Status getAdminUpdateStatus() {
        return adminUpdateStatus;
    }

    public Scene getAdminSearchMember() {
        return AdminSearchMember;
    }

    public Admin_Search_MemberCont getAdminSearchMemberCont() {
        return adminSearchMemberCont;
    }

    public static void database() throws SQLException, ClassNotFoundException {
        Connection con = connections.GetConnection();
        String query = "Select * from users";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            System.out.println("Username: " + rs.getString(1));
            System.out.println("No Telp: " + rs.getString(2));
            System.out.println("Password: " + rs.getString(3));
            System.out.println("Tipe_user: " + rs.getString(4));
            System.out.println("Nama: " + rs.getString(5));
        }
        con.close();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.MainStage = stage;
        MainStage.setTitle("Paw's DayCare and Salon");

        try {
            database();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Login.fxml"));
        LoginScene = new Scene(fxmlLoader.load(), 600, 400);
        loginCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/SignUp.fxml"));
        SignUpScene = new Scene(fxmlLoader.load(), 600, 400);
        signUpCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/User_Home.fxml"));
        UserHome = new Scene(fxmlLoader.load(), 1100, 576);
        userhomeCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/User_Reservation.fxml"));
        UserReservation = new Scene(fxmlLoader.load(), 600, 400);
        userReservationCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/User_Reservation_DayCare.fxml"));
        UserReservationDaycare = new Scene(fxmlLoader.load(), 600, 400);
        userReservationDayCareCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/User_Reservation_Salon.fxml"));
        UserReservationSalon = new Scene(fxmlLoader.load(), 600, 400);
        reservationSalonCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/User_History.fxml"));
        UserHistory = new Scene(fxmlLoader.load(), 1100, 576);
        historyCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Edit_Harga.fxml"));
        AdminEditHarga = new Scene(fxmlLoader.load(), 600, 400);
        adminEditHargaCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Update_Status.fxml"));
        UpdateScene = new Scene(fxmlLoader.load(), 600, 400);
        adminUpdateStatus = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_AddAdmin.fxml"));
        AdminAddAdmin = new Scene(fxmlLoader.load(), 600, 400);
        adminAddAdminCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_History.fxml"));
        AdminHistory = new Scene(fxmlLoader.load(), 1100, 576);
        admin_historyCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Home.fxml"));
        AdminHome = new Scene(fxmlLoader.load(), 1100, 576);
        adminHomeCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Search_Bulan.fxml"));
        AdminSearchBulan = new Scene(fxmlLoader.load(), 800, 576);
        adminSearchBulanCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_View_Jasa.fxml"));
        AdminViewJasa = new Scene(fxmlLoader.load(), 800, 576);


        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_View_Member.fxml"));
        adminViewMember = new Scene(fxmlLoader.load(), 800, 576);

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Search_Jasa.fxml"));
        AdminSearchJasa = new Scene(fxmlLoader.load(), 800, 576);
        adminSearchJasaCont = fxmlLoader.getController();

        fxmlLoader = new FXMLLoader(StartUp.class.getResource("/Admin_Search_Member.fxml"));
        AdminSearchMember = new Scene(fxmlLoader.load(), 800, 576);
        adminSearchMemberCont = fxmlLoader.getController();

        stage.setScene(LoginScene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}