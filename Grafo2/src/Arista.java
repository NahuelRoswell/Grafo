
public class Arista {
	private Integer i;
	private Integer d;
	private Integer p;
	private boolean visitado;
	private Arista siguiente, anterior;
	
	public Arista(){
		visitado = false;
	} 
	
	Arista(Integer inicio, Integer destino, Integer peso) {
		i = inicio;		d = destino;		p = peso;
	}
	
	public void agregarArista(Integer inicio, Integer destino, Integer peso) {
		i = inicio;		d = destino;		p = peso;
	}
	
	public void yaRecorrido(){
		visitado = true;
	}
	
	public Integer getDestino() {
		return d;
	}

	public Integer getPeso() {
		return p;
	}
	
	public boolean fueRecorrido() {
		return visitado;
	}

	public void setPeso(Integer peso){
		p = peso;
	}

	public Integer getInicio() {
		return i;
	}
	
	public void enlazar(Arista arista){
		arista.setAnterior(this);
//		this.siguiente = arista;
		
	}
	private void setAnterior(Arista arista) {
		anterior = arista;
	}
	
	public Arista getAnterior(){
		return anterior;
	}
	
	public Arista getSiguiente(){
		return siguiente;
	}
	
	
	@Override
	public String toString(){
		return "i: "+i +" -> d: " +d +"|| p acumulado: " +p;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean r = false;
		if(obj instanceof Arista){
			Arista a = (Arista) obj;
			r = this.getInicio().equals(a.getInicio()) &&
					this.getDestino().equals(a.getDestino());
		}
		return r;
	}
}
