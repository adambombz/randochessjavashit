/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;



/**
 *
 * @author baxteac
 */
public class king extends Critter implements Piece{
	String name="King";
	public boolean canMove(int x,int y,int num,ActorWorld world){
    	boolean c=true;
    	Location go=new Location(x,y);
    	Location loc=getLocation();
		if(loc.getRow()+1<x||loc.getRow()-1>x||loc.getCol()+1<y||loc.getCol()-1>y){
			c=false;
		}
		if(c&&world.getGrid().get(go) instanceof Piece&&world.getGrid().get(go).getColor()==getColor()){
			c=false;
		}
    	return c;
    }
    public String getName(){
    	return name;
    }
    public ArrayList<Location> possibleMoves(int num,ActorWorld world){
		ArrayList<Location> moves=new ArrayList<Location>();
		for(int i=1;i<9;i++){
			for(int j=1;j<9;j++){
				if(canMove(i,j,num,world)){
					moves.add(new Location(i,j));
				}
			}
		}
		return moves;
	}
    public void act(){
        
    }
    public ArrayList<Location> betweenLocations(Location loc,ActorWorld world){
    	ArrayList<Location> moves=new ArrayList<Location>();
    	return moves;
    }
}
