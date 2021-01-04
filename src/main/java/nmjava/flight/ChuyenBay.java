/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;
public class ChuyenBay {
    String TenHangHangKhong;
    String MaChuyenBay;
    String SanBayDi;
    String ThoiGianDi;
    String SanBayDen;
    String ThoiGianDen;
    String GiaTien;

    public String getTenHangHangKhong() {
        return TenHangHangKhong;
    }

    public void setTenHangHangKhong(String TenHangHangKhong) {
        this.TenHangHangKhong = TenHangHangKhong;
    }

    public String getSanBayDi() {
        return SanBayDi;
    }

    public void setSanBayDi(String SanBayDi) {
        this.SanBayDi = SanBayDi;
    }

    public String getThoiGianDi() {
        return ThoiGianDi;
    }

    public void setThoiGianDi(String ThoiGianDi) {
        this.ThoiGianDi = ThoiGianDi;
    }

    public String getMaChuyenBay() {
        return MaChuyenBay;
    }

    public void setMaChuyenBay(String MaChuyenBay) {
        this.MaChuyenBay = MaChuyenBay;
    }

    public String getSanBayDen() {
        return SanBayDen;
    }

    public void setSanBayDen(String SanBayDen) {
        this.SanBayDen = SanBayDen;
    }

    public String getThoiGianDen() {
        return ThoiGianDen;
    }

    public void setThoiGianDen(String ThoiGianDen) {
        this.ThoiGianDen = ThoiGianDen;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String GiaTien) {
        this.GiaTien = GiaTien;
    }
    public void printOutput() {
        System.out.println(TenHangHangKhong);
        System.out.println(SanBayDi);
        System.out.println(ThoiGianDi);
        System.out.println(MaChuyenBay);
        System.out.println(SanBayDen);
        System.out.println(ThoiGianDen);
        System.out.println(GiaTien);    
    } 
    
}
