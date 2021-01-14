/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import nmjava.flight.BLL.HoaDonBLL;
import nmjava.flight.BLL.ThamSoBLL;
import nmjava.flight.BLL.VeChuyenBayBLL;
import nmjava.flight.DTO.HoaDon;
import nmjava.flight.DTO.VeChuyenBay;
import nmjava.flight.Utility.Mail;
import nmjava.flight.Utility.Notification;
import nmjava.flight.Utility.StringVerify;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author xuanluan
 */
public class BookTicketPanel extends javax.swing.JPanel {

    HoaDonBLL bllHD;
    VeChuyenBayBLL bllVCB;
    ThamSoBLL bllTS;
    DefaultTableModel model;

    /**
     * Creates new form BookTicketPanel
     */
    public BookTicketPanel() {
        bllHD = new HoaDonBLL();
        bllVCB = new VeChuyenBayBLL();
        bllTS = new ThamSoBLL();
        initComponents();
        model = (DefaultTableModel) tableThongTinVe.getModel();
        setImage();
        txt_HoVaTen.setText("");
        txt_CMND.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        lbNotify.setText("");

        tableThongTinVe.getColumn(tableThongTinVe.getColumnName(6)).setCellRenderer(new BookTicketPanel.ButtonRenderer());
        tableThongTinVe.getColumn(tableThongTinVe.getColumnName(6)).setCellEditor(new BookTicketPanel.ButtonEditor(new JCheckBox()));
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
            setText((value == null) ? "xóa" : value.toString());
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
            label = (value == null) ? "xóa" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                model.removeRow(index);
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

    public void setCb(ChuyenBay cb) {
        lbHangMayBay.setText(cb.TenHangHangKhong);
        lbNoiKhoiHanh.setText(cb.SanBayDi);
        lbGioKhoiHanh.setText(cb.ThoiGianDi);
        lbNgayKhoiHanh.setText(cb.NgayDi);
        lbMaChuyenBay.setText(cb.MaChuyenBay);
        lbNoiHaCanh.setText(cb.SanBayDen);
        lbGioHaCanh.setText(cb.ThoiGianDen);
        lbNgayHaCanh.setText(cb.NgayDen);
        String giatien = "";
        String tmp = cb.GiaTien;
        for (char ch : tmp.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                giatien += ch;
            }
        }
        lbGiaTien.setText(String.valueOf(Integer.parseInt(giatien) + bllTS.getTienLaiMoiVe()) + " VND");
    }

    boolean CheckInput() {
        if (txt_HoVaTen.getText().toString().trim().equals("")) {
            Notification.show(lbNotify, "Bạn chưa nhập họ tên", false);
            return false;
        }
        if (txt_SDT.getText().toString().trim().equals("")) {
            Notification.show(lbNotify, "Bạn chưa nhập số điện thoại", false);
            return false;
        }
        if (!StringVerify.isValidPhoneNumber(txt_SDT.getText())) {
            Notification.show(lbNotify, "Số điện thoại không hợp lệ", false);
            return false;
        }
        if (txt_CMND.getText().toString().trim().equals("")) {
            Notification.show(lbNotify, "Bạn chưa nhập CMND", false);
            return false;
        }
        if (!StringVerify.isValidCMND(txt_CMND.getText())) {
            Notification.show(lbNotify, "CMND không hợp lệ", false);
            return false;
        }
        if (txt_Email.getText().toString().trim().equals("")) {
            Notification.show(lbNotify, "Bạn chưa nhập email", false);
            return false;
        }
        if (!StringVerify.isValidEmail(txt_Email.getText())) {
            Notification.show(lbNotify, "Email không hợp lệ", false);
            return false;
        }
        return true;
    }

    public void setImage() {
        String directResource = System.getProperty("user.dir") + "\\resource\\";
        imgPlane.setIcon(new ImageIcon(directResource + "flightIcon.png"));
        imgFly.setIcon(new ImageIcon(directResource + "flightarrow.png"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbHangMayBay = new javax.swing.JLabel();
        lbMaChuyenBay = new javax.swing.JLabel();
        lbNotify = new javax.swing.JLabel();
        txt_HoVaTen = new javax.swing.JTextField();
        lbHoVaTen5 = new javax.swing.JLabel();
        lbCMND5 = new javax.swing.JLabel();
        txt_CMND = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        lbEmail5 = new javax.swing.JLabel();
        lbSDT5 = new javax.swing.JLabel();
        lbGiaTien = new javax.swing.JLabel();
        btn_DatVe5 = new javax.swing.JButton();
        imgFly = new javax.swing.JLabel();
        imgPlane = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbNoiKhoiHanh = new javax.swing.JLabel();
        lbGioKhoiHanh = new javax.swing.JLabel();
        lbNgayKhoiHanh = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbNoiHaCanh = new javax.swing.JLabel();
        lbGioHaCanh = new javax.swing.JLabel();
        lbNgayHaCanh = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableThongTinVe = new javax.swing.JTable();
        lbTongHoaDon = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        btn_XuatVe = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbHangMayBay.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbHangMayBay.setText("Hãng máy bay");

        lbMaChuyenBay.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbMaChuyenBay.setForeground(new java.awt.Color(0, 204, 51));
        lbMaChuyenBay.setText("Mã chuyến bay");

        lbNotify.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbNotify.setForeground(new java.awt.Color(0, 204, 51));
        lbNotify.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNotify.setText("Thông báo");

        txt_HoVaTen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_HoVaTen.setForeground(new java.awt.Color(51, 51, 51));
        txt_HoVaTen.setToolTipText("CMND");

        lbHoVaTen5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbHoVaTen5.setText("Họ và tên:");

        lbCMND5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbCMND5.setText("CMND:");

        txt_CMND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_CMND.setForeground(new java.awt.Color(51, 51, 51));
        txt_CMND.setToolTipText("Họ và tên");
        txt_CMND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CMNDActionPerformed(evt);
            }
        });

