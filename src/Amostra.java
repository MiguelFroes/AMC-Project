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
	
	public int Domain(int n) {//Devolve o dom�nio do n� n, ou seja, quantos valores � que cada vari�vel do vetor do n� n pode tomar
		return domain[n];
	}
	
	public void add(int[] vector) {//Recebe um vetor e adiciona-o � amostra
		node aux= new node(vector,first);
		first=aux;
		dim++;
	}
	
	public int length() {//Devolve o comprimento da amostra
		return dim;	
	}
	
	public int count(int[] var, int[] val) {/*Recebe um vector de vari�veis e um vector de valores e retorna o n�mero de 
							ocorr�ncias desses valores para essas vari�veis na amostra*/
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
	
	public int[] element(int x) {//Devolve o vetor do n� x
		int i;
		node runner=first;
		for(i=0;i<x;i++) {
			runner=runner.next;
		}
		return runner.vector;	
	}

}
