/**
 * 
 */
package edu.nyu.pqs.ps4.playerPackage;

import edu.nyu.pqs.ps4.ConnectFour;

/**
 * @author Sahil
 *
 */
public class HumanPlayer implements
				Player {

	private int id;
	private String name;
	private String color;
	private ConnectFour connectFour;
	private final PlayerType type=PlayerType.Human;
	HumanPlayer(){}
	
	public int getId(){
		return id;
	}
	
	public PlayerType getType(){
		return type;
	}
	
	HumanPlayer(int id,String name,String color,ConnectFour connectFour){
		this.id=id;
		this.name=name;
		this.color=color;
		this.connectFour=connectFour;
	}
	
	public String getName(){
		return name;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player" + (id+1) + ", name=" + name + ", color=" + color;
	}

	/* 
	 * 
	 */
	public int getMove() {
		throw new UnsupportedOperationException();
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		HumanPlayer other = (HumanPlayer) obj;
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
