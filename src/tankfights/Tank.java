/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author sinfante
 */
public class Tank extends GameObject{

    public static int ARRIBA=0;
    public static int DERECHA=1;
    public static int ABAJO=2;
    public static int IZQUIERDA=3;
    public int speed=3;
    int direction=0; //0-arriba, 1- derecha 2- abajo 3- izquierda
    boolean moving=false;
    Point.Double aim_dir=new Point.Double(1.0,0.0);
    double aim_ang=0;
    Image sprite[]=new Image[2];
    int cur_sprite=0;
    Point2D.Double crp;    
    Color ccol=new Color(0.5f,0.5f,0f);
    public Tank(Point2D.Double pos,Game game) {
        super(pos,game);
        sprite[0]= (new ImageIcon("sprite.png")).getImage();
        sprite[1]= (new ImageIcon("sprite2.png")).getImage();
        crp=(Point2D.Double)pos.clone();
    }
    public Tank(double posx,double posy,Game game) {
        super(new Point.Double(posx,posy),game);
        sprite[0]= (new ImageIcon("sprite.png")).getImage();
        sprite[1]= (new ImageIcon("sprite2.png")).getImage();
        crp=(Point2D.Double)pos.clone();
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public void turnRight(){
        direction=(direction+1)%4;
    }
    public void turnLeft(){
        direction=(direction+3)%4;
    }
    public void shoot(){
        Bullet bullet=new Bullet(pos,aim_dir,game);
        game.add(bullet);
    }
    
    

    @Override
    public boolean update() {
        Point mp=game.getMousePosition();
        if(mp!=null)
           aim(mp);
        if(moving){
            
            switch(direction){
                case 0: pos.y=Math.max(17, pos.y-speed);
                        break;
                case 1: pos.x=Math.min(game.getWidth()-17,pos.x+speed);break;
                case 2: pos.y=Math.min(game.getHeight()-17,pos.y+speed);break;
                case 3: pos.x=Math.max(17,pos.x-speed);break;
            }
            cur_sprite=(cur_sprite+1)%2;
        }
        return false;
    }
    public void aim(Point target){
        aim(new Point.Double((double)target.x,(double)target.y));
    }
    public void aim(Point.Double target){
        Point.Double dist_c=new Point.Double(target.x-pos.x,target.y-pos.y);
        double dist=pos.distance(target);
        aim_dir.x=dist_c.x/dist;
        aim_dir.y=dist_c.y/dist;
        aim_ang=Math.atan(aim_dir.y/aim_dir.x);
        if(aim_dir.x<0)aim_ang-=Math.PI;
     
    }

    @Override
    public void paintObject(Graphics2D g) {
        
       switch(direction){
            case 0:g.setTransform(AffineTransform.getTranslateInstance(pos.x-20, pos.y-20));
                   g.drawImage(sprite[cur_sprite],AffineTransform.getRotateInstance(0),null);
                   crp.x=pos.x-1;
                   crp.y=pos.y+3;
                   break;
                   
            case 1:g.setTransform(AffineTransform.getTranslateInstance(pos.x+20, pos.y-20));
                   g.drawImage(sprite[cur_sprite],AffineTransform.getRotateInstance(Math.PI/2),null);
                   crp.x=pos.x-4;
                   crp.y=pos.y-1;
                   break;
	              
            case 2:g.setTransform(AffineTransform.getTranslateInstance(pos.x+20, pos.y+20));
                   g.drawImage(sprite[cur_sprite],AffineTransform.getRotateInstance(Math.PI),null);
                   crp.x=pos.x-1;
                   crp.y=pos.y-3;
                   break;
            case 3:
                g.setTransform(AffineTransform.getTranslateInstance(pos.x-20, pos.y+20));
                   g.drawImage(sprite[cur_sprite],AffineTransform.getRotateInstance(Math.PI*1.5),null);
                   crp.x=pos.x+4;
                   crp.y=pos.y+1;
		break;
        }
        
        //g.rotate(Math.PI/2);
       // g.drawImage(sprite,(int)pos.x,(int)pos.y,null);
       
         //g.setTransform(AffineTransform.getScaleInstance(1.0,1.0));
        g.setTransform(AffineTransform.getScaleInstance(1.0,1.0));
        g.rotate(aim_ang,crp.x,crp.y);
        g.setColor(ccol);
        g.fillRect((int)crp.x+6,(int)crp.y-2,11,4);
        g.setColor(Color.BLACK);
        g.drawRect((int)crp.x+6,(int)crp.y-2,11,4);
        g.setTransform(AffineTransform.getScaleInstance(1.0,1.0));
    }
    
}
