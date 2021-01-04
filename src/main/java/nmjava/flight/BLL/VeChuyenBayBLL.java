/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.BLL;

import nmjava.flight.DAL.VeChuyenBayDAL;

/**
 *
 * @author xuanluan
 */
public class VeChuyenBayBLL {
    VeChuyenBayDAL dalVCB;
    
    public VeChuyenBayBLL(){
        dalVCB = new VeChuyenBayDAL();
    }
    public void getAllData() {
        dalVCB.getALLDaTa();
    }
   
}
