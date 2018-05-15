import java.io.Serializable;
import java.util.LinkedList;

public class BN implements Interface_BN,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DGraph dg;//grafo orientado resultante do MST que necessita estar guardado
	protected LinkedList <Double[][]> theta;//fun��o de probabilade 
	//protected Double [][][] thetaA; //Isto foi so para ele por agora nao me chatear com a fun��o de probabilidades que ia buscar posi��es
	
	public BN(DGraph D, Amostra A, double S) {
		dg=D;
		int xi;
		int c=A.element(0).length-1;
		int cDomain=A.Domain(c);
		theta= new LinkedList<Double[][]>(); 
		for(xi=0;xi<=D.getDim()-1;xi++) { //Isto � def e nao funciona com o menor mas com o menor ou igual e -1 funciona, aceitei
			int xDomain=A.Domain(xi);
			LinkedList<Integer> pi=D.parents(xi);
			if(pi.size()==1) {
				int p=pi.getFirst();
				int pDomain=A.Domain(p);
				int prodDomain=pDomain*cDomain;
				Double[][] mt=new Double[xDomain][prodDomain];
				for(int i=0;i<xDomain;i++) {
					for(int j=0;j<pDomain;j++) {
						for(int k=0;k<cDomain;k++) {
							int[] Var1={xi,p,c};
							int[] Val1={i,j,k};
							int[] Var2={p,c};
							int[] Val2={j,k};
							mt[i][k+(j*cDomain)]=Math.log10((double)A.count(Var1,Val1)+S)-Math.log10((double)A.count(Var2,Val2)+S*xDomain); 
							
						}
					}
				}
				theta.add(mt);
				
			}
			if(pi.size()==0) {
				Double[][] mt=new Double[xDomain][cDomain];
				for(int i=0;i<xDomain;i++) {
					for(int ci=0;ci<cDomain;ci++) {
						int[] Var3={xi,c};
						int[] Val3={i,ci};
						int[] Var4={c};
						int[] Val4={ci};
						mt[i][ci]=Math.log10((double)A.count(Var3,Val3)+S)-Math.log10((double)A.count(Var4,Val4)+S*xDomain); 
					}
				}
				theta.add(mt);
				
			}
		}
		Double[][] mt=new Double[cDomain][1];
		for(int cj=0;cj<cDomain;cj++) {
			int[] Varc={c};
			int[] Valc={cj};
			mt[cj][0]=Math.log10((double)A.count(Varc,Valc)+S)-Math.log10((double)A.length()+S*cDomain);
		}
		theta.add(mt);
		
	}
	
	public LinkedList<Double> prob(int[] vector) {
		int cDomain=theta.getLast().length;
		int c;
		double[]res=new double[cDomain];
		LinkedList<Double> res_final=new LinkedList<Double>();
		double soma=0;
		double max=0;
		double indice=0;
		for(c=0;c<cDomain;c++) {//Ciclo que percorre o dom�nio da classe
			int xi,pi;
			double r=0;
			for(int i=0;i<dg.getDim();i++) {//Ciclo que percorre as vari�veis
				xi=vector[i];
				LinkedList<Integer> pais=dg.parents(xi);
				if(pais.size()==1) {
					pi=dg.parents(xi).getFirst();
					r+=theta.get(i)[xi][c+(pi*cDomain)];
				}
				if(pais.size()==0) {
					r+=theta.get(i)[xi][c];
				}
			} r+=theta.getLast()[c][0];
			res[c]=Math.pow(10, r);
		}
		for(int i=0;i<res.length;i++) {//Ciclo que soma todos os valores de probabilidade das classes
			soma+=res[i];
		}
		for(int pos=0;pos<res.length;pos++) {//Ciclo que escolhe a classe com maior probabilidade
			if(res[pos]>max) {
				max=res[pos];
				indice=pos;
			}
		}
		res_final.addFirst(indice);
		res_final.addLast((max/soma)*100);//Normaliza��o da probabilidade e transforma��o em percentagem
		return res_final;
	}
	

}
