/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;



/**
 *
 * @author baxteac
 */
public class pawn  extends Critter implements Piece{
	String name="Pawn";
	boolean r;
	public pawn(){
		r=true;
	}
    public boolean canMove(int x,int y,int num,ActorWorld world){
    	boolean c=false;
    	Location go=new Location(x,y);
    	if(num==1){
    		if(r&&getLocation().getRow()>x&&getLocation().getRow()<=x+2&&getLocation().getCol()==y&&!(world.getGrid().get(go)instanceof Piece)){
    			c=true;
    		}
    		else if(getLocation().getRow()-1==x&&getLocation().getCol()==y&&!(world.getGrid().get(go)instanceof Piece)){
    			c=true;
    		}         
    		else if(world.getGrid().get(go)instanceof Piece&&world.getGrid().get(go).getColor()==Color.RED&&((x==getLocation().getRow()-1&&y==getLocation().getCol()+1)||(x==getLocation().getRow()-1&&y==getLocation().getCol()-1))){
    			c=true;
    		}
    	}
    	else{
    		if(r&&getLocation().getRow()<x&&getLocation().getRow()>=x-2&&getLocation().getCol()==y&&!(world.getGrid().get(go)instanceof Piece)){
    			c=true;
    		}
    		else if(getLocation().getRow()<x&&getLocation().getRow()>=x-1&&getLocation().getCol()==y&&!(world.getGrid().get(go)instanceof Piece)){
    			c=true;
    		}
    		else if(world.getGrid().get(go) instanceof Piece&&world.getGrid().get(go).getColor()==Color.BLUE&&((x==getLocation().getRow()+1&&y==getLocation().getCol()+1)||(x==getLocation().getRow()+1&&y==getLocation().getCol()-1))){
    			c=true;
    		}
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
    public void setR(boolean i){
    	r=i;
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
