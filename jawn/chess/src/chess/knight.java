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
public class knight extends Critter implements Piece{
	String name="Knight";
	public boolean canMove(int x,int y,int num,ActorWorld world){
		boolean c=false;
		Location loc=getLocation();
		Location go=new Location(x,y);
		if((loc.getRow()+1==x&&loc.getCol()+2==y)||(loc.getRow()+1==x&&loc.getCol()-2==y)||(loc.getRow()-1==x&&loc.getCol()+2==y)||(loc.getRow()-1==x&&loc.getCol()-2==y)||(loc.getRow()+2==x&&loc.getCol()+1==y)||(loc.getRow()+2==x&&loc.getCol()-1==y)||(loc.getRow()-2==x&&loc.getCol()+1==y)||(loc.getRow()-2==x&&loc.getCol()-1==y)){
			c=true;
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
					if(world.getGrid().isValid(new Location(i,j))){
					}
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
