/**
 * 
 */
package edu.nyu.pqs.ps4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ps4.App.ConnectFourGUI;
import edu.nyu.pqs.ps4.playerPackage.Player;
import edu.nyu.pqs.ps4.playerPackage.PlayerFactory;

/**
 * @author Sahil Vora
 *
 */
public class ConnectFourTest {
	private PlayerFactory playerFactory=PlayerFactory.getPlayerFactory();
	private Player player1;
	private Player player2;
	private Player player3;
	private ConnectFour game;
	@Test
	public void testBuilder(){
		game=new ConnectFour.Builder().withRow(6).withColumn(7).withAim(4).build();
		ConnectFour game2=new ConnectFour.Builder()
										.withRow(6).withColumn(7).withAim(4).build();
		
		assertFalse(game.equals(game2));
		assertTrue(game.equals(game));
		
	}
	
	@Test
	(expected=IllegalStateException.class)
	public void testBuilder2(){
		game=new ConnectFour.Builder().withRow(5).withColumn(7).withAim(6).build();
	}	
	
	@Test
	public void testBuilder3(){
		game=new ConnectFour.Builder().build();
		ConnectFour game2=new ConnectFour.Builder()
										.withRow(6).withColumn(7).withAim(4).build();
		assertFalse(game.equals(game2));
		ConnectFour game3=new ConnectFour.Builder()
										.withRow(6).withAim(4).build();
		ConnectFour game4=new ConnectFour.Builder()
										.withColumn(7).withAim(4).build();
		ConnectFour game5=new ConnectFour.Builder()
								.withRow(6).withColumn(7).build();

		ConnectFour game6=new ConnectFour.Builder().build();
		assertTrue(game3.getAim()<game3.getColumn());
		assertTrue(game3.getAim()<game3.getRow());
		assertTrue(game4.getAim()<game4.getColumn());
		assertTrue(game4.getAim()<game4.getRow());
		assertTrue(game5.getAim()<game5.getColumn());
		assertTrue(game5.getAim()<game5.getRow());
		assertTrue(game6.getAim()<game6.getColumn());
	}
	
	@Before
	public void setUp(){
		game=new ConnectFour.Builder().build();
		player1=playerFactory.getPlayer("Human",game)
				.withColor("Blue")
				.withPlayerName("John")
				.buildPlayer();

		player2=playerFactory.getPlayer("Computer",game)
				.withColor("Yellow")
				.withPlayerName("computer")
				.buildPlayer();

		player3=playerFactory.getPlayer("Human",game)
				.withColor("Red")
				.withPlayerName("Lisa")
				.buildPlayer();

	}

	@Test
	public void testCheckRow(){
		game=new ConnectFour.Builder().build();
		game.insert(0, 1);
		assertFalse(game.checkRow(1));
		game.insert(1, 1);
		assertFalse(game.checkRow(1));
		game.insert(2, 1);
		assertFalse(game.checkRow(1));
		game.insert(3, 1);
		assertTrue(game.checkRow(1));
	}
	@Test
	public void testCheckColumn(){
		game=new ConnectFour.Builder().build();
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		game.insert(0, 1);
		assertTrue(game.checkColumn(1));
		
	}
	@Test
	public void testnextBestMove(){
		game=new ConnectFour.Builder().build();
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		int noMove=game.bestNextMove(1);
		assertEquals(noMove,-1);
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		game.insert(0, 1);
		assertFalse(game.checkColumn(1));
		int move=game.bestNextMove(1);
		game.insert(move, 1);
		assertTrue(game.checkColumn(1));
	}

	@Test
	public void testSinglePlayer(){
		setUp();
		game.addPlayer(player1, "image1");
		game.addPlayer(player2, "image2");
		game.startGame();
		game.insert(0, player1.getId());
		game.insert(0, player1.getId());
		game.insert(0, player1.getId());
		game.insert(1, player2.getId());
		game.insert(1, player2.getId());
		game.insert(1, player2.getId());
		game.nextMove(3);
		assertTrue(game.isWinner(player2.getId()));
	}
	
	
	
