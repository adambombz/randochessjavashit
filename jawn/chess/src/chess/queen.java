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
public class queen extends Critter implements Piece{
	String name="Queen";
    @Override
    public boolean canMove(int x, int y, int num, ActorWorld world) {
        boolean c=false;
        Location go=new Location(x,y);
        int temp=getLocation().getRow()-x;
        if(getLocation().getRow()==x||getLocation().getCol()==y||getLocation().getCol()-y==temp||getLocation().getCol()-y==-temp){
        	c=true;
        }
        if(c&&world.getGrid().get(go) instanceof Piece&&world.getGrid().get(go).getColor()==getColor()){
			c=false;
		}
        return c&&!between(new Location(x,y),world);
    }
    public boolean between(Location loc,ActorWorld world){
    	boolean c=false;
    	Location start = getLocation();
    	int dir=start.getDirectionToward(loc);
    	while(!start.equals(loc.getAdjacentLocation((dir+180)%360))&&world.getGrid().isValid(start.getAdjacentLocation(dir))){
    		start=start.getAdjacentLocation(dir);
    		if(world.getGrid().get(start) instanceof Piece){
    			c=true;
    			break;
    		}
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
    	Location start = getLocation();
    	int dir=start.getDirectionToward(loc);
    	while(!start.equals(loc.getAdjacentLocation((dir+180)%360))&&world.getGrid().isValid(start.getAdjacentLocation(dir))){
    		start=start.getAdjacentLocation(dir);
    		moves.add(start);
    		if(world.getGrid().get(start) instanceof Piece){
    			break;
    		}
    	}
    	return moves;
    }
}
