package semi;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class BookControl extends JFrame {
	String header[] = { "번호", "책이름", "저자", "출판사", "수량", "구입가격" };//메뉴선택 JTable 헤더
	DefaultTableModel model = null; //메뉴선택 모델객체 생성
	private JPanel contentPane;
	private JTextField find;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton button;
	private JTable table;
	Connection conn = DBConn.getConn();
	private JButton btnNewButton_4;
	private JLabel lblNewLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookControl frame = new BookControl();
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
	public BookControl() {

		setVisible(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 803);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);
		contentPane.add(getFind());
		contentPane.add(getBtnNewButton());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton_1());

		contentPane.add(getBtnNewButton_3());
		contentPane.add(getBtnNewButton_4());
		contentPane.add(getLblNewLabel());

		btnNewButton.doClick();
	}

	public void search() {
		String sql = " select * from Book   where bookname like ? or author like ? "+
					 " ORDER BY bookno";
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
				for (int i = 1; i <= cnt; i++) {
					if(i == cnt) {
						DecimalFormat df = new DecimalFormat("###,###,###");
						row.add(df.format(rs.getInt(i)));
					}
					else row.add(rs.getString(i));
				}
				model.addRow(row);
			}

			table.setModel(model);
			
			table.getColumnModel().getColumn(0).setPreferredWidth(20);//도서목록 컬럼 사이즈조절
			table.getColumnModel().getColumn(1).setPreferredWidth(125);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(70);
			table.getColumnModel().getColumn(4).setPreferredWidth(20);
			table.getColumnModel().getColumn(5).setPreferredWidth(40);
			
			rs.close();
			ps.close();

		} catch (Exception ex) {

		}

	}

	private JTextField getFind() {
		if (find == null) {
			find = new JTextField();
			find.setBorder(null);
			find.setForeground(SystemColor.inactiveCaptionText);
			find.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 16));
			find.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			find.setBounds(44, 224, 352, 30);
			find.setColumns(10);
		}
		return find;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(BookControl.class.getResource("/semi_icon2/검색하기.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					search();

				}
			});
			btnNewButton.setBounds(408, 220, 120, 43);
		}

		return btnNewButton;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(46, 275, 471, 255);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(BookControl.class.getResource("/semi_icon2/BookControl수정하기.jpg")));
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookInsert bi = new BookInsert();
					bi.setVisible(true);

				}

			});
			btnNewButton_1.setBounds(136, 577, 105, 87);
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(BookControl.class.getResource("/semi_icon2/BookControl삭제하기.jpg")));
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.setBorder(null);
			btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookUpdate bu = new BookUpdate();
					bu.setVisible(true);
				}
			});
			btnNewButton_3.setBounds(361, 578, 105, 85);

		}
		return btnNewButton_3;
	}

	public JTable getTable() {
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

	public JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("");
			btnNewButton_4.setIcon(new ImageIcon(BookControl.class.getResource("/semi_icon2/이전으로.jpg")));
			btnNewButton_4.setBackground(Color.WHITE);
			btnNewButton_4.setBorder(null);
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton_4.setBounds(29, 701, 32, 30);
		}
		return btnNewButton_4;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(BookControl.class.getResource("/semiIcon/BookControl.jpg")));
			lblNewLabel.setBounds(0, -5, 564, 762);
		}
		return lblNewLabel;
	}

}
