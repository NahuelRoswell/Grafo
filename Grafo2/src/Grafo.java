import java.util.ArrayList;

public class Grafo {
	private ArrayList<ArrayList<Arista>> grafo;
	
	public Grafo(int verticesIniciales){
		grafo = new ArrayList<ArrayList<Arista>>();
		
		for (int i = 0; i < verticesIniciales; i++)
			grafo.add(new ArrayList<Arista>());
	}
	
	public void agregarAristaConPeaje(int inicio, int destino, int distancia, boolean peaje){
		chequearArista(inicio,destino, "chequear");
		
		Arista deIda = new Arista(inicio, destino, distancia, peaje);
		Arista deVuelta = new Arista(destino, inicio, distancia, peaje);
		
		//al ser relaciones simétricas si no está en una, no está en la otra
		if (!existeArista(inicio, destino)) {
			grafo.get(inicio).add(deIda);
			grafo.get(destino).add(deVuelta);
		}
	}

	/**Una arista es la linea que conecta 2 vertices**/
	public void agregarArista(int inicio, int destino, int distancia) {
		chequearArista(inicio, destino, "chequear");

		Arista deIda = new Arista(inicio, destino, distancia);
		Arista deVuelta = new Arista(destino, inicio, distancia);
		
		//al ser relaciones simétricas si no está en una, no está en la otra
		if (!existeArista(inicio,destino)){
			grafo.get(inicio).add(deIda);
			grafo.get(destino).add(deVuelta);
		}
	}
	
	public Arista tomarArista(int aristaInicio, int vecino){
//		chequearArista(aristaInicio, vecino,"consultar");
		
		return grafo.get(aristaInicio).get(vecino);
	}
	
	boolean existeArista(int inicio, int destino){
		chequearArista(inicio, destino, "consultar");
		
		boolean existe = false;
		ArrayList<Arista> vecinos = getVecinos(inicio);
		for (Arista a: vecinos)
			existe = existe || a.getInicio() == inicio && a.getDestino() == destino;
		
		return existe;
	}
	
	public void eliminarArista(int inicio, int destino) {
		chequearArista(inicio, destino, "eliminar");
		
		//al ser relaciones simétricas si no está en una, no está en la otra
		for (int j = 0; j < getGrado(inicio); j++)
			if (grafo.get(inicio).get(j).getDestino() == destino)
				grafo.get(inicio).remove(j);

		for (int j = 0; j < getGrado(destino); j++)
			if (grafo.get(destino).get(j).getDestino() == inicio)
				grafo.get(destino).remove(j);
	}
	
	public boolean tienePeaje(int inicio, int destino){
		chequearArista(inicio,destino,"consultar peaje");
		
		boolean tiene = false;
		for (Arista a: grafo.get(inicio))
			if (a.getDestino() == destino)
				tiene = a.tienePeaje();
		
		return tiene;
	}
	
	public void setEstadoPeaje(int inicio, int destino, boolean estado){
		chequearArista(inicio, destino, "poner peaje");
		
		for(Arista a: grafo.get(inicio))
			if (a.getDestino() == destino)
				a.setPeaje(estado);
		
		for (Arista a: grafo.get(destino))
			if (a.getInicio() == inicio)
				a.setPeaje(estado);
	}
	
	public int getPeso(int inicio, int destino){
		chequearArista(inicio ,destino, "comprobar");
		
		int peso = 0;
		for (Arista a: grafo.get(inicio))
			if (a.getDestino() == destino)
				peso = a.getPeso();
		
		return peso;
	}
	
	public ArrayList<Arista> getVecinos(int indice){
		chequearVertice(indice, "los vecinos");
		
		return grafo.get(indice);
	}
	
	/**La cantidad de vertices determinan el tamaño del arreglo**/
	public int getVertices(){
		return grafo.size();
	}
	
	/** grado es la cantidad de aristas que tiene un vertice**/
	public int getGrado(int indice){
		chequearVertice(indice, "el grado");
		
		return grafo.get(indice).size();
	}
	
	public Grafo clonarA(Grafo g){
		ArrayList<ArrayList<Arista>> ArrayGrafo = new ArrayList<ArrayList<Arista>>();
		
		for (int i = 0; i < getVertices(); i++){
			ArrayGrafo.add(new ArrayList<Arista>());
			
			for (int j = 0; j < getVecinos(i).size(); j++)
				ArrayGrafo.get(i).add(grafo.get(i).get(j));
		}
		
		g.setGrafo(ArrayGrafo);
		
		return g;
		
	}
	
	private void setGrafo(ArrayList<ArrayList<Arista>> g){
		grafo = g;
	}
	
	private void chequearVertice(int i, String accion){
		if (i< 0 || i>= getVertices())
			throw new IllegalArgumentException("se intentó consultar "+accion +" de un vertice inexsite i = "+i);
	}
	
	private void chequearArista(int i, int j, String string) {
		if (i < 0 || i >= getVertices())
			throw new IllegalArgumentException("Se intentó " + string + " una arista fuera de rango! I = " + i);

		if (j < 0 || j >= getVertices())
			throw new IllegalArgumentException("se intentó " + string + " una arista fuera de rango! J = " + j);

		if (j == i)
			throw new IllegalArgumentException(
					"Se intentó " + string + " una arista con vertices iguales! i = j =" + i);
	}
	
	public static void main(String[] args) {		
		Grafo principal = new Grafo(5);
		
		principal.agregarArista(0, 1, 8);
		principal.agregarArista(0, 1, 8);
		principal.agregarArista(1, 3, 2);
		principal.agregarArista(3, 4, 2);
		principal.agregarArista(2, 4, 5);
		principal.agregarArista(0, 2, 5);
		
		Grafo clonado = new Grafo(0);
		
		principal.clonarA(clonado);
		
		principal.eliminarArista(0, 1);
		
		ArrayList<Arista> vecinos = clonado.getVecinos(0);
		for (Arista a: vecinos)
			System.out.println(a.getDestino());
		
//		System.out.println(principal.tienePeaje(1, 3));
		
		
	}
	
	

}
