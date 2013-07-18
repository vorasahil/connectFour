package edu.nyu.pqs.ps4.App;

import edu.nyu.pqs.ps4.ConnectFour;
import edu.nyu.pqs.ps4.ConnectFour.Builder;
import edu.nyu.pqs.ps4.playerPackage.Player;
import edu.nyu.pqs.ps4.playerPackage.PlayerFactory;

public class ConnectFourApp {
	private static PlayerFactory playerFactory=PlayerFactory.getPlayerFactory();
	
	public void startGame(ConnectFour connectFour,Player player1,Player player2){
		connectFour.addPlayer(player1, "image\\blue.jpg");
		connectFour.addPlayer(player2, "image\\yellow.jpg");
		connectFour.startGame();
	}
	
	public static void singlePlayerGame(ConnectFour game){
		System.out.println("single");
		Player player1=playerFactory.getPlayer("Human",game)
									.withColor("Blue")
									.withPlayerName("John")
									.buildPlayer();
		
		Player player2=playerFactory.getPlayer("Computer",game)
									.withColor("Yellow")
									.withPlayerName("computer")
									.buildPlayer();
		new ConnectFourApp().startGame(game, player1, player2);
	}
	public static void twoPlayerGame(ConnectFour game){
		System.out.println("two");
		Player player1=playerFactory.getPlayer("Human",game)
									.withColor("Blue")
									.withPlayerName("John")
									.buildPlayer();

		Player player2=playerFactory.getPlayer("Human",game)
									.withColor("Yellow")
									.withPlayerName("Lisa")
									.buildPlayer();
		new ConnectFourApp().startGame(game, player1, player2);
	}
	
	public static void main(String[] arg){
		ConnectFour game=new ConnectFour.Builder().withAim(4).withColumn(7).withRow(6).build();
		ConnectFourGUI gui1=new ConnectFourGUI(game);
		//ConnectFourGUI gui4=new ConnectFourGUI(game);
		//ConnectFourGUI gui3=new ConnectFourGUI(game);
		ConnectFourGUI gui2=new ConnectFourGUI(game);
		GameMenu.go(game);
		
	}
}
