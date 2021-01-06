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
                list.add(new HoaDon(rs.getString(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5)));
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
        String result;
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery(sql);
            result=rs.getString(1);
            // show data  
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
    public boolean insertHoaDon(HoaDon HD) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO HoaDon(SoVe,TongTien,ThoiGianTao,TienLai) "
                + "VALUES(?,?,?,?)";

        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, HD.SoVe);
            stmt.setDouble(2, HD.TongTien);
            stmt.setDate(3, HD.ThoiGianTao);
            stmt.setDouble(4, HD.TienLai);
            
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
}
