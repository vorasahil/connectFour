/**
 * 
 */
package edu.nyu.pqs.ps4;
import java.util.*;

import edu.nyu.pqs.ps4.App.GameListener;
import edu.nyu.pqs.ps4.playerPackage.Player;
import edu.nyu.pqs.ps4.playerPackage.PlayerType;

/**
 * @author Sahil Vora
 * ConnectFour Game class, which holds the logic behind the entire game.
 * The game is by default played on a 6by7 board, connecting 4 coins of the same color between 2 player.
 * This class manages that and more(board can be of a different size and it can be used to connect any lengths of coins).
 *
 */
public class ConnectFour {

	private static int gameCount=1;
	private int row; 
	private int column;
	private int aim;
	private int [][]board;
	private List<Player> players;
	private int turn;
	private static ConnectFour game;
	private List<GameListener> listeners;
	private int gameId;
	private int numberOfPlayers;
	private Map<Integer,String> idImageMap;

	private ConnectFour(){}
	
	private ConnectFour(int row,int column, int aim){
		this.row=row;
		this.column=column;
		this.aim=aim;
		this.turn=0;
		players=new ArrayList<Player>();
		listeners=new ArrayList<GameListener>();
		this.numberOfPlayers=0;
		idImageMap=new HashMap<Integer,String>();
		boardReset();
		resetMap();
		this.gameId=gameCount;
		gameCount++;
	}

	
	public static class Builder {

	    private int row;
	    private int column;
	    private int aim;
	    public Builder() {
	    	row=-1;
	    	column=-1;
	    	aim=-1;
	    }

	    public Builder withRow(int row) {
	    this.row = row;
	    return this;
	    }

	    public Builder withColumn(int column) {
	    this.column = column;
	    return this;
	    }

	    public Builder withAim(int aim) {
		    this.aim = aim;
		    return this;
		    }

	    public ConnectFour build() {
	    	if(this.aim==-1){
	    		this.aim=4;
	    	}
	    	if(this.row==-1){
	    		this.row=aim+2;
	    	}
	    	if(this.column==-1){
	    		this.column=aim+3;
	    	}
	    	if(aim>row||aim>column){
	    		throw new IllegalStateException("Board too small for the aim(Connector-length) provided");
	    	}
	    return new ConnectFour(row,column,aim);
	    }
	   	
	}
	
	

	public int getAim() {
		return aim;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	
	public Map<Integer,String> getIdImageMap(){
		return (idImageMap);
	}
	
	
	public void addGameListener(GameListener gameListener) {
		if(!listeners.contains(gameListener)){
			listeners.add(gameListener);
		}
	}
	
	public int getNumberOfListeners(){
		return listeners.size();
	}
	public void removeGameListener(GameListener gameListener) {
		listeners.remove(gameListener);
	}
	
 
	public void addPlayer(Player player,String image){
		players.add(player);
		idImageMap.put(player.getId(),image);
	}
	
	public void resetMap(){
		idImageMap.clear();
	}
	
	private void boardReset() {
		board=new int[row][column];
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				board[i][j]=-1;
			}
		}
		
	}

	public void startGame(){
		turn=0;
		numberOfPlayers=players.size();
		boardReset();
		fireStartGame(players.get(turn));	
	}

	boolean checkRow(int player){
		int counter=0;
		for(int i=0;i<row;i++){
			counter=0;
			for(int j=0;j<column;j++){
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				
			}
		 }
		return false;
	}
	
	boolean checkColumn(int player){
		int counter=0;
		for(int j=0;j<column;j++){
			counter=0;
			for(int i=0;i<row;i++){	
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				
			}
		 }
		return false;
	}
	
	boolean checkAboveLeftDiagonal(int player){
		int counter=0;
		for(int j=0;j<column;j++){
			counter=0;
			int temp=j;
			int i=0;
			while(i<row&&j<column){
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				i++;j++;
			}
			j=temp;
		}	
		return false;
	}
	
	
	boolean checkBelowLeftDiagonal(int player){
		int counter=0;
		for(int i=0;i<row;i++){
			counter=0;
			int temp=i;
			int j=0;
			while(i<row&&j<column){
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				i++;j++;
			}
			i=temp;
		}
		return false;
	}
	
	
	boolean checkAboveRightDiagonal(int player){
		int counter=0;
		for(int j=column-1;j>=0;j--){
			counter=0;
			int temp=j;
			int i=0;
			while(i<row&&j>=0){
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				i++;j--;
			}
			j=temp;
			
		}
		return false;
	}

	boolean checkBelowRightDiagonal(int player){
		int counter=0;
		for(int i=0;i<row;i++){
			counter=0;
			int temp=i;
			int j=column-1;
			while(i<row&&j>=0){
				if(board[i][j]==player){
					counter++;
				}
				if(board[i][j]!=player){
					counter=0;
				}
				if(counter==aim){
					return true;
				}
				i++;j--;
			}
			i=temp;
		}
		return false;
	}


	boolean isWinner(int player){
		if(checkRow(player)||
						checkColumn(player)||
						checkAboveLeftDiagonal(player)||
						checkAboveRightDiagonal(player)||
						checkBelowLeftDiagonal(player)||
						checkBelowRightDiagonal(player)){
			return true;
		}
		else{
				return false;
		}
	}
	
	public boolean isBoardFull(){
		for(int i=0;i<column;i++){
			if(board[0][i]==-1)
				return false;
		}
		return true;
	}

	
	public boolean isColumnFull(int col){
		if(board[0][col]!=-1){
			return true;
		}
		else{
			return false;
		}
	}
