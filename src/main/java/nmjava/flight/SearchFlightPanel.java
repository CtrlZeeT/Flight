/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author xuanluan
 */
public class SearchFlightPanel extends javax.swing.JPanel {

    ArrayList<ChuyenBay> list = new ArrayList<ChuyenBay>();
    HashMap<String, String> city = new HashMap<String, String>();
    public ChuyenBay TraThongTinChuyenBay = new ChuyenBay();
    Thread thread;

    /**
     * Creates new form SearchFlightPanel
     */
    public SearchFlightPanel() {
        city.put("Đắk Lắk", "BMV");
        city.put("Cà Mau", "CAH");
        city.put("Khánh Hòa", "CXR");
        city.put("Đắk Lắk", "BMV");
        city.put("Đà Nẵng", "DAD");
        city.put("Điện Biên", "DIN");
        city.put("Lâm Đồng", "DLI");
        city.put("Hà Nội", "HAN");
        city.put("Hải Phòng", "HPH");
        city.put("Thừa Thiên – Huế", "HUI");
        city.put("Kiên Giang", "PQC");
        city.put("Gia Lai", "PXU");
        city.put("Hồ Chí Minh", "SGN");
        city.put("Sơn La", "SQH");
        city.put("Phú Yên", "TBB");
        city.put("Thanh Hóa", "THD");
        city.put("Bình Định", "UIH");
        city.put("Cần Thơ", "VCA");
        city.put("Quảng Nam", "VCL");
        city.put("Bà Rịa - Vũng Tàu", "VCS");
        city.put("Quảng Bình", "VDH");
        city.put("Quảng Ninh", "VDO");
        city.put("Nghệ An", "VII");
        city.put("Kiên Giang", "VKG");

        initComponents();
        stopProcess();
        ThongTinCacChuyenBay.getColumn(ThongTinCacChuyenBay.getColumnName(5)).setCellRenderer(new ButtonRenderer());
        ThongTinCacChuyenBay.getColumn(ThongTinCacChuyenBay.getColumnName(5)).setCellEditor(new ButtonEditor(new JCheckBox()));

        DefaultTableModel model = (DefaultTableModel) ThongTinCacChuyenBay.getModel();
        for (int i = 0; i < 5; i++) {
            Object[] row = {'a', 'a', 'a', 'a', 'a'};
            model.addRow(row);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "Chọn" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        private int index;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
                index = row;
            }
            label = (value == null) ? "chọn" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                TraThongTinChuyenBay.TenHangHangKhong = ThongTinCacChuyenBay.getValueAt(index, 0).toString();
                TraThongTinChuyenBay.MaChuyenBay = ThongTinCacChuyenBay.getValueAt(index, 1).toString();
                TraThongTinChuyenBay.ThoiGianDi = ThongTinCacChuyenBay.getValueAt(index, 2).toString();
                TraThongTinChuyenBay.ThoiGianDen = ThongTinCacChuyenBay.getValueAt(index, 3).toString();
                TraThongTinChuyenBay.GiaTien = ThongTinCacChuyenBay.getValueAt(index, 4).toString();
                TraThongTinChuyenBay.SanBayDi = cbSanBayDi.getSelectedItem().toString();
                TraThongTinChuyenBay.SanBayDen = cbSanBayDen.getSelectedItem().toString();
                SellTicketPanel.book.setCb(TraThongTinChuyenBay);
                SellTicketPanel.tab.setSelectedIndex(1);
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }

