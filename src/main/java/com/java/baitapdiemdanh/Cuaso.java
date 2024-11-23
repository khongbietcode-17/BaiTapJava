/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.java.baitapdiemdanh;

/**
 *
 * @author nhattien
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Cuaso extends javax.swing.JFrame implements ActionListener, MouseListener {

    /**
     * Creates new form Cuaso
     */
    Connection con = null;
    Statement st;
    String sql;
    DefaultTableModel dtm = new DefaultTableModel(new String[][]{}, new String[]{"Mã số", "Tên", "Điểm"});

    public Cuaso() {
        initComponents();
        jTable1.setModel(dtm);
        jTable1.setAutoCreateRowSorter(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int d = jTable1.getSelectedRow();
        String masv = (String) jTable1.getValueAt(d, 0);
        jTextField1.setText(masv);
        String tensv = (String) jTable1.getValueAt(d, 1);
        jTextField2.setText(tensv);
        String diemsv = (String) jTable1.getValueAt(d, 2);
        jTextField3.setText(diemsv);
    }

    public void Xoabang() {
        int dongcuoi = dtm.getRowCount();
        for (int i = dongcuoi - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
    }

    public double NapdulieuchoTable() {
        Double tb = 0.0;
        sql = "Select * From BANGDIEM";
        try {
            Xoabang();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String msv = rs.getString(1);
                String ten = rs.getString(2);
                String diem = rs.getString(3);
                tb += Double.parseDouble(diem);
                dtm.addRow(new String[]{msv, ten, diem});
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        dtm.fireTableStructureChanged();
        return tb / dtm.getRowCount();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton1) {
            KetnoiCSDL();
            jScrollPane1.setVisible(true);
            double tb = NapdulieuchoTable();
            jTextField4.setText(String.valueOf(tb));
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == jButton2) {
            KetnoiCSDL();
            String m = jTextField1.getText();
            String t = jTextField2.getText();
            String d = jTextField3.getText();
            sql = "insert into BANGDIEM values (?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, m);//1,2,3 là vị trí tương ứng của dấu ?
                ps.setString(2, t);
                ps.setString(3, d);
                ps.executeUpdate();
                dtm.addRow(new String[]{m, t, d});
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            dtm.fireTableStructureChanged();
            double tb = NapdulieuchoTable();
            jTextField4.setText(String.valueOf(tb));
            jScrollPane1.setVisible(true);
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == jButton3) {
            KetnoiCSDL();
            String m = jTextField1.getText();
            sql = "delete from BANGDIEM where masv=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, m);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            double tb = NapdulieuchoTable();
            jTextField4.setText(String.valueOf(tb));
            jScrollPane1.setVisible(true);
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == jButton4) {
            KetnoiCSDL();
            String m = jTextField1.getText();
            String t = jTextField2.getText();
            String d = jTextField3.getText();
            sql = "update BANGDIEM set TEN=?,DIEM=? where MASV=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, t);//1,2,3 là vị trí tương ứng của dấu ?
                ps.setString(2, d);
                ps.setString(3, m);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            double tb = NapdulieuchoTable();
            jTextField4.setText(String.valueOf(tb));
            jScrollPane1.setVisible(true);
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    private void KetnoiCSDL()
    {
           String username = "root";
           String password = "";
           String connectionURL = "jdbc:mysql://127.0.0.1/my_database";
        try {
           con = DriverManager.getConnection(connectionURL, username, password);
        } catch (SQLException ex) {
            System.out.println(ex);
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
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mã sinh viên: ");

        jLabel2.setText("Tên sinh viên: ");

        jLabel3.setText("Điểm: ");

        jTextField1.setName("tfmsv"); // NOI18N

        jTextField2.setName("tften"); // NOI18N

        jTextField3.setName("tfdiem"); // NOI18N

        jButton1.setText("Fill");
        jButton1.setName("filldata"); // NOI18N

        jButton2.setText("Thêm");
        jButton2.setName("them"); // NOI18N

        jButton3.setText("Xóa");
        jButton3.setName("xoa"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Sửa");
        jButton4.setName("sua"); // NOI18N

        jLabel4.setText("Điểm trung bình trong lớp:");

        jTextField4.setName("tftb"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton4))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addGap(28, 28, 28)
                                            .addComponent(jButton2))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                        .addGap(112, 112, 112))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
        );

        jTextField3.getAccessibleContext().setAccessibleName("");
        jTextField3.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Cuaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cuaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cuaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cuaso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Cuaso().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
