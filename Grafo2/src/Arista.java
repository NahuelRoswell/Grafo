 
public class Arista {
	private int inicio;
	private int destino;
	private int distancia;
	private boolean peaje;
	
	Arista(int i,int des, int dis){
		inicio = i;
		destino = des;
		distancia = dis;
		peaje = false;
	}
	
	Arista(int i, int des, int dis, boolean pe){
		inicio = i;
		destino = des;
		distancia = dis;
		peaje = pe;
	}

	public int getInicio() {
		return inicio;
	}

	public int getDestino() {
		return destino;
	}

	public int getPeso() {
		return distancia;
	}
	
	public boolean tienePeaje(){
		return peaje;
	}
	
	protected void setPeaje(boolean p){
		peaje = p;
	}
	
	@Override
	public String toString(){
		return "[Inicio = " +inicio +"] --> [destino = " +destino +"]";
	}
	
}
