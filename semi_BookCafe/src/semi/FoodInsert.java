package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;

public class FoodInsert extends JFrame {
	
	ButtonGroup group = new ButtonGroup();

	private JPanel contentPane;
	public JLabel status;
	private JTextField tfName;
	private JTextField tInPrice;
	private JTextField tSalPrice;
	private JTextField tEa;
	private JButton btnAdd;
	private JButton btnBack;
	private JRadioButton rSales;
	private JRadioButton rNoSales;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodInsert frame = new FoodInsert();
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
	public FoodInsert() {
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 796);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getStatus());
		contentPane.add(getTfName());
		contentPane.add(getTInPrice());
		contentPane.add(getTSalPrice());
		contentPane.add(getTEa());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnBack());
		contentPane.add(getRSales());
		contentPane.add(getRNoSales());
		contentPane.add(getLblNewLabel_1());
	}
	public JLabel getStatus() {
		if (status == null) {
			status = new JLabel("\uC815\uBCF4\uB97C \uC785\uB825\uD558\uC138\uC694");
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status.setForeground(new Color(240, 128, 128));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBackground(new Color(255, 255, 255));
			status.setOpaque(true);
			status.setBounds(129, 497, 300, 29);
		}
		return status;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tfName.setBorder(null);
			tfName.setBounds(252, 336, 116, 19);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JTextField getTInPrice() {
		if (tInPrice == null) {
			tInPrice = new JTextField();
			tInPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tInPrice.setBorder(null);
			tInPrice.setBounds(246, 368, 149, 21);
			tInPrice.setColumns(10);
		}
		return tInPrice;
	}
	private JTextField getTSalPrice() {
		if (tSalPrice == null) {
			tSalPrice = new JTextField();
			tSalPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tSalPrice.setBorder(null);
			tSalPrice.setBounds(246, 401, 149, 21);
			tSalPrice.setColumns(10);
		}
		return tSalPrice;
	}
	private JTextField getTEa() {
		if (tEa == null) {
			tEa = new JTextField();
			tEa.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tEa.setBorder(null);
			tEa.setBounds(246, 434, 149, 21);
			tEa.setColumns(10);
		}
		return tEa;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("");
			btnAdd.setBackground(Color.WHITE);
			btnAdd.setBorder(null);
			btnAdd.setIcon(new ImageIcon(FoodInsert.class.getResource("/semi_icon2/체크.jpg")));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						FoodDao dao = new FoodDao();
						FoodVo vo = new FoodVo();
						String s = ""; // 판매/미판매 버튼 선택
						if(rSales.isSelected()) {
							s = "Y";
						} else if(rNoSales.isSelected()) {
							s = "N";
						}
						if(tInPrice.getText().contains(" ") || 
								tSalPrice.getText().contains(" ")|| tEa.getText().contains(" ")) {
							status.setText("입력한 데이터에 공백이 있습니다.");
							return;
						}
						if(tfName.getText().equals("") || tInPrice.getText().equals("") || 
								tSalPrice.getText().equals("")|| tEa.getText().equals("")) {
							status.setText("입력하지 않은 데이터가 있습니다.");
							return;
						}
						
						String fNameCheck = "[가-힣a-zA-Z_ ]{1,20}";
						String InPriceCheck = "[\\d]{1,5}"; 
						String SalPriceCheck = "[\\d]{1,10}";
						String fEaCheck = "[\\d]{1,10}";
						
						boolean flag = Pattern.matches(fNameCheck, tfName.getText());
						if(!flag) {
							status.setText("음식 이름에 숫자나 기호가 포함되어있습니다.");
							tfName.requestFocus();
							tfName.selectAll();
							tfName.setBorder(LineBorder.createBlackLineBorder());
							return;
						}
						flag = Pattern.matches(InPriceCheck, tInPrice.getText());
						if(!flag) {
							status.setText("입고 금액에 문자가 포함되어있습니다.");
							tInPrice.requestFocus();
							tInPrice.selectAll();
							tInPrice.setBorder(LineBorder.createBlackLineBorder());
							return;
						}
						
						flag = Pattern.matches(SalPriceCheck, tSalPrice.getText());
						if(!flag) {
							status.setText("판매 금액에 문자가 포함되어있습니다.");
							tSalPrice.requestFocus();
							tSalPrice.selectAll();
							tSalPrice.setBorder(LineBorder.createBlackLineBorder());
							return;
						}
						
						flag = Pattern.matches(fEaCheck, tEa.getText());					
						if(!flag) {
							status.setText("보유 수량에 문자가 포함되어있습니다.");
							tEa.requestFocus();
							tEa.selectAll();
							tEa.setBorder(LineBorder.createBlackLineBorder());
							return;
						}	
						vo.setfName(tfName.getText());
						vo.setInPrice(Integer.parseInt(tInPrice.getText()));
						vo.setSalPrice(Integer.parseInt(tSalPrice.getText()));
						vo.setfEa(Integer.parseInt(tEa.getText()));
						vo.setSales(s);
						
						int cnt = dao.Insert(vo);
						if(cnt>0) {
							status.setText("음식이 정상적으로 추가되었습니다.");
						} else {
							status.setText("정보 입력 중 오류가 발생되었습니다.");
						}
						tfName.requestFocus();
						tfName.selectAll();
						tInPrice.setText("");
						tSalPrice.setText("");
						tEa.setText("");
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnAdd.setBounds(262, 535, 41, 33);
		}
		return btnAdd;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("");
			btnBack.setIcon(new ImageIcon(FoodInsert.class.getResource("/semi_icon2/이전으로.jpg")));
			btnBack.setBackground(Color.WHITE);
			btnBack.setBorder(null);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					FoodSelect fs = new FoodSelect();
				}
			});
			btnBack.setBounds(28, 700, 32, 29);
		}
		return btnBack;
	}
	private JRadioButton getRSales() {
		if (rSales == null) {
			rSales = new JRadioButton("\uD310\uB9E4");
			rSales.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rSales.setBackground(new Color(255, 255, 255));
			rSales.setBounds(248, 466, 57, 23);
			rSales.setSelected(true);
			group.add(rSales);
		}
		return rSales;
	}
	private JRadioButton getRNoSales() {
		if (rNoSales == null) {
			rNoSales = new JRadioButton("\uBBF8\uD310\uB9E4");
			rNoSales.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rNoSales.setBackground(new Color(255, 255, 255));
			rNoSales.setBounds(327, 466, 68, 23);
			group.add(rNoSales);
		}
		return rNoSales;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(FoodInsert.class.getResource("/semiIcon/FoodInsert.jpg")));
			lblNewLabel_1.setBounds(0, -1, 564, 754);
		}
		return lblNewLabel_1;
	}
}
