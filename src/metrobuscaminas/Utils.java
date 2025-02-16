/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author verde
 */
public class Utils {
    public static void showMessageError(String title, String message)
    {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        dialog.dispose();
    }
}
