class node{
	int[] vector;
	node next;
	
	public node(int[] vector, node next) {
		this.vector=vector;
		this.next=next;
	}	
}
public class Amostra implements Interface_Amostra{
	private node first;
	private int dim;
	private int[] domain;
	
	public Amostra(int[] domain) {//Construtor da amostra
		this.domain=domain;
		first=null;
		dim=0;
	}
	
	public int Domain(int n) {//Devolve o domínio do nó n, ou seja, quantos valores é que cada variável do vetor do nó n pode tomar
		return domain[n];
	}
	
	public void add(int[] vector) {//Recebe um vetor e adiciona-o à amostra
		node aux= new node(vector,first);
		first=aux;
		dim++;
	}
	
	public int length() {//Devolve o comprimento da amostra
		return dim;	
	}
	
	public int count(int[] var, int[] val) {/*Recebe um vector de variáveis e um vector de valores e retorna o número de 
							ocorrências desses valores para essas variáveis na amostra*/
		node runner=first;
		int i=0, r=0;
		boolean b;
		while(runner!=null) {
			b=true;
			for(i=0;i<var.length;i++) {
				b&=(runner.vector[var[i]]==val[i]);
			}
			if(b) r++;
			runner=runner.next;
		}
		return r;	
	}
	
	public int[] element(int x) {//Devolve o vetor do nó x
		int i;
		node runner=first;
		for(i=0;i<x;i++) {
			runner=runner.next;
		}
		return runner.vector;	
	}

}
