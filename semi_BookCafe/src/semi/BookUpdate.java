package semi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookUpdate extends JFrame {

	int seri;
	String header[] = { "번호", "책이름", "저자", "출판사", "수량", "구입가격" };//메뉴선택 JTable 헤더
	DefaultTableModel model = null; //메뉴선택 모델객체 생성
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
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnNewButton_2;
	
	int a;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookUpdate frame = new BookUpdate();
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
	public BookUpdate() {

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 797);
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
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton_2());
		
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);//도서목록 컬럼 사이즈조절
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		
		contentPane.add(getLblNewLabel_7());
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBackground(Color.WHITE);
			button.setIcon(new ImageIcon(BookUpdate.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();
				}
			});
			button.setBounds(29, 702, 31, 28);
		}
		return button;
	}

	private JTextField getTmName() {
		if (tmName == null) {
			tmName = new JTextField();
			tmName.setBorder(null);
			tmName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tmName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton_1.doClick();
					}
				}
			});
			tmName.setBounds(258, 236, 108, 19);
			tmName.setColumns(10);
		}
		return tmName;
	}

	private JTextField getTauthor() {
		if (tauthor == null) {
			tauthor = new JTextField();
			tauthor.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tauthor.setBorder(null);
			tauthor.setBounds(250, 270, 153, 21);
			tauthor.setColumns(10);
		}
		return tauthor;
	}

	private JTextField getTcompany() {
		if (tcompany == null) {
			tcompany = new JTextField();
			tcompany.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tcompany.setBorder(null);
			tcompany.setBounds(250, 302, 153, 21);
			tcompany.setColumns(10);
		}
		return tcompany;
	}

	private JTextField getTea() {
		if (tea == null) {
			tea = new JTextField();
			tea.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tea.setBorder(null);
			tea.setBounds(250, 334, 153, 21);
			tea.setColumns(10);
		}
		return tea;
	}

	private JTextField getTprice() {
		if (tprice == null) {
			tprice = new JTextField();
			tprice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tprice.setBorder(null);
			tprice.setBounds(250, 365, 153, 21);
			tprice.setColumns(10);
		}
		return tprice;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");// 수정
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setIcon(new ImageIcon(BookUpdate.class.getResource("/semi_icon2/체크.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					
					try {

						BookDao dao = new BookDao();
						BookVo vo = new BookVo();

						// 무결성체크하는 부분
						String aucheck = "[\\w||가-휗]{1,15}";
						String comcheck = "[\\w||가-휗]{1,15}";
						String eacheck = "\\d{1,10}";
						String pricecheck = "\\d{1,10}";

						boolean flag = Pattern.matches(aucheck, tauthor.getText());

						flag = Pattern.matches(aucheck, tauthor.getText());
						if (!flag) {
							status.setText("저자명은 한글,영어,숫자만 입력해주세요");
							tauthor.selectAll();
							tauthor.requestFocus();
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
							tauthor.setText("");
							tea.setText("");
							tprice.setText("");
							return;

						}
						flag = Pattern.matches(eacheck, tea.getText());
						if (!flag) {
							status.setText("보유 개수에는 숫자만 입력해주세요");
							tea.selectAll();
							tea.requestFocus();
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
							tauthor.setText("");
							tcompany.setText("");
							tea.setText("");
							tprice.setText("");
							return;
						}
						
						int row = table.getSelectedRow();
						if(row < 0) {
							JOptionPane.showMessageDialog(BookUpdate.this, "수정하실 책을 선택하세요.");
							return;
						}
						String b = table.getValueAt(row, 0).toString();

						// 데이터 설정
						vo.setmName(tmName.getText());
						vo.setAuthor(tauthor.getText());
						vo.setCompany(tcompany.getText());
						vo.setEa(Integer.parseInt(tea.getText()));
						vo.setPrice(Integer.parseInt(tprice.getText()));
						vo.setSeri(Integer.parseInt(b));

						int cnt = dao.update(vo);
						System.out.println(cnt);
						if (cnt > 0) {
							status.setText("수정되었습니다.");
							JOptionPane.showMessageDialog(BookUpdate.this, "도서가 수정되었습니다.");
							tmName.setText("");
							tcompany.setText("");
							tauthor.setText("");
							tea.setText("");
							tprice.setText("");
							model.setNumRows(0);
							} else {
							status.setText("에러가 발생했습니다.");
						}

					} catch (Exception ex) {

					}
				
				}
			});
			btnNewButton.setBounds(263, 396, 41, 35);
		}
		return btnNewButton;
	}

	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setForeground(new Color(240, 128, 128));
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 14));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(12, 207, 539, 19);
		}
		return status;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");// 검색버튼클릭시
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setIcon(new ImageIcon(BookUpdate.class.getResource("/semi_icon2/검색.jpg")));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.setNumRows(0);
					BookDao dao = new BookDao();
					String mName = tmName.getText();
					List<BookVo> list = dao.search(mName);

					if (list == null) {
						status.setText("흐흐");
					} else {
						for (int i = 0; i < list.size(); i++) {
							BookVo vo = list.get(i);
							DecimalFormat df = new DecimalFormat("###,###,###");
							model.addRow(new Object[] { vo.getSeri(), vo.getmName(), vo.getAuthor(), vo.getCompany(),
									vo.getEa(), df.format(vo.getPrice()) });
							status.setText("");

						}

						table.setModel(model);
						

					}
				}
			});
			btnNewButton_1.setBounds(376, 232, 31, 28);
		}
		return btnNewButton_1;
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(26, 437, 511, 193);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBackground(Color.WHITE);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			model = new DefaultTableModel(header, 0) {
				public boolean isCellEditable(int row, int com) {
					return false;
				}

			};
			
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						int row = table.getSelectedRow();
						tauthor.setText(table.getValueAt(row,2).toString());
						tcompany.setText(table.getValueAt(row,3).toString());
						tea.setText(table.getValueAt(row,4).toString());
						tprice.setText(table.getValueAt(row,5).toString().replace(",", ""));
					}
				}
			});
		}
		return table;
	}

	public JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setBackground(Color.WHITE);
			btnNewButton_2.setIcon(new ImageIcon(BookUpdate.class.getResource("/semi_icon2/BookUpdate삭제.jpg")));
			btnNewButton_2.setBorder(null);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					a = table.getSelectedRow();
					table.getValueAt(a, 0);
					String b = table.getValueAt(a, 0).toString();

					String mName = tmName.getText();
					BookDao dao = new BookDao();
					int cnt = dao.delete(mName, Integer.parseInt(b));

					if (cnt > 0) {
						status.setText("삭제되었습니다.");
						JOptionPane.showMessageDialog(BookUpdate.this, "도서가 삭제되었습니다.");
						model.setNumRows(a);

					} else
						status.setText("에러.");

					tmName.setText("");
					tauthor.setText("");
					tcompany.setText("");
					tea.setText("");
					tprice.setText("");
					tmName.requestFocus();
					tmName.selectAll();

				}
			});
			btnNewButton_2.setBounds(218, 635, 132, 44);
		}
		return btnNewButton_2;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setIcon(new ImageIcon(BookUpdate.class.getResource("/semiIcon/BookUpdate.jpg")));
			lblNewLabel_7.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_7;
	}
}
