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
public class Game 
{
    private GameWindow window;
    private MainMenu menu;
    private int row_count;
    private int column_count;
    private int mine_count;
    private List<Boolean> mines;
    
    public Game(MainMenu menu, int row_count, int column_count, int mine_count)
    {
        this.row_count = row_count;
        this.column_count = column_count;
        this.mine_count = mine_count;
        this.mines = new List<Boolean>();
        this.menu = menu;
       
        this.assign_mines();
    }
    
    public void initialize_window()
    {
        this.window = new GameWindow(menu, this);
    }
    
    private void assign_mines()
    {
        int fixed_box_count = this.column_count * this.row_count;
        int box_count = fixed_box_count;
        
        while(box_count > 0)
        {
            this.mines.insert_back(fixed_box_count == this.mine_count ? true : false);
            box_count--;
        }
        
        if(fixed_box_count == this.mine_count)
            return;
        
        Random rng = new Random();
        int assigned_mines = 0;
        
        do
        {
            int box;
            
            do
            {
                box = rng.nextInt(fixed_box_count);
            }
            while(this.mines.get(box).get() == true);
            
            this.mines.set(box, true);
            assigned_mines++;
        }
        while(assigned_mines < this.mine_count);
    }
    
    public void show_game_window()
    {
        this.window.setVisible(true);
    }
    
    public int get_columns() { return this.column_count; }
    public int get_rows() { return this.row_count; }
    public int get_mine_count() { return this.mine_count; }
}
