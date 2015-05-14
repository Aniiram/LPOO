package maze.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TabCons extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	char [][] tab = new char[Opcoes.tamanhoTab][Opcoes.tamanhoTab];
	labirinto.Peca pSaida;

	private BufferedImage parede;
	private BufferedImage caminho;
	private BufferedImage saida;
	private BufferedImage heroi;
	private BufferedImage heroiArmado;
	private BufferedImage espada;
	private BufferedImage dragao;
	private BufferedImage dragaoDormir;
	private BufferedImage dardo;

	private int size = 0, x = 0, y = 0;

	public TabCons(){
		inicializarJogoVazio();
		
		try {
			loadImages();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	}

	private void inicializarJogoVazio() {
		for(int i = 0; i < Opcoes.tamanhoTab; i++){
			for (int j = 0; j < Opcoes.tamanhoTab; j++){
				if(i == 0 || j == 0 || 
						i == Opcoes.tamanhoTab -1 || 
						j == Opcoes.tamanhoTab -1)
					tab[i][j] = 'X';
				else tab[i][j] = ' ';
			}
		}
		tab[1][1] = 'D';
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		BufferedImage temp = null;

		size = Math.min(Control.frm.getWidth() - 100, Control.frm.getHeight() - 30 ) / tab.length;

		y = 0;
		for(int j = 0; j < tab.length; j++){
			x = 0;
			for(int i = 0; i < tab.length; i++){
				g.drawImage(caminho, x, y, (int) size,(int) size ,null);

				switch(tab[j][i]){
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
	}	
}
