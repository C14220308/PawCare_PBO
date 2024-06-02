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
import java.sql.*;
import java.util.ResourceBundle;


public class Admin_HomeCont implements Return, Logout, Initializable {

    ObservableList<Data_read_Admin> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Data_read_Admin, String> clm_harga;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_waktu_take;
    @FXML
    private TableColumn<Data_read_Admin, String> clm_status1;
    @FXML
    private TableView<Data_read_Admin> User_table;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_nam_pel;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_no_order;


    @FXML
    private TableColumn<Data_read_Admin, String> clm_tgl;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_user;

    @FXML
    private ImageView logo;

    @FXML
    private MenuItem menu_add_adm;

    @FXML
    private MenuItem menu_edt_status;

    @FXML
    private Menu menu_histo;

    @FXML
    private Menu menu_logout;

    @FXML
    private MenuItem menu_src_bln;

    @FXML
    private MenuItem menu_src_jasa;

    @FXML
    private MenuItem menu_src_member;

    @FXML
    private TableColumn<Data_read_Admin, String> clm_tgl_take;
    @FXML
    private TableColumn<Data_read_Admin, String> clm_jasa;


    public Admin_HomeCont() {
    }

    @FXML
    protected void onclickSearchBulan() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene bulan = start.getAdminSearchBulan();
        MainStage.setScene(bulan);
    }

    @FXML
    protected void onclickSearchJasa() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminSearchJasa();
        MainStage.setScene(cari);
    }

    @FXML
    protected void onclickSearchMember() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminSearchMember();
        MainStage.setScene(cari);

    }

    @FXML
    protected void onclickHistory() throws SQLException {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminHistory();
        MainStage.setScene(cari);
    }

    @FXML
    protected void onclickEdit() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminEditHarga();
        MainStage.setScene(cari);
    }

    @FXML
    protected void onclickAdd() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminAddAdmin();
        MainStage.setScene(cari);
    }

    @Override
    public void Logging_out() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Anda Akan Logout");
        alert.setHeaderText("Apakah Anda Yakin ingin Logout?");


        if (alert.showAndWait().get() == ButtonType.OK) {
            StartUp start = StartUp.getApplicationInstance();
            Stage MainStage = start.getMainStage();
            Scene Home = start.getLoginScene();
            MainStage.setScene(Home);
        }

    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clm_no_order.setCellValueFactory(new PropertyValueFactory<>("id"));
        clm_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        clm_jasa.setCellValueFactory(new PropertyValueFactory<>("Jasa"));
        clm_nam_pel.setCellValueFactory(new PropertyValueFactory<>("Peliharaan"));
        clm_tgl.setCellValueFactory(new PropertyValueFactory<>("Tgl_pesan"));
        clm_waktu_take.setCellValueFactory(new PropertyValueFactory<>("tgl_ambil"));
        clm_harga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        clm_status1.setCellValueFactory(new PropertyValueFactory<>("Status"));

        try {
            Load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void Load() throws SQLException {
        Connection con = connections.GetConnection();
        String query = "SELECT no_order,username,nama_jasa,nama_peliharaan, waktu_pesan,COALESCE(waktu_diambil,'-'),Total_harga,nama_status FROM pesanan p join harga_jasa j on(j.id_jasa=p.id_jasa) join status s on(p.status=s.id_status);";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        int column_count = rs.getMetaData().getColumnCount();

        if (column_count > 0) // ada data
        {
            while (rs.next()) {
                String no_order = rs.getString(1);
                String username = rs.getString(2);
                String jasa = rs.getString(3);
                String Nama_peliharaan = rs.getString(4);
                String Tgl_pesan = rs.getString(5);
                String tgl_ambil = rs.getString(6);
                String harga = rs.getString(7);
                String Status = rs.getString(8);
                data.add(new Data_read_Admin(no_order, username, jasa, Nama_peliharaan, Tgl_pesan, tgl_ambil, harga, Status));
                User_table.setItems(data);
            }
            con.close();
        }
    }

    @FXML
    protected void onViewSelectedClick() throws SQLException {
        Data_Read_User selected = User_table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Hapus Order Dengan Nomor Order: " + selected.getNRP() + "?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Delete Selected");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (selected.getStatus().equalsIgnoreCase("Pesanan Selesai")) {
                    Connection con = connections.GetConnection();
                    String select = "select * from pesanan where no_order = " + selected.getNRP();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(select);
                    int column_count = rs.getMetaData().getColumnCount();
                    String no_order = null;
                    String Tgl_pesan = null;
                    String tgl_ambil = null;
                    String Nama_peliharaan = null;
                    String harga = null;
                    String berat = null;
                    String jenis = null;
                    String username = null;
                    String jasa = null;
                    String Status = null;


                    if (column_count > 0) // ada data
                    {
                        while (rs.next()) {
                            no_order = rs.getString(1);
                            Tgl_pesan = rs.getString(2);
                            tgl_ambil = rs.getString(3);
                            Nama_peliharaan = rs.getString(4);
                            harga = rs.getString(5);
                            berat = rs.getString(6);
                            jenis = rs.getString(7);
                            username = rs.getString(8);
                            jasa = rs.getString(9);
                            Status = rs.getString(10);

                        }

                        String queryupdate = "insert into history_pesan values(?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pe = con.prepareStatement(queryupdate);
                        pe.setString(1, no_order);
                        pe.setString(2, Tgl_pesan);
                        pe.setString(3, tgl_ambil);
                        pe.setString(4, Nama_peliharaan);
                        pe.setString(5, harga);
                        pe.setString(6, berat);
                        pe.setString(7, jenis);
                        pe.setString(8, username);
                        pe.setString(9, jasa);
                        pe.setString(10, Status);

                        pe.executeUpdate();

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
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.NONE, "Order Yang dipilih sedang dikerjakan", ButtonType.OK);
                    alert1.setTitle("Error");
                    alert1.showAndWait();
                }

            }


        } else {
            Alert alert2 = new Alert(Alert.AlertType.NONE, "Tidak ada order yang dipilih", ButtonType.OK);
            alert2.setTitle("Delete Selected");
            alert2.showAndWait();
        }
    }

    @FXML
    protected void onClickrefresh() throws SQLException {
        for (int i = 0; i < User_table.getItems().size(); i++) {
            User_table.getItems().clear();
        }
        Load();
    }

    @FXML
    protected void onclickUpdate() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getUpdateScene();
        MainStage.setScene(cari);
    }
    @FXML
    protected void onclickViewJasa() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminViewJasa();
        MainStage.setScene(cari);
    }
    @FXML
    protected void onclickviewmember() {
        StartUp start = StartUp.getApplicationInstance();
        Stage MainStage = start.getMainStage();
        Scene cari = start.getAdminViewMember();
        MainStage.setScene(cari);
    }

}

