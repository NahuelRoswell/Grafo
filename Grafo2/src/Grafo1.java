import java.io.Serializable;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Grafo1 implements Serializable{
	private Tupla[][] grafo;
	private static int ids = 0;
	private int id;
	
	Grafo1(int vertices){
		grafo = new Tupla[vertices][vertices];
		
		inicializar();
	}
		
	Grafo1(Coordinate coordenada){
		grafo = new Tupla[0][0];
		
		inicializar();
		
		grafo[0][0] = new Tupla();
		grafo[0][0].crearCoordenada(coordenada);
	}

	private void inicializar() {
		for(int i = 0; i < getVertices(); i++) 
			for (int j = 0; j < getVertices(); j++)
				if (grafo[i][j]==null)
					grafo[i][j]= new Tupla();
	}
	
	public void agregarVertice(Coordinate latitudLongitud) {
		int vertices = grafo.length;

		Tupla nueva[][] = new Tupla[vertices + 1][vertices + 1];
		for (int i = 0; i < vertices; ++i)
			for (int j = 0; j < vertices; ++j)
				nueva[i][j] = grafo[i][j];

		grafo = nueva;
		inicializar();
		grafo[vertices][vertices].crearCoordenada(latitudLongitud);
	}

	public void agregarArista(int inicio, int destino, int peso){
		chequearIndice(inicio, destino, "agregar Arista");
		
		grafo[inicio][destino].arista().agregar(inicio,destino, peso);
	}
	
	public void agregarArista(int inicio, int destino, int peso, boolean peaje){
		chequearIndice(inicio, destino, "agregar Arista");
		 
		grafo[inicio][destino].arista().agregar(inicio,destino, peso, peaje);
	}
	
	public Arista tomarArista(int inicio, int destino){
		chequearIndice(inicio,destino,"tomar Arista");
		
		return grafo[inicio][destino].arista();
	}
	
	public Coordinate tomarCoordenada(int inicio, int destino){
		chequearIndice(inicio,destino,"tomar coordenada");
		
		return grafo[inicio][destino].coordenada();
	}
	
	public int tamaño(){
		return grafo.length;
	}
	
	private void chequearIndice(int indice, int destino, String accion) {
		if(indice < 0 || destino < 0 || indice > getVertices()-1 || destino > getVertices()-1)
			throw new IllegalArgumentException("el indice para " +accion +" esta fuera de rango! I =" +indice
					+" J = "+destino);
	}
	
	public ArrayList<Integer> getVecinosInt(int indice){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for (int i = 0; i < grafo.length; i++)
			if (grafo[indice][i].arista().getDestino() != null 
				&& !grafo[indice][i].arista().fueRecorrido())
					ret.add(grafo[indice][i].arista().getDestino());

		return ret;
	}
	
	public ArrayList<Arista> getVecinos(int indice){
		ArrayList<Arista> ret = new ArrayList<Arista>();
		
		for (int i = 0; i < grafo.length; i++)
			if (grafo[indice][i].arista().getDestino() != null 
				&& !grafo[indice][i].arista().fueRecorrido())
					ret.add(grafo[indice][i].arista());

		return ret;
	}
	
	
	public int getVertices(){
		return grafo.length;
	}
	
	/** Retorna la cantidad de vecinos**/
	public int getGrado(int i){
		int vecinos = 0;
		for (int j = 0; j < getVertices(); j++) 
			if (grafo[i][j].arista().getDestino()!=null)
				vecinos++;
		
		return vecinos;
	}
	
	public Grafo clonarEn(Grafo grafoClonado) {
		for (int inicio = 0; inicio < getVertices(); inicio++)
			for (int destino = 0; destino < getVertices(); destino++)
				if (grafo[inicio][destino].arista().getPeso() != null)
					grafoClonado.agregarArista(inicio, tomarArista(inicio, destino).getDestino(),
							tomarArista(inicio, destino).getPeso());

		return grafoClonado;
	}

	public void print() {
		for (int i = 0; i < grafo.length; i++) {
			System.out.print(i + " -- ");
			
			for (int j = 0; j < grafo.length; j++) {
				if (j == i)
					System.out.print("0 ");
				else if (grafo[i][j].arista().getPeso() != null) // si ij ∈ A
					System.out.print(grafo[i][j] + " ");
				else
					System.out.print("& "); // si ij ∈ A

				if (j == grafo.length - 1)
					System.out.println();
			}
		}
	}
	
	public int getId(){
		return id;
	}
	
	public static void main(String[] args) {
		Grafo1 grafo = new Grafo1(0);
//		grafo.agregarArista(0, 1, 8);
//		grafo.agregarArista(0, 2, 1);
//		grafo.agregarArista(1, 3, 7);
//		grafo.agregarArista(2, 3, 1);
		grafo.print();
		
		
//		ArrayList<Integer> ret = grafo.getVecinos(0);
//		for(Integer i: ret) if (i!=null)
//			System.out.print(i +", ");
//		grafo.print();
//		grafo.print();
		 
//		Grafo g = new Grafo1(0);
		Coordinate c = new Coordinate(2,1);
		grafo.agregarVertice(c);
//		grafo.agregarVertice(c);
//		grafo.agregarVertice(c);
//		grafo.agregarArista(0, 1, 3,false);
		grafo.print();
		//		grafo.agregarVertice(c);
	}





		
}