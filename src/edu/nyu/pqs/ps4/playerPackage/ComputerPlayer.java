/**
 * 
 */
package edu.nyu.pqs.ps4.playerPackage;

import edu.nyu.pqs.ps4.ConnectFour;

/**
 * @author Sahil
 *
 */
public class ComputerPlayer implements
				Player {
	private int id;
	private final PlayerType type=PlayerType.Computer;
	private String name="Computer";
	private ConnectFour connectFour;
	private String color;
	ComputerPlayer(){}
	
	ComputerPlayer(int id,String name,String color,ConnectFour connectFour){
		this.name=name;
		this.color=color;
		this.connectFour=connectFour;
		this.id=id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player" + (id+1) + ", name=" + name + ", color=" + color;
	}
	
	public PlayerType getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public int getMove() {
		int col=connectFour.bestNextMove(id);
		if(col!=-1){
			return col;
		}
		else{
			int columns=connectFour.getColumn();
			int randomColumn=(int)(Math.random()*columns);
			while(connectFour.isColumnFull(randomColumn%columns)){
				randomColumn++;
			}
			return randomColumn;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((connectFour == null) ? 0 : connectFour.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerPlayer other = (ComputerPlayer) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (connectFour == null) {
			if (other.connectFour != null)
				return false;
		} else if (!connectFour.equals(other.connectFour))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	
}
