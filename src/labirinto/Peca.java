package labirinto;

public class Peca {
	private boolean stop = false;
	private char type;
	private int x,y;
	
	public Peca(char t, int xI, int yI) {
		type = t;
		x = xI;
		y = yI;
	}
	
	//modificar e mostrar as variaveis privadas desta classe
	public boolean getStop(){
		return stop;
	}
	
	public void setStop(boolean bool) {
		stop = bool;
	}
	
	public char getType(){
		return type;
	}
	
	public void setType(char t){
		type = t;		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int posX){
		x = posX;
	}

	public void setY(int posY){
		y = posY;
	}

	//funções normais de classes
	public String toString(){
		return "Peca(" + type + ", " + this.x + ", " + this.y + ")";
	}
	
	public boolean equals(Object p2) {
		if(		(this.x == ((Peca) p2).getX()) & 
				(this.y == ((Peca) p2).getY()) & 
				(this.type == ((Peca) p2).getType()) &
				(p2 != null))
			return true;
		return false;
	}
	
	public Peca clone() {
		return new Peca(type, x, y);
	}
}