//	
//	int[][] getBoardCopy(){
//		int [][]copy=new int[row][column];
//		for(int i=0;i<row;i++){
//			for(int j=0;j<column;j++){
//				copy[i][j]=board[i][j];
//			}
//		}
//		return copy;
//	}
//	
	int insert(int col,int value){
		int i=row-1;
		while(board[i][col]!=-1){
			i--;
		}
		board[i][col]=value;
		return i;
	}
	
	private void remove(int col){
		int i=0;
		while(board[i][col]==-1){
			i++;
		}
		board[i][col]=-1;
	}
	
	public void nextMove(int col){
		boolean gameOver=false;
		Player player=players.get(turn);
		if(player.getType()==PlayerType.Human)
		{
			if(isColumnFull(col)){
				fireShowErrorMessage("Column Full, retry");
				turn--;
				if(turn<0){
					turn+=numberOfPlayers;
				}
				fireUpdatePlayerDetails(players.get(turn));
			}
			else{
				int rowPointer=insert(col,player.getId());
				fireUpdateCell(rowPointer,col,board[rowPointer][col]);

			}
		}
		else{
			int choice=player.getMove();		
			int rowPointer=insert(choice,player.getId());
			fireUpdateCell(rowPointer,choice,board[rowPointer][choice]);
			
		}
		
		if(isWinner(player.getId())){
			fireUpdateText("Game Over");
			fireShowWinMessage(player);
			gameOver=true;
		}
		
		if(!gameOver&&isBoardFull()){
			fireUpdateText("Game Over");
			fireShowDrawMessage("Match drawn, Game Over");
			gameOver=true;
		}
		if(!gameOver){
			turn++;
			turn%=numberOfPlayers;
			fireUpdatePlayerDetails(players.get(turn));
			if(players.get(turn).getType()==PlayerType.Computer){
				nextMove(-1);
			}
		}
	}
	
	public int bestNextMove(int player){
		for(int j=0;j<column;j++){
			if(!isColumnFull(j)){
				insert(j,player);
				if(isWinner(player)){
					remove(j);
					return j;
				}
				remove(j);
			}
		}
		return -1;
	}
	
	public void fireUpdateGame(ConnectFour connectFour){
		for (GameListener listener : listeners) {
			 listener.updateGame(connectFour);
			}
	}
	
	public void fireUpdateCell(int row,int column,int value){
		for (GameListener listener : listeners) {
			 listener.updateCell(row,column,value);
			}

	}
	
	public void fireUpdatePlayerDetails(Player player){
		for (GameListener listener : listeners) {
			 listener.updatePlayerDetails(player);
			}
	}
	
	public void fireShowErrorMessage(String message){
		for (GameListener listener : listeners) {
			 listener.showErrorMessage(message);
			}

	}
	
	public void fireStartGame(Player player){
		for (GameListener listener : listeners) {
			 listener.startGame(player);
			 listener.updateGame(this);
		}
	}
	public void fireShowWinMessage(Player player){
		for (GameListener listener : listeners) {
			 listener.showWinMessage(player);
			 listener.gameOver();
			}

	}
	public void fireShowDrawMessage(String message){
		for (GameListener listener : listeners) {
			 listener.showDrawMessage(message);
			 listener.gameOver();
		}

	}
	

	public void fireUpdateText(String message){
		for (GameListener listener : listeners) {
			 listener.updateText(message);
			}
	}
	
	public void fireGameOver(){
		for (GameListener listener : listeners) {
			 listener.gameOver();
			}

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aim;
		result = prime * result + column;
		result = prime * result + gameId;
		result = prime * result + row;
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
		ConnectFour other = (ConnectFour) obj;
		if (aim != other.aim)
			return false;
			if (column != other.column)
			return false;
		if (gameId != other.gameId)
			return false;
				if (row != other.row)
			return false;
		return true;
	}

	
	
	

}
