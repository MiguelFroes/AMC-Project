import java.util.LinkedList;


public class DGraph implements Interface_DGraph{
	private int dim;
	private int[][] Dmtx;
	
	public DGraph(int dim){//Construtor de grafos orientados
		this.dim=dim;
		Dmtx= new int [dim][dim];
	}
	
	public int getDim() {//Getter da dimens�o
		return dim;
	}
	
	public void add_edge(int n1, int n2) {//Adiciona uma aresta entre os dois n�s
		Dmtx[n1][n2]=1;
	}
	
	public void remove_edge(int n1, int n2) {//Remove a aresta entre os n�s
		Dmtx[n1][n2]=0;
	}
	
	public boolean edgeQ(int n1, int n2) {//Verifica se existe um caminho entre os n�s
		return Dmtx[n1][n2]==1;	
	}
	
	public LinkedList<Integer> parents(int n){//Devolve a lista de pais do n� n
		LinkedList<Integer> lr = new LinkedList<Integer> ();
		for (int j=0; j<dim; j++)
			if (edgeQ(j,n))//� bloqueada a linha e procura se o n� j origina o n� n
				lr.add(j);
		return lr;		
	}
}
