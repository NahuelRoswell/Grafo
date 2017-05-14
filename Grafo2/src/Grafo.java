
public class Grafo {
	Integer[][] grafo;
	
	public Grafo(int vertices){
		grafo = new Integer[vertices][vertices];
	}
	
	public void agregarArista(int inicio, int destino, int peso){
		 chequarArista(inicio,destino,"agregar Arista");
		
		 grafo[inicio][destino] = peso;
	}

	private void chequarArista(int inicio, int destino, String accion) {
		if (inicio < 0 || inicio > getVertices()-1)
			throw new IllegalArgumentException("Se intentó " +accion +" ! i= "+inicio +", j = "+destino);
	}
	
	public int getVertices(){
		return grafo.length;
	}
	
	public void print(){
		for (int i = 0; i < grafo.length; i++) {
			System.out.print(i + " -- ");
			
			for (int j = 0; j < grafo.length; j++) {
				if (j == i)
					System.out.print("0 ");
				else if (grafo[i][j] != null) // si ij ∈ A
					System.out.print(grafo[i][j] + " ");
				else
					System.out.print("& "); // si ij ∈ A

				if (j == grafo.length - 1)
					System.out.println();
			}
		}
	}

	public void camino() {
		for (int k = 1; k < getVertices(); k++) {
			for (int i = 1; i < getVertices(); i++) {
				for (int j = 1; j < getVertices(); j++) {
					if (grafo[i][k] != null && grafo[k][j] != null)
						if (grafo[i][j] > grafo[i][k] + grafo[k][j]) {
							grafo[i][j] = grafo[i][k] + grafo[k][j];
						}
				}
			}
			System.out.println(k + ":");
			print();
		}
	}
	
	public Grafo clonar(Grafo clon) {
		for (int i = 0; i < getVertices(); i++)
			for (int j = 0; j < getVertices(); j++)
				if (grafo[i][j] != null)
					clon.agregarArista(i, j, grafo[i][j]);

		return clon;
	}
}
