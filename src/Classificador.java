import java.awt.Color;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.xml.soap.Text;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.awt.event.ActionEvent;


public class Classificador {

	private JFrame frame;
	private JTextField textParameters;
	private String redebayes;
	private int[] vector;
	private BN bn;
	private LinkedList<Double> res;
	private JTextField textRes;
	private String filename;
	private boolean numero;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Classificador window = new Classificador();
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
	public Classificador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.setBounds(600, 400, 1036, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClassificadorTanE = new JLabel("CLASSIFICATION APPLICATION");
		lblClassificadorTanE.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassificadorTanE.setForeground(new Color(0, 128, 128));
		lblClassificadorTanE.setFont(new Font("Adobe Gothic Std", Font.BOLD, 26));
		lblClassificadorTanE.setBounds(302, 21, 483, 76);
		frame.getContentPane().add(lblClassificadorTanE);
		
		JLabel lblClickBelowTo = new JLabel("Insert your pacient's data below:");
		lblClickBelowTo.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 23));
		lblClickBelowTo.setBounds(21, 254, 387, 53);
		frame.getContentPane().add(lblClickBelowTo);
		
		textParameters = new JTextField();
		textParameters.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textParameters.setBounds(237, 103, 445, 123);
		frame.getContentPane().add(textParameters);
		textParameters.setColumns(10);
		
		
		JButton btnload = new JButton("LOAD");
		btnload.setForeground(new Color(0, 128, 128));
		btnload.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		btnload.setBackground(new Color(255, 255, 255));
		btnload.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnload.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Select a file", FileDialog.LOAD);
				frame.getContentPane().setLayout(null);
				fd.setDirectory("C:\\"); //Permite ir procurar o ficheiro nas pastas do computador
				fd.setFile("*.BN"); //Procura ficheiros do tipo .BN (tipo de ficheiro guardado pela aprendizagem)
				fd.setVisible(true);
				filename = fd.getFile(); //Guarda o nome do ficheiro selecionado
				if (filename == null)
					System.out.println("Cancelled");
				else 
					System.out.println("File selected: " + filename);
				redebayes= fd.getDirectory()+filename; //Variavel que guarda o caminho do ficheiro selecionado
				textParameters.setText(filename); //Nome do ficheiro aparece na caixa de texto junto ao botao load
				
			}
		});
		btnload.setBounds(21, 104, 184, 123);
		frame.getContentPane().add(btnload);		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		textArea.setSelectionColor(new Color(255, 255, 255));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(21, 319, 966, 166);
		frame.getContentPane().add(textArea);
		
		textRes = new JTextField();
		textRes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textRes.setBounds(237, 501, 750, 131);
		frame.getContentPane().add(textRes);
		textRes.setColumns(10);
		
		JButton btnclassify = new JButton("CLASSIFY");
		btnclassify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				try {
					String[] Input = textArea.getText().split(","); //Transofrma os parametros inseridos pelo medico numa array de strings separados pela ","
					vector=new int[Input.length];
					numero = true;
					for(int i=0;i<Input.length&&numero==true;i++) { //Ciclo que transforma os parametros inseridos pelo medico num vetor de inteiros
						try {
							vector[i]=Integer.parseInt(Input[i]);
					   }catch (NumberFormatException e){
					       System.out.println("not a number"); 
					       numero=false;
					   } 	
					}	
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
				
				if (filename==null) {
					textRes.setText("Please load a .BN document above.");
				}
				else {
				if (numero==false) { //Devolve uma mensagem caso o medico insira, por engano, um vetor que nao e constituido por numeros
				textRes.setText("Please insert the paramaters in the correct form.");
				}
					
				else {
				//O seguinte conjunto de ifs verifica se o numero de parametros introduzido corresponde ao numero de parametros da doenca selecionada pela rede de bayes. Devolve uma mensagem caso nao o seja
				if(filename.equals("Cancer.BN") && vector.length!=10) { 
					textRes.setText("Please insert the correct number of paramaters.");
				}
				else {
				if(filename.equals("Diabetes.BN") && vector.length!=8) {
					textRes.setText("Please insert the correct number of paramaters.");
				}
				else {
				if(filename.equals("Hepatitis.BN") && vector.length!=19) {
					textRes.setText("Please insert the correct number of paramaters.");
				}
				else {
				if(filename.equals("Thyroid.BN") && vector.length!=20) {
					textRes.setText("Please insert the correct number of paramaters.");
				}
				else {
				
				try {
				FileInputStream fis = new FileInputStream(redebayes); //Abre uma conexao com o ficheiro selecionado atraves do caminho anteriormente guardado
				ObjectInputStream ois = new ObjectInputStream(fis); //Desserializa o ficheiro
				bn = (BN) ois.readObject(); //Le o ficheiro
				
				
				fis.close();
				ois.close();
				
				} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
				res=bn.prob(vector); //Devolve um vetor que contem a classe com maior probabilidade e qual a probabilidade obtida por essa classe
				textRes.setText(String.format("The most likely class is %d with the probability of %.2f %%", res.get(0).intValue(),res.get(1) )); //Devolve o resultado na caixa de texto junto ao botao classify
				} catch (IndexOutOfBoundsException e) { //O programa da um erro deste tipo se se inserir no vetor de parametros um numero que esta fora do dominio da variavel
				    System.out.println("Variables with domains not correct");
				    textRes.setText("Please insert the correct domain for each variable");
				}
				}
				}}}}
				}
			}
		});
		btnclassify.setForeground(new Color(0, 128, 128));
		btnclassify.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		btnclassify.setBackground(new Color(255, 255, 255));
		btnclassify.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnclassify.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnclassify.setBounds(21, 501, 184, 131);
		frame.getContentPane().add(btnclassify);
		

		JLabel lblNewJgoodiesLabel = new JLabel("Please insert the data as the following example: 0,1,1,1,1,1,1 (according to the number of parameters of the disease)");
		lblNewJgoodiesLabel.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 13));
		lblNewJgoodiesLabel.setBounds(21, 291, 771, 31);
		frame.getContentPane().add(lblNewJgoodiesLabel);
		
		JLabel lblNumberOfParameters = new JLabel("Number of parameters:");
		lblNumberOfParameters.setFont(new Font("Adobe Gothic Std", Font.BOLD, 20));
		lblNumberOfParameters.setBounds(708, 102, 261, 48);
		frame.getContentPane().add(lblNumberOfParameters);
		
		JLabel lblCancer = new JLabel("Cancer: 10");
		lblCancer.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 15));
		lblCancer.setBounds(708, 149, 120, 31);
		frame.getContentPane().add(lblCancer);
		
		JLabel lblDiabetes =new JLabel("Diabetes: 8");
		lblDiabetes.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 15));
		lblDiabetes.setBounds(708, 177, 120, 31);
		frame.getContentPane().add(lblDiabetes);
		
		JLabel lblHepatitis = new JLabel("Hepatitis: 19");
		lblHepatitis.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 15));
		lblHepatitis.setBounds(813, 147, 120, 34);
		frame.getContentPane().add(lblHepatitis);
		
		JLabel lblThyroid = new JLabel("Thyroid: 20");
		lblThyroid.setFont(new Font("Adobe Gothic Std", Font.PLAIN, 15));
		lblThyroid.setBounds(813, 177, 120, 31);
		frame.getContentPane().add(lblThyroid);

		
	}
}
