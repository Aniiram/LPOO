package labirinto;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

/**
 * Labirinto.java - A lógica do jogo está nesta classe
 * @author Carolina Moreira e Marina Camilo
 * Project 1 - LPOO
 */

public class Labirinto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tabuleiro caminho;

	private Peca heroi = new Peca('H', 1, 1);
	private Peca espada = new Peca('E', 2, 1);
	private Vector<Peca> dragoes = new Vector<Peca>();
	private Vector<Peca> dardos = new Vector<Peca>();
	private Peca dragao = new Peca('D', 2, 4);
	private Peca saida;
	private Peca dardo = new Peca('*', 1, 5);
	private boolean heroiArmado = false;
	//private boolean dragaoArmado = false;
	private int [] tempoDormir;
	int xRedor, yRedor;
	
	private char [] [] tabuleiro = new char [7] [7];
	
	//constructores
	/**
	 * Inicializa as peças de jogo e define a saída
	 */
	public Labirinto() {
		caminho = new Tabuleiro();
		tabuleiro = caminho.tabuleiroClone();
		
		inicializarDragao();
		inicializarEspada();
		inicializarHeroi();
		
		saida = caminho.getSaida();
		saida.setStop(true);
	}
	
	
	/**
	 * Cria o tabuleiro de jogo, fazendo um tabuleiro novo aleatório de acordo com o comprimento fornecido
	 *
	 * @param tamanho Uma variavel do tipo inteiro
	 * @param numDragoes Uma variavel do tipo inteiro
	 */
	public Labirinto(int tamanho, int numDragoes) {
		caminho = new Tabuleiro(tamanho);
		tabuleiro = caminho.tabuleiroClone();
		tempoDormir = new int [numDragoes];
		
		for (int i = 0; i < numDragoes && i < tamanho; i++){
			dragoes.add(inicializarDragao().clone());
		}
		
		inicializarEspada();
		inicializarHeroi();
		
		for (int i = 0; i < (tamanho/2); i++){
			dardos.add(inicializarDardo().clone());
		}
		
		
		saida = caminho.getSaida();
		saida.setStop(true);
	}

	/**
	 * Cria o tabuleiro do jogo, a partir de um array de chars
	 *
	 * @param m1 Uma variavel do tipo array de characteres
	 */
	public Labirinto(char[][] m1) {
		for(int j = 0; j < m1.length;j++){
			for (int i = 0; i < m1[j].length; i++){
				switch(m1[j][i]){
				case 'D':
					dragao.setX(j);
					dragao.setY(i);
					dragoes.add(dragao);
					m1[j][i] = ' ';
					break;
					
				case 'H':
					heroi.setX(j);
					heroi.setY(i);
					m1[j][i] = ' ';
					break;
					
				case 'E':
					espada.setX(j);
					espada.setY(i);
					m1[j][i] = ' ';
					break;
					
				case '*':
					dardo.setX(j);
					dardo.setY(i);
					dardos.add(dardo);
					m1[j][i] = ' ';
					break;
					
				case 'S':
					saida.setX(j);
					saida.setY(i);
				}
			}
		}
		caminho = new Tabuleiro(m1, saida);
		tabuleiro = caminho.tabuleiroClone();
		saida.setStop(true);
	}
	//verificação de jogo
	
	/**
	 * Booleano significativo do estado de progresso do jogo
	 */
	private boolean acabou = false;

	/**
	 * Booleano significativo do estado de victória do jogo
	 */
	private boolean ganhou = false;
	
	/**
	 * Verifica se o jogo acabou
	 *
	 * @return Uma variavel do tipo boleano
	 */
	public boolean getAcabou(){
		return acabou;
	}
	
	/**
	 * Verifica se o utlizador ganhou o jogo
	 *
	 * @return Uma variavel do tipo boleano
	 */
	public boolean getGanhou(){
		return ganhou;
	}
	
	//imprimir tableiro
	/**
	 * Desenha o tabuleiro de acordo com o comprimento especificado pelo utilizador
	 */
	public void desenhaTabuleiro() {
		tabuleiro = caminho.tabuleiroClone();
		
		colocarPeca();

		for (int i = 0; i < tabuleiro.length; i++ ) {
			for(int j = 0; j < tabuleiro[i].length; j++){
				System.out.print(tabuleiro[i][j]);
				System.out.print(' ');
			}
			
			System.out.println();	
		}
	}
	/**
	 * Coloca as várias peças no tabuleiro
	 */
	public void colocarPeca() {
		if(!espada.getStop())
			tabuleiro [espada.getX()][espada.getY()] = espada.getType();
				
		for(int i = 0; i < dardos.size(); i++) {
			if(!dardos.get(i).getStop()) 
			tabuleiro [dardos.get(i).getX()][dardos.get(i).getY()] = dardos.get(i).getType();	
		}
		
		for(int i = 0; i < dragoes.size(); i++) {
			if(!dragoes.get(i).getStop()) 
			tabuleiro [dragoes.get(i).getX()][dragoes.get(i).getY()] = dragoes.get(i).getType();	
		}
		
		if(saida.getStop())
			tabuleiro [saida.getX()][saida.getY()] = 'X';
		
		if(!heroi.getStop())
			tabuleiro [heroi.getX()][heroi.getY()] = heroi.getType();
	}
 
	//Jogada
	/**
	 * De acordo com o modo de jogo, faz a jogada do turno que se segue. 
	 *
	 * @param jogada Uma variavel do tipo inteiro
	 * @param cin Uma variavel do tipo character
	 */
	public void read (int jogada, char cin) {
		switch (jogada) {
		
		case 1:
			jogada (cin, heroi.getX(), heroi.getY());
			break;
		case 2:
			for (int i = 0; i < dragoes.size(); i++){
				dragao = dragoes.get(i);
				moverDragao();
				colocarPeca();
			}
			jogada (cin, heroi.getX(), heroi.getY());
			break;
		case 3:
			for (int i = 0; i < dragoes.size(); i++){
				dragao = dragoes.get(i);
				if(tempoDormir[i] <= 0){
					dragaoDorme(dragao, i);
					moverDragao();
					colocarPeca();
				}

				else 
					tempoDormir[i] -= 1; 

				colocarPeca();
			}
			
			jogada (cin, heroi.getX(), heroi.getY());
			break;
		}
	}
	/**
	 * Verifica o input do utilizador e move o herói ou dispara de acordo com esse input
	 *
	 * @param cin Uma variavel do tipo caracter
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void jogada(char cin, int x, int y) {
		switch (Character.toLowerCase(cin)){
		case 'w':
			colisionHeroi(x-1,y);
			break;
			
		case 'a':
			colisionHeroi(x,y-1);
			break;
			
		case 's':
			colisionHeroi(x+1,y);
			break;
			
		case 'd':
			colisionHeroi(x,y+1);
			break;
			
		case 'i':
			if (heroiArmado) {
				disparar(-1, 0);
			}
			break;
		case 'l':
			if (heroiArmado) {
				disparar(0, 1);
			}
			break;
		case 'j':
			if (heroiArmado) {
				disparar(0, -1);
			}
			break;
		case 'k':
			if (heroiArmado) {
				disparar(1, 0);
			}
			break;
		}
	}
	
	/**
	 * Dispara o dardo. Caso o dardo acerte num dragão, mata esse dragão 
	 *
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void disparar(int x, int y) {
		heroiArmado = false;
		if (x != 0){
			for (int i = heroi.getX(); i < tabuleiro.length && 0 < i; ){
				i += x;
				if (tabuleiro[i][heroi.getY()] == 'X')
					return;
				
				if(tabuleiro[i][heroi.getY()] == 'D' || tabuleiro[i][heroi.getY()] == 'd'|| tabuleiro[i][heroi.getY()] == 'F' || tabuleiro[i][heroi.getY()] == 'f'){
					for(int j = 0; j < dragoes.size(); j++) {
						if(dragoes.get(j).getX() == i && dragoes.get(j).getY() == heroi.getY()){
							killDragon(dragoes.get(j));
							return;
						}
					}
					
				}
			}
		}
		
		if (y != 0){
			for (int i = heroi.getY(); i < tabuleiro.length && 0 < i; ){
				i += y;
				if (tabuleiro[heroi.getX()][i] == 'X')
					return;
				
				if(tabuleiro[heroi.getX()][i] == 'D'|| tabuleiro[heroi.getX()][i] == 'd' || tabuleiro[heroi.getX()][i] == 'F' || tabuleiro[heroi.getX()][i] == 'f'){
					for(int j = 0; j < dragoes.size(); j++) {
						if(dragoes.get(j).getX() == heroi.getX() && dragoes.get(j).getY() == i){
							killDragon(dragoes.get(j));
							return;
						}
					}
					
				}
			}
		}
	}

	/**
	 * Verifica as colisões do herói com cada um dos objectos no mapa (paredes, dragão, dardos, espada, etc etc)
	 *
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void colisionHeroi(int x, int y){
				
		switch(tabuleiro[x][y]){
		
		case ' ':
			moverHeroi(x, y);
			break;
		case 'E':
			moverHeroi(x, y);
			
			heroi.setType('A');
			espada.setStop(true);
			break;
		case 'S':
			moverHeroi(x, y);
			
			ganhou = true;
			acabou = true;
			break;
		case 'D' :
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == x && dragoes.get(i).getY() == y)
					if(colisionHeroiDragao(dragoes.get(i))){
						moverHeroi(x, y);
					}	
			}
			break;	
		case 'F':
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == x && dragoes.get(i).getY() == y)
					if(colisionHeroiDragao(dragoes.get(i))){
						moverHeroi(x, y);
					}	
			}
			break;
		case 'f':
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == x && dragoes.get(i).getY() == y)
					colisionHeroiDragao(dragoes.get(i));
			}
			break;
		case 'X':
			moverHeroi(heroi.getX(), heroi.getY());
			break;
			
		case 'd':
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == x && dragoes.get(i).getY() == y){
					colisionHeroiDragao(dragoes.get(i));
				}	
			}
			break;	
		case '*':
			if(!heroiArmado)
				colisionDardo(x,y);
			moverHeroi(x,y);
			break;	
		}
	}

	/**
	 * Caso o herói não tenha um dardo, apanha-o. Se o herói já tiver um dardo, o dardo fica no mesmo sítio.
	 *
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void colisionDardo(int x, int y) { 
		for (int i = 0; i < dardos.size(); i++){
			if (dardos.get(i).getX() == x && dardos.get(i).getY() == y){
				heroiArmado = true;
				dardos.get(i).setStop(true);
				return;
			}
		}
	}

	/**
	 * Muda a posição do herói e verifica o que acontece de acordo com os objectos que estejam à sua volta
	 *
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void moverHeroi(int x, int y) {
		heroi.setX(x);
		heroi.setY(y);
		if(colisionEnredor('D', heroi.getX(), heroi.getY()) || 
				colisionEnredor('F', heroi.getX(), heroi.getY()) || 
				colisionEnredor('d', heroi.getX(), heroi.getY()) || 
				colisionEnredor('f', heroi.getX(), heroi.getY())) 
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == xRedor && dragoes.get(i).getY() == yRedor)
					colisionHeroiDragao(dragoes.get(i));	
			}
	}
	
	/**
	 * Define o que acontece numa colisão entre o herói e o dragão. Se o herói tiver a espada, mata o dragão. 
	 * Se o herói não tiver a espada mas o dragão esteja a dormir, não acontece nada.
	 * Se o herói não tiver espada e o dragão esteja acordado, o herói morre, e o jogo é perdido.  
	 *
	 * @param dragao Uma variavel do tipo da classe Peca
	 * @return Uma variavel do tipo boleano
	 */
	private boolean colisionHeroiDragao(Peca dragao) {
		if(heroi.getType() == 'A'){
			return killDragon(dragao);
		}
		
		if(dragao.getType() == 'd'){
			return true;
		}
					
		else {
			heroi.setStop(true);
			ganhou = false;
			acabou = true;
			return false;
		}
	}

	/**
	 * Mata o dragão. Caso esse seja o último dragão, mostra a saída.
	 *
	 * @param dragao Uma variavel do tipo da classe Peca
	 * @return Uma variavel do tipo boleano
	 */
	private boolean killDragon(Peca dragao) {
		dragao.setStop(true);
		boolean allDead = true;
		
		for(int i = 0; i < dragoes.size(); i++) {
			if (!dragoes.get(i).getStop())
				allDead = false;	
		}
		
		if(allDead)
			saida.setStop(false);
		return true;
	}

	/**
	 * Verifica se existe uma colisão de uma peça com as peças que a rodeiam 
	 *
	 * @param type Uma variavel do tipo caracter
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 * @return Uma variavel do tipo boleano
	 */
	private boolean colisionEnredor(char type, int x, int y) {
		
		if((x+1) < caminho.TAMANHO){
			if(tabuleiro[x+1][y] == type) {
				xRedor = x+1;
				yRedor= y;
				return true;
			}
		}
		
		if(0 <= (x-1)){
			if(tabuleiro[x-1][y] == type) {
				xRedor = x-1;
				yRedor = y;
				return true;
			}
		}
		
		if((y+1) < caminho.TAMANHO){
			if(tabuleiro[x][y+1] == type) {
				xRedor = x;
				yRedor = y+1;
				return true;
			}
		}
		
		if(0 <= (y-1)){
			if(tabuleiro[x][y-1] == type) {
				xRedor = x;
				yRedor = y-1;
				return true;
			}
		}
	
		return false;	
	}
	
	/**
	 * Move o dragão aleatoriamente
	 */
	private void moverDragao(){
		Random r = new Random();
		
		int n = r.nextInt(4);
		
		switch (n) {
		case 0:
			colisionDragon(dragao.getX()-1, dragao.getY());
			break;
		case 1:
			colisionDragon(dragao.getX(), dragao.getY()-1);
			break;
		case 2:
			colisionDragon(dragao.getX()+1, dragao.getY());
			break;
		case 3:
			colisionDragon(dragao.getX(), dragao.getY()-1);
			break;
		}
	}

	/**
	 * Decide o que acontece quando há um encontro entre o herói e o dragão, caso este esteja a dormir e caso o herói tenha uma espada
	 *
	 * @param x Uma variavel do tipo inteiro
	 * @param y Uma variavel do tipo inteiro
	 */
	private void colisionDragon(int x, int y) {
		
		if(!(0 <= x & x < caminho.TAMANHO & 0 <= y & y < caminho.TAMANHO)) {
			return;
		}
		
		switch(tabuleiro[x][y]){
		
		case ' ':
			if(dragao.getType() == 'd'){
				dragao.setType('d');
				espada.setType('E');
				dragao.setX(x);
				dragao.setY(y);
				break;
			}
			dragao.setType('D');
			espada.setType('E');
			dragao.setX(x);
			dragao.setY(y);
			break;
			
		case 'E':
			if(dragao.getType() == 'd'){
				dragao.setType('f');
				espada.setType('f');
				dragao.setX(x);
				dragao.setY(y);
				break;
			}
			dragao.setType('F');
			espada.setType('F');
			dragao.setX(x);
			dragao.setY(y);
			break;
			
		case '*':
			if(dragao.getType() == 'd'){
				dragao.setType('d');
				espada.setType('E');
				dragao.setX(x);
				dragao.setY(y);
				break;
			}
			dragao.setType('D');
			espada.setType('E');
			dragao.setX(x);
			dragao.setY(y);
			break;
		}
	}	
	/**
	 * Define se o dragão adormece nesta ronda ou não, e, caso adormeça, passado 5 rondas acorda
	 *
	 * @param dragao Uma variavel do tipo da classe Peca
	 * @param i Uma variavel do tipo inteiro
	 */
	private void dragaoDorme(Peca dragao, int i) {
		Random r = new Random();
		int value = r.nextInt(5);
		
		if (value == 0) {
			tempoDormir[i] = 5;
			if(dragao.getType() == 'F')
				dragao.setType('f');
			if(dragao.getType() == 'D')
			dragao.setType ('d');
		}
		else {
			if(dragao.getType() == 'f')
				dragao.setType('F');
			if(dragao.getType() == 'd')
			dragao.setType ('D');
		}	
	}
	
	//inicializar pecas inicialmente 
	
	/**
	 * Inicializa o herói numa localização do tabuleiro na qual não se encontrem quaisquer outros objectos
	 */
	private void inicializarHeroi() {
		Random r = new Random();
		boolean done = false;
		int x, y;

		while (!done){

			x = r.nextInt(caminho.TAMANHO - 2) + 1;
			y = r.nextInt(caminho.TAMANHO - 2) + 1;

			if( tabuleiro [x][y] == ' '){
				if ( !colisionEnredor('S', x, y) && !colisionEnredor('E', x, y) && !colisionEnredor('D', x, y) ) {
					done = true;
					tabuleiro [x][y] = 'H';
					heroi.setX(x);
					heroi.setY(y);
				}
			}
		}
	}
		
	/**
	 * Inicializa cada dragão numa localização do tabuleiro na qual não se encontrem quaisquer outros objectos
	 *
	 * @return Uma variavel do tipo da classe Peca
	 */
	private Peca inicializarDragao() {
		Random r = new Random();
		boolean done = false;
		int x, y;

		while (!done){

			x = r.nextInt(caminho.TAMANHO - 2) + 1;
			y = r.nextInt(caminho.TAMANHO - 2) + 1;

			if( tabuleiro [x][y] == ' '){
				if ( !colisionEnredor('S', x, y) && !colisionEnredor('E', x, y) && !colisionEnredor('H', x, y) && !colisionEnredor('D', x, y)) {
					done = true;
					tabuleiro [x][y] = 'D';
					dragao.setX(x);
					dragao.setY(y);
				}
			}
		}
		return dragao;
	}
	
	/**
	 * Iniciliza a espada numa lozalização aleatória do tabuleiro na qual não se encontrem quaisquer outros objectos
	 */
	private void inicializarEspada() {
		Random r = new Random();
		boolean done = false;
		int x, y;

		while (!done){

			x = r.nextInt(caminho.TAMANHO - 2) + 1;
			y = r.nextInt(caminho.TAMANHO - 2) + 1;

			if( tabuleiro [x][y] == ' '){
				if ( !colisionEnredor('S', x, y) 
						& !colisionEnredor('D', x, y) 
						& !colisionEnredor('H', x, y) ) {
					done = true;
					tabuleiro [x][y] = 'E';
					espada.setX(x);
					espada.setY(y);
				}
			}
		}
	}

	/**
	 * Iniciliza os dardos em localizações aleatórias do tabuleiro com número variável de acordo com o tamanho
	 *
	 * @return Uma variavel do tipo da classe Peca
	 */
	private Peca inicializarDardo() {
		Random r = new Random();
		boolean done = false;
		int x, y;

		while (!done){

			x = r.nextInt(caminho.TAMANHO - 2) + 1;
			y = r.nextInt(caminho.TAMANHO - 2) + 1;

			if( tabuleiro [x][y] == ' '){
					done = true;
					tabuleiro [x][y] = '*';
					dardo.setX(x);
					dardo.setY(y);
			}
		}
		return dardo;
	}
	/**
	 * Devolve a fisionomia do labirinto
	 *
	 * @return Uma variavel do tipo da classe Tabuleiro
	 */
	public Tabuleiro getCaminho(){
		return caminho;
	}
	/**
	 * Devolve o apontador estado actual do jogo em progresso
	 *
	 * @return Uma variavel do tipo array de caracteres
	 */
	public char [] [] getJogo(){
		return tabuleiro;
	}
	/**
	 * Devolve o tamanho do labirinto
	 *
	 * @return Uma variavel do tipo inteiro
	 */
	public int getTamanho() {
		return caminho.TAMANHO;
	}
	
	/**
	 * Devolve a posição da saída
	 *
	 * @return Uma variavel do tipo Object
	 */
	public Object getSaidaPos() {
		return saida.getPos();
	}

	/**
	 * Devolve a posição do herói
	 *
	 * @return Uma variavel do tipo Object
	 */
	public Object getHeroiPos() {
		return heroi.getPos();
	}
	
	/**
	 * Devolve a posição do dragão
	 * 
	 * @return Uma variavel do tipo Object
	 */
	public Object getDragonPos() {
		return dragao.getPos();
	}

	/**
	 * Devolve a posição da espada
	 * 
	 * @return Uma variavel do tipo Object
	 */
	public Object getEspadaPos() {
		return espada.getPos();
	}
	
	/**
	 * Devolve o tamanho do tabuleiro
	 * 
	 * @param size Uma variavel do tipo inteiro
	 * @return Uma variavel do tipo da classe Tabuleiro
	 */
	public Tabuleiro newCaminho(int size){
		return new Tabuleiro(size);
	}
	
}
