
public interface Interface_WGraph {
	
	public int getDim();
	
	public double weight(int n1, int n2, Amostra A);
	
	public void add_edge(int n1, int n2, int w);
	
	public void remove_edge(int n1, int n2);
	
	public DGraph MST(int n);

}
