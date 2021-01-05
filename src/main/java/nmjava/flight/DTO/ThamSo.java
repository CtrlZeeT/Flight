/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.DTO;

/**
 *
 * @author ExpectedSemicolon
 */
public class ThamSo {
    String TenThamSo;
    int GiaTri;
    public ThamSo(String tenThamSo,int giaTri){
        this.TenThamSo = tenThamSo;
        this.GiaTri = giaTri;
    }
    public String getTenThamSo(){
        return TenThamSo;
    } 
    public void setTenThamSo(String tenThamSo){
        this.TenThamSo = tenThamSo;
    }
    public int getGiaTri(){
        return GiaTri;
    } 
    public void setGiaTri(int giaTri){
        this.GiaTri = giaTri;
    }
}
