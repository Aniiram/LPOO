package labirinto;

import java.util.Random;
import java.util.Vector;

public class Labirinto {
	private Tabuleiro caminho;

	private Peca heroi = new Peca('H', 1, 1);
	private Peca espada = new Peca('E', 2, 1);
	private Vector<Peca> dragoes = new Vector<Peca>();
	private Peca dragao = new Peca('D', 3, 1);
	private Peca saida;
	private int [] tempoDormir;
	int xRedor, yRedor;
	
	private char [] [] tabuleiro = new char [7] [7];
	
	//constructores
	Labirinto() {
		caminho = new Tabuleiro();
		tabuleiro = caminho.tabuleiroClone();
		
		inicializarDragao();
		inicializarEspada();
		inicializarHeroi();
		
		saida = caminho.getSaida();
		saida.setStop(true);
	}
	
	Labirinto(int tamanho, int numDragoes) {
		caminho = new Tabuleiro(tamanho);
		tabuleiro = caminho.tabuleiroClone();
		tempoDormir = new int [numDragoes];
		
		for (int i = 0; i < numDragoes && i < tamanho; i++){
			dragoes.add(inicializarDragao().clone());
		}
		
		inicializarEspada();
		inicializarHeroi();
		
		saida = caminho.getSaida();
		saida.setStop(true);
	}
	
	//verificação de jogo
	private boolean acabou = false;

	private boolean ganhou = false;
	
	public boolean getAcabou(){
		return acabou;
	}
	
	public boolean getGanhou(){
		return ganhou;
	}
	
	//imprimir tableiro
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
	
	private void colocarPeca() {
		if(!heroi.getStop())
			tabuleiro [heroi.getX()][heroi.getY()] = heroi.getType();
		
		if(!espada.getStop())
			tabuleiro [espada.getX()][espada.getY()] = espada.getType();
		
		for(int i = 0; i < dragoes.size(); i++) {
			if(!dragoes.get(i).getStop()) 
			tabuleiro [dragoes.get(i).getX()][dragoes.get(i).getY()] = dragoes.get(i).getType();	
		}
		
		if(saida.getStop())
			tabuleiro [saida.getX()][saida.getY()] = 'X';
	}
 
	//Jogada
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
				}

				else 
					tempoDormir[i] -= 1; 

				colocarPeca();
			}
			
			jogada (cin, heroi.getX(), heroi.getY());
			break;
		}
	}
	
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
		}
	}

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
		}
	}

	private void moverHeroi(int x, int y) {
		heroi.setX(x);
		heroi.setY(y);
		if(colisionEnredor('D', heroi.getX(), heroi.getY()) || colisionEnredor('F', heroi.getX(), heroi.getY())) 
			for(int i = 0; i < dragoes.size(); i++) {
				if (dragoes.get(i).getX() == xRedor && dragoes.get(i).getY() == yRedor)
					colisionHeroiDragao(dragoes.get(i));	
			}
	}
	
	private boolean colisionHeroiDragao(Peca dragao) {
		if(heroi.getType() == 'A'){
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
		}
	}	
	
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
}
