/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author sinfante
 */
public class Game extends JComponent implements Runnable,MouseMotionListener,MouseListener{
    boolean active=false;
    Tank tank=new Tank(100,100,this);
   // Vector<GameObject> objects=new Vector();
     ArrayList<GameObject> objects=new ArrayList();
    public Game(){
        //super(null);
        init();
    }
    public void start(){
        if(active)
            return;
        Thread thread=new Thread(this);
        active=true;
        thread.start();
        
    }
    public synchronized void add(GameObject obj){
        objects.add(obj);
        
    }
    public synchronized void remove(GameObject obj){
        objects.remove(obj);
    }
    public void stop(){
        active=false;
    }
    private void init(){
        addMouseMotionListener(this);
        addMouseListener(this);
        objects.add(tank);
        
    }
    synchronized void update(){
        //tank.update();
        for (GameObject object : objects) {
            object.update();
        }
    }
    void redraw(){
        
    }
    
    @Override
    public void run() {
        while(active){
            update();
            render(getGraphics());
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public synchronized void render(Graphics g){
        if(g==null)
            return;
        Dimension dim=getSize();
        if(dim.width==0||dim.height==0)
            return;
        BufferedImage buffer=new BufferedImage(dim.width,dim.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D screen= buffer.createGraphics();
        
        for (GameObject object : objects) {
            object.paintObject(screen);
        }
        
        g.drawImage(buffer,0,0, this);
    }
    public void paintComponent(Graphics g){
        render(g);
    }

    @Override
    public void mouseDragged(MouseEvent me) {}

    @Override
    public void mouseMoved(MouseEvent me) {
          tank.aim(me.getPoint());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
          //tank.shoot();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        tank.shoot();
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    public void keyPressed(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      int kc=ke.getKeyCode();
      switch(kc){
          case KeyEvent.VK_UP: 
          case KeyEvent.VK_W:    tank.setMoving(true);break;
          case KeyEvent.VK_DOWN: 
          case KeyEvent.VK_S: tank.setMoving(false);break;
          case KeyEvent.VK_RIGHT: 
          case KeyEvent.VK_D: tank.turnRight();break;
          case KeyEvent.VK_LEFT: 
          case KeyEvent.VK_A: tank.turnLeft();break;
      }
      
    }

    
    public void keyReleased(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
    }
}
