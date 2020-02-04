package semi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class BookInsert extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextField tmName;
	private JTextField tauthor;
	private JTextField tcompany;
	private JTextField tea;
	private JTextField tprice;
	private JButton btnNewButton;
	private JLabel status;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInsert frame = new BookInsert();
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
	public BookInsert() {

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 798);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getButton());
		contentPane.add(getTmName());
		contentPane.add(getTauthor());
		contentPane.add(getTcompany());
		contentPane.add(getTea());
		contentPane.add(getTprice());
		contentPane.add(getBtnNewButton());
		contentPane.add(getStatus());
		contentPane.add(getLblNewLabel_7());

	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBackground(Color.WHITE);
			button.setBorder(null);
			button.setIcon(new ImageIcon(BookInsert.class.getResource("/semi_icon2/이전으로.jpg")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();

				}
			});
			button.setBounds(29, 697, 39, 35);
		}
		return button;
	}

	private JTextField getTmName() {
		if (tmName == null) {
			tmName = new JTextField();
			tmName.setForeground(SystemColor.inactiveCaptionText);
			tmName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			tmName.setBorder(null);
			tmName.setBounds(252, 335, 137, 19);
			tmName.setColumns(10);
		}
		return tmName;
	}

	private JTextField getTauthor() {
		if (tauthor == null) {
			tauthor = new JTextField();
			tauthor.setForeground(SystemColor.inactiveCaptionText);
			tauthor.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			tauthor.setBorder(null);
			tauthor.setBounds(252, 368, 137, 21);
			tauthor.setColumns(10);
		}
		return tauthor;
	}

	private JTextField getTcompany() {
		if (tcompany == null) {
			tcompany = new JTextField();
			tcompany.setForeground(SystemColor.inactiveCaptionText);
			tcompany.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			tcompany.setBorder(null);
			tcompany.setBounds(252, 404, 137, 21);
			tcompany.setColumns(10);
		}
		return tcompany;
	}

	private JTextField getTea() {
		if (tea == null) {
			tea = new JTextField();
			tea.setForeground(SystemColor.inactiveCaptionText);
			tea.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			tea.setBorder(null);
			tea.setBounds(252, 434, 137, 21);
			tea.setColumns(10);
		}
		return tea;
	}

	private JTextField getTprice() {
		if (tprice == null) {
			tprice = new JTextField();
			tprice.setForeground(SystemColor.inactiveCaptionText);
			tprice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			tprice.setBorder(null);
			tprice.setBounds(252, 464, 137, 21);
			tprice.setColumns(10);
		}
		return tprice;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(""); // 입력버튼 누를시
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.setIcon(new ImageIcon(BookInsert.class.getResource("/semi_icon2/체크.jpg")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						BookDao dao = new BookDao();
						BookVo vo = new BookVo();

						String namecheck = "[\\w||가-휗]{1,15}"; // 무결성체크하는 부분
						String aucheck = "[\\w||가-휗]{1,15}";
						String comcheck = "[\\w||가-휗]{1,15}";
						String eacheck = "\\d{1,10}";
						String pricecheck = "\\d{1,10}";

						boolean flag = Pattern.matches(namecheck, tmName.getText());
						if (!flag) {
							status.setText("도서명은 한글,영어,숫자만 입력해주세요");
							tmName.selectAll();
							tmName.requestFocus();
							tauthor.setText("");
							tcompany.setText("");
							tea.setText("");
							tprice.setText("");
							return;
						}
						flag = Pattern.matches(aucheck, tauthor.getText());
						if (!flag) {
							status.setText("저자명은 한글,영어,숫자만 입력해주세요");
							tauthor.selectAll();
							tauthor.requestFocus();
							tmName.setText("");
							tcompany.setText("");
							tea.setText("");
							tprice.setText("");

							return;
						}
						flag = Pattern.matches(comcheck, tcompany.getText());
						if (!flag) {
							status.setText("출판사는 한글,영어,숫자만 입력해주세요");
							tcompany.selectAll();
							tcompany.requestFocus();
							tmName.setText("");
							tauthor.setText("");
							tea.setText("");
							tprice.setText("");
							return;

						}
						flag = Pattern.matches(eacheck, tea.getText());
						if (!flag) {
							status.setText("수량에는 숫자만 입력해주세요");
							tea.selectAll();
							tea.requestFocus();
							tmName.setText("");
							tauthor.setText("");
							tcompany.setText("");
							tprice.setText("");
							return;

						}
						flag = Pattern.matches(pricecheck, tprice.getText());
						if (!flag) {
							status.setText("가격에는 숫자만 입력해주세요");
							tprice.selectAll();
							tprice.requestFocus();
							tmName.setText("");
							tauthor.setText("");
							tcompany.setText("");
							tea.setText("");
							tprice.setText("");
							return;
						}
						vo.setmName(tmName.getText());
						vo.setAuthor(tauthor.getText());
						vo.setCompany(tcompany.getText());
						vo.setEa(Integer.parseInt(tea.getText()));
						vo.setPrice(Integer.parseInt(tprice.getText()));

						int cnt = dao.insert(vo);
						if (cnt > 0) {
							status.setText("추가되었습니다.");
							JOptionPane.showMessageDialog(BookInsert.this, "도서 목록에 추가되었습니다.");	
							tmName.setText("");
							tauthor.setText("");
							tcompany.setText("");
							tea.setText("");
							tprice.setText("");
						
						} else {
							status.setText("에러가 발생했습니다.");
						}

					} catch (Exception ex) {

					}

				}
			});
			btnNewButton.setBounds(262, 530, 48, 41);
		}
		return btnNewButton;
	}

	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setForeground(new Color(240, 128, 128));
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(12, 495, 540, 28);
		}
		return status;
	}

	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setIcon(new ImageIcon(BookInsert.class.getResource("/semiIcon/BookInsert.jpg")));
			lblNewLabel_7.setBounds(0, -4, 564, 760);
		}
		return lblNewLabel_7;
	}
}

