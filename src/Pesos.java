
public class Pesos {

	public static double weight(int n1, int n2, Amostra A) {//Calcula o peso entre dois nos através da formula dada no enunciado
		int n1Domain=A.Domain(n1);
		int n2Domain=A.Domain(n2);
		int c=A.element(0).length-1;
		int cDomain=A.Domain(c);
		int i,j,k;
		double w=0;
		int[] Variables1= {n1,n2,c};
		int[] Variables2= {n1,c};
		int[] Variables3= {n2,c};
		int[] Variablesc= {c};
		int N=A.length();
		for(i=0;i<n1Domain;i++) { //percorre o dominio da primeira variavel
			for(j=0;j<n2Domain;j++) { //percorre o dominio da segunda variavel
				for(k=0;k<cDomain;k++) { //percorre o dominio da classe
					int[] Values1= {i,j,k};
					int[] Values2= {i,k};
					int[] Values3= {j,k};
					int[] Valuesc= {k};
					int P1=A.count(Variables1, Values1);
					int P2=A.count(Variables2, Values2);
					int P3=A.count(Variables3, Values3);
					int Pc=A.count(Variablesc, Valuesc);
					if(P1!=0)
						w+=((double)P1/N)*Math.log10(((double)P1*Pc)/((double)P3*P2));//o java devolve inteiros se os 
					//membros das divisoes forem inteiros, isto evita o erro
				}
			}
		}
		return w;
	}
}
