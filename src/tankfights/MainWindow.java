/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author sinfante
 */
public class MainWindow extends JFrame implements KeyListener{
    Game game;
    public MainWindow(){
        
        setup();
        setVisible(true);
        game.start();
    }
     private void setup() {
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        addKeyListener(this);
        game=new Game();
        add(game,BorderLayout.CENTER);
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println("Key!");
       if(ke.getKeyChar()==KeyEvent.VK_ESCAPE){
           game.stop();
           dispose();
       }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       game.keyPressed(ke);
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       game.keyReleased(ke);
    }

   
    
}
