import java.util.LinkedList;

public class BN implements Interface_BN{
	protected DGraph dg;//grafo orientado resultante do MST que necessita estar guardado
	protected double[][][] theta;//fun��o de probabilade 
	
	public BN(DGraph D, Amostra A, double S) {
		dg=D;
		int xi;
		int c=A.element(0).length-1;
		int cDomain=A.Domain(c);
		for(xi=0;xi<D.getDim();xi++) {
			int xDomain=A.Domain(xi);
			LinkedList<Integer> pi=D.parents(xi);
			if(pi.size()==1) {
				int p=pi.getFirst();
				int pDomain=A.Domain(p);
				int prodDomain=pDomain*cDomain;
				double[][] mt=new double[xDomain][prodDomain];
				for(int i=0;i<xDomain;i++) {
					for(int j=0;j<pDomain;j++) {
						for(int k=0;k<cDomain;k++) {
							int[] Var1={xi,p,c};
							int[] Val1={i,j,k};
							int[] Var2={p,c};
							int[] Val2={j,k};
							mt[xi][k+(j*cDomain)]=Math.log10(A.count(Var1,Val1)+S)-Math.log10(A.count(Var2,Val2)+S*xDomain);
						}
					}
				}
				theta[xi]=mt;
			}
			if(pi.size()==0) {
				int prodDomain=cDomain;
				double[][] mt=new double[xDomain][prodDomain];
				for(int i=0;i<xDomain;i++) {
					for(int ci=0;ci<cDomain;ci++) {
						int[] Var3={xi,c};
						int[] Val3={i,ci};
						int[] Var4={c};
						int[] Val4={ci};
						mt[xi][ci]=Math.log10(A.count(Var3,Val3)+S)-Math.log10(A.count(Var4,Val4)+S*xDomain);
					}
				}
				theta[xi]=mt;
			}
		}
		double[][] mt=new double[cDomain][1];
		for(int cj=0;cj<cDomain;cj++) {
			int[] Varc={c};
			int[] Valc={cj};
			mt[cj][0]=Math.log10(A.count(Varc,Valc)+S)-Math.log10(A.length()+S*cDomain);
		}
		theta[xi]=mt;
	}
		
	public double prob(int[] vector) {//Devolve a probabilidade do vector na rede de bayes criada
		int c=vector[vector.length-1];
		int xi,pi,i;
		double res = 0;
		for(i=0;i<dg.getDim();i++) {
			xi=vector[i];
			LinkedList<Integer> pais=dg.parents(xi);
			if(pais.size()==1) {
				pi=dg.parents(xi).getFirst();
				res+=theta[i][xi][c+(pi*2)];//como saber o dom�nio de c sem usar fun��es da amostra???
			}
			if(pais.size()==0) {
				res+=theta[i][xi][c];
			}
		}
		int ci=vector[i];
		res+=theta[i][ci][0];
		return Math.pow(10, res);
	}

}