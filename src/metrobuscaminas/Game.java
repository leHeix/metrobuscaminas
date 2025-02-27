/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import metrobuscaminas.interfaces.*;

/**
 *
 * @author Naim
 */
public class Game {
    private GameWindow window;
    private int row_count;
    private int column_count;
    private int mine_count;
    
    public Game(MainMenu menu, int row_count, int column_count, int mine_count)
    {
        this.window = new GameWindow(menu);
        this.row_count = row_count;
        this.column_count = column_count;
        this.mine_count = mine_count;
    }
    
    public void show_game_window()
    {
        this.window.setVisible(true);
    }
}
