package maze.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel toPanel = new ToPanel();

	public GamePanel(){
		setLayout(new BorderLayout(0, 0));
		
		toPanel.grabFocus();
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		
		JButton btnNewButton_2 = new JButton("Salvar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrabalhoFicheiro.salvarJogo(Control.jogo);
				} catch (IOException e1) {
					e1.printStackTrace();
					System.exit(0);
				}
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Anterior");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.showPanel("menu1", 180, 253);
			}
		});
		panel.add(btnNewButton_1);
		
		add(toPanel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Esquerda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.jogo.read(Opcoes.modoJogo, 'a');
				Control.jogo.desenhaTabuleiro();
				toPanel.repaint();
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("Cima");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.jogo.read(Opcoes.modoJogo, 'w');
				Control.jogo.desenhaTabuleiro();
				toPanel.repaint();
			}
		});
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_4 = new JButton("Baixo");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.jogo.read(Opcoes.modoJogo, 's');
				Control.jogo.desenhaTabuleiro();
				toPanel.repaint();
			}
		});
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Direita");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control.jogo.read(Opcoes.modoJogo, 'd');
				Control.jogo.desenhaTabuleiro();
				toPanel.repaint();
			}
		});
		panel_1.add(btnNewButton_3);
	}
	
	public void setToPanelFocus() {
		toPanel.grabFocus();
	}
}
