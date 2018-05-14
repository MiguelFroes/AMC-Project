import java.util.Arrays;
import java.util.LinkedList;

public class WGraph implements Interface_WGraph {
	private int dim;
	private double[][] Wmtx;
	
	public WGraph(int dim) {//Construtor do grafo pesado. Note-se que os grafos s�o representados por matrizes sim�tricas.
		this.dim=dim;
		Wmtx= new double[dim][dim];
	}
	
	
	@Override
	public String toString() {
		return "WGraph [dim=" + dim + ", Wmtx=" + Arrays.deepToString(Wmtx) + "]";
	}


	public void add_edge(int n1, int n2, double w) {//Adiciona uma liga��o entre os n�s com o peso w
		Wmtx[n1][n2]=w;
		Wmtx[n2][n1]=w;
	}
	
	public void remove_edge(int n1, int n2) {//Remove a lga��o entre os n�s
		Wmtx[n1][n2]=0;
		Wmtx[n2][n1]=0;
	}
	
	public boolean edgeQ(int n1, int n2) {//Verifica se existe liga��o entre os n�s
		return Wmtx[n1][n2]!=0;	
	}
	
	public DGraph MST(int n) {/*Retorna a �rvore geradora maximal (como Grafo orientado)
							cuja raiz � n.*/
		LinkedList<Integer> visited= new LinkedList<Integer>();
		double w_max=-1;//descobir que os pesos podem ser 0 por isso assim n�o d� erro
		DGraph Rmtx= new DGraph(dim); 
		visited.addLast(n);
		while(visited.size()!=dim) { 
			int i_max=0;
			int j_max=0;
			for(int i=0;i<visited.size();i++) { 
				for(int j=0;j<Wmtx.length;j++) {
					if(Wmtx[visited.get(i)][j]>w_max&&!(visited.contains(j))) { 
						w_max=Wmtx[visited.get(i)][j];
						i_max=visited.get(i);
						j_max=j;
					}
				}
			}
			visited.addLast(j_max);
			Rmtx.add_edge(i_max,j_max);	
			
		}
		return Rmtx;
	}
}
