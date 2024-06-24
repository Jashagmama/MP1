import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookARoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Booking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookARoom frame = new BookARoom();
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
	public BookARoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 506);
		Booking = new JPanel();
		Booking.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Booking);
		Booking.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Room Reservation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(154, 24, 442, 56);
		Booking.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pick a Month to check Availability");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(154, 90, 402, 56);
		Booking.add(lblNewLabel_1);
		
		JButton btnJan = new JButton("January");
		btnJan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnJan.setBounds(48, 195, 171, 47);
		Booking.add(btnJan);
		
		JButton btnFeb = new JButton("February");
		btnFeb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFeb.setBounds(258, 195, 171, 47);
		Booking.add(btnFeb);
		
		JButton btnMarch = new JButton("March");
		btnMarch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMarch.setBounds(483, 195, 171, 47);
		Booking.add(btnMarch);
		
		JButton btnApril = new JButton("April");
		btnApril.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnApril.setBounds(48, 260, 171, 47);
		Booking.add(btnApril);
		
		JButton btnJuly = new JButton("July");
		btnJuly.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnJuly.setBounds(48, 328, 171, 47);
		Booking.add(btnJuly);
		
		JButton btnOct = new JButton("October");
		btnOct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnOct.setBounds(48, 392, 171, 47);
		Booking.add(btnOct);
		
		JButton btnMay = new JButton("May");
		btnMay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMay.setBounds(258, 260, 171, 47);
		Booking.add(btnMay);
		
		JButton btnJune = new JButton("June");
		btnJune.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnJune.setBounds(483, 260, 171, 47);
		Booking.add(btnJune);
		
		JButton btnAug = new JButton("August");
		btnAug.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAug.setBounds(258, 328, 171, 47);
		Booking.add(btnAug);
		
		JButton btnSept = new JButton("September");
		btnSept.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSept.setBounds(483, 328, 171, 47);
		Booking.add(btnSept);
		
		JButton btnNov = new JButton("November");
		btnNov.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNov.setBounds(258, 392, 171, 47);
		Booking.add(btnNov);
		
		JButton btnDec = new JButton("December");
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDec.setBounds(483, 392, 171, 47);
		Booking.add(btnDec);
		
		JButton btnRRback = new JButton("Back");
		btnRRback.setBounds(10, 448, 85, 21);
		Booking.add(btnRRback);
	}
}
