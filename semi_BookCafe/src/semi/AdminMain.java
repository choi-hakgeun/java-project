package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;

public class AdminMain extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton button;
	private JLabel lblNewLabel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
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
	public AdminMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FirstFrame ff = new FirstFrame();//첫화면으로 이동					
				dispose();
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 793);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getBtnNewButton_3());
		contentPane.add(getBtnNewButton_4());
		contentPane.add(getBtnNewButton_5());
		contentPane.add(getButton());
		contentPane.add(getLabel_1());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain회원관리.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ManagerFrame mm = new ManagerFrame();
					mm.refresh();
				}
			});
			btnNewButton.setBounds(84, 184, 146, 114);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain도서관리.jpg")));
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					BookControl bc = new BookControl();
					bc.setVisible(true);
					
				}
			});
			btnNewButton_1.setBounds(326, 179, 146, 114);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain주문내역.jpg")));
			btnNewButton_2.setBackground(Color.WHITE);
			btnNewButton_2.setBorder(null);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderUpdate ou = new OrderUpdate();
					ou.setVisible(true);
				}
			});
			btnNewButton_2.setBounds(80, 347, 146, 114);
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain음식자재관리.jpg")));
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.setBorder(null);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FoodSelect fs = new FoodSelect();
					fs.setVisible(true);
				}
			});
			btnNewButton_3.setBounds(331, 354, 146, 114);
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("");
			btnNewButton_4.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain매출관리.jpg")));
			btnNewButton_4.setBackground(Color.WHITE);
			btnNewButton_4.setBorder(null);
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SalesManagment sm = new SalesManagment();
					sm.setVisible(true);
				}
			});
			btnNewButton_4.setBounds(326, 500, 146, 124);
		}
		return btnNewButton_4;
	}
	private JButton getBtnNewButton_5() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("");
			btnNewButton_5.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/AdminMain이용요금관리.jpg")));
			btnNewButton_5.setBackground(Color.WHITE);
			btnNewButton_5.setBorder(null);
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ChargeModify cm = new ChargeModify();
					cm.setVisible(true);
				}
			});
			btnNewButton_5.setBounds(83, 514, 146, 114);
		}
		return btnNewButton_5;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(AdminMain.class.getResource("/semi_icon2/UserMain로그아웃.jpg")));
			button.setBackground(Color.WHITE);
			button.setBorder(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FirstFrame ff = new FirstFrame();//첫화면으로 이동					
					dispose();
				}
			});
			button.setBounds(234, 695, 97, 28);
		}
		return button;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(AdminMain.class.getResource("/semiIcon/AdminMain.jpg")));
			lblNewLabel.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBorder(null);
			textField.setBounds(-37, 108, 116, 21);
			textField.setColumns(10);
		}
		return textField;
	}
}
