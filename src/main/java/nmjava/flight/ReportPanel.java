/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.table.DefaultTableModel;
import nmjava.flight.BLL.HoaDonBLL;
import nmjava.flight.DTO.HoaDon;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Cuong
 */
public class ReportPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportPanel
     */
    public ReportPanel() {
        initComponents();
        setVisibleLabelMonth(false);
        setVisibleLabelYear(false);
        btnExcelMonth.setVisible(false);
        btnExcelYear.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabpanel = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblThang = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        lblTongSoVe = new javax.swing.JLabel();
        lblTongTienLai = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnThongKeThang = new javax.swing.JButton();
        cbThang = new javax.swing.JComboBox<>();
        cbNamPanelThang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMonth = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblThang1 = new javax.swing.JLabel();
        lblTongSoVe3 = new javax.swing.JLabel();
        lblTongSoVe1 = new javax.swing.JLabel();
        lblTongDoanhThu1 = new javax.swing.JLabel();
        lblTongSoVe2 = new javax.swing.JLabel();
        lblTongTienLai1 = new javax.swing.JLabel();
        btnExcelMonth = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbNamPanelNam = new javax.swing.JComboBox<>();
        btnThongKeNam = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableYear = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        lblNam = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTongVeNam = new javax.swing.JLabel();
        lblTongVeNam1 = new javax.swing.JLabel();
        lblTongDoanhThuNam = new javax.swing.JLabel();
        lblTongDoanhThuNam1 = new javax.swing.JLabel();
        lblTongTienLaiNam = new javax.swing.JLabel();
        btnExcelYear = new javax.swing.JButton();

        tabpanel.setBackground(new java.awt.Color(239, 243, 246));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(741, 540));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 156, 255));
        jLabel1.setText("THÔNG TIN CHI TIẾT");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Tháng:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Tổng số vé đã bán:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Tổng doanh thu:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Tổng tiền lãi:");

        lblThang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblThang.setText("lblThang");

        lblTongDoanhThu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongDoanhThu.setText("lblTongDoanhThu");

        lblTongSoVe.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongSoVe.setText("lblTongSoVe");

        lblTongTienLai.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongTienLai.setText("lblTongTienLai");

        jLabel6.setBackground(new java.awt.Color(111, 131, 149));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Báo cáo doanh thu bán vé chuyến bay");
        jLabel6.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Tháng:");

        btnThongKeThang.setText("Thống kê");
        btnThongKeThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeThangActionPerformed(evt);
            }
        });

        cbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        cbNamPanelThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021" }));

        tableMonth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hoá Đơn", "Số Vé", "Doanh Thu (VND)", "Tiền Lãi (VND)", "Ngày Lập Hoá Đơn"
            }
        ));
        jScrollPane1.setViewportView(tableMonth);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Tháng:");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Tổng số vé đã bán:");

        lblThang1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblThang1.setText("lblThang");

        lblTongSoVe3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongSoVe3.setText("lblTongSoVe");

        lblTongSoVe1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongSoVe1.setText("Tổng doanh thu:");

        lblTongDoanhThu1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongDoanhThu1.setText("lblTongDoanhThu");

        lblTongSoVe2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongSoVe2.setText("Tổng tiền lãi:");

        lblTongTienLai1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongTienLai1.setText("lblTongTienLai");

        btnExcelMonth.setText("Xuất file Excel");
        btnExcelMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelMonthActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(lblThang))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongSoVe)))
                        .addGap(141, 141, 141)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongSoVe1)
                            .addComponent(lblTongSoVe2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTienLai)
                            .addComponent(lblTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNamPanelThang, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnThongKeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191)
                        .addComponent(btnExcelMonth)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblThang)
                    .addComponent(lblTongSoVe1)
                    .addComponent(lblTongDoanhThu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTongSoVe)
                    .addComponent(lblTongSoVe2)
                    .addComponent(lblTongTienLai))
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnThongKeThang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNamPanelThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcelMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        tabpanel.addTab("Báo cáo tháng", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(1, 156, 255));
        jLabel8.setText("THÔNG TIN CHI TIẾT");

        jLabel14.setBackground(new java.awt.Color(111, 131, 149));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Báo cáo doanh thu bán vé chuyến bay");
        jLabel14.setOpaque(true);

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("Năm: ");

        cbNamPanelNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021" }));

        btnThongKeNam.setText("Thống kê");
        btnThongKeNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeNamActionPerformed(evt);
            }
        });

        tableYear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Số Vé", "Doanh Thu (VND)", "Tiền Lãi"
            }
        ));
        jScrollPane2.setViewportView(tableYear);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Năm:");

        lblNam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNam.setText("lblNam");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("Tổng số vé đã bán:");

        lblTongVeNam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongVeNam.setText("lblTongVeNam");

        lblTongVeNam1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongVeNam1.setText("Tổng doanh thu năm:");

        lblTongDoanhThuNam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongDoanhThuNam.setText("lblTongDoanhThuNam");

        lblTongDoanhThuNam1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongDoanhThuNam1.setText("Tổng tiền lãi năm:");

        lblTongTienLaiNam.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTongTienLaiNam.setText("lblTongTienLaiNam");

        btnExcelYear.setText("Xuất file Excel");
        btnExcelYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNamPanelNam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThongKeNam, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198)
                        .addComponent(btnExcelYear))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(lblNam))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongVeNam)))
                        .addGap(135, 135, 135)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongVeNam1)
                            .addComponent(lblTongDoanhThuNam1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTienLaiNam)
                            .addComponent(lblTongDoanhThuNam))))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblNam)
                    .addComponent(lblTongVeNam1)
                    .addComponent(lblTongDoanhThuNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblTongVeNam)
                    .addComponent(lblTongDoanhThuNam1)
                    .addComponent(lblTongTienLaiNam))
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNamPanelNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(btnThongKeNam, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcelYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        tabpanel.addTab("Báo cáo năm", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabpanel, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeThangActionPerformed
        String month = cbThang.getSelectedItem().toString();
        String year = cbNamPanelThang.getSelectedItem().toString();
        lblThang.setText(month + " - " + year);

        HoaDonBLL bllHoaDon = new HoaDonBLL();
        ArrayList<HoaDon> list = bllHoaDon.getHoaDonWithMonthAndYear(month, year);
        DefaultTableModel model = (DefaultTableModel) tableMonth.getModel();
        model.setRowCount(0);
        int TongSoVe = 0;
        int TongDoanhThu = 0;
        int TongTienLai = 0;
        for (HoaDon item : list) {
            TongSoVe += item.getSoVe();
            TongDoanhThu += item.getTongTien();
            TongTienLai += item.getTienLai();
            model.addRow(new Object[]{item.getMaHoaDon(),item.getSoVe(),item.getTongTien(), item.getTienLai(), item.getThoiGianTao().toString()});
        }
        lblTongSoVe.setText(String.valueOf(TongSoVe));
        lblTongDoanhThu.setText(String.valueOf(TongDoanhThu));
        lblTongTienLai.setText(String.valueOf(TongTienLai));
        setVisibleLabelMonth(true);
        btnExcelMonth.setVisible(true);
    }//GEN-LAST:event_btnThongKeThangActionPerformed

    private void btnThongKeNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeNamActionPerformed
        String year = cbNamPanelNam.getSelectedItem().toString();
        lblNam.setText(year);
        DefaultTableModel model = (DefaultTableModel) tableYear.getModel();
        model.setRowCount(0);
        HoaDonBLL bllHoaDon = new HoaDonBLL();
        int TongVeNam = 0;
        int TongDoanhThuNam = 0;
        int TongTienLaiNam = 0;

        for (int i = 1; i <= 12; i++) {
            ArrayList<HoaDon> list = bllHoaDon.getHoaDonWithMonthAndYear(String.valueOf(i), year);
            int TongSoVe = 0;
            int TongDoanhThu = 0;
            int TongTienLai = 0;
            for (HoaDon item : list) {
                TongSoVe += item.getSoVe();
                TongDoanhThu += item.getTongTien();
                TongTienLai += item.getTienLai();
            }
            model.addRow(new Object[]{i, TongSoVe, TongDoanhThu, TongTienLai});

            TongVeNam += TongSoVe;
            TongDoanhThuNam += TongDoanhThu;
            TongTienLaiNam += TongTienLai;
        }
        lblTongVeNam.setText(String.valueOf(TongVeNam));
        lblTongDoanhThuNam.setText(String.valueOf(TongDoanhThuNam));
        lblTongTienLaiNam.setText(String.valueOf(TongTienLaiNam));
        setVisibleLabelYear(true);
        btnExcelYear.setVisible(true);

    }//GEN-LAST:event_btnThongKeNamActionPerformed

    private void btnExcelMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelMonthActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Doanh thu tháng " + lblThang.getText() + ".xlsx"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file", "xlsx");
        fileChooser.addChoosableFileFilter(filter);
        String filename = null;
        String dir = null;
        int result = fileChooser.showSaveDialog(jPanel3);
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fileChooser.getSelectedFile().getName();
            dir = fileChooser.getCurrentDirectory().toString();
        }
        if (!filename.endsWith(".xlsx")) 
            filename += ".xlsx";
        String filePath = dir + "/" + filename;
        try {
            createExcelFileMonth(filePath);
        } catch (IOException ex) {
            Logger.getLogger(ReportPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcelMonthActionPerformed

    private void btnExcelYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelYearActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Doanh thu năm " + lblNam.getText() + ".xlsx"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel file", "xlsx");
        fileChooser.addChoosableFileFilter(filter);
        String filename = null;
        String dir = null;
        int result = fileChooser.showSaveDialog(jPanel3);
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fileChooser.getSelectedFile().getName();
            dir = fileChooser.getCurrentDirectory().toString();
        }
        if (!filename.endsWith(".xlsx")) 
            filename += ".xlsx";
        String filePath = dir + "/" + filename;
        try {
            createExcelFileYear(filePath);
        } catch (IOException ex) {
            Logger.getLogger(ReportPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcelYearActionPerformed

    private void setVisibleLabelMonth(boolean value) {
        lblThang.setVisible(value);
        lblTongSoVe.setVisible(value);
        lblTongDoanhThu.setVisible(value);
        lblTongTienLai.setVisible(value);
    }

    private void setVisibleLabelYear(boolean value) {
        lblNam.setVisible(value);
        lblTongVeNam.setVisible(value);
        lblTongDoanhThuNam.setVisible(value);
        lblTongTienLaiNam.setVisible(value);
    }

    private void createExcelFileMonth(String filePath) throws FileNotFoundException, IOException {
        DefaultTableModel model = (DefaultTableModel) tableMonth.getModel();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Thống kê tháng");
        //create header
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã Hoá Đơn");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Vé");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Doanh Thu (VND)");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tiền Lãi (VND)");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày Lập Hoá Đơn");

        for (int i = 1; i <= model.getRowCount(); i++) {
            Row rowData = sheet.createRow(i);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cellData = rowData.createCell(col);
                switch (col) {
                    case 0:
                        cellData.setCellValue((String) model.getValueAt(i - 1, col));
                        break;
                    case 1:
                        cellData.setCellValue((int) model.getValueAt(i - 1, col));
                        break;
                    case 2:
                    case 3:
                        cellData.setCellValue((int) model.getValueAt(i - 1, col));
                        break;
                    case 4:
                        cellData.setCellValue((String) model.getValueAt(i - 1, col));
                        break;
                }
            }
        }

        //resize column
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }

        //Create file
        try ( OutputStream os = new FileOutputStream(filePath)) {
            workbook.write(os);
        }

    }
    
    private void createExcelFileYear(String filePath) throws FileNotFoundException, IOException {
        DefaultTableModel model = (DefaultTableModel) tableYear.getModel();
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Thống kê năm");
        //create header
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tháng");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số Vé");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Doanh Thu (VND)");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tiền Lãi (VND)");

        for (int i = 1; i <= model.getRowCount(); i++) {
            Row rowData = sheet.createRow(i);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cellData = rowData.createCell(col);
                switch (col) {
                    case 0:
                        cellData.setCellValue((int) model.getValueAt(i - 1, col));
                        break;
                    case 1:
                        cellData.setCellValue((int) model.getValueAt(i - 1, col));
                        break;
                    case 2:
                    case 3:
                        cellData.setCellValue((int) model.getValueAt(i - 1, col));
                        break;
                }
            }
        }

        //resize column
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }

        //Create file
        try ( OutputStream os = new FileOutputStream(filePath)) {
            workbook.write(os);
        }

    }
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcelMonth;
    private javax.swing.JButton btnExcelYear;
    private javax.swing.JButton btnThongKeNam;
    private javax.swing.JButton btnThongKeThang;
    private javax.swing.JComboBox<String> cbNamPanelNam;
    private javax.swing.JComboBox<String> cbNamPanelThang;
    private javax.swing.JComboBox<String> cbThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblThang;
    private javax.swing.JLabel lblThang1;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JLabel lblTongDoanhThu1;
    private javax.swing.JLabel lblTongDoanhThuNam;
    private javax.swing.JLabel lblTongDoanhThuNam1;
    private javax.swing.JLabel lblTongSoVe;
    private javax.swing.JLabel lblTongSoVe1;
    private javax.swing.JLabel lblTongSoVe2;
    private javax.swing.JLabel lblTongSoVe3;
    private javax.swing.JLabel lblTongTienLai;
    private javax.swing.JLabel lblTongTienLai1;
    private javax.swing.JLabel lblTongTienLaiNam;
    private javax.swing.JLabel lblTongVeNam;
    private javax.swing.JLabel lblTongVeNam1;
    private javax.swing.JTable tableMonth;
    private javax.swing.JTable tableYear;
    private javax.swing.JTabbedPane tabpanel;
    // End of variables declaration//GEN-END:variables
}
