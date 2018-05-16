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
import java.awt.event.ActionEvent;

public class Classificador {

	private JFrame frame;
	private JTextField textField;
	private String database;

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
		frame.setBounds(600, 400, 1128, 785);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClassificadorTanE = new JLabel("CLASSIFICATION APPLICATION");
		lblClassificadorTanE.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassificadorTanE.setForeground(new Color(0, 128, 128));
		lblClassificadorTanE.setFont(new Font("Dialog", Font.BOLD, 24));
		lblClassificadorTanE.setBounds(309, 22, 483, 123);
		frame.getContentPane().add(lblClassificadorTanE);
		
		JLabel lblClickBelowTo = new JLabel("Insert below your pacient's data:");
		lblClickBelowTo.setFont(new Font("Dialog", Font.PLAIN, 23));
		lblClickBelowTo.setBounds(21, 307, 333, 53);
		frame.getContentPane().add(lblClickBelowTo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		textArea.setSelectionColor(new Color(255, 255, 255));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setBounds(21, 358, 966, 166);
		frame.getContentPane().add(textArea);
		
		JButton btnclassify = new JButton("CLASSIFY");
		btnclassify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnclassify.setForeground(new Color(0, 128, 128));
		btnclassify.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnclassify.setBackground(new Color(255, 255, 255));
		btnclassify.setBorder(new LineBorder(new Color(95, 158, 160), 2, true));
		btnclassify.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnclassify.setBounds(316, 547, 470, 105);
		frame.getContentPane().add(btnclassify);
		
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
				database = fd.getDirectory()+filename;
				textField.setText(filename);
				
			}
		});
		btnload.setBounds(21, 155, 184, 123);
		frame.getContentPane().add(btnload);		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setBounds(238, 154, 333, 123);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
