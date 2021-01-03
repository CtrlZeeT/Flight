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

    public boolean insertData(VeChuyenBay vcb) {
        // Tạo câu lệnh SQL (Cách 2: sử dụng PreparedStatement)
        String sql = "INSERT INTO VeChuyenBay(NoiDi,NoiDen,GiaVe,HoTen,CMND,SDT,Email,TrangThai,MaHoaDon) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        // Kết nối database
        try {
            Connection conn = ConnectSQLServer.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vcb.getNoiDi());
            stmt.setString(2, vcb.getNoiDen());

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
