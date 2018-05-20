import java.util.Arrays;

class node{
	int[] vector;
	node next;
	
	public node(int[] vector, node next) {
		this.vector=vector;
		this.next=next;
	}

	@Override
	public String toString() {
		return "node [vector=" + Arrays.toString(vector) + ", next=" + next + "]";
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
	
	@Override
	public String toString() {
		return "Amostra [first=" + first + ", dim=" + dim + ", domain=" + Arrays.toString(domain) + "]";
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
	
	public int count(int[] var, int[] val) {/*Recebe um vector de variaveis e um vector de valores e retorna o número de 
							ocorrencias desses valores para essas variaveis na amostra*/
		node runner=first;
		int i=0, r=0;
		boolean b;
		while(runner!=null) { //Percorre os nos da amosta
			b=true;
			for(i=0;i<var.length;i++) { //percorre as variaveis
				b&=(runner.vector[var[i]]==val[i]); //verifica se os valores se encontram naquele no
			}
			if(b) r++; //Se se verificar, adiciona a contagem 1 
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

