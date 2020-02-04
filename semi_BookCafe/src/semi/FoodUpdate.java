package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class FoodUpdate extends JFrame {
	
	ButtonGroup group = new ButtonGroup();

	private JPanel contentPane;
	private JLabel status;
	private JTextField tfNo;
	private JButton btnSearch;
	private JTextField tfName;
	private JTextField tInPrice;
	private JTextField tSalPrice;
	private JButton btnUp;
	private JButton btnDelete;
	private JRadioButton rSales;
	private JRadioButton rNoSales;
	private JTextField tEa;
	private JSeparator separator_2;
	private JButton button;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodUpdate frame = new FoodUpdate();
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
	public FoodUpdate() {
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 794);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getStatus());
		contentPane.add(getTfNo());
		contentPane.add(getBtnSearch());
		contentPane.add(getTfName());
		contentPane.add(getTInPrice());
		contentPane.add(getTSalPrice());
		contentPane.add(getBtnUp());
		contentPane.add(getBtnDelete());
		contentPane.add(getRSales());
		contentPane.add(getRNoSales());
		contentPane.add(getTEa());
		contentPane.add(getSeparator_2());
		contentPane.add(getButton());
		contentPane.add(getLabel_1());
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("\uC74C\uC2DD \uBC88\uD638\uB97C \uAC80\uC0C9\uD558\uC138\uC694");
			status.setForeground(new Color(240, 128, 128));
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBackground(new Color(255, 255, 255));
			status.setOpaque(true);
			status.setBounds(24, 529, 528, 23);
		}
		return status;
	}
	private JTextField getTfNo() {
		if (tfNo == null) {
			tfNo = new JTextField();
			tfNo.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tfNo.setBorder(null);
			tfNo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnSearch.doClick();
					}
				}
			});
			tfNo.setBounds(253, 335, 108, 19);
			tfNo.setColumns(10);
		}
		return tfNo;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setBorder(null);
			btnSearch.setBackground(Color.WHITE);
			btnSearch.setIcon(new ImageIcon(FoodUpdate.class.getResource("/semi_icon2/검색.jpg")));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FoodDao dao = new FoodDao();
					int fNo = Integer.parseInt(tfNo.getText());
					FoodVo vo = dao.Search(fNo);
					
					tfName.setText("");
					tInPrice.setText("");
					tSalPrice.setText("");
					tEa.setText("");
					
					if(vo == null) {
						status.setText("자료가 없습니다.");
					}else {
						tfName.setText(vo.getfName());
						tInPrice.setText(vo.getInPrice() + "");
						tSalPrice.setText(vo.getSalPrice() + "");
						tEa.setText(vo.getfEa() + "");
						if(vo.getSales().equals("Y")) {
							rSales.setSelected(true);
						}
						else {
							rNoSales.setSelected(true);
						}
						status.setText("정보를 수정하세요~");
					}
				}
			});
			btnSearch.setBounds(373, 330, 33, 31);
		}
		return btnSearch;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tfName.setBorder(null);
			tfName.setBounds(245, 368, 152, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}
	private JTextField getTInPrice() {
		if (tInPrice == null) {
			tInPrice = new JTextField();
			tInPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tInPrice.setBorder(null);
			tInPrice.setBounds(245, 401, 152, 21);
			tInPrice.setColumns(10);
		}
		return tInPrice;
	}
	private JTextField getTSalPrice() {
		if (tSalPrice == null) {
			tSalPrice = new JTextField();
			tSalPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tSalPrice.setBorder(null);
			tSalPrice.setBounds(245, 434, 152, 21);
			tSalPrice.setColumns(10);
		}
		return tSalPrice;
	}
	private JButton getBtnUp() {
		if (btnUp == null) {
			btnUp = new JButton("");
			btnUp.setIcon(new ImageIcon(FoodUpdate.class.getResource("/semi_icon2/FoodUpdate수정.jpg")));
			btnUp.setBorder(null);
			btnUp.setBackground(Color.WHITE);
			btnUp.addActionListener(new ActionListener() {
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
							status.setText("입고 금액에 문자가 포한되어있습니다.");
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
						vo.setfNo(Integer.parseInt(tfNo.getText()));
						vo.setfName(tfName.getText());
						vo.setInPrice(Integer.parseInt(tInPrice.getText()));
						vo.setSalPrice(Integer.parseInt(tSalPrice.getText()));
						vo.setfEa(Integer.parseInt(tEa.getText()));
						vo.setSales(s);
						
						int cnt = dao.Update(vo);
						if(cnt>0) {
							status.setText("정상적으로 수정됨");
							//model.setNumRows(0);
							
						} else {
							status.setText("수정 중 오류가 발생되었습니다.");
						}
						tfNo.requestDefaultFocus();
						tfNo.selectAll();
						tfName.setText("");
						tInPrice.setText("");
						tSalPrice.setText("");
						tEa.setText("");
						
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnUp.setBounds(175, 562, 93, 37);
		}
		return btnUp;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("");
			btnDelete.setIcon(new ImageIcon(FoodUpdate.class.getResource("/semi_icon2/FoodUpdate삭제.jpg")));
			btnDelete.setBorder(null);
			btnDelete.setBackground(Color.WHITE);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FoodDao dao = new FoodDao();
					int fNo = Integer.parseInt(tfNo.getText());
					int cnt = dao.Delete(fNo); // ����踰��몃� 寃������� �곗�댄�� ����
					
					if(cnt>0) {
						status.setText("정보가 삭제되었습니다.");
					}else {
						status.setText("삭제 중 오류가 발생되었습니다.");
					}
					tfNo.requestFocus();
					tfNo.selectAll();
					tfName.setText("");
					tInPrice.setText("");
					tSalPrice.setText("");
					tEa.setText("");
				}
			});
			btnDelete.setBounds(295, 562, 93, 37);
		}
		return btnDelete;
	}
	private JRadioButton getRSales() {
		if (rSales == null) {
			rSales = new JRadioButton("\uD310\uB9E4");
			rSales.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rSales.setBackground(new Color(255, 255, 255));
			rSales.setBounds(246, 500, 57, 23);
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
			rNoSales.setBounds(327, 500, 75, 23);
			group.add(rNoSales);
		}
		return rNoSales;
	}
	private JTextField getTEa() {
		if (tEa == null) {
			tEa = new JTextField();
			tEa.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tEa.setBorder(null);
			tEa.setBounds(245, 467, 152, 21);
			tEa.setColumns(10);
		}
		return tEa;
	}
	private JSeparator getSeparator_2() {
		if (separator_2 == null) {
			separator_2 = new JSeparator();
			separator_2.setForeground(new Color(255, 140, 0));
			separator_2.setBackground(new Color(255, 140, 0));
			separator_2.setBounds(12, 324, 419, -6);
		}
		return separator_2;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(FoodUpdate.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					FoodSelect fs = new FoodSelect();
				}
			});
			button.setBounds(31, 700, 33, 31);
		}
		return button;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(FoodUpdate.class.getResource("/semiIcon/FoodModify.jpg")));
			lblNewLabel.setBounds(0, -1, 564, 754);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("음식 수정 삭제");
			lblNewLabel_1.setForeground(SystemColor.inactiveCaptionText);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBackground(Color.WHITE);
			lblNewLabel_1.setOpaque(true);
			lblNewLabel_1.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 18));
			lblNewLabel_1.setBounds(154, 254, 252, 31);
		}
		return lblNewLabel_1;
	}
}
