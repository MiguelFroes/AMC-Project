
public class Teste {
	public static void main(String[] args) {
		int[] dom= {3,2,4,5,1,3,2};
		Amostra am=new Amostra(dom);
		int[] values3= {2,1,1,2,0,2,1};
		int[] values2= {1,0,1,0,0,0,2};
		int[] values1= {1,0,1,1,1,1,1};
		int[] values0= {1,0,1,2,1,2,2};
		am.add(values3);
		am.add(values2);
		am.add(values1);
		am.add(values0);
		System.out.println(Pesos.weight(4,3,am));
		
	}

}
