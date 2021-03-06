/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import com.sun.activation.viewers.TextEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import nmjava.flight.BLL.HoaDonBLL;
import nmjava.flight.BLL.ThamSoBLL;
import nmjava.flight.BLL.VeChuyenBayBLL;
import nmjava.flight.Utility.Notification;

/**
 *
 * @author Administrator
 */
public class SearchTicket extends javax.swing.JPanel {

    VeChuyenBayBLL bllVCB;
    ThamSoBLL bllTS;
    HoaDonBLL bllHD;
    DefaultTableModel model;

    public SearchTicket() {
        initComponents();
        tableThongTinVe.getColumn(tableThongTinVe.getColumnName(8)).setCellRenderer(new SearchTicket.ButtonRenderer());
        tableThongTinVe.getColumn(tableThongTinVe.getColumnName(8)).setCellEditor(new SearchTicket.ButtonEditor(new JCheckBox()));

        
        jLabel4.setText("");
        
        
        bllVCB = new VeChuyenBayBLL();
        bllTS = new ThamSoBLL();
        bllHD = new HoaDonBLL();

        model = (DefaultTableModel) tableThongTinVe.getModel();
        ArrayList<Object[]> list = new ArrayList<>();
        list = bllVCB.getVeChuyenBay();
        for (Object[] number : list) {
            model.addRow(number);
        }

        // Listen for changes in the text
        // Listen for changes in the text
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                model.setRowCount(0);
                ArrayList<Object[]> list = new ArrayList<>();
                list = bllVCB.getVeChuyenBayFrom(txtSearch.getText());
                for (Object[] number : list) {
                    model.addRow(number);
                }
            }
        });
        
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
            setText((value == null) ? "hủy" : value.toString());
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
            label = (value == null) ? "hủy" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                if (tableThongTinVe.getValueAt(index, 7).toString() != "Hủy") {
                    System.out.println(tableThongTinVe.getValueAt(index, 0).toString());
                    bllVCB.updateVeChuyenBay(tableThongTinVe.getValueAt(index, 0).toString());
                    String MaHoaDon = bllVCB.getMaHoaDon(tableThongTinVe.getValueAt(index, 0).toString());
                    System.out.println(MaHoaDon);
                    bllHD.updateHoaDon(MaHoaDon, bllTS.getTienHuyVe());
                    tableThongTinVe.setValueAt("Hủy", index, 7);
                    Notification.show(jLabel4, "Hủy vé thành công" ,true);
                } else {

                }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThongTinVe = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(99, 117, 131));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH VÉ ĐÃ BÁN ");
        jLabel1.setName(""); // NOI18N
        jLabel1.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Thông báo");

        tableThongTinVe.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        tableThongTinVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vé", "Mã chuyến bay", "Giá tiền", "Họ và tên", "CMND", "Số điện thoại", "Email", "Trạng thái", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableThongTinVe.setRowHeight(32);
        jScrollPane2.setViewportView(tableThongTinVe);
        if (tableThongTinVe.getColumnModel().getColumnCount() > 0) {
            tableThongTinVe.getColumnModel().getColumn(8).setResizable(false);
            tableThongTinVe.getColumnModel().getColumn(8).setPreferredWidth(15);
        }

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setText("Tìm kiếm:");

        txtSearch.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(51, 51, 51));
        txtSearch.setToolTipText("Số điện thoại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 390, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableThongTinVe;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
