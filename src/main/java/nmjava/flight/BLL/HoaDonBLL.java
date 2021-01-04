/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import nmjava.flight.DAL.HoaDonDAL;
import nmjava.flight.DTO.HoaDon;

/**
 *
 * @author Cuong
 */
public class HoaDonBLL {
    HoaDonDAL dalHoaDon;
    public HoaDonBLL() {
        dalHoaDon = new HoaDonDAL();
    }
public ArrayList<HoaDon> getHoaDonWithMonthAndYear(String month, String year) {
        return dalHoaDon.getHoaDonWithMonthAndYear(month, year);
    }

}