        txt_SDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_SDT.setForeground(new java.awt.Color(51, 51, 51));
        txt_SDT.setToolTipText("Số điện thoại");

        txt_Email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Email.setForeground(new java.awt.Color(51, 51, 51));
        txt_Email.setToolTipText("Email");

        lbEmail5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbEmail5.setText("Email:");

        lbSDT5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbSDT5.setText("Số điện thoại:");

        lbGiaTien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbGiaTien.setForeground(new java.awt.Color(255, 153, 0));
        lbGiaTien.setText("Giá tiền");

        btn_DatVe5.setBackground(new java.awt.Color(102, 153, 255));
        btn_DatVe5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_DatVe5.setForeground(new java.awt.Color(255, 255, 255));
        btn_DatVe5.setText("Đặt vé");
        btn_DatVe5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_DatVe5.setBorderPainted(false);
        btn_DatVe5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DatVe5ActionPerformed(evt);
            }
        });

        imgFly.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        imgPlane.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 153, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("THÔNG TIN ĐẶT VÉ");
        jLabel19.setOpaque(true);

        btnReturn.setBackground(new java.awt.Color(255, 255, 255));
        btnReturn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(102, 153, 255));
        btnReturn.setText("Quay lại");
        btnReturn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReturn.setBorderPainted(false);
        btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNoiKhoiHanh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNoiKhoiHanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoiKhoiHanh.setText("Nơi khởi hành");

        lbGioKhoiHanh.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbGioKhoiHanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGioKhoiHanh.setText("GIỜ KHỞI HÀNH");

        lbNgayKhoiHanh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNgayKhoiHanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNgayKhoiHanh.setText("Ngày khởi hành");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNoiKhoiHanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGioKhoiHanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNgayKhoiHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbNoiKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGioKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lbNoiHaCanh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNoiHaCanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoiHaCanh.setText("Nơi hạ cánh");

        lbGioHaCanh.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbGioHaCanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGioHaCanh.setText("GIỜ HẠ CÁNH");

        lbNgayHaCanh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbNgayHaCanh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNgayHaCanh.setText("Ngày hạ cánh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNoiHaCanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbGioHaCanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
            .addComponent(lbNgayHaCanh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbNoiHaCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGioHaCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNgayHaCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lbNotify, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(btn_DatVe5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbHangMayBay)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(imgPlane, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMaChuyenBay, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(imgFly, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbSDT5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbHoVaTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbCMND5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_CMND))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbEmail5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(imgPlane, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbMaChuyenBay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addComponent(imgFly, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(lbHangMayBay, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lbGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_CMND, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCMND5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHoVaTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSDT5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmail5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbNotify, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_DatVe5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_HoVaTen.getAccessibleContext().setAccessibleDescription("Họ Và Tên");
        txt_CMND.getAccessibleContext().setAccessibleDescription("CMND");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setBackground(new java.awt.Color(111, 131, 149));
        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Thông tin vé");
        jLabel20.setOpaque(true);

        tableThongTinVe.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        tableThongTinVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chuyến bay", "Giá tiền", "Họ và tên", "CMND", "Số điện thoại", "Email", ""
            }
        ));
        tableThongTinVe.setRowHeight(32);
        jScrollPane7.setViewportView(tableThongTinVe);
        if (tableThongTinVe.getColumnModel().getColumnCount() > 0) {
            tableThongTinVe.getColumnModel().getColumn(6).setResizable(false);
            tableThongTinVe.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        lbTongHoaDon.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbTongHoaDon.setForeground(new java.awt.Color(255, 153, 0));
        lbTongHoaDon.setText("Tổng hóa đơn:");

        lbTongTien.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lbTongTien.setForeground(new java.awt.Color(255, 153, 0));
        lbTongTien.setText("0");

        btn_XuatVe.setBackground(new java.awt.Color(102, 153, 255));
        btn_XuatVe.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btn_XuatVe.setForeground(new java.awt.Color(255, 255, 255));
        btn_XuatVe.setText("Xuất Vé");
        btn_XuatVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTongHoaDon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_XuatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XuatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_CMNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CMNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CMNDActionPerformed

    private void btn_DatVe5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DatVe5ActionPerformed
        if (CheckInput()) {
            Object[] row = {lbMaChuyenBay.getText(), lbGiaTien.getText(), txt_HoVaTen.getText(), txt_CMND.getText(), txt_SDT.getText(), txt_Email.getText()};
            model.addRow(row);
            String giatien = "";
            String tmp = lbGiaTien.getText();
            for (char ch : tmp.toCharArray()) {
                if (ch >= '0' && ch <= '9') {
                    giatien += ch;
                }
            }
            lbTongTien.setText(String.valueOf(Integer.parseInt(lbTongTien.getText()) + Integer.parseInt(giatien)));
            //System.out.println(Integer.parseInt(lbTongTien.getText())+Integer.parseInt(giatien));
            txt_HoVaTen.setText("");
            txt_CMND.setText("");
            txt_SDT.setText("");
            txt_Email.setText("");
        }
    }//GEN-LAST:event_btn_DatVe5ActionPerformed

    private void btn_XuatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatVeActionPerformed
        if (tableThongTinVe.getRowCount() == 0) {
            Notification.show(lbNotify, "Vui lòng nhập thông tin vé", false);

        } else {
            HoaDon HD = new HoaDon();
            HD.setMaHoaDon(bllHD.getMaHoaDon());
            System.out.println(HD.MaHoaDon);
            HD.TienLai = tableThongTinVe.getRowCount() * bllTS.getTienLaiMoiVe();
            HD.SoVe = tableThongTinVe.getRowCount();
            LocalDateTime ldt = LocalDateTime.now().plusDays(1);
            HD.TongTien = Integer.parseInt(lbTongTien.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            HD.ThoiGianTao = ldt.format(formatter);
            bllHD.InsertHoaDon(HD);
            String ThongTinVe = "";
            for (int i = 0; i < tableThongTinVe.getRowCount(); i++) {
                VeChuyenBay VCB = new VeChuyenBay();
                VCB.MaChuyenBay = tableThongTinVe.getValueAt(i, 0).toString();
                String giatien = "";
                String tmp = tableThongTinVe.getValueAt(i, 1).toString();
                for (char ch : tmp.toCharArray()) {
                    if (ch >= '0' && ch <= '9') {
                        giatien += ch;
                    }
                }
                VCB.GiaVe = Integer.parseInt(giatien);
                VCB.HoTen = tableThongTinVe.getValueAt(i, 2).toString();
                VCB.CMND = tableThongTinVe.getValueAt(i, 3).toString();
                VCB.SDT = tableThongTinVe.getValueAt(i, 4).toString();
                VCB.Email = tableThongTinVe.getValueAt(i, 5).toString();
                VCB.MaHoaDon = HD.MaHoaDon;
                VCB.TrangThai = "Đã thanh toán";
                bllVCB.InsertVeChuyenBay(VCB);
                ThongTinVe += VCB.HoTen + "\n";
                ThongTinVe += VCB.CMND + "\n";
                ThongTinVe += VCB.SDT + "\n";
                ThongTinVe += VCB.MaChuyenBay + "\n";
            }
            ThongTinVe += "Tong tien la : ";
            ThongTinVe += String.valueOf(HD.TongTien);
            Notification.show(lbNotify, "Xuất vé thành công", true);
            DefaultTableModel model = (DefaultTableModel) tableThongTinVe.getModel();
            //Mail.send("Xac nhan dat cho", ThongTinVe, tableThongTinVe.getValueAt(0, 5).toString().trim());

            model.setRowCount(0);
            lbTongTien.setText("0");

        }
    }//GEN-LAST:event_btn_XuatVeActionPerformed

    private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseClicked
        // TODO add your handling code here:
        SellTicketPanel.tab.setSelectedIndex(0);
    }//GEN-LAST:event_btnReturnMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btn_DatVe5;
    private javax.swing.JButton btn_XuatVe;
    private javax.swing.JLabel imgFly;
    private javax.swing.JLabel imgPlane;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbCMND5;
    private javax.swing.JLabel lbEmail5;
    private javax.swing.JLabel lbGiaTien;
    private javax.swing.JLabel lbGioHaCanh;
    private javax.swing.JLabel lbGioKhoiHanh;
    private javax.swing.JLabel lbHangMayBay;
    private javax.swing.JLabel lbHoVaTen5;
    private javax.swing.JLabel lbMaChuyenBay;
    private javax.swing.JLabel lbNgayHaCanh;
    private javax.swing.JLabel lbNgayKhoiHanh;
    private javax.swing.JLabel lbNoiHaCanh;
    private javax.swing.JLabel lbNoiKhoiHanh;
    private javax.swing.JLabel lbNotify;
    private javax.swing.JLabel lbSDT5;
    private javax.swing.JLabel lbTongHoaDon;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tableThongTinVe;
    private javax.swing.JTextField txt_CMND;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoVaTen;
    private javax.swing.JTextField txt_SDT;
    // End of variables declaration//GEN-END:variables
}
