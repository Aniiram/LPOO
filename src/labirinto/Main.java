package labirinto;

import java.util.*;

public class Main {

	static final int TAMANHO = 7;

	public static void main(String[] args) {
		
		Scanner scanner;
		scanner = new Scanner(System.in);
		
		System.out.print("Qual é o tamanho do labirinto? ");
		int tamanho = scanner.nextInt();
		
		System.out.print("Qual é o numero de dragoes? ");
		int numDragoes = scanner.nextInt();
		
		System.out.println("1. Dragao Parado ");
		System.out.println("2. Movimentacao dragão aleatório ");
		System.out.println("3. Movimentacao dragao aleatorio alternada com dormida ");
		System.out.print("Modo de Jogo: ");
		int jogo = scanner.nextInt();
		
		Labirinto t = new Labirinto(tamanho, numDragoes);
		t.desenhaTabuleiro();
	
		while (!t.getAcabou()){
			
			System.out.println("W - cima");
			System.out.println("S - baixo");
			System.out.println("D - direita");
			System.out.println("A - esquerda");
			System.out.println("Comando para mover heroi:");
						
			
			char cin = scanner.next().charAt(0);
			
			t.read(jogo, cin);
			
			System.out.println();
			
			t.desenhaTabuleiro();
		}

		scanner.close();
		if(t.getGanhou())
			System.out.println("PARABÉNS GANHOU!");
		else	System.out.println("PERDEU!");
	
	}
}
