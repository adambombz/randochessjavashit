/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author baxteac
 */
public class Player {
	public String name;
	int x;
	int y;
	int num;
	Color color;
	String opponet;
	ActorWorld world;
	Location cur;
	Location go;
	Piece temp;
	ArrayList<Piece> opPieces=new ArrayList<Piece>();
	ArrayList<Piece> pieces=new ArrayList<Piece>();
	ArrayList<Location> highlight =new ArrayList<Location>();
	private Scanner scan;
	public Player(int i, ActorWorld world){
		scan=new Scanner(System.in);     
		System.out.println("Insert the name of the "+i+" player:");
		name=scan.next();
		x=0;
		y=0;
		this.world=world;
		num=i;
		if(num==1){
			color=Color.BLUE;
			for(int z=7;z<9;z++){
				for(int j=1;j<9;j++){
					pieces.add((Piece) world.getGrid().get(new Location(z,j)));
				}
			}
		}
		else{
			color=Color.RED;
			for(int z=1;z<3;z++){
				for(int j=1;j<9;j++){
					pieces.add((Piece) world.getGrid().get(new Location(z,j)));
				}
			}
		}
		opponet="";
	}
	public void turn() throws InterruptedException{
		refresh();
		getCur();
		if(world.getGrid().get(go)instanceof Piece){
			temp=(Piece) world.getGrid().get(go);
		}
		while(checkCheck(true)){
			refresh();
			System.out.println("Sorry you cannot make this move because you still are/will be in check!");
			remove();
			world.getGrid().get(cur).setColor(color);
			world.show();
			getCur();
			if(world.getGrid().get(go)instanceof Piece){
				temp=(Piece) world.getGrid().get(go);
			}
		}
		if(temp instanceof Piece){
			System.out.println("Aw shucks, you took "+opponet+"'s "+temp.getName()+".");
			world.getGrid().remove(go);
			temp=null;
		}
		world.getGrid().get(cur).setColor(color);
		remove();
		if(world.getGrid().get(cur) instanceof pawn){
			((pawn) world.getGrid().get(cur)).setR(false);
		}
		((Critter) world.getGrid().get(cur)).moveTo(go);
		world.show();
		respawn();
		refresh();
	}


