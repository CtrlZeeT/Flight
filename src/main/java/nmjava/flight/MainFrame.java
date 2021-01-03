/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author xuanluan
 */
public class MainFrame extends javax.swing.JFrame {

    private enum typePanel {
        TICKET, SEARCH, STATISTIC, REGULATE
    };

    private Component currentPanel;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setIcon();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension sizeScreen = getToolkit().getScreenSize();
        this.setLocation((sizeScreen.width - this.getWidth()) / 2, (sizeScreen.height - this.getHeight()) / 2);
        setUnvisibleSelect();
        currentPanel = null;

        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (currentPanel != null) {
                    currentPanel.setSize(e.getWindow().getSize());
                }
            }
        });
        pnFunction.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (currentPanel != null) {
                    currentPanel.setSize(e.getComponent().getSize());
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }
        });
    }

    private void setIcon() {
        String directResource = System.getProperty("user.dir") + "\\resource\\";
        lbNameApp.setIcon(new ImageIcon(directResource + "NameApp.png"));
        lbImageTicket.setIcon(new ImageIcon(directResource + "Ticket.png"));
        lbImageSearch.setIcon(new ImageIcon(directResource + "Search_silver.png"));
        lbImageStatistic.setIcon(new ImageIcon(directResource + "dashboard.png"));
        lbImageRegulate.setIcon(new ImageIcon(directResource + "regulator.png"));
    }

    private void setUnvisibleSelect() {
        pnSelectTicket.setBackground(pnButton.getBackground());
        pnSelectSearch.setBackground(pnButton.getBackground());
        pnSelectStatistic.setBackground(pnButton.getBackground());
        pnSelectRegulate.setBackground(pnButton.getBackground());

        btnTicket.setBackground(new Color(50, 57, 66));
        btnSearch.setBackground(new Color(50, 57, 66));
        btnStatistic.setBackground(new Color(50, 57, 66));
        btnRegulate.setBackground(new Color(50, 57, 66));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbContent = new javax.swing.JLabel();
        lbNameApp = new javax.swing.JLabel();
        pnButton = new javax.swing.JPanel();
        btnTicket = new javax.swing.JPanel();
        pnSelectTicket = new javax.swing.JPanel();
        lbTicket = new javax.swing.JLabel();
        lbImageTicket = new javax.swing.JLabel();
        btnSearch = new javax.swing.JPanel();
        pnSelectSearch = new javax.swing.JPanel();
        lbSearch = new javax.swing.JLabel();
        lbImageSearch = new javax.swing.JLabel();
        btnStatistic = new javax.swing.JPanel();
        pnSelectStatistic = new javax.swing.JPanel();
        lbStatistic = new javax.swing.JLabel();
        lbImageStatistic = new javax.swing.JLabel();
        btnRegulate = new javax.swing.JPanel();
        pnSelectRegulate = new javax.swing.JPanel();
        lbRegulate = new javax.swing.JLabel();
        lbImageRegulate = new javax.swing.JLabel();
        pnFunction = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(652, 74));

        lbContent.setBackground(new java.awt.Color(1, 156, 225));
        lbContent.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbContent.setForeground(new java.awt.Color(255, 255, 255));
        lbContent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbContent.setText("Màn hình chính");
        lbContent.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lbNameApp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(lbContent, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContent, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameApp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnButton.setBackground(new java.awt.Color(43, 50, 59));

        btnTicket.setBackground(new java.awt.Color(50, 57, 66));
        btnTicket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTicketMouseClicked(evt);
            }
        });

        pnSelectTicket.setBackground(new java.awt.Color(1, 156, 225));

        javax.swing.GroupLayout pnSelectTicketLayout = new javax.swing.GroupLayout(pnSelectTicket);
        pnSelectTicket.setLayout(pnSelectTicketLayout);
        pnSelectTicketLayout.setHorizontalGroup(
            pnSelectTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnSelectTicketLayout.setVerticalGroup(
            pnSelectTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbTicket.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbTicket.setForeground(new java.awt.Color(192, 192, 192));
        lbTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTicket.setText("Bán vé");

        javax.swing.GroupLayout btnTicketLayout = new javax.swing.GroupLayout(btnTicket);
        btnTicket.setLayout(btnTicketLayout);
        btnTicketLayout.setHorizontalGroup(
            btnTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTicketLayout.createSequentialGroup()
                .addComponent(pnSelectTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImageTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnTicketLayout.setVerticalGroup(
            btnTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTicketLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(btnTicketLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnSelectTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbImageTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTicket, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
        );

        btnSearch.setBackground(new java.awt.Color(50, 57, 66));
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        pnSelectSearch.setBackground(new java.awt.Color(1, 156, 225));

        javax.swing.GroupLayout pnSelectSearchLayout = new javax.swing.GroupLayout(pnSelectSearch);
        pnSelectSearch.setLayout(pnSelectSearchLayout);
        pnSelectSearchLayout.setHorizontalGroup(
            pnSelectSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnSelectSearchLayout.setVerticalGroup(
            pnSelectSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lbSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbSearch.setForeground(new java.awt.Color(192, 192, 192));
        lbSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSearch.setText("Tra cứu");

        javax.swing.GroupLayout btnSearchLayout = new javax.swing.GroupLayout(btnSearch);
        btnSearch.setLayout(btnSearchLayout);
        btnSearchLayout.setHorizontalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSearchLayout.createSequentialGroup()
                .addComponent(pnSelectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbImageSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnSearchLayout.setVerticalGroup(
            btnSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSearchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnSelectSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbImageSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnStatistic.setBackground(new java.awt.Color(50, 57, 66));
        btnStatistic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnStatisticMouseClicked(evt);
            }
        });

        pnSelectStatistic.setBackground(new java.awt.Color(1, 156, 225));

        javax.swing.GroupLayout pnSelectStatisticLayout = new javax.swing.GroupLayout(pnSelectStatistic);
        pnSelectStatistic.setLayout(pnSelectStatisticLayout);
        pnSelectStatisticLayout.setHorizontalGroup(
            pnSelectStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnSelectStatisticLayout.setVerticalGroup(
            pnSelectStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lbStatistic.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbStatistic.setForeground(new java.awt.Color(192, 192, 192));
        lbStatistic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStatistic.setText("Thống kê");

        javax.swing.GroupLayout btnStatisticLayout = new javax.swing.GroupLayout(btnStatistic);
        btnStatistic.setLayout(btnStatisticLayout);
        btnStatisticLayout.setHorizontalGroup(
            btnStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnStatisticLayout.createSequentialGroup()
                .addComponent(pnSelectStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbImageStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 115, Short.MAX_VALUE))
        );
        btnStatisticLayout.setVerticalGroup(
            btnStatisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnStatisticLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnSelectStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbImageStatistic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbStatistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnRegulate.setBackground(new java.awt.Color(50, 57, 66));
        btnRegulate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegulateMouseClicked(evt);
            }
        });

        pnSelectRegulate.setBackground(new java.awt.Color(1, 156, 225));

        javax.swing.GroupLayout pnSelectRegulateLayout = new javax.swing.GroupLayout(pnSelectRegulate);
        pnSelectRegulate.setLayout(pnSelectRegulateLayout);
        pnSelectRegulateLayout.setHorizontalGroup(
            pnSelectRegulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnSelectRegulateLayout.setVerticalGroup(
            pnSelectRegulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lbRegulate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbRegulate.setForeground(new java.awt.Color(192, 192, 192));
        lbRegulate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRegulate.setText("Quy định");

        javax.swing.GroupLayout btnRegulateLayout = new javax.swing.GroupLayout(btnRegulate);
        btnRegulate.setLayout(btnRegulateLayout);
        btnRegulateLayout.setHorizontalGroup(
            btnRegulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRegulateLayout.createSequentialGroup()
                .addComponent(pnSelectRegulate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbImageRegulate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbRegulate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btnRegulateLayout.setVerticalGroup(
            btnRegulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnRegulateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnSelectRegulate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbImageRegulate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbRegulate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbRegulate.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout pnButtonLayout = new javax.swing.GroupLayout(pnButton);
        pnButton.setLayout(pnButtonLayout);
        pnButtonLayout.setHorizontalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnStatistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRegulate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnButtonLayout.setVerticalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonLayout.createSequentialGroup()
                .addComponent(btnTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegulate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnFunction.setBackground(new java.awt.Color(239, 243, 246));

        javax.swing.GroupLayout pnFunctionLayout = new javax.swing.GroupLayout(pnFunction);
        pnFunction.setLayout(pnFunctionLayout);
        pnFunctionLayout.setHorizontalGroup(
            pnFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        pnFunctionLayout.setVerticalGroup(
            pnFunctionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnFunction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnFunction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnButton.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTicketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTicketMouseClicked
        // TODO add your handling code here:
        setPanel(evt.getComponent(), typePanel.TICKET);
    }//GEN-LAST:event_btnTicketMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // TODO add your handling code here:
        setPanel(evt.getComponent(), typePanel.SEARCH);
    }//GEN-LAST:event_btnSearchMouseClicked

    private void btnStatisticMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStatisticMouseClicked
        // TODO add your handling code here:
        setPanel(evt.getComponent(), typePanel.STATISTIC);
    }//GEN-LAST:event_btnStatisticMouseClicked

    private void btnRegulateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegulateMouseClicked
        // TODO add your handling code here:
        setPanel(evt.getComponent(), typePanel.REGULATE);
    }//GEN-LAST:event_btnRegulateMouseClicked

    private void setPanel(Component button, typePanel type) {
        setUnvisibleSelect();
        button.setBackground(new Color(99, 117, 131));
        switch (type) {
            case TICKET:
                lbContent.setText("Bán vé chuyến bay");
                pnSelectTicket.setBackground(lbContent.getBackground());
                break;
            case SEARCH:
                lbContent.setText("Tra cứu chuyến bay");
                pnSelectSearch.setBackground(lbContent.getBackground());
                currentPanel = new SearchPanel();
                break;
            case STATISTIC:
                lbContent.setText("Thống kê doanh thu");
                pnSelectStatistic.setBackground(lbContent.getBackground());
                break;
            case REGULATE:
                lbContent.setText("Quy định chung");
                pnSelectRegulate.setBackground(lbContent.getBackground());
                break;
        }

        if (currentPanel == null) {
            return;
        }
        pnFunction.removeAll();
        currentPanel.setSize(pnFunction.getSize());
        pnFunction.add(currentPanel);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnRegulate;
    private javax.swing.JPanel btnSearch;
    private javax.swing.JPanel btnStatistic;
    private javax.swing.JPanel btnTicket;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbContent;
    private javax.swing.JLabel lbImageRegulate;
    private javax.swing.JLabel lbImageSearch;
    private javax.swing.JLabel lbImageStatistic;
    private javax.swing.JLabel lbImageTicket;
    private javax.swing.JLabel lbNameApp;
    private javax.swing.JLabel lbRegulate;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbStatistic;
    private javax.swing.JLabel lbTicket;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnFunction;
    private javax.swing.JPanel pnSelectRegulate;
    private javax.swing.JPanel pnSelectSearch;
    private javax.swing.JPanel pnSelectStatistic;
    private javax.swing.JPanel pnSelectTicket;
    // End of variables declaration//GEN-END:variables
}
