package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChargeModify extends JFrame {
	
	DecimalFormat df = new DecimalFormat("##,###,###");
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnBack;
	private JTextField txtAddModify;
	private JTextField txtInitModify;
	private JButton btnPriceModify;
	private JTextField txtInitPrice;
	private JTextField txtAddPrice;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChargeModify frame = new ChargeModify();
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
	public ChargeModify() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				txtInitModify.requestFocus();
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 792);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getPanel_1());
		contentPane.add(getBtnBack());
		
		// DB에 저장된 요금 가져오기
		SalesDao dao = new SalesDao();
		int[] result = dao.chargeSearch();

		txtInitPrice.setText(df.format(result[0]) + "");
		txtAddPrice.setText(df.format(result[1]) + "");
		contentPane.add(getLabel_1());
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(259, 304, 166, 65);
			panel.setLayout(null);
			panel.add(getTxtInitPrice());
			panel.add(getTxtAddPrice());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setOpaque(false);
			panel_1.setBackground(Color.WHITE);
			panel_1.setBorder(null);
			panel_1.setBounds(259, 468, 167, 106);
			panel_1.setLayout(null);
			panel_1.add(getTxtAddModify());
			panel_1.add(getTxtInitModify());
			panel_1.add(getBtnPriceModify());
		}
		return panel_1;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("");
			btnBack.setIcon(new ImageIcon(ChargeModify.class.getResource("/semi_icon2/이전으로.jpg")));
			btnBack.setBorder(null);
			btnBack.setBackground(Color.WHITE);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnBack.setBounds(26, 703, 36, 27);
		}
		return btnBack;
	}
	private JTextField getTxtAddModify() {
		if (txtAddModify == null) {
			txtAddModify = new JTextField();
			txtAddModify.setBorder(null);
			txtAddModify.setOpaque(false);
			txtAddModify.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtAddModify.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnPriceModify.doClick();
					}
				}
			});
			txtAddModify.setColumns(10);
			txtAddModify.setBounds(0, 37, 162, 21);
		}
		return txtAddModify;
	}
	private JTextField getTxtInitModify() {
		if (txtInitModify == null) {
			txtInitModify = new JTextField();
			txtInitModify.setBorder(null);
			txtInitModify.setOpaque(false);
			txtInitModify.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtInitModify.setColumns(10);
			txtInitModify.setBounds(0, 4, 162, 21);
		}
		return txtInitModify;
	}
	private JButton getBtnPriceModify() {
		if (btnPriceModify == null) {
			btnPriceModify = new JButton("");
			btnPriceModify.setIcon(new ImageIcon(ChargeModify.class.getResource("/semi_icon2/체크.jpg")));
			btnPriceModify.setBorder(null);
			btnPriceModify.setBackground(Color.WHITE);
			btnPriceModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (txtInitModify.equals("")) {
						JOptionPane.showInternalMessageDialog(ChargeModify.this, "수정할 가격을 입력해주세요.");
					}

					try {
						int ipm = Integer.parseInt(txtInitModify.getText());
						int apm;
						
						// 추가요금은 없앨 수 있음
						if(txtAddModify.getText().equals("")) {
							apm = 0;
						}
						else {
							apm = Integer.parseInt(txtAddModify.getText());							
						}
						
						SalesDao dao = new SalesDao();
						int result = dao.chargeUpdate(ipm, apm);
						if (result > 0) {
							JOptionPane.showMessageDialog(ChargeModify.this, "이용요금이 수정되었습니다.");
							txtInitModify.setText("");
							txtAddModify.setText("");

							txtInitPrice.setText(df.format(ipm) + "");
							txtAddPrice.setText(df.format(apm) + "");
						} else {
							JOptionPane.showMessageDialog(ChargeModify.this, "수정 중 오류 발생");
						}
					} catch (NumberFormatException nfex) {
						JOptionPane.showMessageDialog(ChargeModify.this, "수정할 가격에 문자 또는 공백이 존재합니다.");
					}
					
				}
			});
			btnPriceModify.setBounds(4, 66, 44, 37);
		}
		return btnPriceModify;
	}
	private JTextField getTxtInitPrice() {
		if (txtInitPrice == null) {
			txtInitPrice = new JTextField();
			txtInitPrice.setBorder(null);
			txtInitPrice.setOpaque(false);
			txtInitPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtInitPrice.setEnabled(false);
			txtInitPrice.setColumns(10);
			txtInitPrice.setBounds(2, 3, 162, 21);
		}
		return txtInitPrice;
	}
	private JTextField getTxtAddPrice() {
		if (txtAddPrice == null) {
			txtAddPrice = new JTextField();
			txtAddPrice.setBorder(null);
			txtAddPrice.setOpaque(false);
			txtAddPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtAddPrice.setEnabled(false);
			txtAddPrice.setColumns(10);
			txtAddPrice.setBounds(2, 35, 162, 21);
		}
		return txtAddPrice;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ChargeModify.class.getResource("/semiIcon/ChargeModify.jpg")));
			lblNewLabel.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel;
	}
}
