package edu.nyu.pqs.ps4.playerPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.nyu.pqs.ps4.ConnectFour;

public class ComputerPlayerTest {

	private ComputerPlayer comp1,comp2;
	@Test
	public void testComputerPlayer() {
		ConnectFour game=new ConnectFour.Builder().build();
		comp1= new ComputerPlayer(1,"comp1","yellow",game);
		ComputerPlayer comp3= new ComputerPlayer(1,"comp1","yellow",game);
		
		comp2= new ComputerPlayer(1,"comp2","blue",game);
		assertTrue(!comp1.equals(comp2));
		assertTrue(!comp2.equals(comp1));
		assertTrue(comp1.equals(comp1));
		assertTrue(comp1.equals(comp3));
		assertTrue(comp1.toString().equals(comp3.toString()));
		assertTrue(comp1.getId()==comp3.getId());
		assertTrue(comp1.getName().equals(comp3.getName()));
		assertTrue(comp1.getType().equals(comp3.getType()));
	}

	@Test
	public void testComputerPlayer2() {
		comp2=new ComputerPlayer(); //testing default constructor
		ConnectFour game=new ConnectFour.Builder().build();
		comp1= new ComputerPlayer(1,"comp1","yellow",game);
		ComputerPlayer comp3= new ComputerPlayer(1,"comp1","yellow",game);
		comp2= new ComputerPlayer(1,"comp2","blue",null);
	
		assertTrue(comp1.hashCode()==comp3.hashCode());
		assertTrue(comp2.hashCode()!=comp1.hashCode());
		comp2= new ComputerPlayer(1,"comp2","blue",null);
	}
}
