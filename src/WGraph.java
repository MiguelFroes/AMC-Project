import java.util.Arrays;
import java.util.LinkedList;

public class WGraph implements Interface_WGraph {
	private int dim;
	private double[][] Wmtx;
	
	public WGraph(int dim) {//Construtor do grafo pesado. Note-se que os grafos sao representados por matrizes simetricas.
		this.dim=dim;
		Wmtx= new double[dim][dim];
	}
	
	
	@Override
	public String toString() {
		return "WGraph [dim=" + dim + ", Wmtx=" + Arrays.deepToString(Wmtx) + "]";
	}


	public void add_edge(int n1, int n2, double w) {//Adiciona uma ligacao entre os nos com o peso w
		if(n1!=n2) { //Garante que nao e calculado o peso de um no com ele proprio
		Wmtx[n1][n2]=w;
		Wmtx[n2][n1]=w;
		}
	}
	
	public void remove_edge(int n1, int n2) {//Remove a ligacao entre os nos
		Wmtx[n1][n2]=0;
		Wmtx[n2][n1]=0;
	}
	
	public boolean edgeQ(int n1, int n2) {//Verifica se existe ligacao entre os nos
		return Wmtx[n1][n2]!=0;	
	}
	
	public DGraph MST(int n) {/*Retorna a arvore geradora maximal (como Grafo orientado)
							cuja raiz e n.*/
		LinkedList<Integer> visited= new LinkedList<Integer>();

		DGraph Rmtx= new DGraph(dim); 
		visited.addFirst(n); 
		while((visited.size())!=dim) { 

			int i_max=0;
			int j_max=0;
			double w_max=-1.0; //peso maximo comeca em -1 porque considera-se que o peso entre 2 nos pode ser 0 
			for(int i=0;i<visited.size();i++) { //percorre os nos que na lista dos visitados para ir apenas a essas linhas da matriz no grafo pesado
				for(int j=0;j<Wmtx.length;j++) { //percorre a coluna da matriz do grafo pesado 
					if(Wmtx[visited.get(i)][j]>w_max && !(visited.contains(j))) { //encontra o proximo no a ser adicionado a arvore geragora maximal
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
