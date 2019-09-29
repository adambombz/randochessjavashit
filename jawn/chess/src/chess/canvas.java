/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author baxteac
 */
public class canvas extends Canvas implements MouseListener{
    public canvas(){
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x=me.getX()/50;
        int y=me.getY()/50;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    @Override
    public void paint(Graphics g){
        
        g.drawRect(0,0,400,400);
        for(int i=50;i<400;i+=50){
            g.drawLine(i, 0, i, 400);
        }
        for(int j=50;j<400;j+=50){
            g.drawLine(0, j, 400, j);
        }
        boolean c=false;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j+=2){
                if(c){
                    g.setColor(Color.black);  
                }
                else{
                    g.setColor(Color.white);
                }
                g.fillRect((i*(400/8))+1, (j*(400/8))+1, 49, 49);
            }
            if(c){
                    c=false;  
                }
                else{
                    c=true;
                }
        }
        for(int i=0;i<8;i++){
            for(int j=1;j<8;j+=2){
                if(c){
                    g.setColor(Color.white);  
                }
                else{
                    g.setColor(Color.black);
                }
                g.fillRect((i*(400/8))+1, (j*(400/8))+1, 49, 49);
            }
            if(c){
                    c=false;  
                }
                else{
                    c=true;
                }
        }
    }
}
