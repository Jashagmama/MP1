import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class DisplayHomeScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel homePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayHomeScreen frame = new DisplayHomeScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DisplayHomeScreen() {
		setTitle("HRS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 465);
		homePage = new JPanel();
		homePage.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(homePage);
		homePage.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hotel Reservations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(190, 42, 454, 55);
		homePage.add(lblNewLabel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(51, 251, 198, 84);
		homePage.add(btnCreate);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManage.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnManage.setBounds(275, 251, 198, 84);
		homePage.add(btnManage);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnView.setBounds(511, 251, 198, 84);
		homePage.add(btnView);
		
		JButton btnBookARoom = new JButton("Book a Room");
		btnBookARoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBookARoom.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnBookARoom.setBounds(146, 129, 471, 84);
		homePage.add(btnBookARoom);
	}
}
