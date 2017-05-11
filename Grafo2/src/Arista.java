
public class Arista {
	private Integer i;
	private Integer d;
	private Integer p;
	private boolean visitado;
	
	public Arista(){
		visitado = false;
	} 
	
	public void agregarArista(Integer inicio, Integer destino, Integer peso) {
		i = inicio;		d = destino;		p = peso;
	}
	
	public void recorrido(){
		visitado = true;
	}
	
	public Integer getDestino() {
		return d;
	}

	public Integer getPeso() {
		return p;
	}
	
	public boolean fueVisitado() {
		return visitado;
	}

	@Override
	public String toString(){
		return "" +d;
	}
	

	
	
	public void setPeso(Integer peso){
		p = peso;
	}

	public Integer getInicio() {
		return i;
	}

}
