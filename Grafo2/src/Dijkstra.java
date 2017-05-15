import java.util.ArrayList;
import java.util.LinkedList;


public class Dijkstra {
	private static Grafo2 [] grafos;
	private LinkedList<Integer> camino = new LinkedList<Integer>();
	private ArrayList<Arista> yaRecorridos = new ArrayList<Arista>();
	private ArrayList<Arista> auxiliar = new ArrayList<Arista>();
	private int inicio, solucion, cantPeajes;
	private Arista resultado;
	private Grafo2 grafo;
	
	public Dijkstra(Grafo2 g, int i, int destino, int cantidadPeajes) {
		grafo = g;		inicio = i;		solucion = destino;		cantPeajes = cantidadPeajes;
		
		yaRecorridos.add(new Arista(i, i, 0));

		if (cantidadPeajes !=0)
			generarGrafos();
	}
	
	private void generarGrafos() {
		grafos = new Grafo2[cantPeajes + 1];
		
		for (int i = 0; i < grafos.length; i++) {
			grafos[i] = new Grafo2(grafo.getVertices());
			grafos[i] = grafo.clonarEn(grafos[i]);
		}
	}

	private void agregarVecinos(int indice, Integer peso){
		ArrayList<Arista> vecinos = grafo.getVecinos(indice);
		
		for(Arista arista: vecinos)
			if (!auxiliar.contains(arista) 
					&& arista.getPeajesVisitados() + peso <= cantPeajes)
				auxiliar.add(arista);
	}
	
	private Arista tomarNoRecorridoConMenorPeso() {
		Arista referencia = new Arista();
		referencia.agregarArista(null, null, (int) Double.POSITIVE_INFINITY);

		for (Arista arista : auxiliar)
			if (!arista.fueRecorrido() 
					&& arista.getPeso() < referencia.getPeso())
//					&&	yaRecorridos.get(buscarPorInicio(referencia)).getPeajesVisitados() <= cantPeajes)
//						y si puede saltar al siguietnte..					&& arista.getPeajesVisitados() + peajesYaVisitados <= cantPeajes) //
				referencia = arista;


		return referencia;
	}
	
	private void irProximoGrafo(Arista arista) {
		Integer inicio = arista.getInicio();
		Integer destino = arista.getDestino();
		
		
	}

	private void marcarPrincipal(Arista menor) {
		int indice = buscarPorInicio(menor);

		menor.yaRecorrido();
		acumularDatos(menor,indice);

		Arista suplente = new Arista(menor.getInicio(), menor.getDestino(), menor.getPeso());
		suplente.setPeajesRecorridos(menor.getPeajesVisitados());
		suplente.yaRecorrido();
		
		yaRecorridos.get(indice).asignarAnteriorDe(suplente);

		yaRecorridos.add(suplente);
	}
	
	
	private void acumularDatos(Arista arista, int indice) {
		if (arista.getInicio() != 0) {
			int peso = yaRecorridos.get(indice).getPeso() == null ? arista.getPeso()
					: arista.getPeso() + yaRecorridos.get(indice).getPeso();

			arista.setPeso(peso);
		}
		int peajes = yaRecorridos.get(indice).getPeajesVisitados() == 0 ? arista.getPeajesVisitados()
				: arista.getPeajesVisitados() + yaRecorridos.get(indice).getPeajesVisitados();

		arista.setPeajesRecorridos(peajes);
	}
	
	private int buscarPorInicio(Arista arista){
		int indice = -1;
		for (int i = 0; i < yaRecorridos.size(); i++) 
			if (yaRecorridos.get(i).getDestino() == arista.getInicio()){
				indice = i;
				break;
			}
		
		return indice;
	}
	
	public Arista resolver() {
		Arista menor = new Arista();
		menor.agregarArista(inicio, inicio, 0);

		while (menor.getPeso() != (int) Double.POSITIVE_INFINITY && menor.getDestino() != solucion) {
			agregarVecinos(menor.getDestino(), menor.getPeajesVisitados());
			
			menor = tomarNoRecorridoConMenorPeso();

			marcarPrincipal(menor);
		}
		
		resultado = ResultadoEnRecorrido();
		transformarAEntero();
		return resultado;
	}
	
	private Arista ResultadoEnRecorrido(){
		Arista resultado = new Arista();
		for (int i = 0; i < yaRecorridos.size(); i++) 
			if (yaRecorridos.get(i).getDestino() == solucion)
				resultado = yaRecorridos.get(i);
		
		return resultado;
	}
	
	private void transformarAEntero() {
		Arista a = resultado;
		while (a.getAnterior() != null) {
			camino.addFirst(a.getDestino());
			a = a.getAnterior();
		}
		camino.addFirst(yaRecorridos.get(0).getInicio());
	}
	
	public void mostrarResultado(){
		System.out.print("[ ");
		for (Integer i: camino)
			System.out.print(i +", ");
		System.out.println("]");
	}
 
	public static void main(String[] args) {
		Grafo2 grafo = new Grafo2(6);
		grafo.agregarArista(0, 1, 10,true);
		grafo.agregarArista(0, 2, 1,true);
		grafo.agregarArista(1, 3, 9);
		grafo.agregarArista(2, 3, 100,true);
		grafo.agregarArista(3, 4, 12);
		grafo.agregarArista(3, 5, 1);
		Dijkstra s = new Dijkstra(grafo,0,4, 3);

//		Grafo2 grafo = new Grafo2(8);
//		grafo.agregarArista(0, 1, 3);
//		grafo.agregarArista(0, 2, 1);
//		grafo.agregarArista(1, 6, 5);
//		grafo.agregarArista(1, 3, 1);
//		grafo.agregarArista(2, 3, 1);
//		grafo.agregarArista(2, 5, 5);
//		grafo.agregarArista(3, 4, 4);
//		grafo.agregarArista(3, 5, 1);
//		grafo.agregarArista(4, 7, 1);
//		grafo.agregarArista(5, 7, 1);
//		grafo.agregarArista(6, 4, 2);
//		Dijkstra s = new Dijkstra(grafo,0,7,0);
		
//		Grafo2 grafo = new Grafo2(6);
//		grafo.agregarArista(0, 1, 1);
//		grafo.agregarArista(0, 2, 4);
//		grafo.agregarArista(1, 3, 1, true);
//		grafo.agregarArista(2, 4, 4);
//		grafo.agregarArista(3, 4, 1, true);
//		grafo.agregarArista(4, 5, 6);
//		Dijkstra s = new Dijkstra(grafo,0,5,2);

		Arista menor = s.resolver();
		System.out.println(menor.getPeajesVisitados());
		s.mostrarResultado();
		
				
	}
	
	
}
