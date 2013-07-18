package edu.nyu.pqs.ps4.playerPackage;
import java.util.*;

import edu.nyu.pqs.ps4.ConnectFour;

public class PlayerFactory {

	private static PlayerFactory factory;
	private Player player;
	private int id;
	private String type;
	private String color;
	private String name;
	private ConnectFour connectFour;
	private Map<String,ConnectFour>listOfNames; 
	private Map<String,ConnectFour>listOfColors; 
	private Map<ConnectFour,Integer>idCount;
	
	private PlayerFactory(){
		listOfNames=new HashMap<String,ConnectFour>();
		listOfColors=new HashMap<String,ConnectFour>();
		idCount=new HashMap<ConnectFour,Integer>();
	}
	
	public static PlayerFactory getPlayerFactory(){
		if(factory==null){
			factory=new PlayerFactory();
		}
			return factory;
	}
	
	public PlayerFactory getPlayer(String type,ConnectFour connectFour){
		if(type.equals("Human")||type.equals("Computer")){
			this.type=type;	
			this.connectFour=connectFour;
			this.name=null;
			this.color=null;
			if(idCount.get(connectFour)==null){
				this.id=0;
				idCount.put(connectFour, this.id);
			}
			else{
				this.id=(idCount.get(connectFour)+1);
				idCount.put(connectFour, this.id);
			}
			
		}
		else{
			throw new IllegalStateException("Player Type can be only Human or Computer");
		}
		return this;
	}
	

	 public PlayerFactory withPlayerName(String name) {
		    if(listOfNames.get(name)==null||!listOfNames.get(name).equals(connectFour)){
		    	this.name = name;
		    	listOfNames.put(name,connectFour);
			    return this;	
		    }
		    else{
		    	throw new IllegalStateException("Name already in Use");
		    }
	 }
	 public PlayerFactory withColor(String color) {
		    if(listOfColors.get(color)==null||!listOfColors.get(color).equals(connectFour)){
		    	this.color = color;
		    	listOfColors.put(color,connectFour);
			    return this;	
		    }
		    else{
		    	throw new IllegalStateException("Color already in Use");
		    }
	 }
	 
 
	 public Player buildPlayer(){
		 if(color==null){
			 throw new IllegalStateException("Color not provided");
		 }
		 if(name==null){
			 throw new IllegalStateException("Name not provided");
		 }
			if(type.equals("Human")){
				player=new HumanPlayer(id,name,color,connectFour);
				}
			else{
				player=new ComputerPlayer(id,name,color,connectFour);
			}
			return player;
	 }
	
}
