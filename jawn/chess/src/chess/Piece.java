/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 *
 * @author baxteac
 */
public interface Piece{
    public boolean canMove(int x,int y,int num,ActorWorld world);
    public String getName();
    public ArrayList<Location> possibleMoves(int num,ActorWorld world);
    public ArrayList<Location> betweenLocations(Location loc,ActorWorld world);
}
