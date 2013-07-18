/**
 * 
 */
package edu.nyu.pqs.ps4.App;

import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.nyu.pqs.ps4.ConnectFour;
import edu.nyu.pqs.ps4.playerPackage.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Sahil
 *
 */
public class ConnectFourGUI implements GameListener {

	private static int GUIId=1;
	private int id;
	private ConnectFour connectFour;
	private int row;
	private int column;
	private GridLayout labelLayout;
	private GridLayout buttonLayout;
	private JFrame frame;
	private JLabel [][] labelGrid;
	private JPanel labelGridPanel;
	private JPanel dropButtonPanel;
	private JButton [] buttonGrid;
	private JPanel namePanel;
	private JLabel playerDetails;
	public ConnectFourGUI(final ConnectFour connectFour){
		this.id=GUIId;
		GUIId++;
		connectFour.addGameListener(this);
		row=connectFour.getRow();
		column=connectFour.getColumn();
		labelLayout=new GridLayout(row,column);
		buttonLayout=new GridLayout(0,column);
		
		labelGrid=new JLabel[row][column];
		buttonGrid=new JButton [column];
		
		labelGridPanel=new JPanel();
		dropButtonPanel=new JPanel();
		
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER,labelGridPanel);
		frame.getContentPane().add(BorderLayout.NORTH,dropButtonPanel);
		//frame.setVisible(true);
		labelGridPanel.setLayout(labelLayout);
		dropButtonPanel.setLayout(buttonLayout);
		
		playerDetails=new JLabel();
		namePanel=new JPanel();
		namePanel.setLayout(new BorderLayout());
		frame.getContentPane().add(BorderLayout.SOUTH,namePanel);
		namePanel.add(BorderLayout.CENTER,playerDetails);
		playerDetails.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				labelGrid[i][j]=new JLabel();
				labelGrid[i][j].setIcon(new ImageIcon("image\\black.jpg"));
				
				labelGrid[i][j].setBorder(new EmptyBorder(2, 2, 2, 2));
				labelGridPanel.add(labelGrid[i][j]);
			}
		}
		
		for(int j=0;j<column;j++){
			buttonGrid[j]=new JButton("Drop Here");
			final int col=j;
			buttonGrid[j].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				connectFour.nextMove(col);
				 }
				 });
			dropButtonPanel.add(buttonGrid[j]);
		}
		frame.pack();
	}
	
	
	public void updateCell(int row,int column,int value)
	{
		Map<Integer,String> map=connectFour.getIdImageMap();
		labelGrid[row][column].setIcon(new ImageIcon(map.get(value)));
	}
	
	public void showErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message, message, JOptionPane.ERROR_MESSAGE);
	}
	
	public void updateGame(ConnectFour game){
		this.connectFour=game;
	}
	public void startGame(Player player)
	{	updatePlayerDetails(player);
		frame.setVisible(true);
	}
	
	public void showWinMessage(Player player)
	{	String winner="Player "+player.getName()+" ("+(player.getId()+1)+") "+" Has WON! Congratulations!";
		JOptionPane.showMessageDialog(null,winner,winner,JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void gameOver(){
		for(int j=0;j<column;j++){
			buttonGrid[j].setEnabled(false);
		}
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + id;
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
		ConnectFourGUI other = (ConnectFourGUI) obj;
		if (column != other.column)
			return false;
		if (id != other.id)
			return false;
		if (row != other.row)
			return false;
		return true;
	}


	public void showDrawMessage(String message){
		JOptionPane.showMessageDialog(null,message,message,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void updatePlayerDetails(Player player){
		playerDetails.setText(player.toString());	
	}
	
	public void updateText(String message){
		playerDetails.setText(message);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConnectFourGUI [id=" + id
						+ ", connectFour="
						+ connectFour + ", row="
						+ row + ", column="
						+ column + "]";
	}


	
	
	
	
}
