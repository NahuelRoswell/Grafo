import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solver {
	ArrayList<Arista> auxiliar = new ArrayList<Arista>();
	Set<Integer> vecinos = new HashSet<Integer>();
	LinkedList<Arista> camino = new LinkedList<Arista>();
	
	private Grafo2 g;
	private int i, solucion;
	
	public Solver(Grafo2 grafo, int inicio, int destino) {
		g = grafo;		i = inicio;		solucion = destino;
	}
	
	private void mostrarArreglo(ArrayList<Integer> array){
		for (Integer i: array)
			System.out.print(i +", ");
	}
	
	
	public static void main(String[] args) {
		Grafo2 grafo = new Grafo2(4);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 9);
		grafo.agregarArista(2, 3, 9);
		grafo.agregarArista(1, 3, 1);
//		grafo.agregarArista(1, 3, 2);
//		grafo.agregarArista(2, 0, 0);
//		grafo.agregarArista(2, 3, 1);
//		grafo.agregarArista(3, 1, 4);
//		grafo.agregarArista(3, 2, 4);
		Solver s = new Solver(grafo,0,3);
		System.out.print("auxiliar: ");
		s.caminoMinimo(0);
		s.limpiarVecinos();
	}
	

	public LinkedList<Arista> distra(int inicio, int fin) {
		if (camino.isEmpty())
			caminoMinimo(inicio);

		while (camino.getLast().getDestino() != fin) {
			caminoMinimo(inicio);

		}
		return camino;
	}
	
	private  void caminoMinimo(int indice) {
		agregarVecinos(indice);
		// recorre vecinos y agrega a camino auxiliar
		cargarAristas(indice);
		
		//se queda con la mejor opcion de los vecinos y lo agrega al camino final
		Mejorvecino();
				
//		__________________________________________________
//		agregarVecinos(camino.get(0).getDestino());

//		cargarAristas(camino.get(0).getDestino());
		
//		Mejorvecino();


	}

	private void Mejorvecino() {
		if (!auxiliar.isEmpty()) {
			Arista menor = auxiliar.get(0);

			for (Arista i : auxiliar)
				if (i.getPeso() < menor.getPeso())
					menor = i;

			if (camino.isEmpty()) {
				menor.yaRecorrido();
				camino.add(menor);
			} else {
				cambiarAristayPeso(menor);

			}
		}
		limpiarVecinos();
	}


	private void cambiarAristayPeso(Arista menor) {
		for (int i = 0; i < camino.size(); i++) 
			if (camino.get(i).getDestino() == menor.getInicio()){
				menor.setPeso(camino.get(i).getPeso() + menor.getPeso());
				camino.remove(i);
			}
		
		camino.add(menor);
	}

	private void limpiarVecinos() {
		for (int i = 0; i < auxiliar.size(); i++) {
			if(auxiliar.get(i).fueRecorrido()==true)
				auxiliar.remove(i);
		}

		for(Arista i: auxiliar)
			System.out.print(i +", ");
	}

	
	private void cargarAristas(int indice) {
//		limpiarAuxiliar();
		
		for(Integer destino: vecinos) 
			if (g.tomarArista(indice, destino).getPeso() !=null && !g.tomarArista(indice, destino).fueRecorrido())
				auxiliar.add(g.tomarArista(indice, destino));
	}

	private void agregarVecinos(int indice) {
		ArrayList<Integer> aux = g.getVecinosInt(indice);
		
		for (Integer vecino: aux)
			if (!g.tomarArista(indice, vecino).fueRecorrido())
				vecinos.add(vecino);
	}	
	
}
