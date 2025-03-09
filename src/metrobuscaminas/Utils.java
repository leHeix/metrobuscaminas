/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Clase de utilidades.
 * 
 * @version 16/02/2025
 * @author Naim
 */
public class Utils {
    /**
     * Muestra un mensaje de error en la pantalla.
     * @param title Título de la ventana del mensaje.
     * @param message Cuerpo del mensaje.
     */
    public static void showMessageError(String title, String message)
    {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);    
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        dialog.dispose();
    }
}
