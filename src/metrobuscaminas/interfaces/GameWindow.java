/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package metrobuscaminas.interfaces;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import metrobuscaminas.Utils;
import metrobuscaminas.Game;

/**
 *
 * @author Nikos
 */
public class GameWindow extends javax.swing.JFrame {
    private BufferedImage top_mid_image_buf;
    private BufferedImage side_border_image_buf;
    private javax.swing.ImageIcon[] mine_icons;
    private javax.swing.ImageIcon mine_icon_normal;
    private javax.swing.ImageIcon mine_icon_pressed;
    private javax.swing.ImageIcon closed_box_image_icon;
    private MainMenu main_menu;
    private Game game;
    
    /**
     * Creates new form Game
     */
    public GameWindow(MainMenu menu, Game parent) {
        top_mid_image_buf = this.load_image("border_hor.png");
        if(top_mid_image_buf == null)
            System.exit(1);
        
        side_border_image_buf = this.load_image("border_vert.png");
        if(side_border_image_buf == null)
            System.exit(1);
        
        BufferedImage closed_box_image_buf = this.load_image("closed.png");
        if(closed_box_image_buf == null)
            System.exit(1);
        
        BufferedImage open_box_image_buf = this.load_image("empty.png");
        if(open_box_image_buf == null)
            System.exit(1);
        
        BufferedImage mine_icon_image_buf = this.load_image("mine.png");
        if(mine_icon_image_buf == null)
            System.exit(1);
        
        this.mine_icon_normal = new javax.swing.ImageIcon(mine_icon_image_buf);
        
        BufferedImage mine_icon_red_image_buf = this.load_image("mine_red.png");
        if(mine_icon_red_image_buf == null)
            System.exit(1);
        
        this.mine_icon_pressed = new javax.swing.ImageIcon(mine_icon_red_image_buf);
        
        this.mine_icons = new javax.swing.ImageIcon[7];
        this.mine_icons[0] = new javax.swing.ImageIcon(open_box_image_buf);
        
        for(int i = 1; i <= 6; ++i)
        {
            BufferedImage number_image = this.load_image(String.format("%d.png", i));
            if(number_image == null)
                return;
            
            mine_icons[i] = new javax.swing.ImageIcon(number_image);
        }
        
        Font counter_font = this.load_font("digital-7.ttf");
        if(counter_font == null)
            return;
        
        counter_font = counter_font.deriveFont(32f);
        this.closed_box_image_icon = new javax.swing.ImageIcon(closed_box_image_buf);
        
        this.main_menu = menu;
        this.game = parent;
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Inicializar campo de minas
        GridBagLayout game_layout = new GridBagLayout();
        GridBagConstraints layout_constraints = new GridBagConstraints();
        
        this.game_panel.setLayout(game_layout);
        int box_count = this.game.get_rows() * this.game.get_columns();
        
        layout_constraints.insets = new Insets(1, 1, 1, 1);
        layout_constraints.gridx = 0;
        layout_constraints.weightx = 0.0;
        layout_constraints.weighty = 0.0;
        layout_constraints.gridy = 0;
        layout_constraints.fill = GridBagConstraints.HORIZONTAL;
        
        int current_column = (int)'A';
        int current_row = 0;
        
        for(int i = 0; i < box_count; ++i)
        {
            Game.MineBox button = this.game.new MineBox(i);
            javax.swing.JLabel box_button = button.get_button();
            box_button.setIcon(closed_box_image_icon);
            
            box_button.setSize(closed_box_image_icon.getIconWidth(), closed_box_image_icon.getIconHeight());
            box_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            
            current_column++;
            
            // Generar nueva fila al terminarse la actual
            if(i % this.game.get_columns() == 0)
            {
                current_row++;
                current_column = (int)'A';
                layout_constraints.gridy++;
                layout_constraints.gridx = 0;
                layout_constraints.fill = GridBagConstraints.REMAINDER;
            }
            
            game_layout.setConstraints(box_button, layout_constraints);
            
            layout_constraints.gridx++;
            
            this.game_panel.add(box_button);
            button.set_identifier((char)current_column, current_row);
            this.game.register_box(button);
        }
        
        this.setSize(this.getWidth() + (closed_box_image_icon.getIconWidth() * this.game.get_columns()), this.getHeight() + (closed_box_image_icon.getIconHeight() * this.game.get_rows()));
        this.setPreferredSize(this.getSize());
        
        this.resize_icons();
        this.flag_count_label.setFont(counter_font);
        this.timer_label.setFont(counter_font);
        this.update_flag_count(this.game.get_mine_count());
        
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent ev)
            {
                GameWindow.this.resize_icons();
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev)
            {
                int response = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir de la partida?", "Salir", JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION)
                {
                    GameWindow.this.close_game();
                }
            }
        });
        
        this.restart_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                if(SwingUtilities.isLeftMouseButton(e))
                {
                    GameWindow.this.game.reset_game();
                }
            }
        });
    }
    
    private BufferedImage load_image(String name)
    {
        BufferedImage img;
        try
        {
            img = ImageIO.read(getClass().getResourceAsStream("/metrobuscaminas/interfaces/assets/" + name));
        }
        catch(IOException | IllegalArgumentException e)
        {
            Utils.showMessageError("Error de carga de recursos", "No se pudo cargar " + name + ". Asegúrate de que los archivos del juego están íntegros.");
            return null;
        }
        
        return img;
    }
    
    private Font load_font(String name)
    {
        try
        {
            return Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/metrobuscaminas/interfaces/assets/" + name));
        }
        catch(IOException | FontFormatException e)
        {
            Utils.showMessageError("Error de carga de recursos", "No se pudo cargar " + name + ". Asegúrate de que los archivos del juego están íntegros.");
            return null;
        }
    }
    
    private void resize_icons()
    {
        Image mid_panel_bar = top_mid_image_buf.getScaledInstance(this.getSize().width - corner_top_right.getIcon().getIconWidth() - 40, corner_top_middle.getHeight(), Image.SCALE_SMOOTH);
        javax.swing.ImageIcon mid_panel_imageicon = new javax.swing.ImageIcon(mid_panel_bar);
        corner_top_middle.setIcon(mid_panel_imageicon);
        
        Image mid_side_border_image = side_border_image_buf.getScaledInstance(mid_panel_border_left.getWidth(), mid_panel_border_left.getHeight(), Image.SCALE_SMOOTH);
        javax.swing.ImageIcon mid_side_border_imageicon = new javax.swing.ImageIcon(mid_side_border_image);
        mid_panel_border_left.setIcon(mid_side_border_imageicon);
        mid_panel_border_right.setIcon(mid_side_border_imageicon);
        
        Image game_side_border_image = side_border_image_buf.getScaledInstance(game_panel_border_left.getWidth(), game_panel_border_left.getHeight(), Image.SCALE_SMOOTH);
        javax.swing.ImageIcon game_side_border_imagebuf = new javax.swing.ImageIcon(game_side_border_image);
        game_panel_border_left.setIcon(game_side_border_imagebuf);
        game_panel_border_right.setIcon(game_side_border_imagebuf);
        
        panel_separator_middle.setIcon(mid_panel_imageicon);
        
        corner_bottom_mid.setIcon(mid_panel_imageicon);
    }
    
    private void close_game()
    {
        this.setVisible(false);
        this.dispose();
        this.main_menu.setVisible(true);
    }
    
    private void update_flag_count(int count)
    {
        this.flag_count_label.setText(
                (count >= 100 
                    ? "   " + count
                    : (count >= 10 
                        ? "   0" + count 
                        : "   00" + count
                      )
                )
        );
    }
    
    public void reveal_box(JLabel box, int mine_count)
    {
        box.setIcon(this.mine_icons[mine_count]);
    }
    
    public void reveal_mine(JLabel box, boolean pressed)
    {
        box.setIcon(pressed ? this.mine_icon_pressed : this.mine_icon_normal);
    }
    
    public void reset_box(JLabel box)
    {
        box.setIcon(this.closed_box_image_icon);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top_panel = new javax.swing.JPanel();
        corner_top_right = new javax.swing.JLabel();
        corner_top_left = new javax.swing.JLabel();
        corner_top_middle = new javax.swing.JLabel();
        mid_parent_panel = new javax.swing.JPanel();
        mid_panel_border_left = new javax.swing.JLabel();
        mid_panel_border_right = new javax.swing.JLabel();
        mid_panel = new javax.swing.JPanel();
        flag_count_label = new javax.swing.JLabel();
        timer_label = new javax.swing.JLabel();
        restart_button = new javax.swing.JLabel();
        mid_panel_separator = new javax.swing.JPanel();
        panel_separator_left = new javax.swing.JLabel();
        panel_separator_right = new javax.swing.JLabel();
        panel_separator_middle = new javax.swing.JLabel();
        bottom_panel = new javax.swing.JPanel();
        corner_bottom_left = new javax.swing.JLabel();
        corner_bottom_right = new javax.swing.JLabel();
        corner_bottom_mid = new javax.swing.JLabel();
        game_parent_panel = new javax.swing.JPanel();
        game_panel_border_left = new javax.swing.JLabel();
        game_panel_border_right = new javax.swing.JLabel();
        game_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        top_panel.setLayout(new java.awt.BorderLayout());

        corner_top_right.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        corner_top_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/corner_up_right.png"))); // NOI18N
        top_panel.add(corner_top_right, java.awt.BorderLayout.EAST);

        corner_top_left.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        corner_top_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/corner_up_left.png"))); // NOI18N
        top_panel.add(corner_top_left, java.awt.BorderLayout.WEST);

        corner_top_middle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        corner_top_middle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_hor.png"))); // NOI18N
        top_panel.add(corner_top_middle, java.awt.BorderLayout.CENTER);

        mid_parent_panel.setLayout(new java.awt.BorderLayout());

        mid_panel_border_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_vert.png"))); // NOI18N
        mid_parent_panel.add(mid_panel_border_left, java.awt.BorderLayout.WEST);

        mid_panel_border_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_vert.png"))); // NOI18N
        mid_parent_panel.add(mid_panel_border_right, java.awt.BorderLayout.EAST);

        mid_panel.setBackground(new java.awt.Color(62, 62, 62));
        mid_panel.setLayout(new java.awt.BorderLayout());

        flag_count_label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        flag_count_label.setForeground(new java.awt.Color(170, 1, 0));
        flag_count_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        flag_count_label.setText("   000");
        mid_panel.add(flag_count_label, java.awt.BorderLayout.WEST);

        timer_label.setBackground(new java.awt.Color(170, 1, 0));
        timer_label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        timer_label.setForeground(new java.awt.Color(170, 1, 0));
        timer_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timer_label.setText("000   ");
        mid_panel.add(timer_label, java.awt.BorderLayout.EAST);

        restart_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        restart_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/face_unpressed.png"))); // NOI18N
        mid_panel.add(restart_button, java.awt.BorderLayout.CENTER);

        mid_parent_panel.add(mid_panel, java.awt.BorderLayout.CENTER);

        mid_panel_separator.setLayout(new java.awt.BorderLayout());

        panel_separator_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/t_left.png"))); // NOI18N
        mid_panel_separator.add(panel_separator_left, java.awt.BorderLayout.WEST);

        panel_separator_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/t_right.png"))); // NOI18N
        mid_panel_separator.add(panel_separator_right, java.awt.BorderLayout.EAST);

        panel_separator_middle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_hor.png"))); // NOI18N
        mid_panel_separator.add(panel_separator_middle, java.awt.BorderLayout.CENTER);

        bottom_panel.setLayout(new java.awt.BorderLayout());

        corner_bottom_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/corner_bottom_left.png"))); // NOI18N
        bottom_panel.add(corner_bottom_left, java.awt.BorderLayout.WEST);

        corner_bottom_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/corner_bottom_right.png"))); // NOI18N
        bottom_panel.add(corner_bottom_right, java.awt.BorderLayout.EAST);

        corner_bottom_mid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_hor.png"))); // NOI18N
        bottom_panel.add(corner_bottom_mid, java.awt.BorderLayout.CENTER);

        game_parent_panel.setLayout(new java.awt.BorderLayout());

        game_panel_border_left.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_vert.png"))); // NOI18N
        game_parent_panel.add(game_panel_border_left, java.awt.BorderLayout.WEST);

        game_panel_border_right.setIcon(new javax.swing.ImageIcon(getClass().getResource("/metrobuscaminas/interfaces/assets/border_vert.png"))); // NOI18N
        game_parent_panel.add(game_panel_border_right, java.awt.BorderLayout.EAST);

        game_panel.setBackground(new java.awt.Color(62, 62, 62));
        game_panel.setLayout(new java.awt.GridBagLayout());
        game_parent_panel.add(game_panel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mid_parent_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
            .addComponent(mid_panel_separator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottom_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(game_parent_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mid_parent_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mid_panel_separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(game_parent_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(bottom_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom_panel;
    private javax.swing.JLabel corner_bottom_left;
    private javax.swing.JLabel corner_bottom_mid;
    private javax.swing.JLabel corner_bottom_right;
    private javax.swing.JLabel corner_top_left;
    private javax.swing.JLabel corner_top_middle;
    private javax.swing.JLabel corner_top_right;
    private javax.swing.JLabel flag_count_label;
    private javax.swing.JPanel game_panel;
    private javax.swing.JLabel game_panel_border_left;
    private javax.swing.JLabel game_panel_border_right;
    private javax.swing.JPanel game_parent_panel;
    private javax.swing.JPanel mid_panel;
    private javax.swing.JLabel mid_panel_border_left;
    private javax.swing.JLabel mid_panel_border_right;
    private javax.swing.JPanel mid_panel_separator;
    private javax.swing.JPanel mid_parent_panel;
    private javax.swing.JLabel panel_separator_left;
    private javax.swing.JLabel panel_separator_middle;
    private javax.swing.JLabel panel_separator_right;
    private javax.swing.JLabel restart_button;
    private javax.swing.JLabel timer_label;
    private javax.swing.JPanel top_panel;
    // End of variables declaration//GEN-END:variables
}
