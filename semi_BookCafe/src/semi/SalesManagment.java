package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.knowm.xchart.XChartPanel;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SpringLayout.Constraints;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.toedter.calendar.JDateChooser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SalesManagment extends JFrame {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DecimalFormat df = new DecimalFormat("##,###,###");
	SalesDao dao = new SalesDao();
	ButtonGroup bg = new ButtonGroup();
    
	private JPanel contentPane;
	private JButton btnSearch;
	private JButton btnSalesSearch;
	private JButton btnDelete;
	private JButton btnInsert;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnBack;
	private JRadioButton rdoDay;
	private JRadioButton rdoMonth;
	private JRadioButton rdoYear;
	private JLabel label_1;
	private JTextField txtProfit;
	private JTextField txtTotalPrice;
	private JTextField txtTotalOrder;
	private JTextField txtTotalUser;
	private JDateChooser txtDate;
	private JDateChooser txtMinDate;
	private JDateChooser txtMaxDate;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesManagment frame = new SalesManagment();
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
	public SalesManagment() {
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 794);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSearch());
		contentPane.add(getBtnSalesSearch());
		contentPane.add(getBtnDelete());
		contentPane.add(getBtnInsert());
		contentPane.add(getPanel_2());
		contentPane.add(getPanel_1_1());
		contentPane.add(getBtnBack());
		contentPane.add(getTxtDate());
		contentPane.add(getLblNewLabel_4());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dao.connClose();
			}
		});
	}
	
	// textField의 텍스트를 format을 가지고 설정해주는 함수
	private void dataFormat(Date dt) {		
		SalesVo vo = dao.search(dt);

		txtTotalUser.setText(df.format(vo.getTotalUser()));
		txtTotalOrder.setText(df.format(vo.getTotalOrder()));
		txtTotalPrice.setText(df.format(vo.getTotalPrice()));
		txtProfit.setText(df.format(vo.getProfit()));
	}
	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setIcon(new ImageIcon(SalesManagment.class.getResource("/semi_icon2/검색.jpg")));
			btnSearch.setBackground(Color.WHITE);
			btnSearch.setBorder(null);
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Date dt = txtDate.getDate(); // 날짜 정보 받아오기
					if(dt != null) {
						dataFormat(dt);
					}
					else {
						JOptionPane.showMessageDialog(SalesManagment.this, "조회할 날짜를 선택해주세요.");
					}
					
				}
			});
			btnSearch.setBounds(377, 253, 30, 30);
		}
		return btnSearch;
	}
	private JButton getBtnSalesSearch() {
		if (btnSalesSearch == null) {
			btnSalesSearch = new JButton("");
			btnSalesSearch.setIcon(new ImageIcon(SalesManagment.class.getResource("/semi_icon2/체크.jpg")));
			btnSalesSearch.setBackground(Color.WHITE);
			btnSalesSearch.setBorder(null);
			btnSalesSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Date minDate = txtMinDate.getDate();
					Date maxDate = txtMaxDate.getDate();
					
					if(minDate == null || maxDate == null) {
						JOptionPane.showMessageDialog(SalesManagment.this, "조회할 기간을 선택해주세요.");
						return;
					}
					if(maxDate.compareTo(minDate) < 0) { // minDate媛� maxDate蹂대�� �ш굅�� 媛��� �� ����
						JOptionPane.showMessageDialog(SalesManagment.this, "기간 선택값을 확인해주세요.");
						return;
					}
					String flag = "";
					List<SalesVo> listVo = null;
					SimpleDateFormat sdfflag = null;
					
					
					if(rdoDay.isSelected()) {
						flag = "일별";
						sdfflag = new SimpleDateFormat("yyyy-MM-dd");
					}
					else if(rdoMonth.isSelected()) {
						flag = "월별";
						sdfflag = new SimpleDateFormat("yyyy-MM");
					}
					else {
						flag = "년별";
						sdfflag = new SimpleDateFormat("yyyy");
					}
					listVo = dao.select(minDate, maxDate, flag);
					
					// DB에 데이터가 하나라도 존재하면 그래프 그려줌
					if(listVo.size() > 0) {
						SalesSearch ss = new SalesSearch(listVo, sdfflag, minDate, maxDate);
						ss.setVisible(true);						
					}
					else {
						JOptionPane.showMessageDialog(SalesManagment.this, "조회할 데이터가 없습니다.");
					}
				}
			});
			btnSalesSearch.setBounds(265, 606, 38, 34);
		}
		return btnSalesSearch;
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("");
			btnDelete.setIcon(new ImageIcon(SalesManagment.class.getResource("/semi_icon2/SalseMnagement기록백업.jpg")));
			btnDelete.setBackground(Color.WHITE);
			btnDelete.setBorder(null);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 엑셀파일로 모든 데이터 저장					
					String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss"));
					CreateFile cf = new CreateFile();
					boolean result = cf.createFile(currentDate);
					
					// 파일이 정상 저장되면 현재 날짜로부터 1년 이전에 저장된 데이터 삭제
					if(result) {
						int cnt = dao.delete();
						if(cnt > 0) {
							JOptionPane.showMessageDialog(SalesManagment.this, "데이터가 C:\\txtBackUp\\ 경로에 백업 되었습니다.");
						}
						else {
							JOptionPane.showMessageDialog(SalesManagment.this, "데이터가 C:\\txtBackUp\\ 경로에 백업 되었습니다.");
						}
					}
					else {
						JOptionPane.showMessageDialog(SalesManagment.this, "파일 생성 중 오류 발생");
					}
					
				}
			});
			btnDelete.setBounds(313, 440, 115, 27);
		}
		return btnDelete;
	}
	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton("");
			btnInsert.setIcon(new ImageIcon(SalesManagment.class.getResource("/semi_icon2/SalseMnagement기록저장.jpg")));
			btnInsert.setBackground(Color.WHITE);
			btnInsert.setBorder(null);
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// 숫자열 format 제거를 위해 , 제거
					int totUser = Integer.parseInt(txtTotalUser.getText().replaceAll(",", ""));
					int totPrice = Integer.parseInt(txtTotalPrice.getText().replaceAll(",", ""));
					int profit = Integer.parseInt(txtProfit.getText().replaceAll(",", ""));
					Date aDT = txtDate.getDate(); // 데이터 저장할 날 저장
					
					SalesVo vo = new SalesVo(totUser, totPrice, profit, aDT);
					int result = dao.insert(vo);
					if(result > 0) {
						JOptionPane.showMessageDialog(SalesManagment.this, "정상적으로 저장되었습니다.");
					}
					else {
						JOptionPane.showMessageDialog(SalesManagment.this, "저장 중 오류 발생");
					}
					
				}
			});
			btnInsert.setBounds(137, 440, 117, 27);
		}
		return btnInsert;
	}
	private JPanel getPanel_2() {
		if (panel == null) {
			panel = new JPanel();
			panel.setOpaque(false);
			panel.setBorder(null);
			panel.setBackground(Color.WHITE);
			panel.setBounds(259, 290, 188, 130);
			panel.setLayout(null);
			panel.add(getTxtProfit());
			panel.add(getTxtTotalPrice());
			panel.add(getTxtTotalOrder());
			panel.add(getTxtTotalUser());
		}
		return panel;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(158, 538, 249, 65);
			panel_1.setLayout(null);
			panel_1.add(getRdoDay());
			panel_1.add(getRdoMonth());
			panel_1.add(getRdoYear());
			panel_1.add(getLabel_1());
			panel_1.add(getTxtMinDate());
			panel_1.add(getTxtMaxDate());
		}
		return panel_1;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("");
			btnBack.setIcon(new ImageIcon(SalesManagment.class.getResource("/semi_icon2/이전으로.jpg")));
			btnBack.setBackground(Color.WHITE);
			btnBack.setBorder(null);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnBack.setBounds(29, 700, 32, 31);
		}
		return btnBack;
	}
	private JRadioButton getRdoDay() {
		if (rdoDay == null) {
			rdoDay = new JRadioButton("\uC77C\uBCC4");
			rdoDay.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rdoDay.setBackground(Color.WHITE);
			rdoDay.setBounds(26, 6, 54, 23);
			
			rdoDay.setSelected(true);
			bg.add(rdoDay);
		}
		return rdoDay;
	}
	private JRadioButton getRdoMonth() {
		if (rdoMonth == null) {
			rdoMonth = new JRadioButton("\uC6D4\uBCC4");
			rdoMonth.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rdoMonth.setBackground(Color.WHITE);
			rdoMonth.setBounds(102, 6, 54, 23);
			
			bg.add(rdoMonth);
		}
		return rdoMonth;
	}
	private JRadioButton getRdoYear() {
		if (rdoYear == null) {
			rdoYear = new JRadioButton("\uB144\uBCC4");
			rdoYear.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			rdoYear.setBackground(Color.WHITE);
			rdoYear.setBounds(179, 6, 54, 23);
			
			bg.add(rdoYear);
		}
		return rdoYear;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("~");
			label_1.setBounds(122, 38, 16, 15);
		}
		return label_1;
	}
	private JTextField getTxtProfit() {
		if (txtProfit == null) {
			txtProfit = new JTextField();
			txtProfit.setBorder(null);
			txtProfit.setOpaque(false);
			txtProfit.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtProfit.setEnabled(false);
			txtProfit.setColumns(10);
			txtProfit.setBounds(4, 104, 172, 21);
		}
		return txtProfit;
	}
	private JTextField getTxtTotalPrice() {
		if (txtTotalPrice == null) {
			txtTotalPrice = new JTextField();
			txtTotalPrice.setBorder(null);
			txtTotalPrice.setOpaque(false);
			txtTotalPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtTotalPrice.setEnabled(false);
			txtTotalPrice.setColumns(10);
			txtTotalPrice.setBounds(4, 70, 172, 21);
		}
		return txtTotalPrice;
	}
	private JTextField getTxtTotalOrder() {
		if (txtTotalOrder == null) {
			txtTotalOrder = new JTextField();
			txtTotalOrder.setBorder(null);
			txtTotalOrder.setOpaque(false);
			txtTotalOrder.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtTotalOrder.setEnabled(false);
			txtTotalOrder.setColumns(10);
			txtTotalOrder.setBounds(4, 38, 172, 21);
		}
		return txtTotalOrder;
	}
	private JTextField getTxtTotalUser() {
		if (txtTotalUser == null) {
			txtTotalUser = new JTextField();
			txtTotalUser.setBorder(null);
			txtTotalUser.setOpaque(false);
			txtTotalUser.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			txtTotalUser.setEnabled(false);
			txtTotalUser.setColumns(10);
			txtTotalUser.setBounds(4, 4, 172, 21);
		}
		return txtTotalUser;
	}
	private JDateChooser getTxtDate() {
		if (txtDate == null) {
			txtDate = new JDateChooser();
			txtDate.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					
					if(evt.getPropertyName() == "date") {
						btnSearch.doClick();
					}
				
				}	
			});
			txtDate.setBounds(158, 257, 208, 21);
			
			Date dt = new Date();
			txtDate.setDate(dt);
			dataFormat(dt);
		}
		return txtDate;
	}
	private JDateChooser getTxtMinDate() {
		if (txtMinDate == null) {
			txtMinDate = new JDateChooser();
			txtMinDate.setBounds(12, 35, 98, 21);
		}
		return txtMinDate;
	}
	private JDateChooser getTxtMaxDate() {
		if (txtMaxDate == null) {
			txtMaxDate = new JDateChooser();
			txtMaxDate.setBounds(139, 35, 94, 21);
		}
		return txtMaxDate;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon(SalesManagment.class.getResource("/semiIcon/SalesManagment.jpg")));
			lblNewLabel_4.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_4;
	}
}
