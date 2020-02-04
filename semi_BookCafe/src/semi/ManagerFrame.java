package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField find;
	private JButton btnNewButton;
	public JScrollPane scrollPane;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_1;
	private JTextField mId;
	private JTextField mPwd;
	private JTextField mName;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton button;
	private JTable table;
	private JTable table_1;
	public JTextField nPwd;
	private JTextField mcheck;
	String id;
	Connection conn = DBConn.getConn();
	public JTextField staa;
	String[] colName = { "NO", "ID", "비밀번호", "EMAIL", "생일", "전화번호", "성별", "가입일", "권한", "이름" };
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrame frame = new ManagerFrame();
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

	public void refresh() { // table占쏙옙 member 占쏙옙占싱븝옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占�
		String sql = " select * from member order by USERNO ";
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData meta = ps.getMetaData();
			String[] colName = { "NO", "ID", "비밀번호", "EMAIL", "생일", "전화번호", "성별", "가입일", "권한", "이름" };

			DefaultTableModel model = new DefaultTableModel(colName, 0) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			int cnt = meta.getColumnCount();
			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= cnt; i++) {
					if(i == 5 || i == 8) {
						row.add(rs.getDate(i));
					}
					else row.add(rs.getString(i));
				}
				model.addRow(row);
			}
		
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

			DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
			celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

			table.setModel(model);
			resizeColumnWidth(table);
			table.getColumn("성별").setPreferredWidth(30);
			table.getColumn("권한").setPreferredWidth(30);

			table.getColumn("NO").setCellRenderer(celAlignCenter);
			table.getColumn("ID").setCellRenderer(celAlignRight);
			table.getColumn("비밀번호").setCellRenderer(celAlignRight);
			table.getColumn("성별").setCellRenderer(celAlignRight);
			table.getColumn("권한").setCellRenderer(celAlignRight);
			table.getColumn("이름").setCellRenderer(celAlignRight);
			table.getColumn("EMAIL").setCellRenderer(celAlignRight);

			// JScrollPane aa = new JScrollPane(table);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					JScrollBar vertical = scrollPane.getVerticalScrollBar();
					vertical.setValue(vertical.getMaximum());
				}
			});
			ps.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ManagerFrame() {

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 805);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getFind());
		contentPane.add(getBtnNewButton());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getScrollPane_1());
		contentPane.add(getMId());
		contentPane.add(getMPwd());
		contentPane.add(getMName());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getBtnNewButton_3());
		contentPane.add(getButton());
		contentPane.add(getNPwd());
		contentPane.add(getMcheck());
		contentPane.add(getStaa());
		contentPane.add(getLabel_1());

		btnNewButton.doClick();
		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}

	private JTextField getFind() {
		if (find == null) {
			find = new JTextField();
			find.setForeground(SystemColor.inactiveCaptionText);
			find.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			find.setBorder(null);
			find.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
				@Override
				public void keyReleased(KeyEvent e) {
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setResizingAllowed(false);
					String sql = " select * from member where userid like ? or username like ? order by userno ";
					String bb = find.getText();
					try {
						int a = 3;
						int b = 3;
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, "%" + bb + "%");
						ps.setString(2, "%" + bb + "%");

						ResultSetMetaData meta = ps.getMetaData();

						DefaultTableModel model = new DefaultTableModel(colName, 0) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						ResultSet rs = ps.executeQuery();
						model.setRowCount(0);

						int cnt = meta.getColumnCount();
						while (rs.next()) {
							Vector row = new Vector();
							for (int i = 1; i <= cnt; i++) {
								if(i == 5 || i == 8) {
									row.add(rs.getDate(i));
								}
								else row.add(rs.getString(i));
							}
							model.addRow(row);
						}
					
						
						
						
						DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
						celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

						DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
						celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

						table.setModel(model);
						resizeColumnWidth(table);
						table.getColumn("성별").setPreferredWidth(30);
						table.getColumn("권한").setPreferredWidth(30);

						table.getColumn("NO").setCellRenderer(celAlignCenter);
						table.getColumn("ID").setCellRenderer(celAlignRight);
						table.getColumn("비밀번호").setCellRenderer(celAlignRight);
						table.getColumn("성별").setCellRenderer(celAlignRight);
						table.getColumn("권한").setCellRenderer(celAlignRight);
						table.getColumn("이름").setCellRenderer(celAlignRight);
						table.getColumn("EMAIL").setCellRenderer(celAlignRight);

						// JScrollPane aa = new JScrollPane(table);

						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						JScrollBar vertical = scrollPane.getVerticalScrollBar();
						vertical.setValue(vertical.getMaximum());

						rs.close();
						ps.close();

					} catch (Exception ex) {

					}

				}
			});
			find.setBounds(211, 193, 145, 20);
			find.setColumns(10);
		}
		return find;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");// 검색
			btnNewButton.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semi_icon2/검색.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setResizingAllowed(false);
					String sql = " select * from member where userid like ? or username like ? order by userno ";
					String bb = find.getText();
					try {
						int a = 3;
						int b = 3;
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, "%" + bb + "%");
						ps.setString(2, "%" + bb + "%");

						ResultSetMetaData meta = ps.getMetaData();

						DefaultTableModel model = new DefaultTableModel(colName, 0) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						ResultSet rs = ps.executeQuery();
						model.setRowCount(0);

						int cnt = meta.getColumnCount();
						while (rs.next()) {
							Vector row = new Vector();
							for (int i = 1; i <= cnt; i++) {
								if(i == 5 || i == 8) {
									row.add(rs.getDate(i));
								}
								else row.add(rs.getString(i));
							}
							model.addRow(row);
						}
					
						
						
						
						DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
						celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

						DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
						celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

						table.setModel(model);
						resizeColumnWidth(table);
						table.getColumn("성별").setPreferredWidth(30);
						table.getColumn("권한").setPreferredWidth(30);

						table.getColumn("NO").setCellRenderer(celAlignCenter);
						table.getColumn("ID").setCellRenderer(celAlignRight);
						table.getColumn("비밀번호").setCellRenderer(celAlignRight);
						table.getColumn("성별").setCellRenderer(celAlignRight);
						table.getColumn("권한").setCellRenderer(celAlignRight);
						table.getColumn("이름").setCellRenderer(celAlignRight);
						table.getColumn("EMAIL").setCellRenderer(celAlignRight);

						// JScrollPane aa = new JScrollPane(table);

						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						JScrollBar vertical = scrollPane.getVerticalScrollBar();
						vertical.setValue(vertical.getMaximum());

						rs.close();
						ps.close();

					} catch (Exception ex) {

					}

				}
			});
			btnNewButton.setBounds(372, 186, 30, 32);
		}
		return btnNewButton;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(42, 225, 483, 108);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");// 비밀번호수정
			btnNewButton_1.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semi_icon2/ManagerFrame비밀번호수정.jpg")));
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String pwdCheck = "[\\w!@#]{1,14}";

					boolean flag = Pattern.matches(pwdCheck, nPwd.getText());

					System.out.println(flag);
					if (flag) {// 占쏙옙橘占싫� 占쏙옙占쏙옙 占쏙옙튼클占쏙옙占싹몌옙 占시뤄옙 占쌔댐옙id占쏙옙 yes or no 占쏙옙튼占쏙옙
						int result = JOptionPane.showConfirmDialog(ManagerFrame.this, id + " 의 비밀번호를 수정하시겠습니까?", "확占쏙옙",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.CLOSED_OPTION) {

						} else if (result == JOptionPane.YES_OPTION) {
							String pwd = nPwd.getText();
							ManagerDao dao = new ManagerDao();

							ManagerVo vo = new ManagerVo();
							System.out.println(id + " : ���곗�댄��");
							vo.setcId(id);
							vo.setcPwd(pwd);
							dao.update(vo); // 占시뤄옙占쏙옙占쏙옙 클占쏙옙占쏙옙 id占쏙옙 userdao 占쏙옙占쏙옙占쏙옙트占쏙옙 占쌍는댐옙
							JOptionPane.showMessageDialog(ManagerFrame.this, "비밀번호가 수정되었습니다.", "message",
									JOptionPane.INFORMATION_MESSAGE);
							String sql = " select * from member order by USERNO ";
							try {
								PreparedStatement ps = conn.prepareStatement(sql);
								ResultSet rs = ps.executeQuery();
								ResultSetMetaData meta = ps.getMetaData();
								String[] colName = { "NO", "ID", "비밀번호", "EMAIL", "생일", "전화번호", "성별", "가입일", "권한",
										"이름" };

								DefaultTableModel model = new DefaultTableModel(colName, 0) {
									public boolean isCellEditable(int row, int column) {
										return false;
									}
								};

								int cnt = meta.getColumnCount();
								while (rs.next()) {
									Vector row = new Vector();
									for (int i = 1; i <= cnt; i++) {
										if(i == 5 || i == 8) {
											row.add(rs.getDate(i));
										}
										else row.add(rs.getString(i));
									}
									model.addRow(row);
								}
								DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
								celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

								DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
								celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

								table.setModel(model);
								resizeColumnWidth(table);
								table.getColumn("성별").setPreferredWidth(30);
								table.getColumn("권한").setPreferredWidth(30);

								table.getColumn("NO").setCellRenderer(celAlignCenter);
								table.getColumn("ID").setCellRenderer(celAlignRight);
								table.getColumn("비밀번호").setCellRenderer(celAlignRight);
								table.getColumn("성별").setCellRenderer(celAlignRight);
								table.getColumn("권한").setCellRenderer(celAlignRight);
								table.getColumn("이름").setCellRenderer(celAlignRight);
								table.getColumn("EMAIL").setCellRenderer(celAlignRight);

								// JScrollPane aa = new JScrollPane(table);

								table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

								ps.close();
								rs.close();

							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}

						}

					} else {
						System.out.println(id + " : �ㅻ�");
						JOptionPane.showMessageDialog(ManagerFrame.this, "다시 확인해주세요.", "message",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			btnNewButton_1.setBounds(322, 372, 92, 28);
		}
		return btnNewButton_1;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(43, 440, 483, 103);
			scrollPane_1.setViewportView(getTable_1());
		}
		return scrollPane_1;
	}

	private JTextField getMId() {
		if (mId == null) {
			mId = new JTextField();
			mId.setBorder(null);
			mId.setForeground(SystemColor.inactiveCaptionText);
			mId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			mId.setBounds(217, 584, 164, 18);
			mId.setColumns(10);
		}
		return mId;
	}

	private JTextField getMPwd() {
		if (mPwd == null) {
			mPwd = new JTextField();
			mPwd.setBorder(null);
			mPwd.setForeground(SystemColor.inactiveCaptionText);
			mPwd.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			mPwd.setBounds(218, 609, 163, 18);
			mPwd.setColumns(10);
		}
		return mPwd;
	}

	private JTextField getMName() {
		if (mName == null) {
			mName = new JTextField();
			mName.setBorder(null);
			mName.setForeground(SystemColor.inactiveCaptionText);
			mName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			mName.setBounds(217, 636, 164, 18);
			mName.setColumns(10);
		}
		return mName;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");// 매니저id생성
			btnNewButton_2.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semi_icon2/체크.jpg")));
			btnNewButton_2.setBackground(Color.WHITE);
			btnNewButton_2.setBorder(null);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idCheck = "\\w{2,15}";
					String pwdCheck = "^[a-zA-Z0-9~!@#$%\\\\^&\\\\*\\\\(\\\\)]{8,16}";
					String nameCheck = "[가-힣a-zA-Z]{1,20}";
					String autCheck = "[au]";
					String id = mId.getText();
					String pwd = mPwd.getText();
					String name = mName.getText();
					String check = mcheck.getText();

					boolean flag = Pattern.matches(idCheck, id) && Pattern.matches(pwdCheck, pwd)
							&& Pattern.matches(nameCheck, name) && Pattern.matches(autCheck, check);
					// Pattern.matches(nameCheck,mName.getText());
					if (flag) {
						int a = 0;
						char r;
						r = check.charAt(0);
						ManagerDao dao = new ManagerDao();
						ManagerVo vo = new ManagerVo();
						vo.setcId(id);
						vo.setcPwd(pwd);
						vo.setcName(name);
						vo.setcCheck(r);
						a = dao.Minsert(vo);
						if (a == 0) {
							JOptionPane.showMessageDialog(ManagerFrame.this, "ID가 중복되었습니다.", "message",
									JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(ManagerFrame.this, "ID가 생성되었습니다.", "message",
									JOptionPane.INFORMATION_MESSAGE);
						}
						refresh();
						ManagerFrame.this.id = mId.getText();
						// staa.setText(id);

						mId.setText("");
						mName.setText("");
						mPwd.setText("");
						mcheck.setText("");

					} else {
						JOptionPane.showMessageDialog(ManagerFrame.this, "다시 확인해주세요.", "message",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});
			btnNewButton_2.setBounds(268, 688, 41, 32);
		}
		return btnNewButton_2;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semi_icon2/삭제.jpg")));
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.setBorder(null);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ManagerDelete1 ad = new ManagerDelete1(id, ManagerFrame.this);
                    dispose();
					ad.toFront();

				}
			});
			btnNewButton_3.setBounds(356, 341, 57, 27);
		}
		return btnNewButton_3;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBackground(Color.WHITE);
			button.setBorder(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(33, 705, 34, 32);
		}
		return button;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();

			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {// table 占시뤄옙 클占쏙옙占싹몌옙 占쌔댐옙 占쏙옙占쏙옙占쏙옙 占십드변占쏙옙 id占쏙옙 占쏙옙占쏙옙
					table_1.getTableHeader().setReorderingAllowed(false);
					table_1.getTableHeader().setResizingAllowed(false);
					if (e.getButton() == 1) {
						int row = table.getSelectedRow();
						int columm = table.getSelectedColumn();
						id = table.getValueAt(row, 1).toString();// 클占쏙옙占쏙옙 id占쏙옙 sql占쏙옙占쏙옙 userid占쏙옙 占쏙옙載�

						staa.setText(id);
						nPwd.setText(table.getValueAt(row, 2).toString());
					}
					String sql = " select * from Loginlog where UserId = ?  ";
					// table占쏙옙占쏙옙 클占쏙옙占쏙옙 userid占쏙옙 占싸깍옙占싸로그곤옙 table_1占쏙옙 占싼뤄옙占쏙옙
					try {
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, id);

						ResultSetMetaData meta = ps.getMetaData();

						String[] colName1 = { "NO", "유저 ID", "로그인 시간", "로그아웃 시간", "사용시간", "이용요금" };

						DefaultTableModel model = new DefaultTableModel(colName1, 0) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};

						ResultSet rs = ps.executeQuery();
						model.setRowCount(0);

						int cnt = meta.getColumnCount();

						while (rs.next()) {
							Vector row = new Vector();
							for (int i = 1; i <= cnt; i++) {
								 row.add(rs.getString(i));
							}
							model.addRow(row);
						}
						DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
						celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

						DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
						celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

						table_1.setModel(model);
						resizeColumnWidth(table_1);

						table_1.getColumn("NO").setCellRenderer(celAlignCenter);
						table_1.getColumn("유저 ID").setCellRenderer(celAlignRight);
						table_1.getColumn("사용시간").setCellRenderer(celAlignRight);
						table_1.getColumn("이용요금").setCellRenderer(celAlignRight);

						// JScrollPane aa = new JScrollPane(table);

						table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						table_1.setModel(model);
						rs.close();
						ps.close();
					} catch (Exception ex) {

					}

				}
			});
		}
		return table;
	}

	private JTable getTable_1() {
		if (table_1 == null) {
			table_1 = new JTable();
		}
		return table_1;
	}

	private JTextField getNPwd() {
		if (nPwd == null) {
			nPwd = new JTextField();
			nPwd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					nPwd.selectAll();
				}
			});
			nPwd.setForeground(SystemColor.inactiveCaptionText);
			nPwd.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			nPwd.setBorder(null);
			nPwd.setBounds(164, 376, 145, 20);
			nPwd.setColumns(10);
		}
		return nPwd;
	}

	private JTextField getMcheck() {
		if (mcheck == null) {
			mcheck = new JTextField();
			mcheck.setBorder(null);
			mcheck.setForeground(SystemColor.inactiveCaptionText);
			mcheck.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			mcheck.setBounds(217, 661, 164, 18);
			mcheck.setColumns(10);
		}
		return mcheck;
	}

	public JTextField getStaa() {
		if (staa == null) {
			staa = new JTextField();
			staa.setForeground(SystemColor.inactiveCaptionText);
			staa.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 13));
			staa.setBorder(null);
			staa.setBounds(267, 345, 77, 20);
			staa.setColumns(10);
		}
		return staa;
	}

	private JLabel getLabel_1() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ManagerFrame.class.getResource("/semiIcon/ManagerFrame.jpg")));
			lblNewLabel.setBounds(0, -6, 672, 767);
		}
		return lblNewLabel;
	}

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

}
