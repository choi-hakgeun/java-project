package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;

public class UserMain extends JFrame {
	
	public String lcId;//넘어온값을 필드에 저장
	 public Date logIn;
	 Date logOut;

	private JPanel contentPane;
	public JLabel cId;
	public JLabel lIn;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel status;
	private JLabel lblNewLabel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
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
	public UserMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logOut = new Date();//로그아웃시간 저장
				
				LoginLogVo lvo = new LoginLogVo(lcId,logIn,logOut);//아이디,로그인시간,로그아웃시간을 bd에 저장
				LoginLogDao ldao = new LoginLogDao();
				int r = ldao.insert(lvo);//결과값가져오기
				
				if(r>0) {
					UserPrice up = new UserPrice(lcId);//영수증창띄움 아이디전달
					FirstFrame ff = new FirstFrame();//첫화면으로 이동
					up.toFront();
					
					dispose();
					
					
				}else {
					status.setText("로그아웃시간 저장중 오류발생 관리자를 호출하세요");
				}
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 793);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextField());
		contentPane.add(getCId());
		contentPane.add(getLIn());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getBtnNewButton_3());
		contentPane.add(getStatus());
		contentPane.add(getLabel_2());
	}
	
	public UserMain(String id , Date da) {//넘어온값 필드에 저장
		this();
		this.lcId = id;
		this.logIn = da;
		
		
	}
	public JLabel getCId() {
		if (cId == null) {
			cId = new JLabel("\uD68C\uC6D0 \uC774\uB984");
			cId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cId.setForeground(new Color(255, 255, 255));
			cId.setBounds(449, 31, 106, 23);
		}
		return cId;
	}
	public JLabel getLIn() {
		if (lIn == null) {
			lIn = new JLabel("\uC785\uC7A5 \uC2DC\uAC04");
			lIn.setForeground(new Color(255, 255, 255));
			lIn.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			lIn.setBounds(449, 58, 106, 23);
		}
		return lIn;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(UserMain.class.getResource("/semi_icon2/UserMain음식주문.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderInsert oi = new OrderInsert(cId.getText());
					oi.setVisible(true);
				}
			});
			btnNewButton.setBounds(206, 339, 145, 131);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(UserMain.class.getResource("/semi_icon2/UserMain도서검색.jpg")));
			btnNewButton_1.setBackground(new Color(255, 255, 255));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					GuestSearch bc = new GuestSearch();
					bc.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(209, 181, 145, 131);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(UserMain.class.getResource("/semi_icon2/UserMain정보수정.jpg")));
			btnNewButton_2.setBorder(null);
			btnNewButton_2.setBackground(new Color(255, 255, 255));
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserModifyCheck um = new UserModifyCheck(lcId,logIn);//수정창띄움,수정장에 아이디,로그인시간 전달
					um.toFront();
					dispose();
				}
			});
			btnNewButton_2.setBounds(215, 504, 145, 131);
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(UserMain.class.getResource("/semi_icon2/UserMain로그아웃.jpg")));
			btnNewButton_3.setBorder(null);
			btnNewButton_3.setBackground(new Color(255, 255, 255));
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logOut = new Date();//로그아웃시간
					
					LoginLogVo lvo = new LoginLogVo(lcId,logIn,logOut);//아이디,로그인,로그아웃시간 db에 전달
					LoginLogDao ldao = new LoginLogDao();
					int r = ldao.insert(lvo);//결과값
					
					if(r>0) {
						
						UserPrice up = new UserPrice(lcId);//영수증창띄움 아이디전달
						FirstFrame ff = new FirstFrame();//첫화면으로 이동
						up.toFront();
						
						dispose();
					}else {
						status.setText("로그아웃중 오류발생 관리자를 호출하세요");
					}
				}
			});
			btnNewButton_3.setBounds(237, 688, 90, 29);
		}
		return btnNewButton_3;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setForeground(new Color(255, 140, 0));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(12, 297, 241, 15);
		}
		return status;
	}
	private JLabel getLabel_2() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(UserMain.class.getResource("/semiIcon/UserMain.jpg")));
			lblNewLabel.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBorder(null);
			textField.setBounds(-18, 93, 116, 21);
			textField.setColumns(10);
		}
		return textField;
	}
}
