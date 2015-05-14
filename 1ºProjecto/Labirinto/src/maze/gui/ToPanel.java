package maze.gui;

//import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ToPanel extends JPanel 
implements MouseListener, MouseMotionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -595069901993563958L;
	
	//private BufferedImage hero, other;
	//boolean cena = true;
	
	private BufferedImage parede;
	private BufferedImage caminho;
	private BufferedImage saida;
	private BufferedImage heroi;
	private BufferedImage heroiArmado;
	private BufferedImage espada;
	private BufferedImage dragao;
	private BufferedImage dragaoDormir;
	private BufferedImage dardo;
	private BufferedImage gameOver;
	private BufferedImage winner;
	
	private int x = 0, y = 0, size = 100;		
	
	public ToPanel() {
		this.grabFocus();
		this.setEnabled(true);
		this.setFocusable(true);
		this.requestFocus();
	    this.requestFocusInWindow();
	    
	    this.addKeyListener(this);
	    
	    boolean teste = this.isFocusable();
	    
		try {
			loadImages();
		} catch (IOException e) {
			System.out.println("Erro carregar imagens");
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("cheguei " + teste);

	}
	
	private void loadImages() throws IOException{
		parede = ImageIO.read(new File("imagens/parede.png"));
		caminho = ImageIO.read(new File("imagens/chao.png"));
		saida = ImageIO.read(new File("imagens/porta_fechada.png"));
		heroi = ImageIO.read(new File("imagens/heroi_frente.gif"));
		heroiArmado = ImageIO.read(new File("imagens/heroi_frente_espada.gif"));	
		espada = ImageIO.read(new File("imagens/espada.gif"));
		dragao = ImageIO.read(new File("imagens/dragao.gif"));
		dragaoDormir = ImageIO.read(new File("imagens/dragao_dormir.gif"));
		dardo = ImageIO.read(new File("imagens/dardo.gif"));
		gameOver = ImageIO.read(new File("imagens/lose.jpg"));
		winner = ImageIO.read(new File("imagens/win.png"));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage temp = null;
		
		size = Math.min(Control.frm.getWidth() - 10, Control.frm.getHeight() - 104) / Control.jogo.getTamanho();
		
		y = 0;
		for(int j = 0; j < Control.jogo.getTamanho(); j++){
			
			x = 0;
			for(int i = 0; i < Control.jogo.getTamanho(); i++){
				g.drawImage(caminho, x, y, (int) size,(int) size ,null);
				
				switch(Control.jogo.getJogo()[j][i]){
				case 'X':
					temp = parede;
					break;
				case 'S':
					temp = saida;
					break;
				case 'H':
					temp = heroi;
					break;
				case 'A':
					temp = heroiArmado;
					break;
				case 'E':
					temp = espada;
					break;
				case 'D':
					temp = dragao;
					break;
				case 'd':
					temp = dragaoDormir;
					break;
				case 'F':
					temp = dragao;
				case 'f':
					temp = dragaoDormir;
				case '*':
					temp = dardo;
					break;
				}
				g.drawImage(temp, x, y, (int) size,(int) size ,null);
				temp = null;
				x += size;
			}
			y += size;
		}
	
		if(Control.jogo.getAcabou()){
			int i = Math.min(x-10, y-104);
			
			if(Control.jogo.getGanhou()){
				g.drawImage(winner, i*1/4, i*1/4, i*3/4, i*3/4, null);
			}
			
			else {
				g.drawImage(gameOver, i*1/4, i*1/4, i*3/4, i*3/4, null);
			}
		}
		
		/*
		BufferedImage temp;
		if(cena)
		temp = hero;
		else temp = other;
		g.drawImage(temp, x, y, (int) x+size,(int) y+size ,null);
		*/
		
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	// Mais eventos do rato, que neste caso nao interessam
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("cheguei");
		if(Control.jogo.getAcabou()){
			return;
		}
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: 
			Control.jogo.read(Opcoes.modoJogo, 'a');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;

		case KeyEvent.VK_RIGHT: 
			Control.jogo.read(Opcoes.modoJogo, 'd');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;

		case KeyEvent.VK_UP:
			Control.jogo.read(Opcoes.modoJogo, 'w');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;

		case KeyEvent.VK_DOWN:
			Control.jogo.read(Opcoes.modoJogo, 's');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;
		
		case KeyEvent.VK_W:
			Control.jogo.read(Opcoes.modoJogo, 'i');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;
			
		case KeyEvent.VK_D:
			Control.jogo.read(Opcoes.modoJogo, 'l');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;
			
		case KeyEvent.VK_A:
			Control.jogo.read(Opcoes.modoJogo, 'j');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;
			
		case KeyEvent.VK_S:
			Control.jogo.read(Opcoes.modoJogo, 'k');
			Control.jogo.desenhaTabuleiro();
			repaint(); 
			break;
		}
		
		//correcto mas falta array
		/*
		char jogada = 'z';
		jogada = Opcoes.controles[e.getKeyCode()];
		if(jogada != 'z'){
			Control.jogo.read(Opcoes.modoJogo, jogada);
			Control.jogo.desenhaTabuleiro();
			repaint();
			
		}
		 */		
	}
	
	// Mais eventos do teclado, que neste caso nao interessam
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
