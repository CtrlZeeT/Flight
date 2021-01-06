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

/**
 *
 * @author ExpectedSemicolon
 */
public class ThamSoDAL {
    
    public int getTienLaiMoiVe() {
        String sql = "select GiaTri from ThamSo where TenThamSo = 'TienLaiMoiVe'";
        int tmp = 0; 
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'ThamSo'
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            tmp = rs.getInt("GiaTri");
            rs.close();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmp;
    }

    public void setTienLaiMoiVe(int tienLai) {
        String sql = "Update ThamSo Set GiaTri = ? where TenThamSo = 'TienLaiMoiVe'";

        try {
            Connection conn = ConnectSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,tienLai);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int getTienHuyVe() {
        String sql = "select GiaTri from ThamSo where TenThamSo = 'ChiPhiHuyVe'";
        int tmp = 0;       
        try {
            Connection conn = ConnectSQLServer.getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'ThamSo'
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            tmp = rs.getInt("GiaTri");
            rs.close();
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmp;
    }
    
    public void setTienHuyVe(int tienHuyVe) {
        String sql = "Update ThamSo Set GiaTri = ? where TenThamSo = 'ChiPhiHuyVe'";

        try {
            Connection conn = ConnectSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,tienHuyVe);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