	public void getCur() throws InterruptedException{
		System.out.println(name+", what is the x coordinate of the piece you would like to move?");
		x=scan.nextInt();
		System.out.println("What is the y coordinate of the piece you would like to move?");
		y=scan.nextInt();
		cur=new Location(x,y);
		while(!(world.getGrid().isValid(cur)&&world.getGrid().get(cur) instanceof Piece&&world.getGrid().get(cur).getColor()==color&&x!=0&&y!=0)){
			System.out.println("Sorry this is not a location of one of your pieces, please enter another x coordinate:");
			x=scan.nextInt();
			System.out.println("And another y coordinate:");
			y=scan.nextInt();
			cur=new Location(x,y);
		}
		highlight();
		if(highlight.size()==0){
			world.getGrid().get(cur).setColor(color);
			System.out.println("This piece has no possible moves. Please pick another one.");
			getCur();
		}
		else{
			getGo();
		}
	}
	public void getGo() throws InterruptedException{
		System.out.println("You have selected your "+((Piece) world.getGrid().get(cur)).getName()+" piece.");
		world.getGrid().get(cur).setColor(Color.CYAN);
		world.show();
		System.out.println("What is the x coordinate of where you would like to move?");
		x=scan.nextInt();
		System.out.println("What is the y coordinate of where you would like to move?");
		y=scan.nextInt();
		go=new Location(x,y);	
		while(!(x>0&&x<9)||!(y>0&&y<9)||(!(world.getGrid().get(go) instanceof Highlight)&&(world.getGrid().get(go)instanceof Piece && world.getGrid().get(go).getColor()!=Color.GREEN))||(!(world.getGrid().get(go)instanceof Piece) && !(world.getGrid().get(go) instanceof Highlight))){              
			System.out.println("Sorry this is not a valid location, please enter another x coordinate:");
			x=scan.nextInt();
			System.out.println("And another y coordinate:");
			y=scan.nextInt();
			go=new Location(x,y);
			if(!(x>0&&x<9)||!(y>0&&y<9)||(!(world.getGrid().get(go) instanceof Highlight)&&(world.getGrid().get(go)instanceof Piece && world.getGrid().get(go).getColor()!=Color.GREEN))||(!(world.getGrid().get(go)instanceof Piece) && !(world.getGrid().get(go) instanceof Highlight))){
				System.out.println("If you would like to select another piece type \"y\" if not type anything else:");
				String ay=scan.next();
				if(ay.equalsIgnoreCase("y")){
					world.getGrid().get(cur).setColor(color);
					remove();
					world.show();
					getCur();
					break;
				}
			}
		}
	}
	public void respawn(){
		String respawn="";
		boolean yes=true;
		if(num==1){
			for(int i=1;i<9;i++){
				if(world.getGrid().get(new Location(1,i)) instanceof pawn&&world.getGrid().get(new Location(1,i)).getColor()==color){
					System.out.println("Congrats! You earned a respawn by getting your pawn into the last row! Please enter the first letter of the piece you would like to respawn:");
					respawn=scan.next();
					if(respawn.equalsIgnoreCase("q")){
						System.out.println("You have choosen to respawn your queen. What column you would like to put it in?");
						y=scan.nextInt();
						world.getGrid().remove(go);
						world.add(new Location(8,y), new queen());
						yes=false;
					}
					else if(respawn.equalsIgnoreCase("r")){
						System.out.println("You have choosen to respawn a rook. What column you would like to put it in?");
						y=scan.nextInt();
						world.getGrid().remove(go);
						world.add(new Location(8,y), new rook());
						yes=false;
					}
					else if(respawn.equalsIgnoreCase("b")){
						System.out.println("You have choosen to respawn a bishop. What column you would like to put it in?");
						y=scan.nextInt();
						world.getGrid().remove(go);
						world.add(new Location(8,y), new bishop());
						yes=false;
					}
					else if(respawn.equalsIgnoreCase("k")){
						System.out.println("You have choosen to respawn a knight. What column you would like to put it in?");
						y=scan.nextInt();
						world.getGrid().remove(go);
						world.add(new Location(8,y), new knight());
						yes=false;
					}
					while(yes){
						System.out.println("You have not entered a valid letter. You entered:"+respawn+" Please enter another one:");
						respawn=scan.next();
						if(respawn.equalsIgnoreCase("q")){
							System.out.println("You have choosen to respawn your queen. What column you would like to put it in?");
							y=scan.nextInt();
							world.getGrid().remove(go);
							world.add(new Location(8,y), new queen());
							yes=false;
						}
						else if(respawn.equalsIgnoreCase("r")){
							System.out.println("You have choosen to respawn a rook. What column you would like to put it in?");
							y=scan.nextInt();
							world.getGrid().remove(go);
							world.add(new Location(8,y), new rook());
							yes=false;
						}
						else if(respawn.equalsIgnoreCase("b")){
							System.out.println("You have choosen to respawn a bishop. What column you would like to put it in?");
							y=scan.nextInt();
							world.getGrid().remove(go);
							world.add(new Location(8,y), new bishop());
							yes=false;
						}
						else if(respawn.equalsIgnoreCase("k")){
							System.out.println("You have choosen to respawn a knight. What column you would like to put it in?");
							y=scan.nextInt();
							world.getGrid().remove(go);
							world.add(new Location(8,y), new knight());
							yes=false;
						}
					}
				}
			}
		}
	}
	public void refresh(){
		pieces=new ArrayList<Piece>();
		for(int i=1;i<9;i++){
			for(int j=1;j<9;j++){
				if(world.getGrid().get(new Location(i,j))instanceof Piece&&((Critter) world.getGrid().get(new Location(i,j))).getColor()==color){
					pieces.add((Piece) world.getGrid().get(new Location(i,j)));
				}
			}
		}
	}
	public boolean checkCheck(boolean b){
		boolean c=false;
		boolean yes=false;
		if(b){
			Critter te=new Critter();
			if(world.getGrid().get(go) instanceof Piece){
				te=(Critter) world.getGrid().get(go);
				opPieces.remove((Piece) te);
				yes=true;
			}
			Piece temp=(Piece) world.getGrid().get(cur);
			((Actor) temp).moveTo(go);
			for(int j=0;j<opPieces.size();j++){
				ArrayList<Location> moves=((Piece) world.getGrid().get(((Actor) opPieces.get(j)).getLocation())).possibleMoves(num,world);
				for(Location l:moves){
					for(Piece lo:pieces){
						if(l.equals(((Actor) lo).getLocation())&&lo instanceof king){
							c=true;
						}
					}
				}
			}
			if(yes){
				opPieces.add((Piece) te);
			}
			((Actor) temp).moveTo(cur);
		}
		else{
			for(int j=0;j<pieces.size();j++){
				ArrayList<Location> moves=((Piece) world.getGrid().get(((Actor) pieces.get(j)).getLocation())).possibleMoves(num,world);
				for(int i=0;i<moves.size();i++){
					if(world.getGrid().get(moves.get(i)) instanceof Piece&&((Piece) world.getGrid().get(moves.get(i))).getName().equals("King")&&((Critter) world.getGrid().get(moves.get(i))).getColor()!=color){
						c=true;
					}
				}
			}
		}
		return c;
	}
	public String getName(){
		return name;
	}
	public void setOpName(String s){
		opponet=s;
	}
	public void setOpPieces(ArrayList<Piece> op){
		opPieces=op;
	}
	public ArrayList<Piece> getPieces(){
		refresh();
		return pieces;
	}
	public void highlight() throws InterruptedException{
		highlight=((Piece) world.getGrid().get(cur)).possibleMoves(num,world);
		for(Location l:highlight){
			if(!(world.getGrid().get(l) instanceof Piece)){
				world.add(l,new Highlight());
				world.getGrid().get(l).setColor(Color.CYAN);
			}
			else{
				world.getGrid().get(l).setColor(Color.GREEN);
			}
		}
		world.show();

	}
	public void remove(){
		for(Location l:highlight){
			if(!(world.getGrid().get(l) instanceof Piece)){
			world.getGrid().remove(l);
			}
			else{
				if(num==1){
					world.getGrid().get(l).setColor(Color.RED);
				}
				else{
					world.getGrid().get(l).setColor(Color.BLUE);
				}
			}
		}
	}
	public Piece king(){
		for(Piece a:pieces){
			if(a instanceof king){
				return a;
			}
		}
		return (Piece) world.getGrid().get(cur);
	}
}
