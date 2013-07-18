package edu.nyu.pqs.ps4.playerPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs.ps4.ConnectFour;

public class HumanPlayerTest {

		private HumanPlayer human1,human2;
		@Test
		public void testComputerPlayer() {
			ConnectFour game=new ConnectFour.Builder().build();
			human1= new HumanPlayer(1,"human1","yellow",game);
			HumanPlayer human3= new HumanPlayer(1,"human1","yellow",game);
			
			human2= new HumanPlayer(1,"human2","blue",game);
			assertTrue(!human1.equals(human2));
			assertTrue(!human2.equals(human1));
			assertTrue(human1.equals(human1));
			assertTrue(human1.equals(human3));
			assertTrue(human1.toString().equals(human3.toString()));
			assertTrue(human1.getId()==human3.getId());
			assertTrue(human1.getName().equals(human3.getName()));
			assertTrue(human1.getType().equals(human3.getType()));
		}

		@Test
		public void testComputerPlayer2() {
			ConnectFour game=new ConnectFour.Builder().build();
			human1= new HumanPlayer(1,"human1","yellow",game);
			HumanPlayer human3= new HumanPlayer(1,"human1","yellow",game);
			human2= new HumanPlayer(1,"human2","blue",null);
		
			assertTrue(human1.hashCode()==human3.hashCode());
			assertTrue(human2.hashCode()!=human1.hashCode());
			human2= new HumanPlayer(1,"human2","blue",null);
		}
		
		@Test
		(expected=UnsupportedOperationException.class)
		public void testNextMove(){
			human2=new HumanPlayer();
			human2.getMove();
		}

}




