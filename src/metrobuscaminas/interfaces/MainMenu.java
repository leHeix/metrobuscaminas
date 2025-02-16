/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package metrobuscaminas.interfaces;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import metrobuscaminas.Utils;

/**
 *
 * @author Naim
 */
public class MainMenu extends javax.swing.JFrame {
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("MetroBuscaminas | Menú principal");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        row_count = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        column_count = new javax.swing.JComboBox<>();
        play_button = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        mine_count_range_label = new javax.swing.JLabel();
        mine_count_input = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main_title.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        main_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        main_title.setText("MetroBuscaminas");
        getContentPane().add(main_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 10, 390, 26));

        jLabel1.setText("Número de filas:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        jSeparator1.setToolTipText("");
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 45, 205, 10));

        row_count.setMaximumRowCount(8);
        row_count.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9", "10" }));
        row_count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                row_countActionPerformed(evt);
            }
        });
        getContentPane().add(row_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 54, -1));

        jLabel2.setText("Número de columnas:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        column_count.setMaximumRowCount(8);
        column_count.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8", "9", "10" }));
        column_count.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                column_countActionPerformed(evt);
            }
        });
        getContentPane().add(column_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 54, -1));

        play_button.setText("Jugar");
        play_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(play_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 30, 20));

        jLabel3.setText("Número de minas:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        mine_count_range_label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mine_count_range_label.setText("Rango: 1-9");
        getContentPane().add(mine_count_range_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 80, 20));

        mine_count_input.setText("2");
        mine_count_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mine_count_inputActionPerformed(evt);
            }
        });
        getContentPane().add(mine_count_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 50, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void row_countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_row_countActionPerformed
        int max_mine_count = (this.column_count.getSelectedIndex() + 3) * (this.row_count.getSelectedIndex() + 3);
        this.mine_count_range_label.setText("Rango: 1-" + max_mine_count);
    }//GEN-LAST:event_row_countActionPerformed

    private void column_countActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_column_countActionPerformed
        int max_mine_count = (this.column_count.getSelectedIndex() + 3) * (this.row_count.getSelectedIndex() + 3);
        this.mine_count_range_label.setText("Rango: 1-" + max_mine_count);
    }//GEN-LAST:event_column_countActionPerformed

    private void mine_count_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mine_count_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mine_count_inputActionPerformed

    private void play_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_buttonActionPerformed
        int row_count = this.row_count.getSelectedIndex() + 3;
        int column_count = this.column_count.getSelectedIndex() + 3;
        int mine_count;
        
        try
        {
            mine_count = Integer.parseInt(this.mine_count_input.getText());
            if(mine_count < 1 || mine_count > (row_count * column_count))
                throw new IllegalArgumentException();
        }
        catch(NumberFormatException e)
        {
            Utils.showMessageError("Minas inválidas", "El número de minas es inválido. Debe ser un número.");
            return;
        }
        catch(IllegalArgumentException e)
        {
            Utils.showMessageError("Minas inválidas", "El número de minas es inválido. Debe estar entre el rango establecido.");
            return;
        }
    }//GEN-LAST:event_play_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> column_count;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel main_title;
    private javax.swing.JTextField mine_count_input;
    private javax.swing.JLabel mine_count_range_label;
    private javax.swing.JButton play_button;
    private javax.swing.JComboBox<String> row_count;
    // End of variables declaration//GEN-END:variables
}
