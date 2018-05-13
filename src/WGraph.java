import java.util.LinkedList;

public class WGraph implements Interface_WGraph {
	private int dim;
	private int [][] Wmtx;
	
	public WGraph(int dim) {//Construtor do grafo pesado. Note-se que os grafos são representados por matrizes simétricas.
		this.dim=dim;
		Wmtx= new int [dim][dim];
	}
	
	public void add_edge(int n1, int n2, int w) {//Adiciona uma ligação entre os nós com o peso w
		Wmtx[n1][n2]=w;
		Wmtx[n2][n1]=w;
	}
	
	public void remove_edge(int n1, int n2) {//Remove a lgação entre os nós
		Wmtx[n1][n2]=0;
		Wmtx[n2][n1]=0;
	}
	
	public boolean edgeQ(int n1, int n2) {//Verifica se existe ligação entre os nós
		return Wmtx[n1][n2]!=0;	
	}
	
	public DGraph MST(int n) {/*Retorna a árvore geradora maximal (como Grafo orientado)
							cuja raiz é n.*/
		LinkedList<Integer> visited= new LinkedList<Integer>();
		double w_max=-1;//descobir que os pesos podem ser 0 por isso assim não dá erro
		DGraph Rmtx= new DGraph(dim); 
		visited.add(n);
		int i,j;
		while(visited.size()!=dim) { 
			int i_max=0;
			int j_max=0;
			for(i=0;i<visited.size();i++) { 
				for(j=0;j<dim;j++) {
					if(Wmtx[visited.get(i)][j]>w_max&&!visited.contains(j)) 
						w_max=Wmtx[visited.get(i)][j];
						i_max=visited.get(i);
						j_max=j;
				}
			}
		visited.add(j_max);
		Rmtx.add_edge(i_max,j_max);		
		}
		return Rmtx;
	}
}
