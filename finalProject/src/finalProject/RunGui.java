package finalProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JSpinner;

public class RunGui {


	private JFrame frame;
	private JTabbedPane pane;
	private JTextField txtThisIsThe;
	private JRadioButton rdbtnCircle;
	private JRadioButton rdbtnWhale;
	private JRadioButton rdbtnMountain;
	private JRadioButton rdbtnPi;
	private JRadioButton rdbtnNewRadioButton;
	private JLabel lblOfThe;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton rdbtnMost;
	private JRadioButton rdbtnLeast;
	private JLabel lblIWantWords;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunGui window = new RunGui();
					window.frame.setVisible(true);
					//firstRun.runQuery();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RunGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Generate Word Cloud");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String someString = txtThisIsThe.getText();
				int convertedInt = Integer.parseInt(someString);
				RunProject firstRun = new RunProject();
				String lowRange = textField.getText();
				String highRange = textField_1.getText();
				int lowRangeInt = Integer.parseInt(lowRange);
				int highRangeInt = Integer.parseInt(highRange);
				String similarTo = textField_2.getText();
				try {
					if (rdbtnLeast.isSelected()){
						System.out.println("Least selected");
						if (rdbtnCircle.isSelected()){
							firstRun.runQuery(convertedInt, "circle", 0, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnWhale.isSelected()){
							firstRun.runQuery(convertedInt, "whale", 0, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnMountain.isSelected()){
							firstRun.runQuery(convertedInt, "mountain", 0, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnPi.isSelected()){
							firstRun.runQuery(convertedInt, "pi", 0, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnNewRadioButton.isSelected()){
							firstRun.runQuery(convertedInt, "thumbs up", 0, lowRangeInt, highRangeInt, similarTo);
						}
					} else {
						System.out.println("most selected");
						if (rdbtnCircle.isSelected()){
							firstRun.runQuery(convertedInt, "circle", 1, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnWhale.isSelected()){
							firstRun.runQuery(convertedInt, "whale", 1, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnMountain.isSelected()){
							firstRun.runQuery(convertedInt, "mountain", 1, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnPi.isSelected()){
							firstRun.runQuery(convertedInt, "pi", 1, lowRangeInt, highRangeInt, similarTo);
						} else if (rdbtnNewRadioButton.isSelected()){
							firstRun.runQuery(convertedInt, "thumbs up", 1, lowRangeInt, highRangeInt, similarTo);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Image generated! View the image in your eclipse project folder.");
				///Users/max/Documents/365/final/finalProject/datarank_wordcloud_circle_sqrt_font.png
				//frame.add(new JLabel(new ImageIcon("resources/jerry.png")));
			}
		});
		btnNewButton.setBounds(226, 363, 204, 29);
		frame.getContentPane().add(btnNewButton);
		
		txtThisIsThe = new JTextField();
		txtThisIsThe.setText("100");
		txtThisIsThe.setBounds(116, 24, 79, 26);
		frame.getContentPane().add(txtThisIsThe);
		txtThisIsThe.setColumns(10);
		
		JLabel lblSelectNumberOf = new JLabel("I want");
		lblSelectNumberOf.setBounds(51, 29, 53, 16);
		frame.getContentPane().add(lblSelectNumberOf);
		
		rdbtnCircle = new JRadioButton("Circle");
		rdbtnCircle.setBounds(269, 224, 141, 23);
		frame.getContentPane().add(rdbtnCircle);
		
		rdbtnWhale = new JRadioButton("Whale");
		rdbtnWhale.setBounds(269, 246, 141, 23);
		frame.getContentPane().add(rdbtnWhale);
		
		rdbtnMountain = new JRadioButton("Mountain");
		rdbtnMountain.setBounds(269, 270, 141, 23);
		frame.getContentPane().add(rdbtnMountain);
		
		rdbtnPi = new JRadioButton("Pi");
		rdbtnPi.setBounds(269, 293, 141, 23);
		frame.getContentPane().add(rdbtnPi);
		
		rdbtnNewRadioButton = new JRadioButton("Thumbs Up");
		rdbtnNewRadioButton.setBounds(269, 317, 141, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		lblOfThe = new JLabel("of the");
		lblOfThe.setBounds(212, 29, 48, 16);
		frame.getContentPane().add(lblOfThe);
		
		rdbtnMost = new JRadioButton("most");
		rdbtnMost.setBounds(255, 43, 79, 23);
		frame.getContentPane().add(rdbtnMost);
		
		rdbtnLeast = new JRadioButton("least");
		rdbtnLeast.setBounds(255, 17, 79, 23);
		frame.getContentPane().add(rdbtnLeast);
		
		JLabel lblPopularWordsUsed = new JLabel("popular words used in the song database.");
		lblPopularWordsUsed.setBounds(328, 29, 306, 16);
		frame.getContentPane().add(lblPopularWordsUsed);
		
		JLabel lblIWantWord = new JLabel("I want the length of words to be between");
		lblIWantWord.setBounds(43, 112, 275, 16);
		frame.getContentPane().add(lblIWantWord);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(328, 107, 53, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAnd = new JLabel("and");
		lblAnd.setBounds(393, 112, 37, 16);
		frame.getContentPane().add(lblAnd);
		
		textField_1 = new JTextField();
		textField_1.setText("100");
		textField_1.setBounds(442, 107, 42, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCharacters = new JLabel("characters.");
		lblCharacters.setBounds(502, 112, 99, 16);
		frame.getContentPane().add(lblCharacters);
		
		JLabel lblIWantMy = new JLabel("I want my word cloud in the shape of...");
		lblIWantMy.setBounds(212, 196, 333, 16);
		frame.getContentPane().add(lblIWantMy);
		
		lblIWantWords = new JLabel("I want words similar to");
		lblIWantWords.setBounds(145, 157, 173, 16);
		frame.getContentPane().add(lblIWantWords);
		
		textField_2 = new JTextField();
		textField_2.setBounds(304, 150, 180, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
	}
}
