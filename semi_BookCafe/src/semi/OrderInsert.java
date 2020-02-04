package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class OrderInsert extends JFrame {
	OrderDao dao = new OrderDao();
	Connection conn;
	String header[] = { "메뉴명", "가격" };// 메뉴선택 JTable 헤더
	String header1[] = { "메뉴명", "주문가격", "주문수량" }; // 주문내역 JTable 헤더
	DefaultTableModel model = new DefaultTableModel(header, 0); // 메뉴선택 모델객체 생성
	DefaultTableModel model2 = new DefaultTableModel(header1, 0);// 주문내역 모델 객체생성
	DecimalFormat df = new DecimalFormat("###,###,###");// 주문금액 1000단위 콤마 찍어주기
	int tot;// 주문총액 변수작성
	List orderFood = new ArrayList<>();

	private JPanel contentPane;
	private JButton button;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_1;
	private JLabel status;
	private JButton btnNewButton_2;
	private JTextField tmId;
	private JTable table;
	private JTable table_1;
	private JTextField tSum;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderInsert frame = new OrderInsert();
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
	public OrderInsert() {
		setTitle("메뉴 주문");

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 795);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getButton());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getScrollPane_1());
		contentPane.add(getStatus());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getTmId());
		contentPane.add(getTSum());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_5());

		List<FoodVo> list = dao.menu(); // DB에서 food테이블의 foodname컬럼과 salePrice컬럼의 정보를 받아와 list에 담음
		try {
			Vector row = new Vector(list.size());
			for (int i = 0; i < list.size(); i++) {// i의 초기값을 0으로 해야 메뉴 출력 시 모든메뉴가 출력됨 1을 초기값으로 하면 1개가 부족함..;
				FoodVo vo = list.get(i);
				model.addRow(new Object[] { vo.getfName(), df.format(vo.getSalPrice()) });
				table.setModel(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		table_1.setModel(model2);

	}

	public OrderInsert(String userID) {
		this();
		tmId.setText(userID);
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBackground(Color.WHITE);
			button.setIcon(new ImageIcon(OrderInsert.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(26, 696, 31, 31);
		}
		return button;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(37, 231, 488, 145);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(OrderInsert.class.getResource("/semi_icon2/FoodInsert메뉴추가.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					status.setText("");
					int row = table.getSelectedRow(); // 메뉴선택의 JTable에서 선택한 table의 첫번째 행의 index를 반환
					if (row < 0) {// 행이 선택되지 않은경우 -1(선택안되면 리턴)
						status.setText("주문할 음식을 선택해주세요.");
						return;
					}
					Vector vc = model.getDataVector(); // 생성자에서 실행된 Jtable의 데이터를 벡터 변수vc에 대입

					Vector rowVC = (Vector) vc.get(row);// 메뉴선택의 JTable에서 선택된 첫번째 행의 index값(row)를 반환받아 모델에 들어가있는 데이터의
														// 인덱스값의 요소를 반환받음

					if (orderFood.contains(rowVC.get(0))) { // 이미 주문한 음식이 있으면 가격, 수량 추가
						String sprice = rowVC.get(1).toString();
						int price = Integer.parseInt(sprice.replace(",", ""));
						int index = orderFood.indexOf(rowVC.get(0));

						Vector oVC = model2.getDataVector();
						Vector ooVC = (Vector) oVC.get(index);

						String sfoodPrice = ooVC.get(1).toString();
						int foodPrice = Integer.parseInt(sfoodPrice.replace(",", ""));
						ooVC.set(1, df.format((foodPrice + price)));
						ooVC.set(2, (Integer.parseInt(ooVC.get(2).toString()) + 1));

						model2.insertRow(index, ooVC);
						model2.removeRow(index);

					} else {
						orderFood.add(rowVC.get(0));
						Vector addRow = new Vector();
						addRow.add(rowVC.get(0));
						addRow.add(rowVC.get(1));
						addRow.addElement(1); // 수량 추가
						model2.addRow(addRow);
					}

					table_1.setModel(model2);
					status.setText("메뉴가 추가되었습니다.");

					// 전체 가격 출력
					String price = model.getValueAt(row, 1).toString();
					price = price.replace(",", "");
					Integer num = Integer.parseInt(price);
					tot += num;
					tSum.setText(df.format(tot));
				}
			});
			btnNewButton.setBounds(25, 396, 114, 35);
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(OrderInsert.class.getResource("/semi_icon2/FoodInsert메뉴삭제.jpg")));
			btnNewButton_1.setBackground(new Color(255, 255, 255));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int row = table_1.getSelectedRow();
					if (row == -1) {
						status.setText("취소할 메뉴를 선택해 주세요");
					} else {
						orderFood.remove(model2.getValueAt(row, 0).toString());
						// 취소할 메뉴 선택후 버튼 클릭시 선택한 행의 콤마를 빼고 인트형으로 바꿔 총 금액에서 빼줌
						String price = model2.getValueAt(row, 1).toString();
						price = price.replace(",", "");
						Integer num = Integer.parseInt(price);
						tot -= num;

						model2.removeRow(row);
						table_1.setModel(model2);

						tSum.setText(df.format(tot));
						status.setText("메뉴가 정상적으로 삭제되었습니다.");
					}
				}
			});
			btnNewButton_1.setBounds(422, 394, 115, 37);
		}
		return btnNewButton_1;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBackground(Color.WHITE);
			scrollPane_1.setBounds(35, 497, 490, 152);
			scrollPane_1.setViewportView(getTable_1());
		}
		return scrollPane_1;
	}

	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel(
					"\uBA54\uB274\uB97C \uC120\uD0DD\uD558\uC5EC \uCD94\uAC00\uD558\uACE0, \uC8FC\uBB38\uD558\uAE30 \uBC84\uD2BC\uC744 \uB20C\uB7EC\uC8FC\uC138\uC694");
			status.setForeground(new Color(240, 128, 128));
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(35, 434, 496, 20);
		}
		return status;
	}

	private JButton getBtnNewButton_2() {// 주문하기 버튼
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(OrderInsert.class.getResource("/semi_icon2/FoodInsert주문하기.jpg")));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						OrderVo vo = new OrderVo();
						vo.setmId(tmId.getText());
						boolean result = false; // 정상접수시 DB에 커밋을 위한 논리변수

						Vector vc = model2.getDataVector();
						System.out.println(vc.size());
						if (vc.size() == 0) {
							status.setText("메뉴를 추가하시고 주문해 주세요^^");
							return;
						}
						for (int i = 0; i < vc.size(); i++) { // model2의 크기만큼 루핑돌리며 row(index)값을 바꿔가며 table_1에 있는 데이터 담기
						    vo.setoName((String) table_1.getValueAt(i, 0));
						    System.out.println("size" + i);
							String price = table_1.getValueAt(i, 1).toString();
							price = price.replace(",", "");
							vo.setoPrice(Integer.parseInt(price));
							vo.setOea((Integer) table_1.getValueAt(i, 2));

							// 재고확인
							int bool = dao.eaUpdate(vo);
							if (bool == 0) {								
								// 재고 개수 감소시키기
								result = false;
								JOptionPane.showMessageDialog(OrderInsert.this, "재고 수량이 부족합니다.");
								status.setText("카운터에 문의하여주세요");
								break;
							} else {
								//result = true; // dao에 입력값이 안담기면 fales를 반환하고 for문을 break								
								int cnt = dao.insert(vo);
								if (cnt > 0) {
									result = true; // dao에 입력값이 정상적으로 담기면 true를 반환
								}
								
							}
								
						}

						if (result) {
							dao.conn.commit(); // 정상접수시 커밋

							status.setText("주문이 정상적으로 접수되었습니다.");
						} else {
							dao.conn.rollback(); // 오류가 있을시 롤백
							status.setText("카운터에 문의하여주세요");
						}
						orderFood.clear(); // 주문했던 음식 내용들 다 지워줌
						tot = 0; // 총 주문금액 초기화
						tSum.setText("");
						model2.setNumRows(0);// 버튼 클릭시 주문내역 목록 초기화

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnNewButton_2.setBounds(429, 664, 111, 36);
		}
		return btnNewButton_2;
	}

	private JTextField getTmId() {
		if (tmId == null) {
			tmId = new JTextField();
			tmId.setBackground(Color.WHITE);
			tmId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			tmId.setBorder(null);
			tmId.setHorizontalAlignment(SwingConstants.CENTER);
			tmId.setEditable(false);
			tmId.setText("testID");
			tmId.setBounds(178, 463, 82, 21);
			tmId.setColumns(10);
		}
		return tmId;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {// 더블클릭시 메뉴추가
					if (e.getClickCount() == 2) {
						btnNewButton.doClick();
					}
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 행 하나만 선택가능하도록함.
			table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
			table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
			model = new DefaultTableModel(header, 0) {// JTable 더블클릭으로 내용수정 안되도록함
				public boolean isCellEditable(int row, int com) {
					return false;
				}
			};

		}
		return table;
	}

	private JTable getTable_1() {
		if (table_1 == null) {
			table_1 = new JTable();
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {// 더블클릭시 메뉴삭제
					if (e.getClickCount() == 2) {
						btnNewButton_1.doClick();
					}
				}
			});
			table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 행 하나만 선택가능하도록함.
			table_1.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
			table_1.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가

			model2 = new DefaultTableModel(header1, 0) { // JTable 더블클릭으로 내용수정 안되도록함
				public boolean isCellEditable(int row, int com) {
					return false;
				}
			};
		}
		return table_1;
	}

	private JTextField getTSum() {
		if (tSum == null) {
			tSum = new JTextField();
			tSum.setBackground(SystemColor.window);
			tSum.setBorder(null);
			tSum.setEnabled(false);
			tSum.setBounds(279, 667, 143, 30);
			tSum.setColumns(10);
		}
		return tSum;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("총 주문 금액 :");
			lblNewLabel_2.setForeground(SystemColor.inactiveCaptionText);
			lblNewLabel_2.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_2.setBounds(178, 671, 96, 20);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setIcon(new ImageIcon(OrderInsert.class.getResource("/semiIcon/OrderInsert.jpg")));
			lblNewLabel_5.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_5;
	}
}
