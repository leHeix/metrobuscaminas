/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
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
    private List<MineBox> boxes;
    
    public class MineBox
    {
        private int index;
        private char column;
        private int row;
        
        private JLabel button;
        private boolean mine; 
        
        public MineBox(int index)
        {
            this.index = index;
            
            this.button = new JLabel();
            this.button.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e)
                {
                }
            });
        }
        
        public JLabel get_button() { return this.button; }
        public void set_mine(boolean set) 
        { 
            this.mine = set;
        }
        public boolean is_mine() { return this.mine; }
        public void set_identifier(char column, int row)
        {
            this.column = column;
            this.row = row;
        }
        
        public char get_column() { return this.column; }
        public int get_row() { return this.row; }
    }
    
    public Game(MainMenu menu, int row_count, int column_count, int mine_count)
    {
        this.row_count = row_count;
        this.column_count = column_count;
        this.mine_count = mine_count;
        this.boxes = new List<MineBox>();
        this.menu = menu;
    }
    
    public void initialize_window()
    {
        this.window = new GameWindow(menu, this);
        this.assign_mines();
    }
    
    public void register_box(MineBox box)
    {
        this.boxes.insert_back(box);
    }
    
    private void assign_mines()
    {
        int fixed_box_count = this.column_count * this.row_count;
        int box_count = fixed_box_count;
        
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
            while(this.boxes.get(box).get().is_mine() == true);
            
            this.boxes.get(box).get().set_mine(true);
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
