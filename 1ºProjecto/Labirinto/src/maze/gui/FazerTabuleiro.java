package maze.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FazerTabuleiro extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	TabCons Panel = new TabCons();
		
	FazerTabuleiro(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAdicionarSada = new JButton("Adicionar Sa\u00EDda");
		btnAdicionarSada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(btnAdicionarSada);
		
		JButton btnAdicionarDrago = new JButton("Adicionar Drag\u00E3o");
		btnAdicionarDrago.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAdicionarDrago);
		
		JButton btnAdicionarEspada = new JButton("Adicionar Espada");
		btnAdicionarEspada.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAdicionarEspada);
		
		JButton btnAdicionarParede = new JButton("Adicionar Parede");
		btnAdicionarParede.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAdicionarParede);
		
		JButton btnAdicionarArmadura = new JButton("Adicionar Armadura");
		btnAdicionarArmadura.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAdicionarArmadura);
		
		JButton btnAdicionarDardo = new JButton("Adicionar Dardo");
		btnAdicionarDardo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnAdicionarDardo);
		
		JButton btnAdicionarheri = new JButton("Adicionar Her\u00F3i");
		btnAdicionarheri.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});		
		panel_1.add(btnAdicionarheri);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Control.jogo = new labirinto.Labirinto(Panel.tab);
				Control.showPanel("gamePanel", 500, 604);
			}
		});
		panel_1.add(btnJogar);
		
		JButton btnVoltarAtras = new JButton("VoltarAtras");
		btnVoltarAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Control.showPanel("menu1", 180, 253);
			}
		});
		panel_1.add(btnVoltarAtras);
		
		add(Panel, BorderLayout.CENTER);
	}
}
