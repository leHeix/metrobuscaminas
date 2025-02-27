/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package metrobuscaminas.interfaces;

import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import metrobuscaminas.Utils;

/**
 *
 * @author Nikos
 */
public class Game extends javax.swing.JFrame {
    private BufferedImage top_mid_image_buf;
    private BufferedImage side_border_image_buf;
    private MainMenu main_menu;
    
    /**
     * Creates new form Game
     */
    public Game(MainMenu menu) {
        top_mid_image_buf = this.load_image("border_hor.png");
        if(top_mid_image_buf == null)
            return;
        
        side_border_image_buf = this.load_image("border_vert.png");
        if(side_border_image_buf == null)
            return;
        
        this.main_menu = menu;
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.resize_icons();
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent ev)
            {
                Game.this.resize_icons();
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev)
            {
                int response = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir de la partida?", "Salir", JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION)
                {
                    Game.this.close_game();
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
        catch(IOException e)
        {
            Utils.showMessageError("Error de carga de recursos", "No se pudo cargar " + name + ". Asegúrate de que los archivos del juego están íntegros.");
            return null;
        }
        
        return img;
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

        javax.swing.GroupLayout mid_panelLayout = new javax.swing.GroupLayout(mid_panel);
        mid_panel.setLayout(mid_panelLayout);
        mid_panelLayout.setHorizontalGroup(
            mid_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        mid_panelLayout.setVerticalGroup(
            mid_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout game_panelLayout = new javax.swing.GroupLayout(game_panel);
        game_panel.setLayout(game_panelLayout);
        game_panelLayout.setHorizontalGroup(
            game_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        game_panelLayout.setVerticalGroup(
            game_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 267, Short.MAX_VALUE)
        );

        game_parent_panel.add(game_panel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mid_parent_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(game_parent_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JPanel top_panel;
    // End of variables declaration//GEN-END:variables
}
