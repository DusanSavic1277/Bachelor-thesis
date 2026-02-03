package rs.ac.singidunum;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    
    public static JFrame window;

    public static void main(String[] args) {
        
        window = new JFrame("Diplomski rad - Dušan Savić 2021200044");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        new Main().setIcon();
        
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gp.setupGame();
        gp.startGameThread();
    }
    
    public void setIcon(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("playerImg/boy_down_1.png"));
        window.setIconImage(icon.getImage());
    }
    
}