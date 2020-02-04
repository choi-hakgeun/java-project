package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;

public class FoodSelect extends JFrame {

	Connection conn = DBConn.getConn();
	
	String header[] = {"No", "음식 명", "수량", "입고 금액", "판매 금액", "판매중"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	DecimalFormat df = new DecimalFormat("##,###,###");
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JButton btnSearch;
	private JTextField tFind;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton button;
	private JTable table;
	private JLabel lblNewLabel_3;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodSelect frame = new FoodSelect();
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
	public FoodSelect() {
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 812);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getBtnSearch());
		contentPane.add(getTFind());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnUpdate());
		contentPane.add(getButton());
		
		// ȭ�� ó�� Ʋ�� ��� ����� �ٷ� ����
				FoodDao dao = new FoodDao();
				List<FoodVo> list = dao.list();
				try {
						Vector row = new Vector(list.size());
						for(int i = 0; i <list.size(); i++) {
							FoodVo vo = list.get(i);
							model.addRow(new Object[] {
									vo.getfNo(), vo.getfName(), vo.getfEa(), 
									df.format(vo.getInPrice()), df.format(vo.getSalPrice()), vo.getSales()
							});
						}		
					table.setModel(model);
					contentPane.add(getLblNewLabel_3());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				table.getColumnModel().getColumn(0).setPreferredWidth(20);//주문내역 컬럼 사이즈조절
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				table.getColumnModel().getColumn(2).setPreferredWidth(20);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(50);
				table.getColumnModel().getColumn(5).setPreferredWidth(20);

	}
	
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(51, 269, 462, 223);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setBorder(null);
			btnSearch.setBackground(Color.WHITE);
			btnSearch.setIcon(new ImageIcon(FoodSelect.class.getResource("/semi_icon2/검색.jpg")));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // �˻� ��������
					model.setNumRows(0);
					FoodDao dao = new FoodDao();
					String f = tFind.getText();
					List<FoodVo> list = dao.Select(f);
					try {						
						Vector row = new Vector(list.size());
						for(int i = 0; i <list.size(); i++) {
							FoodVo vo = list.get(i);
							model.addRow(new Object[] {
									vo.getfNo(), vo.getfName(), vo.getfEa(), 
									df.format(vo.getInPrice()), df.format(vo.getSalPrice()), vo.getSales()
							});
						}
					table.setModel(model);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				}
			});
			btnSearch.setBounds(488, 203, 34, 31);
		}
		return btnSearch;
	}
	private JTextField getTFind() {
		if (tFind == null) {
			tFind = new JTextField();
			tFind.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 15));
			tFind.setBorder(null);
			tFind.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					model.setNumRows(0);
					FoodDao dao = new FoodDao();
					String f = tFind.getText();
					List<FoodVo> list = dao.Select(f);
					try {						
						Vector row = new Vector(list.size());
						for(int i = 0; i <list.size(); i++) {
							FoodVo vo = list.get(i);
							model.addRow(new Object[] {
									vo.getfNo(), vo.getfName(), vo.getfEa(), 
									df.format(vo.getInPrice()), df.format(vo.getSalPrice()), vo.getSales()
							});
						}
					table.setModel(model);
				} catch (Exception ex) {
					ex.printStackTrace();
			   }
			  }
			});
			tFind.setBounds(41, 203, 433, 24);
			tFind.setColumns(10);
		}
		return tFind;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("");
			btnAdd.setIcon(new ImageIcon(FoodSelect.class.getResource("/semi_icon2/FoodSelect수정하기.jpg")));
			btnAdd.setBorder(null);
			btnAdd.setBackground(Color.WHITE);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // �߰� ��ư�� �������� 
					dispose();
					FoodInsert panel = new FoodInsert();
					panel.toFront();
				}
			});
			btnAdd.setBounds(101, 553, 132, 85);
		}
		return btnAdd;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("");
			btnUpdate.setIcon(new ImageIcon(FoodSelect.class.getResource("/semi_icon2/FoodSelect삭제하기.jpg")));
			btnUpdate.setBorder(null);
			btnUpdate.setBackground(Color.WHITE);
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // ����/���� ��ư�� ��������
					dispose();
					FoodUpdate panel = new FoodUpdate();
					panel.toFront();
				}
			});
			btnUpdate.setBounds(316, 553, 151, 85);
		}
		return btnUpdate;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(FoodSelect.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { // back ��ư�� ��������
					dispose();
				}
			});
			button.setBounds(27, 699, 34, 31);
		}
		return button;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			model = new DefaultTableModel(header, 0) {
				public boolean isCellEditable(int row, int com) {
					return false;
				}
			};
		}
		return table;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(FoodSelect.class.getResource("/semiIcon/FoodSelect.jpg")));
			lblNewLabel_3.setBounds(0, -1, 564, 754);
		}
		return lblNewLabel_3;
	}
}
