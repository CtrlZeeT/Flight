/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.DAL;

import nmjava.flight.DAL.ConnectSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import nmjava.flight.DTO.VeChuyenBay;

/**
 *
 * @author xuanluan
 */
public class VeChuyenBayDAL {

    public void getALLDaTa() {
        String sql = "select * from VeChuyenBay";

        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(sql);
            // show data
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<Object [] > getVeChuyenBay() {
       // ArrayList<VeChuyenBay> list = new ArrayList<>();
        ArrayList<Object [] > list = new ArrayList<>();
        String sql = "select * from VeChuyenBay";
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row={rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                list.add(row);
            }
            // show data  
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean insertVeChuyenBay(VeChuyenBay vcb) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO VeChuyenBay(MaChuyenBay,GiaVe,HoTen,CMND,SDT,Email,TrangThai,MaHoaDon) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vcb.MaChuyenBay);
            stmt.setInt(2, vcb.GiaVe);
            stmt.setString(3, vcb.HoTen);
            stmt.setString(4, vcb.CMND);
            stmt.setString(5, vcb.SDT);
            stmt.setString(6, vcb.Email);
            stmt.setString(7, vcb.TrangThai);
            stmt.setString(8, vcb.MaHoaDon);
            
            // Thực hiện lệnh SQL
            stmt.executeUpdate();

            // Đóng kết nối
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean updateVeChuyenBay(String MaVe) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "UPDATE VECHUYENBAY SET TrangThai = ? WHERE MaVe = ?";

        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Hủy");
            stmt.setString(2, MaVe);           
            // Thực hiện lệnh SQL
            stmt.executeUpdate();

            // Đóng kết nối
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public String getMaHoaDon(String MaVe) {
       // ArrayList<VeChuyenBay> list = new ArrayList<>();
        String sql = "select MaHoaDon from VeChuyenBay where MaVe= ?";
        String MaHoaDon="";
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, MaVe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MaHoaDon=rs.getString(1);
            }
            // show data  
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return MaHoaDon;
    }
}
