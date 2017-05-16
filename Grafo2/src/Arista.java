import java.io.Serializable;
 
public class Arista implements Serializable{
	private Integer inicio;
	private Integer destino;
	private Integer peso;
	private Arista siguiente, anterior;
	private boolean visitado;
	private boolean peaje;
	private int peajesVisitados = 0;

	public Arista(){
		visitado = false;
	} 

	Arista(Integer i, Integer d, Integer p) {
		agregar(i, d, p, false);
	}
	
	public void agregar(Integer i, Integer d, Integer p) {
		inicio = i;		destino = d;	peso = p;	peaje = false;
	}
	
	public void agregar(Integer i, Integer d, Integer p, boolean peaj) {
		agregar(i, d, p);
		peaje = peaj;

		if (tienePeaje())
			sumarPeajeRecorrido();
	}

	public void yaRecorrido() {
		visitado = true;
	}
	
	public Integer getInicio() {
		return inicio;
	}
	
	public Integer getDestino() {
		return destino;
	}

	public Integer getPeso() {
		return peso;
	}
	
	public boolean fueRecorrido() {
		return visitado;
	}

	public void setPeso(Integer peso){
		peso = peso;
	}
	
	public void asignarAnteriorDe(Arista arista){
		arista.setAnterior(this);
		
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
	
	public boolean tienePeaje() {
		return peaje;
	}
	
	public int getPeajesVisitados() {
		return peajesVisitados;
	}

	public void sumarPeajeRecorrido(){
		peajesVisitados++;
	}

	@Override
	public String toString(){
		return "\ni: "+inicio +" -> d: " +destino 
				+"\n p acumulado: " +peso +" peajesV: "+peajesVisitados;
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

	public void setPeajesRecorridos(int peajes) {
		peajesVisitados = peajes;
	}
}