	@Test
	public void isWinnerDiagonals2(){
	//below leftDiagonal
		setUp();
		for(int i=0;i<4;i++){
			for(int j=0;j<(4-i);j++){
				if(i==j){
					game.insert(i, 1);
				}
				else{
					game.insert(i, 0);
				}
			}
		}
		
		assertTrue(game.isWinner(0));
		
	}
	
	
	@Test
	public void isWinnerDiagonals1(){
	//above right Diagonal	
		setUp();
		for(int i=0;i<6;i++){
			for(int j=0;j<=i;j++){
				if(i==j){
					game.insert(i, 0);
				}
				else
					game.insert(i, 1);
				}
		}
		assertTrue(game.isWinner(0));
	}
	
	@Test
	public void isWinnerDiagonals(){
		//below right diagonal
		setUp();
		for(int i=0;i<6;i++){
			for(int j=0;j<=i;j++){
				if(i>1&&j==(i-2)){
					game.insert(i, 0);
				}
				else
					game.insert(i, 1);
				}
		}
		assertTrue(game.isWinner(0));
		
	}
	
	@Test
	public void isWinnerDiagonals3(){
	//above leftDiagonal
		setUp();
		int row=6;
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(j==row-i){
					game.insert(i, 1);
				}
				else{
					game.insert(i, 0);
				}
			}
			
			
		}
		assertTrue(game.isWinner(1));
		
	}
	
	@Test
	public void testTwoPlayer(){
		setUp();
		game.addPlayer(player1, "image1");
		game.addPlayer(player3, "image2");
		assertTrue(game.getIdImageMap().get(player1.getId()).equals("image1"));
		game.startGame();
		assertTrue(game.getNumberOfPlayers()==2);
		for(int i=0;i<3;i++){
			game.nextMove(i);
			game.nextMove(i);
		}
		game.nextMove(3);
		assertTrue(game.isWinner(player1.getId()));
	}
	@Test
	public void testTwoPlayer2(){
		setUp();
		game.addPlayer(player1, "image1");
		game.addPlayer(player3, "image2");
		assertTrue(game.getIdImageMap().get(player1.getId()).equals("image1"));
		game.startGame();
		assertTrue(game.getNumberOfPlayers()==2);
		for(int i=0;i<3;i++){
			game.nextMove(0);
			game.nextMove(0);
		}
		game.nextMove(0);
		game.nextMove(0);
		assertTrue(game.getTurn()==0);
		assertTrue(game.isColumnFull(0));
	}

	@Test
	public void testIsBoardFull(){
		setUp();
		for(int i=0;i<game.getRow();i++){
				for(int j=0;j<game.getColumn();j++){
					game.insert(j, 1);
				}
		}
		assertTrue(game.isBoardFull());
	}
	
	@Test
	public void computerPlayerNextMoveWithBoardAlmostFull(){
		game=new ConnectFour.Builder().build();
		Player compPlayer=playerFactory.getPlayer("Computer", game)
																	 .withColor("Yellow")
																	 .withPlayerName("comp").buildPlayer();
		int col=0;	
		for(int i=0;i<game.getRow();i++){
			for(int j=0;j<game.getColumn();j++){
					if(i==game.getRow()-1 && j==game.getColumn()-1){
					}
					else{
						game.insert(j, 1);
					}
			}
		}
		col=compPlayer.getMove();
		assertTrue(col==game.getColumn()-1);
	}
	
	@Test
	public void testAddRemoveGameListeners(){
		game=new ConnectFour.Builder().build();
		ConnectFourGUI gui=new ConnectFourGUI(game);//calls addListener() in its constructor 
		ConnectFourGUI gui2=new ConnectFourGUI(game);
		for(int i=0;i<10;i++){
			game.addGameListener(gui);
		}
		assertEquals(game.getNumberOfListeners(),2);
		
		game.removeGameListener(gui);
		assertEquals(game.getNumberOfListeners(),1);
	}
	
}

