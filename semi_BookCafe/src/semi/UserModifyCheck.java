package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserModifyCheck extends JFrame {

	public UserVo vos;//로그인 정보 필드에저장
	public String lcId;
	 public Date logIn;
	
	private JPanel contentPane;
	private JButton button;
	private JTextField cId;
	private JPasswordField cPwd;
	private JButton btnNewButton;
	private JLabel status;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserModifyCheck frame = new UserModifyCheck();
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
	public UserModifyCheck() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UserMain um = new UserMain(lcId,logIn);
				um.cId.setText(lcId);
				SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
				um.lIn.setText(sdf2.format(logIn));
				
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 803);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getButton());
		contentPane.add(getCId());
		contentPane.add(getCPwd());
		contentPane.add(getBtnNewButton());
		contentPane.add(getStatus());
		contentPane.add(getLblNewLabel_4());
	}
	
	public UserModifyCheck(String id,Date login) {//받아온값 필드에 정보저장
		this();
		this.lcId = id;
		this.logIn = login;
	}
	public UserModifyCheck(UserVo vo,String id,Date login) {
		this();
		this.lcId = id;
		this.logIn = login;
		this.vos = vo;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(UserModifyCheck.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserMain um = new UserMain(lcId,logIn);
					um.cId.setText(lcId);
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
					um.lIn.setText(sdf2.format(logIn));
					dispose();
				}
			});
			button.setBounds(30, 702, 31, 25);
		}
		return button;
	}
	private JTextField getCId() {
		if (cId == null) {
			cId = new JTextField();
			cId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cId.setBorder(null);
			cId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cId.setBounds(218, 278, 168, 23);
			cId.setColumns(10);
		}
		return cId;
	}
	private JPasswordField getCPwd() {
		if (cPwd == null) {
			cPwd = new JPasswordField();
			cPwd.setFont(new Font("굴림", Font.PLAIN, 12));
			cPwd.setBorder(null);
			cPwd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cPwd.setBounds(218, 336, 168, 23);
			cPwd.setColumns(10);
		}
		return cPwd;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(UserModifyCheck.class.getResource("/semi_icon2/다음으로2.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(Color.WHITE);
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
					char user = 'u';//회원과 관리자 비교
					char admin = 'a';
					
					UserDao dao = new UserDao();
					String uId = cId.getText();//입력한 아이디
					String uPwd = cPwd.getText();//입력한 비밀번호
					char r = dao.login(uId, uPwd);//dao의 메소드 호출
					if(r==user) {//유저라면
						vos = dao.checkAndsearch(uId, uPwd);
						UserModifyCheck2 mc = new UserModifyCheck2(vos,lcId,logIn);
						setVisible(false);						
						
					}else if(r==admin){//관리자라면
						status.setText("관리자는 관리자페이지에서 수정하세요");					
						
					}else {
						status.setText("정보가 없습니다 아이디,비밀번호를 확인해주세요");
					}
					
					
				}
			});
			btnNewButton.setBounds(239, 468, 84, 81);
		}
		return btnNewButton;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setForeground(new Color(240, 128, 128));
			status.setBounds(12, 393, 540, 15);
		}
		return status;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
			lblNewLabel_4.setIcon(new ImageIcon(UserModifyCheck.class.getResource("/semiIcon/UserModifyCheck.jpg")));
			lblNewLabel_4.setBounds(0, -9, 564, 771);
		}
		return lblNewLabel_4;
	}
}
