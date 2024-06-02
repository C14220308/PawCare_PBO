package com.example.demo1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class User_HomeCont extends LoginCont implements Return, Logout, Initializable {

    ObservableList<Data_Read_User> data = FXCollections.observableArrayList();

//    720, 576
    @FXML
    private TableView<Data_Read_User> User_table;

    @FXML
    private Button btn_reserv;

    @FXML
    private TableColumn<Data_Read_User,String> clm_nam_pel;

    @FXML
    private TableColumn<Data_Read_User,String> clm_waktu_take;


    @FXML
    private TableColumn<Data_Read_User, String> clm_no_order;

    @FXML
    private TableColumn<Data_Read_User, String> clm_status;

    @FXML
    private TableColumn<Data_Read_User, String> clm_tgl;

    @FXML
    private Menu menu_cancel;

    @FXML
    private Menu menu_histo;

    @FXML
    private Menu menu_logout;

    @FXML
    private TableColumn<Data_Read_User, String> clm_jasa;


    @FXML
    private Button btn_cancel;

    @FXML
    private ImageView logo;
    Image myImage  = new Image("logo.png");
    public void displayImage(){
        logo.setImage(myImage);
    }

    @FXML
    private TableColumn<Data_Read_User, String> clm_Harga;

    @FXML
    protected void onclickReserve() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene Reservasi = start.getUserReservation();
        MainStage.setScene(Reservasi);

        try {
            onclickrefresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onclickHistory() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene History = start.getUserHistory();
        MainStage.setScene(History);

        try {
            onclickrefresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    }

    public void Load() throws SQLException {
        Connection con = connections.GetConnection();
        String query = "SELECT no_order,nama_jasa,nama_peliharaan, waktu_pesan,COALESCE(waktu_diambil,'-'),Total_harga,nama_status FROM pesanan p join harga_jasa j on(j.id_jasa=p.id_jasa) join status s on(p.status=s.id_status)where p.username = '" + username+ "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int column_count = rs.getMetaData().getColumnCount();

        if(column_count > 0) // ada data
        {
            while (rs.next())
            {
                String no_order = rs.getString(1);
                String jasa = rs.getString(2);
                String Nama_peliharaan = rs.getString(3);
                String Tgl_pesan = rs.getString(4);
                String tgl_ambil = rs.getString(5);
                String harga = rs.getString(6);
                String Status = rs.getString(7);
                data.add(new Data_Read_User(no_order,jasa,Nama_peliharaan,Tgl_pesan,tgl_ambil,harga,Status));
                User_table.setItems(data);
            }
            con.close();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            clm_no_order.setCellValueFactory(new PropertyValueFactory<>("id"));
            clm_jasa.setCellValueFactory(new PropertyValueFactory<>("Jasa"));
            clm_nam_pel.setCellValueFactory(new PropertyValueFactory<>("Peliharaan"));
            clm_tgl.setCellValueFactory(new PropertyValueFactory<>("Tgl_pesan"));
            clm_waktu_take.setCellValueFactory(new PropertyValueFactory<>("tgl_ambil"));
            clm_Harga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
            clm_status.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            Load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onViewSelectedClick() throws SQLException {
        Data_Read_User selected = User_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            Alert alert = new Alert(Alert.AlertType.NONE, "Hapus Order Dengan Nomor Order: " + selected.getNRP() +  "?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete Selected");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES  ) {
                if (selected.getStatus().equalsIgnoreCase("Pesanan Diterima")) {
                    Connection con = connections.GetConnection();
                    String delete = "delete from pesanan where no_order = (?) ";
                    PreparedStatement te = con.prepareStatement(delete);
                    te.setString(1, selected.getNRP());
                    int row_inserted = te.executeUpdate();

                    if (row_inserted > 0) {
                        System.out.println("Hapus sukses");
                    } else {
                        System.out.println("Insert gagal");
                    }
                    con.close();

                    for (int i = 0; i < User_table.getItems().size(); i++) {
                        User_table.getItems().clear();
                    }
                    Load();
                }
                else {
                    Alert alert1 = new Alert(Alert.AlertType.NONE, "Order Yang dipilih sudah dikerjakan", ButtonType.OK);
                    alert1.setTitle("Error");
                    alert1.showAndWait();
                }
            }

        }

        else {
            Alert alert = new Alert(Alert.AlertType.NONE, "Tidak ada order yang dipilih", ButtonType.OK);
            alert.setTitle("Delete Selected");
            alert.showAndWait();
        }



    }

    public void onclickrefresh() throws SQLException {
        for (int i = 0; i < User_table.getItems().size(); i++) {
            User_table.getItems().clear();
        }
        Load();
    }

}
