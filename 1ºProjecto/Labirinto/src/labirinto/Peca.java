package labirinto;

import java.io.Serializable;

/**
 * Peca.java - Esta classe quarda os elementos do Labirinto a exceccao do parede
 * @author Carolina Moreira e Marina Camilo
 * Project 1 - LPOO
 */

public class Peca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean stop = false;
	private char type;
	private int x,y;
	
	/**
	 * Iniciliza a posic�o inicial, final e o tipo do elemento do Jogo
	 * 
	 * @param t Uma variavel do tipo caracter
	 * @param xI Uma variavel do tipo inteiro
	 * @param yI Uma variavel do tipo inteiro
	 */
	public Peca(char t, int xI, int yI) {
		type = t;
		x = xI;
		y = yI;
	}
	
	//modificar e mostrar as variaveis privadas desta classe
	
	/**
	 * Devolve se a pe�a ainda existe
	 *
	 * @return Uma variavel do tipo boleano
	 */
	public boolean getStop(){
		return stop;
	}
	
	/**
	 * Se stop estiver a true, a pe�a deixou de existir (deixa de ser desenhada)
	 *
	 * @param bool Uma variavel do tipo boleano
	 */
	public void setStop(boolean bool) {
		stop = bool;
	}
	
	/**
	 * Devolve o tipo de pe�a
	 *
	 * @return Uma variavel do tipo caracter
	 */
	public char getType(){
		return type;
	}
	
	/**
	 * Define o tipo de pe�a
	 *
	 * @param t Uma variavel do tipo caracter
	 */
	public void setType(char t){
		type = t;		
	}
	
	/**
	 * Devolve a posi��o vertical da pe�a
	 *
	 * @return Uma variavel do tipo inteiro
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Devolve a posi��o horizontal da pe�a
	 *
	 * @return Uma variavel do tipo inteiro
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Define a posi��o vertical da pe�a
	 *
	 * @param posX Uma variavel do tipo inteiro
	 */
	public void setX(int posX){
		x = posX;
	}

	/**
	 * Define a posi��o horizontal da pe�a
	 *
	 * @param posY Uma variavel do tipo inteiro
	 */
	public void setY(int posY){
		y = posY;
	}

	//fun��es normais de classes
	/**
	 * Devolve uma frase com os param�tros da pe�a
	 * 
	 * @return Uma variavel do tipo String
	 */
	public String toString(){
		return "Peca(" + type + ", " + this.x + ", " + this.y + ")";
	}
	
	/**
	 * Verifica se duas pe�as s�o iguais e est�o na mesma posi��o
	 * 
	 * @return Uma variavel do tipo boleano
	 */
	public boolean equals(Object p2) {
		if(		(this.x == ((Peca) p2).getX()) & 
				(this.y == ((Peca) p2).getY()) & 
				(this.type == ((Peca) p2).getType()) &
				(p2 != null))
			return true;
		return false;
	}
	
	/**
	 * Devolve a posi��o da pe�a
	 *
	 * @return Uma variavel do tipo array de inteiros
	 */
	public int[] getPos() {
		return new int[]{x,y};
	}
	
	/**
	 * Cria um clone de uma pe�a
	 * 
	 * @return Uma variavel do tipo da classe Peca
	 */
	public Peca clone() {
		return new Peca(type, x, y);
	}
}
