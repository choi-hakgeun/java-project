package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class OrderUpdate extends JFrame {
	OrderDao dao = new OrderDao();
	String header[] = { "No", "음식명", "수량", "주문날짜", "주문가격", "주문자ID" }; // 주문내역 JTable header작성
	DefaultTableModel model = new DefaultTableModel(header, 0); // 테이블 모델 객체생성	
	DecimalFormat df = new DecimalFormat("###,###,###");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private JPanel contentPane;
	private JButton button;
	private JScrollPane scrollPane;
	private JTextField toNum;
	private JButton btnNewButton;
	private JTextField tmId;
	private JTextField toDate;
	private JTextField toName;
	private JTextField toea;
	private JTextField toPrice;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel status;
	private JTable table;
	private JTextField txtid;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_4;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUpdate frame = new OrderUpdate();
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
	public OrderUpdate() {
		setTitle("\uC74C\uC2DD \uC8FC\uBB38 \uB0B4\uC5ED \uAD00\uB9AC");
		addWindowListener(new WindowAdapter() {// 윈도우 창 오픈시 커서이동
			@Override
			public void windowOpened(WindowEvent arg0) {
				txtid.requestFocus();// 검색 텍스트필드에 커서이동
				txtid.selectAll();// 검색 텍스트필드 전체선택
			}
		});		
		
		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 792);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getButton());
		contentPane.add(getScrollPane());
		contentPane.add(getToNum());
		contentPane.add(getBtnNewButton());
		contentPane.add(getTmId());
		contentPane.add(getToDate());
		contentPane.add(getToName());
		contentPane.add(getToea());
		contentPane.add(getToPrice());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getStatus());

		List<OrderVo> list = dao.OrderList(); // 프레임시작시 모든 주문내역 조회

		try {
			Vector row = new Vector(list.size());

			for (int i = 1; i < list.size(); i++) {
				OrderVo vo = list.get(i);
				model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
						df.format(vo.getoPrice()), vo.getmId() });// model.addRow end

			} // for end			

			table.setModel(model);
			contentPane.add(getTxtid());
			contentPane.add(getBtnNewButton_3());
			contentPane.add(getLblNewLabel_4());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(20);//주문내역 컬럼 사이즈조절
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		SwingUtilities.invokeLater(new Runnable() {//주문내역관리 스크롤바 맨 밑으로
			@Override
			public void run() {
				JScrollBar vertical = scrollPane.getVerticalScrollBar();
				vertical.setValue(vertical.getMaximum());
			}
		});

	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBackground(Color.WHITE);
			button.setBorder(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(31, 700, 34, 30);
		}
		return button;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 248, 472, 158);
			scrollPane.setViewportView(getTable());

		}
		return scrollPane;
	}

	private JTextField getToNum() {
		if (toNum == null) {
			toNum = new JTextField();
			toNum.setForeground(SystemColor.inactiveCaptionText);
			toNum.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			toNum.setBorder(null);
			toNum.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {					
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton_3.doClick();
					}
				}
			});
			toNum.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					toNum.requestFocus();
					toNum.selectAll();
				}
			});
			toNum.setBounds(275, 452, 102, 19);
			toNum.setColumns(10);
		}
		return toNum;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton(""); // 주문내역조회 버튼
			btnNewButton.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semi_icon2/OrderUpdate주문내역검색.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					model.setNumRows(0);// 버튼 클릭시 주문내역 목록 초기화
					
					

					String find = txtid.getText();

					if (find.equals("")) {// 주문내역 검색 textField 공백체크
						status.setText("주문번호, 회원ID, 또는 음식명을 검색하세요.");

						List<OrderVo> list = dao.OrderList();
						Vector row = new Vector(list.size());

						for (int i = 1; i < list.size(); i++) {
							OrderVo vo = list.get(i);
							model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });

						} // for end
						table.setModel(model);

						txtid.setText("");

						txtid.requestFocus();
						txtid.selectAll();
						return;
					} // if end

					try {
						int oNum = Integer.parseInt(find);
						OrderVo vo = dao.search(oNum);
						if (vo == null) {
							status.setText("주문번호, 회원ID 또는 음식명을 검색하세요.");
						} else {
							model.addRow(new Object[] { oNum, vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });
						}
					} catch (Exception ex) {
						List<OrderVo> list = dao.select(find);

						for (OrderVo vo : list) {
							model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });
						}

					} finally {
						table.setModel(model);
					}
					
				}
			});			

			btnNewButton.setBounds(408, 196, 117, 41);
		}
		return btnNewButton;
	}

	private JTextField getTmId() {
		if (tmId == null) {
			tmId = new JTextField();
			tmId.setForeground(SystemColor.inactiveCaptionText);
			tmId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tmId.setBorder(null);
			tmId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tmId.requestFocus();
					tmId.selectAll();
				}
			});
			tmId.setBounds(275, 485, 134, 19);
			tmId.setColumns(10);
		}
		return tmId;
	}

	private JTextField getToDate() {
		if (toDate == null) {
			toDate = new JTextField();
			toDate.setForeground(SystemColor.inactiveCaptionText);
			toDate.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			toDate.setBorder(null);
			toDate.setEnabled(false);
			toDate.setBounds(275, 519, 134, 19);
			toDate.setColumns(10);
		}
		return toDate;
	}

	private JTextField getToName() {
		if (toName == null) {
			toName = new JTextField();
			toName.setForeground(SystemColor.inactiveCaptionText);
			toName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			toName.setBorder(null);
			toName.setEnabled(false);
			toName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					toName.requestFocus();
					toName.selectAll();
				}
			});
			toName.setBounds(275, 553, 134, 19);
			toName.setColumns(10);
		}
		return toName;
	}

	private JTextField getToea() {
		if (toea == null) {
			toea = new JTextField();
			toea.setForeground(SystemColor.inactiveCaptionText);
			toea.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			toea.setBorder(null);
			toea.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					toea.requestFocus();
					toea.selectAll();
				}
			});
			toea.setBounds(275, 586, 134, 19);
			toea.setColumns(10);
		}
		return toea;
	}

	private JTextField getToPrice() {
		if (toPrice == null) {
			toPrice = new JTextField();
			toPrice.setForeground(SystemColor.inactiveCaptionText);
			toPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			toPrice.setBorder(null);
			toPrice.setEnabled(false);
			toPrice.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					toPrice.requestFocus();
					toPrice.selectAll();
				}
			});
			toPrice.setBounds(275, 619, 134, 19);
			toPrice.setColumns(10);
		}
		return toPrice;
	}

	private JButton getBtnNewButton_1() {// 수정버튼
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semi_icon2/OrderUpdate수정하기.jpg")));
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderVo vo = new OrderVo();
					if (tmId.getText().isEmpty() || toName.getText().isEmpty() // 공백체크
							|| toea.getText().isEmpty() || toPrice.getText().isEmpty()) {
						status.setText("수정 내용 중 공백이 있습니다.");

					} else { // 공백 체크 후 else 시작
						try {
							vo.setoNum(Integer.parseInt(toNum.getText()));
							vo.setmId(tmId.getText());
							try {
								vo.setoDate(sdf.parse(toDate.getText()) );
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							vo.setoName(toName.getText());

							int i = Integer.parseInt(toea.getText());// 수량 2자리 체크
							if (i > 99) {
								status.setText("수량은 두자리까지 수정이 가능합니다.");
							} else {
								String price = toPrice.getText();
								price = price.replace(",", "");
								
								int unitPrice = Integer.parseInt(price) / (Integer)model.getValueAt(0, 2); // 기존 주문 가격 / 기존 주문 개수
								
								int changePrice = unitPrice * i; // 한개 가격 * 수정한 주문 개수			
								
								vo.setoPrice(changePrice);
								vo.setOea(i);

								model.setNumRows(0);// 버튼 클릭시 주문내역 목록 초기화
								int cnt = dao.update(vo);
								if (cnt > 0) {
									status.setText("정상적으로 수정되었습니다.");
									toPrice.setText(df.format(changePrice));

									//수정 후 수정된 내용 주문내역목록에 보여주기
									model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
											df.format(vo.getoPrice()), vo.getmId() });
									
									table.setModel(model);

								} else {
									status.setText("수정 중 오류가 발생 되었습니다.");
								}
							}

						} catch (NumberFormatException ex) {
							status.setText("수량과 주문 금액은 숫자만 입력이 가능합니다.");
						}
					}
				}
			});
			btnNewButton_1.setBounds(160, 666, 110, 24);
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_2() {// 삭제버튼
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semi_icon2/OrderUpdate삭제하기.jpg")));
			btnNewButton_2.setBackground(Color.WHITE);
			btnNewButton_2.setBorder(null);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int oNum = Integer.parseInt(toNum.getText());
					int cnt = dao.delete(oNum);

					if (cnt > 0) {
						status.setText("주문내역이 삭제 되었습니다.");
						List<OrderVo> list = dao.OrderList(); // 프레임 시작시 모든 주문내역 조회

						model.setNumRows(0);
						try {
							Vector row = new Vector(list.size());

							for (int i = 1; i < list.size(); i++) {
								OrderVo vo = list.get(i);
								model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
										vo.getoPrice(), vo.getmId() });

							} // for end
							table.setModel(model);
						} catch (Exception qq) {
							qq.printStackTrace();
						}
					} else {
						status.setText("주문내역 삭제 중 오류가 발생하였습니다.");
					}
					toNum.setText("");
					tmId.setText("");
					toDate.setText("");
					toName.setText("");
					toea.setText("");
					toPrice.setText("");

				}
			});
			btnNewButton_2.setBounds(296, 666, 110, 24);
		}
		return btnNewButton_2;
	}

	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("\uC8FC\uBB38 \uBC88\uD638\uB97C \uAC80\uC0C9\uD558\uC138\uC694");
			status.setForeground(new Color(240, 128, 128));
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(140, 642, 295, 23);
		}
		return status;
	}

	private JTable getTable() {
		if (table == null)  {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {//메뉴 클릭시 텍스트필드에 자동으로 업데이트 되도록.
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					for(int i = 0; i<table.getSelectedColumnCount(); i++) {
						toNum.setText(table.getModel().getValueAt(row, i).toString());
						btnNewButton_3.doClick();
					}
					
				}
			});
			DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();//컬럼명 가운대정렬
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//컬럼명 가운대정렬			
			
			 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//클릭한 행 하나만 선택됨.
			 table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		     table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가		
			model = new DefaultTableModel(header,0) {//JTable 더블클릭으로 내용수정 안되도록함				
				public boolean isCellEditable(int row, int com) {
					return false;
				    }
				};
			
		}
		return table;
	}

	private JTextField getTxtid() {
		if (txtid == null) {
			txtid = new JTextField();
			txtid.setBorder(null);
			txtid.setForeground(SystemColor.inactiveCaptionText);
			txtid.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 15));
			txtid.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					model.setNumRows(0);// 버튼 클릭시 주문내역 목록 초기화

					String find = txtid.getText();

					try {
						int oNum = Integer.parseInt(find);
						OrderVo vo = dao.search(oNum);
						if (vo == null) {
							status.setText("주문번호, 회원ID 또는 음식명을 검색하세요.");
						} else {
							model.addRow(new Object[] { oNum, vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });
						}
					} catch (Exception ex) {
						List<OrderVo> list = dao.select(find);

						for (OrderVo vo : list) {
							model.addRow(new Object[] { vo.getoNum(), vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });
						}

					} finally {
						table.setModel(model);
					}
				}
			});
			txtid.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					txtid.requestFocus();
					txtid.selectAll();
				}
			});
			txtid.setText(
					"\uC8FC\uBB38\uBC88\uD638, \uD68C\uC6D0ID, \uB610\uB294 \uC74C\uC2DD\uBA85\uC744 \uAC80\uC0C9\uD574\uC8FC\uC138\uC694.");
			txtid.setBounds(46, 206, 350, 21);
			txtid.setColumns(10);
			txtid.requestFocus();

		}
		return txtid;
	}

	private JButton getBtnNewButton_3() {// 주문번호검색 버튼
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semi_icon2/검색.jpg")));
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.setBorder(null);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					model.setNumRows(0);// 주문내역 목록 초기화
					txtid.setText("");

					try {
						int oNum = Integer.parseInt(toNum.getText());
						OrderVo vo = dao.search(oNum);
						if (vo == null) {
							status.setText("주문번호를 검색하세요.");
						} else {
							tmId.setText(vo.getmId());
							toDate.setText(sdf.format(vo.getoDate()));
							toName.setText(vo.getoName());
							toea.setText(String.valueOf(vo.getOea()));
							toPrice.setText(df.format(vo.getoPrice()));

							model.addRow(new Object[] { oNum, vo.getoName(), vo.getOea(), sdf.format(vo.getoDate()),
									df.format(vo.getoPrice()), vo.getmId() });
						}
					} catch (Exception ex) {
						status.setText("주문번호를 숫자로 입력해 주세요.");
						tmId.setText("");
						toDate.setText("");
						toName.setText("");
						toea.setText("");
						toPrice.setText("");

						toNum.requestFocus();
					}
					table.setModel(model);

				}
			});
			btnNewButton_3.setBounds(388, 447, 34, 29);
		}
		return btnNewButton_3;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon(OrderUpdate.class.getResource("/semiIcon/OrderUpdate.jpg")));
			lblNewLabel_4.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_4;
	}
}
