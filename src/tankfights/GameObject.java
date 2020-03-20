/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 *
 * @author sinfante
 */
public abstract class GameObject{
    Point.Double pos;
    Game game;
    public GameObject(Point.Double pos,Game game){
        this.pos=pos;
        this.game=game;
    }

    public abstract boolean update();
    public abstract void paintObject(Graphics2D g);
    
}
