import java.awt.Color;


import java.awt.Component;
import java.awt.EventQueue;

import java.awt.FileDialog;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
	private boolean Other;
	private String parameter;
	private BN bn;
	private int[][] matrix;
	private Amostra am;
	private WGraph wg;
	private DGraph mst;
	private String filename;
	private JTextField textTeach;


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
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.setBounds(100, 100, 567, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblApp = new JLabel("LEARNING APPLICATION");
		lblApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblApp.setForeground(new Color(0, 128, 128));
		lblApp.setFont(new Font("Adobe Gothic Std", Font.BOLD, 18));
		lblApp.setBounds(65, 10, 410, 37);
		frame.getContentPane().add(lblApp);
		
		JButton btnChooseFile = new JButton("CHOOSE ME");
		btnChooseFile.setForeground(new Color(0, 128, 128));
		btnChooseFile.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnChooseFile.setBackground(new Color(255, 255, 255));
		btnChooseFile.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnChooseFile.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Select a file", FileDialog.LOAD);
				frame.getContentPane().setLayout(null);
				fd.setDirectory("C:\\"); //Permite ir procurar o ficheiro nas pastas do computador
				fd.setFile("*.csv"); //Procura ficheiros do tipo .csv
				fd.setVisible(true);
				filename = fd.getFile(); //Variavel que guarda o nome do ficheiro selecionado
				if (filename == null)
					System.out.println("Cancelled");
				else 
					System.out.println("File selected: " + filename);
				database = fd.getDirectory()+filename; //Variavel que guarda o caminho do ficheiro selecionado
				textField.setText(filename); //Nome do ficheiro aparece na aplicação  na caixa de texto junto ao botão choose me
				
			}
		});
		btnChooseFile.setBounds(24, 56, 141, 47);
		frame.getContentPane().add(btnChooseFile);
		
		textField = new JTextField();
		textField.setBounds(186, 56, 334, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnTeachMe = new JButton("TEACH ME");
		btnTeachMe.setForeground(new Color(0, 128, 128));
		btnTeachMe.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnTeachMe.setBackground(new Color(255, 255, 255));
		btnTeachMe.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnTeachMe.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnTeachMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] domains=null; 
				int numblines=0;
			
