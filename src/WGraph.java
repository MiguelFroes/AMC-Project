
public class WGraph {
	private int dim;
	private int [][] Wmtx;
	
	public WGraph(int dim) {
		this.dim=dim;
		Wmtx= new int [dim][dim];
	}

	public int getDim() {
		return dim;
	}

	
	public void add_edge(int n1, int n2, int w) {
		Wmtx[n1][n2]=w;
	}
	
	public void remove_edge(int n1, int n2) {
		Wmtx[n1][n2]=0;
	}
	
	public void MST(int n) {/*retorna a árvore geradora maximal (como Grafo orientado)
							cuja raiz é n.*/
		
	}
}
