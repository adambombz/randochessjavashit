/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;


/**
 *
 * @author baxteac
 */
public class chessRunner {

	private static Grid<?> gr;
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws InterruptedException {
		ActorWorld world = new ActorWorld();
		gr = new BoundedGrid<Actor>(9,9);
		world.setGrid((Grid<Actor>) gr);
		for(int i=1;i<9;i++){
			world.add(new Location(2,i),new pawn());
			world.add(new Location(7,i),new pawn());
		}
		world.add(new Location(0,1),new One());
		world.add(new Location(0,2),new Two());
		world.add(new Location(0,3),new Three());
		world.add(new Location(0,4),new Four());
		world.add(new Location(0,5),new Five());
		world.add(new Location(0,6),new Six());
		world.add(new Location(0,7),new Seven());
		world.add(new Location(0,8),new Eight());
		world.add(new Location(1,0),new One());
		world.add(new Location(2,0),new Two());
		world.add(new Location(3,0),new Three());
		world.add(new Location(4,0),new Four());
		world.add(new Location(5,0),new Five());
		world.add(new Location(6,0),new Six());
		world.add(new Location(7,0),new Seven());
		world.add(new Location(8,0),new Eight());
		world.add(new Location(1,1),new rook());
		world.add(new Location(1,8),new rook());
		world.add(new Location(8,1),new rook());
		world.add(new Location(8,8),new rook());
		world.add(new Location(1,2),new knight());
		world.add(new Location(1,7),new knight());
		world.add(new Location(8,2),new knight());
		world.add(new Location(8,7),new knight());
		world.add(new Location(1,3),new bishop());
		world.add(new Location(1,6),new bishop());
		world.add(new Location(8,3),new bishop());
		world.add(new Location(8,6),new bishop());
		world.add(new Location(8,5),new king());
		world.add(new Location(8,4),new queen());
		world.add(new Location(1,5),new king());
		world.add(new Location(1,4),new queen());
		for(int i=1;i<9;i++){
			for(int j=1;j<3;j++){
				world.getGrid().get(new Location(j,i)).setColor(Color.RED);
				world.getGrid().get(new Location(0,i)).setColor(Color.BLACK);
				world.getGrid().get(new Location(i,0)).setColor(Color.BLACK);
			}
		}
		Player one = new Player(1,world);
		Player two = new Player(2,world);
		one.setOpName(two.getName());
		two.setOpName(one.getName());
		boolean c=true;
		world.show();
		System.out.println(one.getName()+" take your turn. You are the blue pieces.");
		one.turn();
		world.show();
		System.out.println(two.getName()+" take your turn. You are the red pieces.");
		two.turn();
		world.show();
		int winner=1;
		while(!checkWin(one,two,world,1)){
			if(c){
				one.turn();
				if(one.checkCheck(false)){
					System.out.println("Oh snap! "+one.getName()+" put "+two.getName()+" in check! Better make a move!");
				}
				world.show();
				c=false;
				two.setOpPieces(one.getPieces());
			}
			if(checkWin(one,two,world,2)){
				winner=2;
				break;
			}
			else{
				two.turn();
				if(two.checkCheck(false)){
					System.out.println("Oh snap! "+two.getName()+" put "+one.getName()+" in check! Better make a move!");
				}
				world.show();
				c=true;
				one.setOpPieces(two.getPieces());
			}
			winner=1;
		}
		if(winner==1){
			System.out.println("Not only has "+one.getName()+" put "+two.getName()+" in check, but he has also won the game! Checkmate!");
		}
		else{
			System.out.println("Not only has "+two.getName()+" put "+one.getName()+" in check, but he has also won the game! Checkmate!");
		}
	}
	static public boolean checkWin(Player one, Player two,ActorWorld world,int num){
		// TODO: unused
//		boolean win=false;
		
		boolean b=false;
		boolean match=false;
		int counter=0;
		ArrayList<Location> king=new ArrayList<Location>();
		if(num==1){
			king=one.king().possibleMoves(1, world);
			king.add(((Actor) one.king()).getLocation());
			ArrayList<Piece> defenders=one.getPieces();
			defenders.remove(one.king());
			for(Location l:king){
				for(Piece p:two.getPieces()){
					for(Location op:p.possibleMoves(2, world)){
						if(l.equals(op)){
							for(Piece defender:defenders){
								for(Location move:defender.possibleMoves(1,world)){
									for(Location opp:((Piece) p).betweenLocations(l, world)){
										if(opp.equals(move)||move.equals(((Actor) p).getLocation())){
											System.out.println(opp);
											match=true;
											break;
										}
									}
									if(match){
										break;
									}
								}
								if(match){
									break;
								}
							}
							if(!match){
								b=true;
								counter++;
								break;
							}
						}
					}
					if(b){
						b=false;
						break;
					}
				}
			}
		}
		else{
			king=two.king().possibleMoves(2, world);
			king.add(((Actor) two.king()).getLocation());
			ArrayList<Piece> defenders=two.getPieces();
			defenders.remove(two.king());
			for(Location l:king){
				for(Piece p:one.getPieces()){
					for(Location op:p.possibleMoves(1, world)){
						if(l.equals(op)){
							for(Piece defender:defenders){
								for(Location move:defender.possibleMoves(2,world)){
									for(Location opp:((Piece) p).betweenLocations(l, world)){
										if(opp.equals(move)||move.equals(((Actor) p).getLocation())){
											match=true;
											break;
										}
									}
									if(match){
										break;
									}
								}
								if(match){
									break;
								}
							}
							if(!match){
								counter++;
								b=true;
								break;
							}
						}
					}
					if(b){
						b=false;
						break;
					}
				}
			}
		}
		return king.size()>0&&king.size()==counter;
	}
}