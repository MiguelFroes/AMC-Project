
public class Pesos {

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
}