    public void SearchFlight(String url) throws IOException {
        list.clear();
        try (final WebClient webClient = new WebClient(BrowserVersion.CHROME) {
        }) {
            HtmlPage page = webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(5000);
            Document doc = Jsoup.parse(page.asXml());
            Elements elements = doc.select("div[class=dtc-flight-item]");
            try {
                for (int i = 0; i < elements.size(); i++) {
                    ChuyenBay chuyenbay = new ChuyenBay();
                    if (elements.get(i).select("p").get(0).text().contains("Pacific")) {
                        chuyenbay.setTenHangHangKhong("Pacific Airline");
                    } else {
                        chuyenbay.setTenHangHangKhong(elements.get(i).select("p").get(0).text());
                    }
                    chuyenbay.setSanBayDi(elements.get(i).select("div[class=dtc-flight-city]").get(0).text());
                    chuyenbay.setThoiGianDi(elements.get(i).select("div[class=dtc-flight-time]").get(0).text());
                    chuyenbay.setMaChuyenBay(elements.get(i).select("div[class=dtc-flight-numb dtc-color-text]").get(0).text());
                    chuyenbay.setSanBayDen(elements.get(i).select("div[class=dtc-flight-city]").get(1).text());
                    chuyenbay.setThoiGianDen(elements.get(i).select("div[class=dtc-flight-time]").get(1).text());
                    chuyenbay.setGiaTien(elements.get(i).select("div[class=dtc-flight-price]").get(0).text());
                    list.add(chuyenbay);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startProcess() {
        pbProcess.setVisible(true);
        btnCancel.setVisible(true);
        btnSearch.setEnabled(false);
    }

    public void stopProcess() {
        pbProcess.setVisible(false);
        btnCancel.setVisible(false);
        btnSearch.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ChonNgayBay = new com.toedter.calendar.JCalendar();
        jLabel4 = new javax.swing.JLabel();
        cbSanBayDi = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbSanBayDen = new javax.swing.JComboBox<>();
        cbHangBay = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThongTinCacChuyenBay = new javax.swing.JTable();
        JPanel6 = new javax.swing.JPanel();
        pbProcess = new javax.swing.JProgressBar();
        btnCancel = new javax.swing.JButton();

        jLabel2.setBackground(new java.awt.Color(99, 117, 131));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách chuyến bay");
        jLabel2.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Ngày khởi hành:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Nơi đi:");

        cbSanBayDi.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cbSanBayDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đắk Lắk", "Cà Mau", "Khánh Hòa", "Đà Nẵng", "Điện Biên", "Lâm Đồng", "Hà Nội", "Hải Phòng", "Thừa Thiên – Huế", "Kiên Giang", "Gia Lai", "Hồ Chí Minh", "Sơn La", "Phú Yên", "Thanh Hóa", "Bình Định", "Cần Thơ", "Quảng Nam", "Bà Rịa - Vũng Tàu", "Quảng Bình", "Quảng Ninh", "Nghệ An", "Kiên Giang" }));
        cbSanBayDi.setSelectedIndex(11);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setText("Nơi đến:");

        cbSanBayDen.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cbSanBayDen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đắk Lắk", "Cà Mau", "Khánh Hòa", "Đà Nẵng", "Điện Biên", "Lâm Đồng", "Hà Nội", "Hải Phòng", "Thừa Thiên – Huế", "Kiên Giang", "Gia Lai", "Hồ Chí Minh", "Sơn La", "Phú Yên", "Thanh Hóa", "Bình Định", "Cần Thơ", "Quảng Nam", "Bà Rịa - Vũng Tàu", "Quảng Bình", "Quảng Ninh", "Nghệ An", "Kiên Giang" }));
        cbSanBayDen.setSelectedIndex(3);

        cbHangBay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        cbHangBay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả các hãng", "Vietnam Airlines", "VietJet Air", "Bamboo Airways", "Pacific Airlines" }));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setText("Hãng bay");

        btnSearch.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSearch.setText("TÌM KIẾM");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(111, 131, 149));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tiêu chuẩn tra cứu");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChonNgayBay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cbSanBayDen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSanBayDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbHangBay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ChonNgayBay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSanBayDi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSanBayDen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbHangBay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addGap(68, 68, 68))
        );

        btnSearch.getAccessibleContext().setAccessibleName("");

        ThongTinCacChuyenBay.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        ThongTinCacChuyenBay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hãng bay", "Mã chuyến bay", "Giờ cất cánh", "Giờ hạ cánh", "Giá tiền", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ThongTinCacChuyenBay.setCellSelectionEnabled(true);
        ThongTinCacChuyenBay.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        ThongTinCacChuyenBay.setName(""); // NOI18N
        ThongTinCacChuyenBay.setRowHeight(32);
        jScrollPane1.setViewportView(ThongTinCacChuyenBay);
        ThongTinCacChuyenBay.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (ThongTinCacChuyenBay.getColumnModel().getColumnCount() > 0) {
            ThongTinCacChuyenBay.getColumnModel().getColumn(5).setResizable(false);
            ThongTinCacChuyenBay.getColumnModel().getColumn(5).setPreferredWidth(15);
        }

        JPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pbProcess.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        pbProcess.setForeground(new java.awt.Color(0, 204, 51));
        pbProcess.setIndeterminate(true);

        btnCancel.setText("hủy");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JPanel6Layout = new javax.swing.GroupLayout(JPanel6);
        JPanel6.setLayout(JPanel6Layout);
        JPanel6Layout.setHorizontalGroup(
            JPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addGap(19, 19, 19))
        );
        JPanel6Layout.setVerticalGroup(
            JPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(JPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pbProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(JPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        String MaNoiDi = city.get(cbSanBayDi.getSelectedItem().toString());
        String MaNoiDen = city.get(cbSanBayDen.getSelectedItem().toString());
        Calendar c = ChonNgayBay.getCalendar();
        int Ngay = c.get(Calendar.DATE);
        int Thang = c.get(Calendar.MONTH) + 1;
        int Nam = c.get(Calendar.YEAR);
        String d, m, y;
        if (Ngay < 10) {
            d = "0" + String.valueOf(Ngay);
        } else {
            d = String.valueOf(Ngay);
        }
        if (Thang < 10) {
            m = "0" + String.valueOf(Thang);
        } else {
            m = String.valueOf(Thang);
        }
        y = String.valueOf(Nam);
        String[] MaHang = {"VN,VJ,QH", "VN", "VJ", "QH", "VN"};
        String url = "https://demo.datacom.vn/flight?Request=" + MaNoiDi + MaNoiDen + d + m + y + "-1-0-0&Airline=" + MaHang[cbHangBay.getSelectedIndex()];
        TraThongTinChuyenBay.NgayDi = d + "/" + m + "/" + y;
        TraThongTinChuyenBay.NgayDen = d + "/" + m + "/" + y;
        DefaultTableModel model = (DefaultTableModel) ThongTinCacChuyenBay.getModel();
        model.setRowCount(0);
        list.clear();
        thread = new Thread() {
            @Override
            public synchronized void start() {
                super.start(); //To change body of generated methods, choose Tools | Templates.
                startProcess();
            }

            @Override
            public void run() {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                try {
                    SearchFlight(url);
                } catch (IOException ex) {
                    Logger.getLogger(SearchFlightPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < list.size(); i++) {
                    Object[] row = {list.get(i).getTenHangHangKhong(), list.get(i).getMaChuyenBay(), list.get(i).getThoiGianDi(), list.get(i).getThoiGianDen(), list.get(i).getGiaTien()};
                    model.addRow(row);
                }
                this.interrupt();
            }

            @Override
            public void interrupt() {
                super.interrupt(); //To change body of generated methods, choose Tools | Templates.
                stopProcess();
                System.out.println("interupt");
            }

        };
        thread.start();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        // TODO add your handling code here:
        thread.interrupt();
    }//GEN-LAST:event_btnCancelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar ChonNgayBay;
    private javax.swing.JPanel JPanel6;
    private javax.swing.JTable ThongTinCacChuyenBay;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbHangBay;
    private javax.swing.JComboBox<String> cbSanBayDen;
    private javax.swing.JComboBox<String> cbSanBayDi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar pbProcess;
    // End of variables declaration//GEN-END:variables
}