//Primeira leitura que percorre o ficheiro e forma o vetor de dominios e descobre as dimensões da matriz
				if(filename==null)
					textTeach.setText("Please choose a .csv file");
				else {
					if(!Cancer&&!Diabetes&&!Hepatitis&&!Thyroid&&!Other)
						textTeach.setText("Choose the kind of disease you want to learn");				
				else {
				try { 
					
					FileReader fr=new FileReader(database); //Permite selecionar o documento a ser lido a partir do caminho anteriormente guardado 
					BufferedReader br=new BufferedReader(fr); //Permite ler o documento
					String CurrentLine;
					String[] line;
					CurrentLine=br.readLine(); //Le a primeira linha do ficheiro (como string)
					line=CurrentLine.split(","); //Transofrma a informacao da primeira linha num vetor de strigs separados por ,
					domains=new int[line.length]; 
					numblines++;
					for(int i=0;i<line.length;i++) { //Ciclo que transforma os dados dum vetor de strings para um vetor de inteiros para a primeira linha
						int x=Integer.parseInt(line[i]);
						if(x>domains[i]) { //Atualiza o valor maximo de cada variavel e vai adicionando ao vetor de dominios
							domains[i]=x;
						}
					}
					while((CurrentLine=br.readLine())!=null) { //Ciclo que vai percorrendo as restantes linhas do ficheiro
						numblines++; //Permite descobrir a dimensao da matriz = dmiensao doo numero de linhas do ficheiro
						line=CurrentLine.split(",");
						for(int i=0;i<line.length;i++) { //Ciclo que transforma os dados dum vetor de strings para um vetor de inteiros para as restantes linhas
							int x=Integer.parseInt(line[i]);
							if(x>domains[i]) { //Atualiza o valor maximo de cada variavel e vai adicionando ao vetor de dominios
								domains[i]=x;
							}
						}
					}
					br.close();
					fr.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
				
				for(int j=0;j<domains.length;j++) { //Adiciona 1 a todos os elementos do dominio (uma vez que atraves do maximo nao se tem em conta o elemento 0)
					domains[j]++;
				}
				System.out.println(Arrays.toString(domains));
//Criação da amostra
				

				matrix=new int[numblines][domains.length];

				
				try {
					FileReader fr=new FileReader(database);
					BufferedReader br=new BufferedReader(fr);
					String CurrentLine;
					String[] line;
					
						for(int i=0;i<numblines;i++) { //Ciclo que cria uma matriz  de inteiros que contem toda a informacao do ficheiro (cada elemento de cada linha e uma entrada na matriz)
							CurrentLine=br.readLine(); //Le linha a linha
							line=CurrentLine.split(","); //Insere a informacao de cada linha num vetor separado por ,
							for(int j=0;j<line.length;j++) { //Transforma a informacao num inteiro e insere-a na posicao da matriz correspondente
								matrix[i][j]=Integer.parseInt(line[j]);
							}
						}
					
					br.close();
					fr.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				am= new Amostra(domains); //Controi a amostra
				
				for(int i=0;i<matrix.length;i++) { //Adiciona a informacao contida na matriz na amostra 
					am.add(matrix[i]);
				}
			
				
//Criação do Grafo Pesado
				wg=new WGraph(domains.length-1); 
					for(int i=0;i<domains.length-1;i++) { //O grafo pesado e construido obtenndo-se os pesos dos nos entre eles
						for(int j=0;j<domains.length-1;j++) {
							if(i!=j) { //Garante que nao estamos a calcular os pesos de um no com ele proprio
							double w=Pesos.weight(i, j, am); //Calculo dos pesos
							wg.add_edge(i,j,w); //Insercao do peso no grafo entre os nos i e j
							}
						}
					}
//Criação da MST
				
					mst=wg.MST(0);
								
				
//Criação da rede de Bayes
				
				bn=new BN(mst,am,0.5);
				textTeach.setText("Ready to be saved");

		}}}	
		});
		btnTeachMe.setBounds(24, 169, 138, 47);
		frame.getContentPane().add(btnTeachMe);
		
		JButton btnSave = new JButton("SAVE ME");
		btnSave.setForeground(new Color(0, 128, 128));
		btnSave.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnSave.setBackground(new Color(255, 255, 255));
		btnSave.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//De acordo com o JRadio Button Selecionado, o ficheiro vai ser guardado com o nome desse botão
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
				if(Other) {
					parameter="Other";
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
		btnSave.setBounds(200, 228, 141, 44);
		frame.getContentPane().add(btnSave);
		
		JRadioButton rdbtnCancer = new JRadioButton("Cancer");
		rdbtnCancer.setBounds(9, 115, 91, 35);
		frame.getContentPane().add(rdbtnCancer);
		rdbtnCancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancer=true;
			}
		});
		
		JRadioButton rdbtnDiabetes = new JRadioButton("Diabetes");
		rdbtnDiabetes.setBounds(109, 115, 100, 35);
		frame.getContentPane().add(rdbtnDiabetes);
		rdbtnDiabetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diabetes=true;
			}
		});
		
		JRadioButton rdbtnHepatitis = new JRadioButton("Hepatitis");
		rdbtnHepatitis.setBounds(218, 115, 111, 35);
		frame.getContentPane().add(rdbtnHepatitis);
		rdbtnHepatitis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hepatitis=true;
			}
		});
		
		JRadioButton rdbtnThyroid= new JRadioButton("Thyroid");
		rdbtnThyroid.setBounds(338, 115, 91, 35);
		frame.getContentPane().add(rdbtnThyroid);
		rdbtnThyroid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thyroid=true;
			}
		});
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(438, 114, 91, 37);
		frame.getContentPane().add(rdbtnOther);
		rdbtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Other=true;
			}
		});
		 
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnCancer);
		group.add(rdbtnDiabetes);
		group.add(rdbtnHepatitis);
		group.add(rdbtnThyroid);
		group.add(rdbtnOther);
		
		textTeach = new JTextField();
		textTeach.setColumns(10);
		textTeach.setBounds(183, 169, 337, 47);
		frame.getContentPane().add(textTeach);
	}
}
