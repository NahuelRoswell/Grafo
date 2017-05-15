import java.util.ArrayList;

public class Grafo2 {
	private Arista[][] grafo;
	private static int ids = 0;
	private int id;
	
	Grafo2(int vertices){
		grafo = new Arista[vertices][vertices];
		
		id = ids++;
		
		for(int i = 0; i < getVertices(); i++)
			for (int j = 0; j < getVertices(); j++)
				grafo[i][j] = new Arista();
	}
	
	public void agregarArista(int inicio, int destino, int peso){
		chequearIndice(inicio, destino, "agregar Arista");
		
		grafo[inicio][destino].agregarArista(inicio,destino, peso);
	}
	
	public void agregarArista(int inicio, int destino, int peso, boolean peaje){
		chequearIndice(inicio, destino, "agregar Arista");
		
		grafo[inicio][destino].agregarArista(inicio,destino, peso, peaje);
	}
	
	public Arista tomarArista(int inicio, int destino){
		chequearIndice(inicio,destino,"tomar Arista");
		
		return grafo[inicio][destino];
	}
	
	private void chequearIndice(int indice, int destino, String accion) {
		if(indice < 0 || destino < 0 || indice > getVertices()-1 || destino > getVertices()-1)
			throw new IllegalArgumentException("el indice para " +accion +" esta fuera de rango! I =" +indice
					+" J = "+destino);
	}
	
	public ArrayList<Integer> getVecinosInt(int indice){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for (int i = 0; i < grafo.length; i++)
			if (grafo[indice][i].getDestino() != null && !grafo[indice][i].fueRecorrido())
				ret.add(grafo[indice][i].getDestino());

		return ret;
	}
	
	public ArrayList<Arista> getVecinos(int indice){
		ArrayList<Arista> ret = new ArrayList<Arista>();
		
		for (int i = 0; i < grafo.length; i++)
			if (grafo[indice][i].getDestino() != null && !grafo[indice][i].fueRecorrido())
				ret.add(grafo[indice][i]);

		return ret;
	}
	
	
	public int getVertices(){
		return grafo.length;
	}
	
	/** Retorna la cantidad de vecinos**/
	public int getGrado(int i){
		int vecinos = 0;
		for (int j = 0; j < getVertices(); j++) 
			if (grafo[i][j].getDestino()!=null)
				vecinos++;
		
		return vecinos;
	}
	
	public Grafo2 clonarEn(Grafo2 grafoClonado) {
		for (int inicio = 0; inicio < getVertices(); inicio++)
			for (int destino = 0; destino < getVertices(); destino++)
				if (grafo[inicio][destino].getPeso() != null)
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
				else if (grafo[i][j].getPeso() != null) // si ij ∈ A
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
		Grafo2 grafo = new Grafo2(4);
		grafo.agregarArista(0, 1, 8);
		grafo.agregarArista(0, 2, 1);
		grafo.agregarArista(1, 3, 7);
		grafo.agregarArista(2, 3, 1);
		
		System.out.println(grafo.tomarArista(0, 1).getPeso());
		
		
//		ArrayList<Integer> ret = grafo.getVecinos(0);
//		for(Integer i: ret) if (i!=null)
//			System.out.print(i +", ");
//		grafo.print();
		grafo.print();
	}





		
}
