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
		frame.setBounds(600, 400, 1009, 699);
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
		
		JTextField textParameters = new JTextField();
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
				fd.setDirectory("C:\\");
				fd.setFile("*.BN");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename == null)
					System.out.println("Cancelled");
				else 
					System.out.println("File selected: " + filename);
				redebayes= fd.getDirectory()+filename;
				textParameters.setText(filename);
				
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
		
		JTextField textRes = new JTextField();
		textRes.setBounds(237, 501, 750, 131);
		frame.getContentPane().add(textRes);
		textRes.setColumns(10);
		
		JButton btnclassify = new JButton("CLASSIFY");
		btnclassify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String[] Input = textArea.getText().split(",");
					vector=new int[Input.length];
					for(int i=0;i<Input.length;i++) {
						vector[i]=Integer.parseInt(Input[i]);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
				try {
				FileInputStream fis = new FileInputStream(redebayes); 
				ObjectInputStream ois = new ObjectInputStream(fis);
				bn = (BN) ois.readObject();
				
				
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
				
				res=bn.prob(vector);
				String text = new String ("The most likely class is " + res.get(0).toString() + " with the probability of " + res.get(1).toString() "%");
				System.out.println(text);
				textRes.setText(text);
			
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
