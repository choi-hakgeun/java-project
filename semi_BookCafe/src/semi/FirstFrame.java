package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FirstFrame extends JFrame{
	
	boolean a;

	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel watch;
	private JLabel day;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
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
	public FirstFrame() {
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 792);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getWatch());
		contentPane.add(getDay());
		
		this.a= this.isVisible();
		MainWatch mw = new MainWatch(watch,day,a);//시계
		contentPane.add(getLblNewLabel());
		mw.setDaemon(true);
		mw.start();
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(FirstFrame.class.getResource("/semi_icon2/다음으로2.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBorder(null);
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setFont(new Font("�������", Font.BOLD, 13));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login ac = new Login();
					ac.toFront();
					setVisible(false);
				}
			});
			btnNewButton.setBounds(238, 630, 79, 74);
		}
		return btnNewButton;
	}
	private JLabel getWatch() {
		if (watch == null) {
			watch = new JLabel("13 : 38");
			watch.setHorizontalAlignment(SwingConstants.CENTER);
			watch.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 34));
			watch.setBounds(169, 572, 223, 48);
		}
		return watch;
	}
	private JLabel getDay() {
		if (day == null) {
			day = new JLabel("New label");
			day.setHorizontalAlignment(SwingConstants.CENTER);
			day.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 18));
			day.setBounds(220, 553, 122, 26);
		}
		return day;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(FirstFrame.class.getResource("/semiIcon/FirstFrame.jpg")));
			lblNewLabel.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel;
	}
}
