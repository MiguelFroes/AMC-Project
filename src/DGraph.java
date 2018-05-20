import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;


public class DGraph implements Interface_DGraph,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dim;
	private int[][] Dmtx;
	
	public DGraph(int dim){//Construtor de grafos orientados
		this.dim=dim;
		Dmtx= new int [dim][dim];
	}
	
	
	@Override
	public String toString() {
		return "DGraph [dim=" + dim + ", Dmtx=" + Arrays.deepToString(Dmtx) + "]";
	}


	public int getDim() {//Getter da dimensao
		return dim;
	}
	
	public void add_edge(int n1, int n2) {//Adiciona uma aresta entre os dois nos
		Dmtx[n1][n2]=1;
	}
	
	public void remove_edge(int n1, int n2) {//Remove a aresta entre os nos
		Dmtx[n1][n2]=0;
	}
	
	public boolean edgeQ(int n1, int n2) {//Verifica se existe um caminho entre os n�s
		return Dmtx[n1][n2]==1;	
	}
	
	public LinkedList<Integer> parents(int n){//Devolve a lista de pais do no n
		LinkedList<Integer> lr = new LinkedList<Integer> ();
		for (int j=0; j<dim; j++)
			if (edgeQ(j,n))//E bloqueada a linha e procura se o no j origina o no n
				lr.add(j);
		return lr;		
	}
}
