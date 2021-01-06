/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author ExpectedSemicolon
 */
public class SearchPanel extends javax.swing.JPanel {

    ArrayList<ChuyenBay> list = new ArrayList<ChuyenBay>();
    HashMap<String, String> city = new HashMap<String, String>();
    public ChuyenBay TraThongTinChuyenBay = new ChuyenBay();
    JPanel search;

    public SearchPanel() {

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

        ThongTinCacChuyenBay.getColumn(ThongTinCacChuyenBay.getColumnName(5)).setCellRenderer(new ButtonRenderer());
        ThongTinCacChuyenBay.getColumn(ThongTinCacChuyenBay.getColumnName(5)).setCellEditor(new ButtonEditor(new JCheckBox()));

        DefaultTableModel model = (DefaultTableModel) ThongTinCacChuyenBay.getModel();
        for (int i = 0; i < 5; i++) {
            Object[] row = {'a', 'a', 'a', 'a', 'a'};
            model.addRow(row);
        }
        search = this;
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
                SellTicketpanel ticket = new SellTicketpanel();
                TraThongTinChuyenBay.TenHangHangKhong=ThongTinCacChuyenBay.getValueAt(index, 0).toString();
                TraThongTinChuyenBay.MaChuyenBay=ThongTinCacChuyenBay.getValueAt(index, 1).toString();
                TraThongTinChuyenBay.ThoiGianDi=ThongTinCacChuyenBay.getValueAt(index, 2).toString();
                TraThongTinChuyenBay.ThoiGianDen=ThongTinCacChuyenBay.getValueAt(index, 3).toString();
                TraThongTinChuyenBay.GiaTien=ThongTinCacChuyenBay.getValueAt(index, 4).toString();
                TraThongTinChuyenBay.SanBayDi=cbSanBayDi.getSelectedItem().toString();               
                TraThongTinChuyenBay.SanBayDen=cbSanBayDen.getSelectedItem().toString();      
                ticket.setCb(TraThongTinChuyenBay);
                ticket.setSize(search.getSize());
                search.removeAll();
                search.add(ticket);
                search.repaint();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ChonNgayBay = new com.toedter.calendar.JCalendar();
        jLabel4 = new javax.swing.JLabel();
        cbSanBayDi = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbSanBayDen = new javax.swing.JComboBox<>();
        cbHangBay = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        javax.swing.JButton btnTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThongTinCacChuyenBay = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(741, 540));

        jLabel1.setBackground(new java.awt.Color(111, 131, 149));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tiêu chuẩn tra cứu");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(99, 117, 131));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách chuyến bay");
        jLabel2.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        btnTimKiem.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ChonNgayBay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cbSanBayDen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSanBayDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbHangBay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                .addGap(31, 31, 31)
                .addComponent(btnTimKiem)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        cbSanBayDi.getAccessibleContext().setAccessibleName("");
        cbSanBayDi.getAccessibleContext().setAccessibleDescription("");

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
        ThongTinCacChuyenBay.setRowHeight(30);
        jScrollPane1.setViewportView(ThongTinCacChuyenBay);
        ThongTinCacChuyenBay.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (ThongTinCacChuyenBay.getColumnModel().getColumnCount() > 0) {
            ThongTinCacChuyenBay.getColumnModel().getColumn(5).setResizable(false);
            ThongTinCacChuyenBay.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

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
        TraThongTinChuyenBay.NgayDi=d+"/"+m+"/"+y;
        TraThongTinChuyenBay.NgayDen=d+"/"+m+"/"+y;    
        DefaultTableModel model = (DefaultTableModel) ThongTinCacChuyenBay.getModel();
        model.setRowCount(0);
        list.clear();
        try {
            SearchFlight(url);
        } catch (IOException ex) {
            Logger.getLogger(SearchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < list.size(); i++) {
            Object[] row = {list.get(i).getTenHangHangKhong(), list.get(i).getMaChuyenBay(), list.get(i).getThoiGianDi(), list.get(i).getThoiGianDen(), list.get(i).getGiaTien()};
            model.addRow(row);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar ChonNgayBay;
    private javax.swing.JTable ThongTinCacChuyenBay;
    private javax.swing.JComboBox<String> cbHangBay;
    private javax.swing.JComboBox<String> cbSanBayDen;
    private javax.swing.JComboBox<String> cbSanBayDi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
