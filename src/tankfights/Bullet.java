/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author chango
 */
public class Bullet extends GameObject{
    Point.Double dir;
    boolean alive=true;
    double speed=5;
    public Bullet(Point.Double pos,Point.Double dir,Game game){
        super((Point.Double)pos.clone(),game);
        this.dir=(Point.Double)dir.clone();
    }

    @Override
    public boolean update() {
        if(!alive)
            return false;
        pos.x+=dir.x*speed;
        pos.y+=dir.y*speed;
        if(pos.x<0||pos.x>game.getWidth()||pos.y<0||pos.y>game.getHeight())
            alive=false;
        return true;
    }

    @Override
    public void paintObject(Graphics2D g) {
        if(!alive)
            return;
        g.setColor(Color.GRAY);
        g.fillOval((int)pos.x-3, (int)pos.y-3, 6,6);
        g.setColor(Color.WHITE);
    }
    
}
