/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.DTO;

import java.sql.Date;

/**
 *
 * @author Cuong
 */
public class HoaDon {
    public String MaHoaDon;
    public int SoVe;
    public String ThoiGianTao;
    public double TongTien;
    public double TienLai;

    public HoaDon(String MaHoaDon, int SoVe, double TongTien, double TienLai, String ThoiGianTao) {
        this.MaHoaDon = MaHoaDon;
        this.ThoiGianTao = ThoiGianTao;
        this.TongTien = TongTien;
        this.TienLai = TienLai;
        this.SoVe = SoVe;
    }

    public HoaDon() {
    }
    
    
    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getThoiGianTao() {
        return ThoiGianTao;
    }

    public void setThoiGianTao(String ThoiGianTao) {
        this.ThoiGianTao = ThoiGianTao;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public double getTienLai() {
        return TienLai;
    }

    public void setTienLai(double TienLai) {
        this.TienLai = TienLai;
    }

    public int getSoVe() {
        return SoVe;
    }

    public void setSoVe(int SoVe) {
        this.SoVe = SoVe;
    }
    
    
    
    
    
}
