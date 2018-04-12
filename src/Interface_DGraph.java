import java.util.LinkedList;

public interface Interface_DGraph {

	public void add_edge(int n1, int n2);
	
	public void remove_edge(int n1, int n2);
	
	public boolean edgeQ(int n1, int n2);
	
	public LinkedList<Integer> parents(int n);
}
