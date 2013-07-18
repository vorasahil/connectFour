/**
 * 
 */
package edu.nyu.pqs.ps4.App;

import edu.nyu.pqs.ps4.ConnectFour;
import edu.nyu.pqs.ps4.playerPackage.Player;

/**
 * @author Sahil
 *
 */
public interface GameListener {
	
	public void updatePlayerDetails(Player player);
	public void updateGame(ConnectFour connectFour);
	public void updateCell(int row,int column,int value);
	public void showErrorMessage(String message);
	public void startGame(Player player);
	public void showWinMessage(Player player);
	public void showDrawMessage(String Message);
	public void gameOver();
	public void updateText(String message);
}

