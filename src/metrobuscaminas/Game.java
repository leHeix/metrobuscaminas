/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metrobuscaminas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JLabel;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import metrobuscaminas.interfaces.*;
import org.graphstream.graph.implementations.AdjacencyListGraph;

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
    private Map<String, List<String>> adjacency_list;
    private AdjacencyListGraph graph;
    private boolean game_lost;
    private boolean use_dfs;
    private int click_count;
    private int flag_count;
    private int clicked_boxes;
    private Queue<MineBox> clicked_track;
    
    public class MineBox
    {
        private int index;
        private char column;
        private int row;
        
        private JLabel button;
        private org.graphstream.graph.Node node;
        private boolean mine; 
        private boolean revealed;
        private boolean has_flag;
        
        public MineBox(int index)
        {
            this.index = index;
            this.revealed = false;
            this.has_flag = false;
            this.mine = false;
            
            this.button = new JLabel();
            this.button.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e)
                {
                    if(Game.this.game_lost || MineBox.this.revealed)
                        return;
                                
                    if(SwingUtilities.isLeftMouseButton(e))
                    {
                        if(MineBox.this.has_flag)
                            return;
                        
                        Game.this.add_click();
                        
                        if(MineBox.this.mine)
                        {
                            Game.this.game_lost = true;
                            Game.this.reveal_all_mines(MineBox.this);
                            return;
                        }
                        
                        if(Game.this.use_dfs)
                        {
                            Game.this.perform_search_dfs(MineBox.this);
                        }
                        else
                        {
                            Game.this.perform_search_bfs(MineBox.this);
                        }
                    }
                    else if(SwingUtilities.isRightMouseButton(e))
                    {
                        if(Game.this.flag_count <= 0 && !MineBox.this.has_flag)
                            return;
                        
                        Game.this.add_click();
                        
                        MineBox.this.has_flag = !MineBox.this.has_flag;
                        if(MineBox.this.has_flag)
                        {
                            Game.this.flag_count--;
                            Game.this.clicked_boxes++;
                        }
                        else
                        {
                            Game.this.flag_count++;
                            Game.this.clicked_boxes--;
                        }
                        
                        Game.this.window.update_flag_count(Game.this.flag_count);
                        Game.this.window.set_box_flag(MineBox.this.button, MineBox.this.has_flag);
                        
                        Game.this.check_for_victory();
                    }
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
        public String get_identifier() { return String.format("%c%d", this.column, this.row); }
    }
    
    public Game(MainMenu menu, int row_count, int column_count, int mine_count, boolean use_dfs)
    {
        this.game_lost = false;
        this.use_dfs = use_dfs;
        this.click_count = 0;
        this.clicked_boxes = 0;
        this.row_count = row_count;
        this.column_count = column_count;
        this.mine_count = mine_count;
        this.flag_count = mine_count;
        this.boxes = new List<MineBox>();
        this.clicked_track = new Queue<>();
        this.menu = menu;
        this.adjacency_list = new Map<>();
        this.graph = new AdjacencyListGraph("GRID", false, true);
        this.graph.setAttribute("ui.stylesheet", "node{\n" +
"    size: 10px, 10px;\n" +
"    shape: circle;\n" +
"    fill-color: #B1DFF7;\n" +
"    stroke-mode: plain;\n" +
"    stroke-color: #B1DFF7;\n" +
"    stroke-width: 3;\n" +
"    text-mode: normal; /*normal or hidden*/\n" +
"    text-background-mode: plain; /*plain or none*/\n" +
"    text-background-color: rgba(255, 255, 255, 200);\n" +
"    text-alignment: above;\n" +
"}"
                + "node.marked {"
                + "fill-color: red;"
                + "}");
    }
    
    public boolean load_from_file(File f)
    {
        try
        {
           BufferedReader br = new BufferedReader(new FileReader(f));
           String line = br.readLine();
           String[] values = line.split(",");
           
           this.row_count = Integer.parseInt(values[0]);
           this.column_count = Integer.parseInt(values[1]);
           this.click_count = Integer.parseInt(values[2]);
           this.clicked_boxes = Integer.parseInt(values[3]);
           this.use_dfs = (Integer.parseInt(values[4]) == 1);
           
           this.initialize_window(false);
           this.window.update_click_count(this.click_count);
           
           Node<MineBox> box_node = this.boxes.get_first_node();
           
           for(int i = 5; i < (this.row_count * this.column_count) + 5 && box_node != null; ++i)
           {
               MineBox box = box_node.getValue();
               
               switch(values[i])
               {
                   case "0":
                   {
                       box.revealed = false;
                       break;
                   }
                   case "1":
                   case "2":
                   case "3":
                   case "4":
                   case "5":
                   case "6":
                   case "7":
                   {
                       box.revealed = true;
                       this.window.reveal_box(box.button, Integer.parseInt(values[i]) - 1);
                       break;
                   }
                   case "8":
                   {
                       box.mine = true;
                       this.mine_count++;
                       this.flag_count++;
                       break;
                   }
                   case "9":
                   {
                       box.has_flag = true;
                       box.mine = false;
                       this.flag_count--;
                       this.window.set_box_flag(box.button, true);
                       break;
                   }
                   case "10":
                   {
                       box.has_flag = true;
                       box.mine = true;
                       this.mine_count++;
                       this.flag_count--;
                       this.window.set_box_flag(box.button, true);
                       break;
                   }
               }
               
               box_node = box_node.getNext();
           }
           
           this.window.update_flag_count(this.flag_count);
        }
        catch(java.io.IOException | NumberFormatException e)
        {
            return false;
        }
        
        return true;
    }
    
    public void reset_game()
    {
        this.game_lost = false;
        this.click_count = 0;
        this.flag_count = this.mine_count;
        this.clicked_boxes = 0;
        
        this.window.update_click_count(0);
        this.window.update_flag_count(this.mine_count);
        
        Node<MineBox> box_node = this.boxes.get_first_node();
        while(box_node != null)
        {
            MineBox box = box_node.getValue();
            
            box.revealed = false;
            box.mine = false;
            box.has_flag = false;
            this.window.reset_box(box.button);
            
            box_node = box_node.getNext();
        }
        
        this.assign_mines();
    }
    
    public void initialize_window(boolean assign_mines)
    {
        this.window = new GameWindow(menu, this);
        
        if(assign_mines)
            this.assign_mines();
        
        Node<MineBox> mb_node = this.boxes.get_first_node();
        
        while(mb_node != null)
        {
            MineBox box = mb_node.getValue();
            char[] neighbor_columns = {
                (char)((int)mb_node.getValue().get_column() - 1),
                mb_node.getValue().get_column(),
                (char)((int)mb_node.getValue().get_column() + 1)
            };
            
            for(int i = 0; i < neighbor_columns.length; ++i)
            {
                if(this.is_valid_box(neighbor_columns[i], box.get_row() - 1))
                {
                    String node1 = String.format("%c%d", neighbor_columns[i], box.get_row() - 1);
                    String node2 = String.format("%c%d", box.get_column(), box.get_row());
                    
                    if(this.graph.getEdge(node1 + node2) == null && this.graph.getEdge(node2 + node1) == null)
                    {
                        this.graph.addEdge(node1 + node2, node1, node2).setAttribute("ui.label", node1 + node2);
                        this.adjacency_list.get(node1).get().insert_back(node2);
                        this.adjacency_list.get(node2).get().insert_back(node1);
                    }
                }
                
                if(this.is_valid_box(neighbor_columns[i], box.get_row()) && neighbor_columns[i] != box.get_column())
                {
                    String node1 = String.format("%c%d", neighbor_columns[i], box.get_row());
                    String node2 = String.format("%c%d", box.get_column(), box.get_row());
                    
                    if(this.graph.getEdge(node1 + node2) == null && this.graph.getEdge(node2 + node1) == null)
                    {
                        this.graph.addEdge(node1 + node2, node1, node2).setAttribute("ui.label", node1 + node2);
                        this.adjacency_list.get(node1).get().insert_back(node2);
                        this.adjacency_list.get(node2).get().insert_back(node1);
                    }
                }
                
                if(this.is_valid_box(neighbor_columns[i], box.get_row() + 1))
                {
                    String node1 = String.format("%c%d", neighbor_columns[i], box.get_row() + 1);
                    String node2 = String.format("%c%d", box.get_column(), box.get_row());
                    
                    if(this.graph.getEdge(node1 + node2) == null && this.graph.getEdge(node2 + node1) == null)
                    {
                        this.graph.addEdge(node1 + node2, node1, node2).setAttribute("ui.label", node1 + node2);
                        this.adjacency_list.get(node1).get().insert_back(node2);
                        this.adjacency_list.get(node2).get().insert_back(node1);
                    }
                }
            }
            
            mb_node = mb_node.getNext();
        }
        
        this.graph.display();
    }
    
    public void register_box(MineBox box)
    {
        this.boxes.insert_back(box);

        String node = String.format("%c%d", box.get_column(), box.get_row());
        this.adjacency_list.insert(node, new List<>());
        box.node = this.graph.addNode(node);
        box.node.setAttribute("ui.label", node);
    }
    
    private void assign_mines()
    {
        int fixed_box_count = this.column_count * this.row_count;
        
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
    
    public void perform_search_dfs(MineBox starting)
    {
        List<String> visited = new List<>();
        
        Stack<String> stack = new Stack<>();
        stack.push(starting.get_identifier());
        
        while(stack.size() != 0)
        {
            String current = stack.pop();
            
            if(visited.has_value(current) == -1)
            {
                visited.insert_back(current);
                
                List<String> neighbors = this.adjacency_list.get(current).get();
                Node<String> neighbor_node = neighbors.get_first_node();
                int mine_count = 0;
                
                while(neighbor_node != null)
                {
                    MineBox neighbor = this.get_at(neighbor_node.getValue());
                    if(neighbor.is_mine())
                        mine_count++;
                    
                    neighbor_node = neighbor_node.getNext();
                }
                
                MineBox current_box = this.get_at(current);
                
                if(mine_count > 0)
                {
                    this.window.reveal_box(current_box.get_button(), mine_count);
                    this.clicked_boxes++;
                    current_box.revealed = true;
                    continue;
                }
                else
                {                    
                    if(!current_box.has_flag)
                    {
                        this.window.reveal_box(current_box.get_button(), 0);
                        this.clicked_boxes++;
                        current_box.revealed = true;
                    }
                    else
                        continue;
                }
                
                
                neighbor_node = neighbors.get_first_node();
                while(neighbor_node != null)
                {
                    String neighbor = neighbor_node.getValue();
                    if(visited.has_value(neighbor) == -1)
                    {
                        stack.push(neighbor);
                    }
                    neighbor_node = neighbor_node.getNext();
                }
            }
        }
        
        this.check_for_victory();
    }
    
    public void perform_search_bfs(MineBox starting)
    {        
        List<String> visited = new List<>();
        
        Queue<String> queue = new Queue<>();
        queue.add(starting.get_identifier());
        
        while(queue.size() != 0)
        {
            String current = queue.poll();
            
            if(visited.has_value(current) == -1)
            {
                visited.insert_back(current);
                
                List<String> neighbors = this.adjacency_list.get(current).get();
                Node<String> neighbor_node = neighbors.get_first_node();
                int mine_count = 0;
                
                while(neighbor_node != null)
                {
                    MineBox neighbor = this.get_at(neighbor_node.getValue());
                    if(neighbor.is_mine())
                        mine_count++;
                    
                    neighbor_node = neighbor_node.getNext();
                }
                
                MineBox current_box = this.get_at(current);
                
                if(mine_count > 0)
                {
                    this.window.reveal_box(current_box.get_button(), mine_count);
                    this.clicked_boxes++;
                    current_box.revealed = true;
                    continue;
                }
                else
                {
                    if(!current_box.has_flag)
                    {
                        this.window.reveal_box(current_box.get_button(), 0);
                        this.clicked_boxes++;
                        current_box.revealed = true;
                    }
                    else
                        continue;
                }
                
                
                neighbor_node = neighbors.get_first_node();
                while(neighbor_node != null)
                {
                    String neighbor = neighbor_node.getValue();
                    if(visited.has_value(neighbor) == -1)
                    {
                        queue.add(neighbor);
                    }
                    neighbor_node = neighbor_node.getNext();
                }
            }
        }
        
        this.check_for_victory();
    }
    
    public void reveal_all_mines(MineBox pressed)
    {
        Node<MineBox> node = this.boxes.get_first_node();
        while(node != null)
        {
            if(node.getValue().is_mine())
            { 
                this.window.reveal_mine(node.getValue().get_button(), (node.getValue() == pressed ? true : false));
            }
            
            node = node.getNext();
        }
    }
    
    public void add_click()
    {
        this.click_count++;
        this.window.update_click_count(this.click_count);
    }
    
    public void check_for_victory()
    {
        if(this.clicked_boxes >= (this.get_columns() * this.get_rows()))
        {
            Node<MineBox> box_node = this.boxes.get_first_node();
            
            int correct_flags = 0;
            
            while(box_node != null)
            {
                MineBox box = box_node.getValue();
                
                if(box.has_flag && box.mine)
                    correct_flags++;
                else if(!box.revealed)
                    return;
                
                box_node = box_node.getNext();
            }
            
            if(correct_flags == this.mine_count)
            {
                this.game_lost = true;
                
                // Anunciar mensaje de victoria
                JOptionPane.showMessageDialog(null, "¡Victoria! Limpiaste el tablero correctamente. Presiona X para cambiar las opciones o el botón carita para volver a empezar.", "¡Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void show_game_window()
    {
        this.window.setVisible(true);
    }
    
    public int get_columns() { return this.column_count; }
    public int get_rows() { return this.row_count; }
    public int get_mine_count() { return this.mine_count; }
    
    // 'A', 1 -> return MineBox at index 0
    public MineBox get_at(char column, int row)
    {
        char upper_column = Character.toUpperCase(column); // fail-safe para casos como 'a', 'b'
        Node<MineBox> mb_node = this.boxes.get_first_node();
        
        while(mb_node != null)
        {
            MineBox box = mb_node.getValue();
            if(box.get_row() == row && box.get_column() == upper_column)
                return box;
            
            mb_node = mb_node.getNext();
        }
        
        return null;
    }
    
    public MineBox get_at(String identifier)
    {
        Node<MineBox> mb_node = this.boxes.get_first_node();
        
        while(mb_node != null)
        {
            MineBox box = mb_node.getValue();
            if(box.get_identifier().equals(identifier))
                return box;
            
            mb_node = mb_node.getNext();
        }
        
        return null;
    }
    
    public boolean is_valid_box(char column, int row)
    {        
        if(column < 'A' || column >= 'A' + this.get_columns())
            return false;
        
        if(row < 1 || row > this.get_rows())
            return false;
        
        return true;
    }
    
    public boolean has_ended() { return this.game_lost; }
    
    public boolean save_to_file()
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo CSV", "csv");
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        
        int return_val = chooser.showSaveDialog(null);
        if(return_val == JFileChooser.APPROVE_OPTION)
        {
            File f = chooser.getSelectedFile();
            
            /*
                Estructura del archivo de juego, en CSV:
                [cantidad_filas],[cantidad_columnas],[numero_clicks],[casillas_descubiertas],[algoritmo_de_busqueda],[array_casillas],[array_recorrido]
                
                algoritmo_de_busqueda puede ser 1 (Depth-First Search) o 0 (Breadth-First Search)
                array_casillas contiene toda la información sobre las casillas, almacenadas en estados (números) que corresponden a:
                0 -> casilla cerrada
                1 -> casilla abierta, vacía
                2, 7 -> casilla abierta, número del 1 al 7
                8 -> mina
                9 -> bandera, casilla vacía
                10 -> bandera, casilla mina
            
                array_recorrido contiene, ordenadamente, el recorrido de casillas que ha presionado el jugador
            */
            
            String data = String.format("%d,%d,%d,%d,%d,", this.row_count, this.column_count, this.click_count, this.clicked_boxes, (this.use_dfs ? 1 : 0));
            
            Node<MineBox> box_node = this.boxes.get_first_node();
            while(box_node != null)
            {
                MineBox box = box_node.getValue();
                
                if(box.mine)
                {
                    if(box.has_flag)
                        data += "10,";
                    else
                        data += "8,";
                }
                else if(box.has_flag && !box.mine)
                    data += "9,";
                else if(!box.revealed)
                    data += "0,";
                else if(box.revealed)
                {
                    int box_number = this.window.get_box_number_from_icon(box.get_button());
                    data += String.format("%d,", box_number + 1);
                }
                
                box_node = box_node.getNext();
            }
            
            try
            {
                PrintWriter writer = new PrintWriter(f, "UTF-8");
                writer.write(data);
                writer.close();
            }
            catch(java.io.FileNotFoundException | java.io.UnsupportedEncodingException e)
            {
                Utils.showMessageError("Error al guardar partida", "No se pudo escribir al archivo.");
                return false;
            }
            
            return true;
        }
        
        return false;
    }
}
