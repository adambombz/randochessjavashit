/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javax.swing.JFrame;

/**
 *
 * @author baxteac
 */
public class frame extends JFrame{
    public frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Chess");
        this.add(new canvas());
    }
}
