
public class Arista {
	private Integer d;
	private Integer p;
	private boolean recorrida;
	
	public Arista(){
		recorrida = false;
	}
	
	public void agregarArista(int destino, int peso) {
		d = destino;
		p = peso;
	}
	
	public void recorrida(){
		recorrida = true;
	}
	
	public Integer getDestino() {
		return d;
	}

	public Integer getPeso() {
		return p;
	}
	
	public boolean fueRecorrida() {
		return recorrida;
	}

	@Override
	public String toString(){
		return "" + d;
	}


}
