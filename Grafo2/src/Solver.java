import java.util.ArrayList;

public class Solver {
	private ArrayList<Integer> auxiliar, camino;
	private Grafo g;
	private int i, solucion;
	
	public Solver(Grafo grafo, int inicio, int destino) {
		g = grafo;		i = inicio;		solucion = destino;
	}
	
	public void resolver(){
		camino = new ArrayList<Integer>();
		auxiliar = new ArrayList<Integer>();
		considerar(0);
		mostrarArreglo(camino);
	}

	private void mostrarArreglo(ArrayList<Integer> array){
		for (Integer i: array)
			System.out.print(i +", ");
	}
	
	public void considerar(int indice){
		if (indice == solucion){
			IntercambiarArreglos();
			return;
		}
		ArrayList<Integer> vecinos = g.getVecinosInt(indice);

		for (int i = 0; i < g.getGrado(indice)-1; i++) 
			auxiliar.add(vecinos.get(indice));
		considerar(indice + 1);
		
		for (int i = 0; i < g.getGrado(indice)-1; i++) 
			auxiliar.remove(vecinos.get(indice));
		considerar(indice + 1);
	}

	private void IntercambiarArreglos() {
//		camino = new ArrayList<Integer>();
		for(Integer a: auxiliar)
			camino.add(a);
	}
	
	public static void main(String[] args) {
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(0, 1, 8);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 3, 7);
		grafo.agregarArista(2, 3, 1);
		
		//uso indice 0
		int indice = 0;
		ArrayList<Arista> auxiliar = new ArrayList<Arista>();
		ArrayList<Integer> vecinos = grafo.getVecinosInt(indice);
		ArrayList<Arista> camino = new ArrayList<Arista>();
		// recorre vecinos y agrega a camino auxiliar
		for(Integer a: vecinos)
			auxiliar.add(grafo.tomarArista(indice, a));
		
		//se queda con la mejor opcion de los vecinos y lo agrega al camino final
		Arista menor = auxiliar.get(indice);
		for(Arista i: auxiliar)
			if(i.getPeso() < menor.getPeso())
				menor = i;
		
		camino.add(grafo.tomarArista(menor.getDestino(), indice));
		camino.add(menor);
				
		//imprimir solucion
		System.out.print("Camino: ");
		for (Arista a: camino)
			System.out.print(a +", ");
	}
	
	
}
