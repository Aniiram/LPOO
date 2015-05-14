package maze.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu1 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4260501496762970876L;

	public Menu1() {
		Control.frm.setSize(180, 253);
		//labirinto.Labirinto jogo = new labirinto.Labirinto(11, 1);
		setLayout(new GridLayout(5,1));
		
		JButton btnNewButton = new JButton("Novo Jogo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Control.jogo = new labirinto.Labirinto(Opcoes.tamanhoTab, Opcoes.numDrag);
				Control.showPanel("gamePanel", 500, 604);//Control.frm.getWidth(), Control.frm.getHeight());
			}
		});
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Carregar Jogo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TrabalhoFicheiro.carregarJogo(Control.jogo);
			}
		});
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Fazer Tabuleiro\r\n\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.showPanel("fazerTabuleiro", 615, 490);
			}
		});
		add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Op\u00E7\u00F5es");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.showPanel("opcoes", 435, 480);
			}

			
		});
		add(btnNewButton_3);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnQuit);

	}
}
