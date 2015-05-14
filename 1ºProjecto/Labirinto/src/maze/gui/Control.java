package maze.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Control {
	static CardLayout cardLayout;
	static JPanel card = new JPanel();
	static JFrame frm = new JFrame();
	static JPanel menu1 = new Menu1();
	//static JPanel toPanel = new ToPanel();
	static JPanel opcoes = new Opcoes();
	static GamePanel gamePanel = new GamePanel();
	static FazerTabuleiro fazerTabuleiro = new FazerTabuleiro();
	static labirinto.Labirinto jogo = new labirinto.Labirinto(11, 1);
	
	
	public static void main(String[] args) {
			
		card.setLayout(cardLayout = new CardLayout());
		card.add("menu1", menu1);
		//card.add("toPanel", toPanel);
		card.add("opcoes", opcoes);
		card.add("gamePanel", gamePanel);
		card.add("fazerTabuleiro", fazerTabuleiro);
		
		cardLayout.show(card, "menu1");
		
		frm.getContentPane().add(card);
		
		frm.setVisible(true);
		frm.setResizable(true);
		frm.setSize(180, 253);
		frm.setLocation(230,180);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 
	}
	
	public static void showPanel(String painel, int dimx, int dimy){
		cardLayout.show(card, painel);
		frm.setSize(dimx, dimy);
		if(painel == "gamePanel")
			gamePanel.setToPanelFocus();
	}
}
