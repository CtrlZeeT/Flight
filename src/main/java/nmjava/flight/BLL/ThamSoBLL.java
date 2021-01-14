/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.BLL;

import nmjava.flight.DAL.ThamSoDAL;

/**
 *
 * @author ExpectedSemicolon
 */
public class ThamSoBLL {

    ThamSoDAL dalThamSo;

    public ThamSoBLL() {
        dalThamSo = new ThamSoDAL();
    }

    public int getTienLaiMoiVe() {
        return dalThamSo.getTienLaiMoiVe();
    }

    public void setTienLaiMoiVe(int tienLai) {
        dalThamSo.setTienLaiMoiVe(tienLai);
    }

    public int getTienHuyVe() {
        return dalThamSo.getTienHuyVe();
    }
    
    public void setTienHuyVe(int tienHuyVe){
        dalThamSo.setTienHuyVe(tienHuyVe);
    }
}
