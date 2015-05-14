package labirinto;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

/**
 * Tabuleiro.java - Esta classe constroi o tabuleiro que dá base ao Jogo do Labirinto
 * @author Carolina Moreira e Marina Camilo
 * Project 1 - LPOO
 */
/**
 * @author alcamilo
 *
 */
public class Tabuleiro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Cria um caminho aleatório que será a base do jogo, este tem um tamanho defenido de 7
	 */
	public Tabuleiro() {
		inicializarTabuleiro();
		inicializarCaminho();
		tabuleiroInicial [saida.getX()][saida.getY()] = saida.getType();
	}
	
	/**
	 * Cria um caminho aleatório que será a base do jogo, este tem um tamanho que será defenido pelo utilizador
	 * 
	 * @param tamanho Uma variavel do tipo inteiro
	 */
	Tabuleiro(int tamanho) {
		TAMANHO = tamanho;
		TAMCELVAZ = (TAMANHO-1)/2;
		tabuleiroInicial = new char [tamanho] [tamanho];
		visitedCells = new char [TAMCELVAZ] [TAMCELVAZ];
		inicializarTabuleiro();
		inicializarCaminho();
		tabuleiroInicial [saida.getX()] [saida.getY()] = saida.getType();
	}
	
	/**
	 * Guarda o Tabuleiro fefenido pelo utilizador tam com a peca saida
	 *  
	 * @param cam Uma variavel do tipo array de caracteres
	 * @param s Uma variavel do tipo da classe de Peca
	 */
	public Tabuleiro(char[][] cam, Peca s) {
		tabuleiroInicial = cam;
		saida = s;
		TAMANHO = cam.length;
	}
	
	public int TAMANHO = 7;
	private int TAMCELVAZ = (TAMANHO-1)/2;
	
	private char [] [] tabuleiroInicial = new char [TAMANHO] [TAMANHO];
	private char [] [] visitedCells = new char [TAMCELVAZ] [TAMCELVAZ];	
	private int [] headCell = new int [2];
	private Stack<Integer> stackX = new Stack<Integer>();
	private Stack<Integer> stackY = new Stack<Integer>();
	
	/**
	 * Peca Saida inicializar e imprimir e modificar
	 */
	private Peca saida; 
	
	/**
	 * @return Uma variavel do tipo da classe de Peca
	 */
	public Peca getSaida() {
		return saida;
	}

	/**
	 * @param saida Uma variavel do tipo da classe Peca
	 */
	public void setSaida(Peca saida) {
		this.saida = saida;
	}
	
	/** 
	 * Preparar as variáveis para abrir Caminho
	 */
	void inicializarSaida() {
		Random r = new Random();
		int escolha, x = 0, y = 0, xHead = 0, yHead = 0;
		
		escolha = r.nextInt(4);
		
		switch(escolha) {
		case 0:
			xHead = 0;
			yHead = r.nextInt(TAMCELVAZ);			
			x = 0;
			y = 2 * yHead + 1;
			break;
		case 1:
			xHead = TAMCELVAZ-1;
			yHead = r.nextInt(TAMCELVAZ);
			x = TAMANHO-1;
			y = 2 * yHead + 1;
			break;
		case 2:
			xHead = r.nextInt(TAMCELVAZ);
			yHead = 0;
			x = 2 * xHead + 1;
			y = 0;
			break;
		case 3:
			xHead = r.nextInt(TAMCELVAZ);
			yHead = TAMCELVAZ-1;
			x = 2 * xHead + 1;
			y = TAMANHO-1;
			break;
		}
		
		setSaida(new Peca('S', x, y));
		
		headCell[0] = xHead;
		headCell[1] = yHead;
	}
	
	/**
	 * Inicializa o tabuleiro
	 */
	final void inicializarTabuleiro() {
		for (int i = 0; i < TAMANHO; i++) 
			for (int j = 0; j < TAMANHO; j++) 
				if(odd(i) & odd(j))
					tabuleiroInicial [i] [j] = ' ';
				else tabuleiroInicial [i][j] = 'X';	
		
		for (int i = 0; i < visitedCells.length; i++)
			for (int j = 0; j < visitedCells.length; j++)
				visitedCells [i] [j] = '.';
				
	}
	
	boolean odd(int x) {
		double r = x % 2.0;
		if(r != 0)
			return true;
		return false;
	}
	
	/**
	 * Inicializa o caminho
	 */
	final void inicializarCaminho() {
		
		inicializarSaida();
		
		stackCellsPush();
		
		while (!stackX.isEmpty() || !stackY.isEmpty()) {
			stackCellsPop();
			
			Random r = new Random();
			
			boolean tomouCaminho = false;
			boolean deadEnd = deadEnd();
			
			while (!deadEnd && !tomouCaminho){
				
				int opcao = r.nextInt(4);
				
				switch (opcao) {
				case 0:
					if((headCell[0] - 1) >= 0){
						tomouCaminho = abrirCaminho(-1, 0);
					}
					break;
				case 1:
					if((headCell[1] - 1) >= 0){
						tomouCaminho = abrirCaminho(0, -1);
					}
					break;
				case 2:
					if((headCell[0] + 1) < TAMCELVAZ){
						tomouCaminho = abrirCaminho(1, 0);
					}
					break;
				case 3:
					if((headCell[1] + 1) < TAMCELVAZ){
						tomouCaminho = abrirCaminho(0, 1);
					}
					break;
				}
			}
		}
	}

	private void stackCellsPop() {
		headCell [0] = stackX.pop();
		headCell [1] = stackY.pop();
	}
	
	private void stackCellsPush() {
		stackX.push(headCell[0]);
		stackY.push(headCell[1]);
	}
	
	/**
	 * Abre o caminho
	 *
	 * @return Uma variavel do tipo boleano
	 */
	private boolean deadEnd() {
		if((headCell[0] - 1) >= 0)
			if(visitedCells [headCell[0] - 1] [headCell[1]] != '+')
				return false;
		
		if((headCell[1] - 1) >= 0)
			if(visitedCells [headCell[0]] [headCell[1] - 1] != '+')
				return false;
		
		if((headCell[0] + 1) < TAMCELVAZ)
			if(visitedCells [headCell[0] + 1] [headCell[1]] != '+')
				return false;
		
		if((headCell[1] + 1) < TAMCELVAZ)
			if(visitedCells [headCell[0]] [headCell[1] + 1] != '+')
				return false;
		
		return true;
	}

	private boolean abrirCaminho(int x, int y) {
		if (visitedCells [headCell[0] + x] [headCell[1] + y] != '+'){
			stackCellsPush();
			
			tabuleiroInicial [headCell[0]*2 + 1 + x] [headCell[1]*2 + 1 + y] = ' ';
			
			headCell [0] = headCell[0] + x;
			headCell [1] = headCell[1] + y;
			
			visitedCells [headCell[0]] [headCell[1]] = '+';
			
			stackCellsPush();
			
			return true;
		}
		return false;
	}
	
	/**
	 * Faz print na consola das variáveis da classe
	 */
	public void printHeadCell() {
		System.out.println(headCell[0]);
		System.out.println(headCell[1]);
	}
	
	public void printTabuleiro() {
		for (int i = 0; i < TAMANHO; i++ ) {
			for(int j = 0; j < TAMANHO; j++){
				System.out.print(tabuleiroInicial[i][j]);
				System.out.print(' ');
			}
			
			System.out.println();	
		}
	}
	
	/**
	 * Cria um clone da variavel tabuleiroInicial 
	 * 
	 * @return Uma variavel do tipo array de caracteres
	 */
	public char [] [] tabuleiroClone() {
		char[][] A2 = tabuleiroInicial.clone();
		for (int i = 0; i < A2.length; i++) {
		    A2[i] = A2[i].clone();
		}
		return A2;
	}

	/**
	 * Devolve o conteudo defenido pelas coordenadas do tabuleiro inicial
	 *
	 * @param i Uma variavel do tipo inteiro
	 * @param j Uma variavel do tipo inteiro
	 * @return Uma variavel do tipo caracter
	 */
	public char getChar(int i, int j) {
		return tabuleiroInicial[i][j];
	}

	/**
	 * Devolve o apontador do array completo do tabuleiro inicial
	 *
	 * @return Uma variavel do tipo array de caracteres
	 */
	public char[][] getTabuleiroInicial() {
		return tabuleiroInicial;
	}

	
}
