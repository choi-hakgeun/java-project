package semi;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.knowm.xchart.XChartPanel;

public class SalesSearch extends JFrame {
	
	List<SalesVo> listVo = null;
	SimpleDateFormat sdfflag = null;
	DefaultTableModel model = null;
	DecimalFormat df = new DecimalFormat("##,###,###");
	Date minDate = null;
	Date maxDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private JPanel contentPane;
	private JButton button;
	private JPanel panChartUsers;
	private JPanel panChartPrice;
	private JScrollPane panTable;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblTotUser;
	private JLabel lblTotal;
	private JLabel lblTotPrice;
	private JLabel lblTotProfit;
	private JLabel label;
	private JLabel txtPeriod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesSearch frame = new SalesSearch();
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
	public SalesSearch() {
		setVisible(true);
		
		setTitle("\uB9E4\uCD9C \uC870\uD68C");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1249, 842);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblTotUser());
		contentPane.add(getLblTotPrice());
		contentPane.add(getLblTotProfit());
		contentPane.add(getLblTotal());
		contentPane.add(getTxtPeriod());
		contentPane.add(getLabel());
		contentPane.add(getButton());
		contentPane.add(getPanChartUsers());
		contentPane.add(getPanChartPrice());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLblNewLabel_1());
	}
	public SalesSearch(List<SalesVo> listVo, SimpleDateFormat sdfflag, Date minDate, Date maxDate) {
		this();
		this.listVo = listVo;
		this.sdfflag = sdfflag;
		this.minDate = minDate;
		this.maxDate = maxDate;
		
		// 조회 기간 설정
		String period = sdf.format(minDate) + " ~ " + sdf.format(maxDate);
		txtPeriod.setText(period);
		
		javax.swing.SwingUtilities.invokeLater(
		        new Runnable() {

		          @Override
		          public void run() {

		            createAndShowGUI();
		          }
		        });
	}
	private void createAndShowGUI() {
		List<String> xAxis = new ArrayList<>();
		List<Integer> yUsers = new ArrayList<>();
		List<Integer> yPrice = new ArrayList<>();
		List<Integer> yProfit = new ArrayList<>();
		
		for(int i = 0 ; i < listVo.size() ; i++) {
			SalesVo vo = listVo.get(i);
			
			// 그래프에 사용할 데이터 만들기
			xAxis.add(sdfflag.format((vo.getAdjustmentDT())));
			yUsers.add(vo.getTotalUser());
			yPrice.add(vo.getTotalPrice());
			yProfit.add(vo.getProfit());
			// 테이블에 사용할 데이터 만들기
		    model.addRow(new Object[] {xAxis.get(i), df.format(vo.getTotalUser()),
		    		df.format(yPrice.get(i)), df.format(yProfit.get(i))});
		}
		// 그래프 그리기
		panChartUsers.add(new XChartPanel(new DrawChart(xAxis, yPrice, yProfit).getChart(yUsers)));
	    panChartPrice.add(new XChartPanel(new DrawChart(xAxis, yPrice, yProfit).getChart()));
	    panChartUsers.updateUI();
	    panChartPrice.updateUI();
	    
	    // 테이블 만들기
	    table.setModel(model);
	    panTable.setViewportView(table);
	    panTable.updateUI();
	    
	    // 테이블의 모든 정보 열별로 합산
	    int cnt = model.getRowCount();
	    Vector vc = model.getDataVector(); // 테이블의 전체 데이터 가져오기
	    int totUser = 0;
	    int totPrice = 0;
	    int totProfit = 0;
	    for(int i = 0 ; i < cnt ; i++) {
	    	Vector subvc = (Vector) vc.get(i);
	    	totUser += Integer.parseInt(subvc.get(1).toString());
	    	totPrice += Integer.parseInt(subvc.get(2).toString().replace(",", ""));
	    	totProfit += Integer.parseInt(subvc.get(3).toString().replace(",", ""));
	    }
	    
	    lblTotUser.setText(totUser + "");
	    lblTotPrice.setText(df.format(totPrice));
	    lblTotProfit.setText(df.format(totProfit));
	    
	    // 스크롤 바 제일 아래로 내리기
	    panTable.getVerticalScrollBar().setValue(panTable.getVerticalScrollBar().getMaximum());
	    
	 // 컬럼 너비 설정
        int widths[] = {100, 100, 150, 150};
        for(int i = 0 ; i < 4 ; i++) {
       	 TableColumn column = table.getColumnModel().getColumn(i);
       	 column.setPreferredWidth(widths[i]);
        }
	  }
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.setIcon(new ImageIcon(SalesSearch.class.getResource("/semi_icon2/체크.jpg")));
			button.setSelectedIcon(null);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button.setBounds(635, 745, 52, 49);
		}
		return button;
	}
	private JPanel getPanChartUsers() {
		if (panChartUsers == null) {
			panChartUsers = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panChartUsers.getLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			panChartUsers.setBounds(28, 112, 640, 274);
		}
		return panChartUsers;
	}
	private JPanel getPanChartPrice() {
		if (panChartPrice == null) {
			panChartPrice = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panChartPrice.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			panChartPrice.setBounds(26, 442, 641, 273);
		}
		return panChartPrice;
	}
	private JScrollPane getScrollPane_1() {
		if (panTable == null) {
			panTable = new JScrollPane();
			panTable.setBounds(717, 112, 467, 573);
			panTable.setViewportView(getTable());
		}
		return panTable;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.getTableHeader().setReorderingAllowed(false); // 컬럼 이동 불가
	         table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	         
	         String[] colNames = {"날짜", "이용자 수", "총 매출액", "순이익"};
	         model = new DefaultTableModel(colNames, 0) {
	            // JTable 더블클릭으로 내용 수정 X
	            @Override
	            public boolean isCellEditable(int row, int com) {
	               return false;
	            }
	         };
	         
	         
		}
		return table;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(SalesSearch.class.getResource("/semiIcon/Salse.jpg")));
			lblNewLabel_1.setBounds(0, 0, 1233, 804);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblTotUser() {
		if (lblTotUser == null) {
			lblTotUser = new JLabel("총 이용자수");
			lblTotUser.setFont(new Font("굴림", Font.PLAIN, 15));
			lblTotUser.setBounds(803, 689, 99, 26);
		}
		return lblTotUser;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("총 이용자수 :            총 매출액 :                총 순이익 : ");
			lblTotal.setFont(new Font("굴림", Font.PLAIN, 15));
			lblTotal.setBounds(717, 689, 467, 26);
		}
		return lblTotal;
	}
	private JLabel getLblTotPrice() {
		if (lblTotPrice == null) {
			lblTotPrice = new JLabel("총 매출금액");
			lblTotPrice.setFont(new Font("굴림", Font.PLAIN, 15));
			lblTotPrice.setBounds(937, 689, 99, 26);
		}
		return lblTotPrice;
	}
	private JLabel getLblTotProfit() {
		if (lblTotProfit == null) {
			lblTotProfit = new JLabel("총 순이익");
			lblTotProfit.setFont(new Font("굴림", Font.PLAIN, 15));
			lblTotProfit.setBounds(1085, 689, 99, 26);
		}
		return lblTotProfit;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("조회 기간 : ");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("굴림", Font.PLAIN, 15));
			label.setBounds(693, 54, 84, 26);
		}
		return label;
	}
	private JLabel getTxtPeriod() {
		if (txtPeriod == null) {
			txtPeriod = new JLabel("기간");
			txtPeriod.setForeground(Color.WHITE);
			txtPeriod.setFont(new Font("굴림", Font.PLAIN, 15));
			txtPeriod.setBounds(769, 54, 351, 26);
		}
		return txtPeriod;
	}
}
