package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
	
	public String lcId;//다른프레임에 아이디를 전달
	public Date logIn;//다른프레임에 로그인시간 전달

	private JPanel contentPane;
	private JTextField cId;
	private JPasswordField cPwd;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel status;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FirstFrame ff = new FirstFrame();
				dispose();
			}
		});
		
		
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 799);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCId());
		contentPane.add(getCPwd());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getBtnNewButton_3());
		contentPane.add(getStatus());
		contentPane.add(getLblNewLabel_8());
	}
	private JTextField getCId() {
		if (cId == null) {
			cId = new JTextField();
			cId.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 16));
			cId.setBorder(null);
			cId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cId.setBounds(215, 273, 167, 34);
			cId.setColumns(10);
		}
		return cId;
	}
	private JPasswordField getCPwd() {
		if (cPwd == null) {
			cPwd = new JPasswordField();
			cPwd.setBorder(null);
			cPwd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cPwd.setBounds(215, 330, 167, 34);
			cPwd.setColumns(10);
		}
		return cPwd;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setIcon(new ImageIcon(Login.class.getResource("/semi_icon2/다음으로2.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(cId.getText().contains(" ")||cPwd.getText().contains(" ")) {
						status.setText("입력항목 중간에 공백이 있습니다");
						return;
					}
					if(cId.getText().equals("")||cPwd.getText().equals("")) {
						status.setText("입력항목에 공백이 있습니다");
						return;
					}
					
					char user = 'u';//db에서 가져온 관리자를 비교하기위해
					char admin = 'a';
				
					UserDao dao = new UserDao();
					lcId = cId.getText();//아이디를 필드에 저장
					String uPwd = cPwd.getText();
					char r = dao.login(lcId, uPwd);
					if(r==user) {//유저라면 유저창을
					
					
						//로그인시간을 다른프레임에 보내기위해-
						logIn = new Date();
						UserMain um = new UserMain(lcId,logIn);//유저메인에 필드값 보내기
						
					
						SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
						um.lIn.setText(sdf.format(logIn));//유저메인의 아이디상태
						um.cId.setText(lcId);//유저메인의 로그인시간 상태
						setVisible(false);		
						//-----------------------------------
					}else if(r==admin || r == 'm'){//어드민이면 관리자창을
						AdminMain am = new AdminMain();
						setVisible(false);						
						
					}else {
						status.setText("아이디와 비밀번호를 확인해주세요");
					}
				}
			});
			btnNewButton.setBounds(241, 466, 85, 84);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setIcon(new ImageIcon(Login.class.getResource("/semi_icon2/회원가입.jpg")));
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserCreate uc = new UserCreate();
					dispose();
				}
			});
			btnNewButton_1.setBounds(249, 600, 72, 23);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(Login.class.getResource("/semi_icon2/아이디찾기.jpg")));
			btnNewButton_2.setBorder(null);
			btnNewButton_2.setBackground(Color.WHITE);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FindId fi = new FindId();
					dispose();
				}
			});
			btnNewButton_2.setBounds(221, 632, 132, 23);
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.setIcon(new ImageIcon(Login.class.getResource("/semi_icon2/이전으로.jpg")));
			btnNewButton_3.setBorder(null);
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FirstFrame ff = new FirstFrame();
					dispose();
				}
			});
			btnNewButton_3.setBounds(22, 698, 38, 34);
		}
		return btnNewButton_3;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status.setForeground(new Color(240, 128, 128));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(22, 385, 530, 15);
		}
		return status;
	}
	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("");
			lblNewLabel_8.setIcon(new ImageIcon(Login.class.getResource("/semiIcon/Login.jpg")));
			lblNewLabel_8.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_8;
	}
}
