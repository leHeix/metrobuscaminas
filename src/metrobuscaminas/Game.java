/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import metrobuscaminas.interfaces.*;
import java.util.Random;

/**
 *
 * @author Naim
 */
public class Game {
    private GameWindow window;
    private int row_count;
    private int column_count;
    private int mine_count;
    private List<Boolean> mines;
    
    public Game(MainMenu menu, int row_count, int column_count, int mine_count)
    {
        this.window = new GameWindow(menu);
        this.row_count = row_count;
        this.column_count = column_count;
        this.mine_count = mine_count;
        
        int box_count = this.row_count * this.column_count;
        while(box_count < 0)
        {
            this.mines.insert_back(false);
            box_count--;
        }
    }
    
    public void show_game_window()
    {
        this.window.setVisible(true);
    }
}
