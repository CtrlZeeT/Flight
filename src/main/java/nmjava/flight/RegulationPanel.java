/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import nmjava.flight.BLL.ThamSoBLL;
import nmjava.flight.DTO.ThamSo;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ExpectedSemicolon
 */
public class RegulationPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegulationPanel
     */  
    private ThamSoBLL bllThamSo = new ThamSoBLL();
    public RegulationPanel() {
        initComponents();
        jTextField1.setText(String.valueOf(bllThamSo.getTienLaiMoiVe()));
        jTextField2.setText(String.valueOf(bllThamSo.getTienHuyVe()));
        jButton_Luu.setEnabled(false);
        jButton_Huy.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel_TB = new javax.swing.JLabel();
        jButton_ChinhSua = new javax.swing.JButton();
        jButton_Luu = new javax.swing.JButton();
        jButton_Huy = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(111, 131, 149));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Các tham số");
        jLabel11.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tiền lãi mỗi vé:");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Đồng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Chi phí hủy vé:");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Đồng");

        jLabel_TB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_TB.setForeground(new java.awt.Color(0, 204, 51));
        jLabel_TB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_TB.setToolTipText("Thông báo");

        jButton_ChinhSua.setBackground(new java.awt.Color(102, 153, 255));
        jButton_ChinhSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_ChinhSua.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ChinhSua.setText("Chỉnh sửa");
        jButton_ChinhSua.setMaximumSize(new java.awt.Dimension(100, 40));
        jButton_ChinhSua.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton_ChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ChinhSuaActionPerformed(evt);
            }
        });

        jButton_Luu.setBackground(new java.awt.Color(102, 153, 255));
        jButton_Luu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Luu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Luu.setText("Lưu");
        jButton_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LuuActionPerformed(evt);
            }
        });

        jButton_Huy.setBackground(new java.awt.Color(102, 153, 255));
        jButton_Huy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Huy.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Huy.setText("Hủy");
        jButton_Huy.setMaximumSize(new java.awt.Dimension(100, 40));
        jButton_Huy.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jButton_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addComponent(jLabel_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 251, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void setEditInfomation(boolean x){
        jTextField1.setEditable(x);
        jTextField2.setEditable(x);
        jButton_Luu.setEnabled(x);
        jButton_Huy.setEnabled(x);
    }
    
    private void jButton_ChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ChinhSuaActionPerformed
        // TODO add your handling code here:
        setEditInfomation(true);
        jButton_ChinhSua.setEnabled(false);
        jLabel_TB.setText("");
    }//GEN-LAST:event_jButton_ChinhSuaActionPerformed

    private void jButton_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LuuActionPerformed
        // TODO add your handling code here:
        // Kiểm tra số
        String patternStr = ".*[^0-9].*"; // tìm ký tự khác số trong String
        Pattern p = Pattern.compile(patternStr);
        Matcher m1 = p.matcher(jTextField1.getText());
        Matcher m2 = p.matcher(jTextField2.getText());
        if (m1.find() || m2.find()){
            jLabel_TB.setForeground(new Color(255,0,0));
            jLabel_TB.setText("Tham số vừa thêm không đúng kiểu");
        }
        else {
            bllThamSo.setTienLaiMoiVe(Integer.parseInt(jTextField1.getText()));
            bllThamSo.setTienHuyVe(Integer.parseInt(jTextField2.getText()));
            setEditInfomation(false);
            jButton_ChinhSua.setEnabled(true);        
            jLabel_TB.setForeground(new Color(0,204,51));
            jLabel_TB.setText("Chỉnh sửa thành công");
        }
    }//GEN-LAST:event_jButton_LuuActionPerformed

    private void jButton_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HuyActionPerformed
        // TODO add your handling code here:
        jTextField1.setText(String.valueOf(bllThamSo.getTienLaiMoiVe()));
        jTextField2.setText(String.valueOf(bllThamSo.getTienHuyVe()));
        
        setEditInfomation(false);
        jButton_ChinhSua.setEnabled(true);
        jLabel_TB.setForeground(new Color(0,0,0));
        jLabel_TB.setText("Hủy chỉnh sửa");
    }//GEN-LAST:event_jButton_HuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ChinhSua;
    private javax.swing.JButton jButton_Huy;
    private javax.swing.JButton jButton_Luu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_TB;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}