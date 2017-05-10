import java.util.ArrayList;

public class Solver {
	private Grafo grafo;
	private ArrayList<Grafo> grafos;
	private ArrayList<Arista> auxiliar;
	private ArrayList<Arista> principal;
	
	public Solver(Grafo grafo, int peajes){
		this.grafo = grafo;
		
		grafos = new ArrayList<Grafo>();
		
		for (int i = 0; i<peajes+1; i++){
			grafos.add(new Grafo(0));
			grafo.clonarA(grafos.get(i));
		}
	}
	
	private int getPeso(ArrayList<Arista> arista){
		int peso = 0;
		for (Arista a: arista)
			peso = a.getPeso();
	
		return peso;
	}
	
	public void resolver(){
		principal = new ArrayList<Arista>();
		auxiliar = new ArrayList<Arista>();
		considerar(0);
	}
	
	public void considerar(int indice) {
		if (indice == grafo.getVertices()) {
			if (getPeso(auxiliar) < getPeso(principal))
				intercambiar();
			return;
		}
		auxiliar.add(grafo.tomarArista(indice, indice));
		considerar(indice + 1);

		auxiliar.remove(indice);
		considerar(indice + 1);
	}

	private void intercambiar() {
		principal = null;
		for (Arista a: auxiliar)
			principal.add(a);
	}
	
	public static void main(String[] args) {
		Grafo principal = new Grafo(4);
		
		principal.agregarArista(0, 1, 1);
		principal.agregarArista(0, 2, 9);
		principal.agregarArista(1, 3, 6);
		principal.agregarArista(2, 3, 1);
		System.out.println(principal.tomarArista(0, 0));
		
		System.out.println(principal.getGrado(1));
				Solver s = new Solver(principal,2);
		s.resolver();
	}
}
