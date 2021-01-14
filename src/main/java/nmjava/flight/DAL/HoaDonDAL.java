/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import nmjava.flight.DTO.HoaDon;

/**
 *
 * @author Cuong
 */
public class HoaDonDAL {
    public ArrayList<HoaDon> getHoaDonWithMonthAndYear(String month, String year) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select * from HOADON where MONTH(ThoiGianTao)="+month+" and YEAR(ThoiGianTao)=" + year;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new HoaDon(rs.getString(1), rs.getInt(3), rs.getInt(2), rs.getInt(4), rs.getString(5)));
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
    public String getMaHoaDon() {
        String sql = "select dbo.AUTO_IDHD()";
        String result= new String();
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result=rs.getString(1);
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
    public boolean insertHoaDon(HoaDon HD) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO HoaDon(MaHoaDon,SoVe,TongTien,ThoiGianTao,TienLai) "
                + "VALUES(?,?,?,?,?)";

        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println(HD.MaHoaDon+"\n"+HD.SoVe+"\n"+HD.TongTien+"\n"+HD.ThoiGianTao+"\n"+HD.TienLai);
            stmt.setString(1, HD.MaHoaDon);
            stmt.setInt(2, HD.SoVe);
            stmt.setDouble(3, HD.TongTien);
            stmt.setString(4, HD.ThoiGianTao);
            stmt.setDouble(5, HD.TienLai);
            
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
    public boolean updateHoaDon(String MaHoaDon,int TienHuy) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "UPDATE HoaDon SET TongTien = TongTien - ? WHERE MaHoaDon = ? ";
        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);           
            // Thực hiện lệnh SQL
            stmt.setInt(1, TienHuy);
            stmt.setString(2, MaHoaDon);
            stmt.executeUpdate();
            // Đóng kết nối
            conn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
