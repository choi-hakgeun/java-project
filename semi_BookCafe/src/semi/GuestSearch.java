package semi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuestSearch extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextField find;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	Connection conn = DBConn.getConn();
	private JButton btnNewButton_1;
	String header[] = { "도서명", "저자", "출판사", "보유수량" };// 메뉴선택 JTable 헤더
	DefaultTableModel model = null; // 메뉴선택 모델객체 생성
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestSearch frame = new GuestSearch();
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
	public GuestSearch() {

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 792);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getFind());
		contentPane.add(getBtnNewButton());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getLblNewLabel_2());

		btnNewButton.doClick();
	}

	private JTextField getFind() {
		if (find == null) {
			find = new JTextField();
			find.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					String sql = " select * from Book   where bookname like ? or author like ? ";
					String a = find.getText();
					model.setNumRows(0);
					try {

						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, "%" + a + "%");
						ps.setString(2, "%" + a + "%");

						ResultSetMetaData meta = ps.getMetaData();

						int cnt = meta.getColumnCount();

						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							Vector row = new Vector();

							row.add(rs.getString("BookName"));
							row.add(rs.getString("Author"));
							row.add(rs.getString("Publishing"));
							row.add(rs.getString("count"));

							model.addRow(row);
							}
						}catch (Exception ex) {							
						ex.printStackTrace();
						}
					
				}
			});
			find.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			find.setBorder(null);
			find.setBounds(47, 233, 349, 21);
			find.setColumns(10);
		}
		return find;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(GuestSearch.class.getResource("/semi_icon2/검색하기.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sql = " select * from Book   where bookname like ? or author like ? ";
					String a = find.getText();
					model.setNumRows(0);
					try {

						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, "%" + a + "%");
						ps.setString(2, "%" + a + "%");

						ResultSetMetaData meta = ps.getMetaData();

						int cnt = meta.getColumnCount();

						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							Vector row = new Vector();

							row.add(rs.getString("BookName"));
							row.add(rs.getString("Author"));
							row.add(rs.getString("Publishing"));
							row.add(rs.getString("count"));

							model.addRow(row);
						}

						table.setModel(model);
						table.getColumnModel().getColumn(0).setPreferredWidth(120);//주문내역 컬럼 사이즈조절
						table.getColumnModel().getColumn(1).setPreferredWidth(120);
						table.getColumnModel().getColumn(2).setPreferredWidth(120);
						table.getColumnModel().getColumn(3).setPreferredWidth(50);
						rs.close();
						ps.close();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(409, 224, 119, 36);
		}
		return btnNewButton;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBackground(new Color(255, 255, 255));
			scrollPane.setBorder(null);
			scrollPane.setBounds(47, 280, 470, 396);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));

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

	public JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(GuestSearch.class.getResource("/semi_icon2/이전으로.jpg")));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(new Color(255, 255, 255));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_1.setBounds(33, 704, 23, 23);
		}
		return btnNewButton_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(GuestSearch.class.getResource("/semiIcon/GuestSearch.jpg")));
			lblNewLabel_2.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_2;
	}
}
