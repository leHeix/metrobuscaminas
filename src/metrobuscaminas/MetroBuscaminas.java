/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metrobuscaminas;

import metrobuscaminas.interfaces.MainMenu;

/**
 * Clase principal, encargada de abrir la ventana del menú.
 * 
 * @version 07/03/2025
 * @author Naim
 */
public class MetroBuscaminas 
{
    /**
     * Función llamada al inicio del programa.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing"); 
        MainMenu menu = new MainMenu();
    }
    
}
