/**
 * 
 */
package edu.nyu.pqs.ps4.playerPackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.ps4.ConnectFour;

/**
 * @author Sahil Vora
 *
 */
public class PlayerFactoryTest {

	PlayerFactory factory;
	ConnectFour connectFour;
	
	@Before
	public void setUp(){
		factory=PlayerFactory.getPlayerFactory();
		connectFour=new ConnectFour.Builder().build();	
	}
	
	@Test
	(expected=IllegalStateException.class)
	public void testAlienPlayerType() {
		setUp();
		Player player1=factory.getPlayer("Alien", connectFour)
													.withColor("yellow")
													.withPlayerName("name")
													.buildPlayer();
		
		
	}
	@Test
	(expected=IllegalStateException.class)
	public void testCollidingNames() {
		setUp();
		Player player1=factory.getPlayer("Human", connectFour)
													.withColor("yellow")
													.withPlayerName("name")
													.buildPlayer();
		Player player2=factory.getPlayer("Human", connectFour)
													.withColor("blue")
													.withPlayerName("name")
													.buildPlayer();
		
	}
	
	@Test
	(expected=IllegalStateException.class)
	public void testCollidingColor() {
		setUp();
		Player player1=factory.getPlayer("Human", connectFour)
													.withColor("blue")
													.withPlayerName("name")
													.buildPlayer();
		Player player2=factory.getPlayer("Computer", connectFour)
													.withColor("blue")
													.withPlayerName("name1")
													.buildPlayer();
		
	}
	@Test
	(expected=IllegalStateException.class)
	public void testNoColor() {
		setUp();
		Player player1=factory.getPlayer("Human", connectFour)
													.withPlayerName("name")
													.buildPlayer();
		
	}
	

	@Test
	(expected=IllegalStateException.class)
	public void testNoName() {
		setUp();
		Player player1=factory.getPlayer("Human", connectFour)
													.withColor("blue")
													.buildPlayer();
		
	}
	
	@Test
	public void testPlayers(){
		Player player1=factory.getPlayer("Human", connectFour)
						.withColor("blue")
						.withPlayerName("name")
						.buildPlayer();
		Player player2=factory.getPlayer("Computer", connectFour)
						.withColor("yellow")
						.withPlayerName("name1")
						.buildPlayer();
		Player player3=factory.getPlayer("Human", connectFour)
						.withColor("red")
						.withPlayerName("name2")
						.buildPlayer();
		Player player4=factory.getPlayer("Computer", connectFour)
						.withColor("green")
						.withPlayerName("name3")
						.buildPlayer();
		assertTrue(player1.getId()!=player2.getId());
		assertTrue(player1.equals(player1));
		assertTrue(player2.equals(player2));
		assertFalse(player1.equals(player2));
		assertFalse(player2.equals(player1));
		assertFalse(player1.equals(player3));
		assertFalse(player2.equals(player4));
	}
	
}
