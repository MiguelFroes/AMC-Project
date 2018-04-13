
public class WGraph implements Interface_WGraph {
	private int dim;
	private int [][] Wmtx;
	
	public WGraph(int dim) {//Construtor do grafo pesado, ATENÇÃO os grafos pesados são matrizes simétricas
		this.dim=dim;
		Wmtx= new int [dim][dim];
	}


	public int getDim() {
		return dim;
	}


	public double weight(int n1, int n2, Amostra A) {//Calcula o peso entre dois nós através da fórmula que o prof dá
		int n1Domain=A.Domain(n1);
		int n2Domain=A.Domain(n2);
		int c=A.element(0).length-1;
		int cDomain=A.Domain(c);
		int i,j,k;
		double w=0;
		for(i=0;i<n1Domain;i++) {
			for(j=0;j<n2Domain;j++) {
				for(k=0;k<cDomain;k++) {
					int[] Variables1= {n1,n2,c};
					int[] Values1= {i,j,k};
					int[] Variables2= {n1,c};
					int[] Values2= {i,k};
					int[] Variables3= {n2,c};
					int[] Values3= {j,k};
					int[] Valuesc= {k};
					int[] Variablesc= {c};
					int P1=A.count(Variables1, Values1)/A.length();
					int P2=A.count(Variables2, Values2)/A.length();
					int P3=A.count(Variables3, Values3)/A.length();
					int Pc=A.count(Variablesc, Valuesc)/A.length();
					w+=P1*Math.log10((P1*Pc)/(P3*P2));
				}
			}
		}
		return w;
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
	
	public DGraph MST(int n) {/*retorna a árvore geradora maximal (como Grafo orientado)
							cuja raiz é n.*/
		
	}
}
