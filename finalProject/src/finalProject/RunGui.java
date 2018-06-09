package finalProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class RunGui {

	private JFrame frame;
	private JTextField txtThisIsThe;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Generate Word Cloud");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String someString = txtThisIsThe.getText();
				int convertedInt = Integer.parseInt(someString);
				RunProject firstRun = new RunProject();
				try {
					firstRun.runQuery(convertedInt);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, someString);
			}
		});
		btnNewButton.setBounds(134, 217, 204, 29);
		frame.getContentPane().add(btnNewButton);
		
		txtThisIsThe = new JTextField();
		txtThisIsThe.setText("100");
		txtThisIsThe.setBounds(129, 52, 209, 26);
		frame.getContentPane().add(txtThisIsThe);
		txtThisIsThe.setColumns(10);
		
		JLabel lblSelectNumberOf = new JLabel("Select Number of Words");
		lblSelectNumberOf.setBounds(134, 24, 204, 16);
		frame.getContentPane().add(lblSelectNumberOf);
	}
}
