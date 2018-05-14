
public class Teste {
	public static void main(String[] args) {
		//int[] dom= {3,2,4};
		//Amostra am=new Amostra(dom);
		//int[] values3= {2,1,1};
		//int[] values2= {1,0,1};
		//int[] values1= {1,0,1};
		//int[] values0= {1,0,1};
		//am.add(values3);
		//am.add(values2);
		//am.add(values1);
		//am.add(values0);
		WGraph wm=new WGraph(4);
		wm.add_edge(0, 1, 1);
		wm.add_edge(0, 2, 3);
		wm.add_edge(1, 2, 2);
		wm.add_edge(1, 3, 3);
		wm.add_edge(3, 2, 5);
		//System.out.println(Pesos.weight(0,1,am));
		System.out.println(wm.MST(0));
		
	}

}
