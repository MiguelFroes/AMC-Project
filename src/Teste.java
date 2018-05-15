
public class Teste {
	public static void main(String[] args) {
		int[] dom= {3,2,1};
		Amostra am=new Amostra(dom);
		int[] values3= {2,1,1};
		int[] values2= {1,0,1};
		int[] values1= {1,0,1};
		int[] values0= {1,0,1};
		am.add(values3);
		am.add(values2);
		am.add(values1);
		am.add(values0);
		System.out.println(am);
		WGraph wg=new WGraph(dom.length-1);
		for(int i=0;i<dom.length-1;i++) {
			for(int j=0;j<dom.length-1;j++) {
				if(i!=j) {
				double w=Pesos.weight(i, j, am);
				wg.add_edge(i,j,w);
				}
			}
		}
		//wm.add_edge(3, 2, 5);
		//System.out.println(Pesos.weight(0,1,am));
		DGraph mst = wg.MST(0);
		System.out.println(wg.MST(0));
		BN bn = new BN(mst,am,0.5);
		int[] vector= {1,0,0};
		System.out.println(bn.prob(vector));
		//System.out.println(bn);
	}

}

	 
	    
	 
	   
	 
	  
	 
	
	 