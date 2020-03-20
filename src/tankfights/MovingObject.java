/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankfights;

import java.awt.geom.Point2D;

/**
 *
 * @author sinfante
 */
public abstract class MovingObject extends GameObject{
    boolean alive=true;
    
    
    public MovingObject(Point2D.Double pos,Game game) {
        super(pos,game);
    }
   
    public void run(){
        while(alive){
            
        }
    }
    
}
