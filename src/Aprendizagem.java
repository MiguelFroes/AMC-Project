import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;



import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Aprendizagem {

	private JFrame frame;
	private JTextField textField;
	private String database;
	private boolean Cancer;
	private boolean Diabetes;
	private boolean Hepatitis;
	private boolean Thyroid;
	private String parameter;
	private BN bn;
	private int[][] matrix;
	private Amostra am;
	private WGraph wg;
	private DGraph mst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aprendizagem window = new Aprendizagem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aprendizagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 530, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnChooseFile = new JButton("Choose Me");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Select a file", FileDialog.LOAD);
				frame.getContentPane().setLayout(null);
				fd.setDirectory("C:\\");
				fd.setFile("*.csv");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename == null)
					System.out.println("Cancelled");
				else 
					System.out.println("File selected: " + filename);
				database = fd.getDirectory()+filename;
				textField.setText(filename);
				
			}
		});
		btnChooseFile.setBounds(21, 31, 141, 35);
		frame.getContentPane().add(btnChooseFile);
		
		textField = new JTextField();
		textField.setBounds(169, 32, 186, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnTeachMe = new JButton("Teach Me");
		btnTeachMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] domains=null;
				int numblines=0;
			
//Primeira leitura que percorre o ficheiro e forma o vetor de domínios e descobre as dimensões da matriz

				try { 
					
					FileReader fr=new FileReader(database);
					BufferedReader br=new BufferedReader(fr);
					String CurrentLine;
					String[] line;
					CurrentLine=br.readLine();
					line=CurrentLine.split(",");
					domains=new int[line.length];
					numblines++;
					for(int i=0;i<line.length;i++) {
						int x=Integer.parseInt(line[i]);
						if(x>domains[i]) {
							domains[i]=x;
						}
					}
					while((CurrentLine=br.readLine())!=null) {
						numblines++;
						line=CurrentLine.split(",");
						for(int i=0;i<line.length;i++) {
							int x=Integer.parseInt(line[i]);
							if(x>domains[i]) {
								domains[i]=x;
							}
						}
					}
					br.close();
					fr.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
				
				for(int j=0;j<domains.length;j++) {
					domains[j]++;
				}
				
//Criação da amostra
				
				matrix=new int[numblines][domains.length];
				
				try {
					FileReader fr=new FileReader(database);
					BufferedReader br=new BufferedReader(fr);
					String CurrentLine;
					String[] line;
					
						for(int i=0;i<numblines;i++) {
							CurrentLine=br.readLine();
							line=CurrentLine.split(",");
							for(int j=0;j<line.length;j++) {
								matrix[i][j]=Integer.parseInt(line[j]);
							}
						}
					//System.out.println(Arrays.deepToString(matrix));
					br.close();
					fr.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				am= new Amostra(domains);
				
				for(int i=0;i<matrix.length;i++) {
					am.add(matrix[i]);
				}
				System.out.println(am);
				
//Criação do Grafo Pesado
				wg=new WGraph(domains.length-1);
					for(int i=0;i<domains.length-1;i++) {
						for(int j=0;j<domains.length-1;j++) {
							double w=Pesos.weight(i, j, am);
							wg.add_edge(i,j,w);
						}
					}
				System.out.println(wg);

//Criação da MST
				mst=wg.MST(0);
				System.out.println(mst);//está a dar erro na criação do MST!!! temos de ver outra vez a função que isto está a dar mal
				
//Criação da rede de Bayes
				
				bn=new BN(mst,am,0.5);
						
			}	
		});
		btnTeachMe.setBounds(21, 116, 141, 35);
		frame.getContentPane().add(btnTeachMe);
		
		JButton btnSave = new JButton("Save Me");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cancer) {
					parameter="Cancer";
				}
				if(Hepatitis) {
					parameter="Hepatitis";
				}
				if(Diabetes) {
					parameter="Diabetes";
				}
				if(Thyroid) {
					parameter="Thyroid";
				}
				JFileChooser fc = new JFileChooser();
		        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		        fc.showSaveDialog(null);
		        
		        System.out.println(fc.getCurrentDirectory()+"/"+parameter+".BN");
				
				try {
					FileOutputStream fos=new FileOutputStream(fc.getCurrentDirectory()+"/"+parameter+".BN");
					ObjectOutputStream oos=new ObjectOutputStream(fos);
					oos.writeObject(bn);
					oos.close();
				} catch (FileNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setBounds(21, 189, 141, 35);
		frame.getContentPane().add(btnSave);
		
		JRadioButton rdbtnCancer = new JRadioButton("Cancer");
		rdbtnCancer.setBounds(259, 116, 201, 35);
		frame.getContentPane().add(rdbtnCancer);
		rdbtnCancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancer=true;
			}
		});
		
		JRadioButton rdbtnDiabetes = new JRadioButton("Diabetes");
		rdbtnDiabetes.setBounds(259, 167, 201, 35);
		frame.getContentPane().add(rdbtnDiabetes);
		rdbtnDiabetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diabetes=true;
			}
		});
		
		JRadioButton rdbtnHepatitis = new JRadioButton("Hepatitis");
		rdbtnHepatitis.setBounds(259, 215, 201, 35);
		frame.getContentPane().add(rdbtnHepatitis);
		rdbtnHepatitis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hepatitis=true;
			}
		});
		
		JRadioButton rdbtnThyroid= new JRadioButton("Thyroid");
		rdbtnThyroid.setBounds(259, 263, 201, 35);
		frame.getContentPane().add(rdbtnThyroid);
		rdbtnThyroid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thyroid=true;
			}
		});
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnCancer);
		group.add(rdbtnDiabetes);
		group.add(rdbtnHepatitis);
		group.add(rdbtnThyroid);
	}
}
