package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class UserCreate extends JFrame {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ButtonGroup group = new ButtonGroup();
	ButtonGroup group2 = new ButtonGroup();
	UserDao dao = new UserDao();

	private JPanel contentPane;
	private JTextField cId;
	private JPasswordField cPwd;
	private JTextField cName;
	private JTextField cPhone;
	private JTextField cBirth;
	private JTextField cDate;
	private JRadioButton cMail;
	private JRadioButton cFemail;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel status;
	private JTextField cEmail;
	private JRadioButton cUser;
	private JRadioButton cAdmin;
	private JButton btnNewButton_2;
	private JLabel status2;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCreate frame = new UserCreate();
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
	public UserCreate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				Login log = new Login();
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 794);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCId());
		contentPane.add(getCPwd());
		contentPane.add(getCName());
		contentPane.add(getCPhone());
		contentPane.add(getCBirth());
		contentPane.add(getCDate());
		contentPane.add(getCEmail());
		contentPane.add(getCMail());
		contentPane.add(getCFemail());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getStatus());
		contentPane.add(getCUser());
		contentPane.add(getCAdmin());
		contentPane.add(getBtnNewButton_2());
		contentPane.add(getStatus2());
		contentPane.add(getLblNewLabel());
	}
	private JTextField getCId() {
		if (cId == null) {
			cId = new JTextField();
			cId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cId.setBorder(null);
			cId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {//엔터키를 누르면 버튼눌러지기
						btnNewButton_2.doClick();
					}
				}
			});
			cId.setBounds(210, 257, 146, 19);
			cId.setColumns(10);
		}
		return cId;
	}
	private JPasswordField getCPwd() {
		if (cPwd == null) {
			cPwd = new JPasswordField();
			cPwd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			cPwd.setBorder(null);
			cPwd.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {//엔터키를 누르면 버튼눌러지기
						btnNewButton.doClick();
					}
				}
			});
			cPwd.setEnabled(false);
			cPwd.setBounds(212, 288, 176, 19);
			cPwd.setColumns(10);
			
		}
		return cPwd;
	}
	private JTextField getCName() {
		if (cName == null) {
			cName = new JTextField();
			cName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cName.setBorder(null);
			cName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {//엔터키를 누르면 버튼눌러지기
						btnNewButton.doClick();
					}
				}
			});
			cName.setEnabled(false);
			cName.setBounds(260, 358, 156, 21);
			cName.setColumns(10);
		}
		return cName;
	}
	private JTextField getCPhone() {
		if (cPhone == null) {
			cPhone = new JTextField();
			cPhone.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cPhone.setBorder(null);
			cPhone.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {//엔터키를 누르면 버튼눌러지기
						btnNewButton.doClick();
					}
				}
			});
			cPhone.setForeground(Color.LIGHT_GRAY);
			cPhone.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					cPhone.selectAll();
					cPhone.setForeground(Color.BLACK);
				}
			});
			
			cPhone.setEnabled(false);
			cPhone.setBounds(260, 454, 156, 21);
			cPhone.setColumns(10);
			cPhone.setText("ex)000-0000-0000");
		}
		return cPhone;
	}
	private JTextField getCBirth() {
		if (cBirth == null) {
			cBirth = new JTextField();
			cBirth.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cBirth.setBorder(null);
			cBirth.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cBirth.setForeground(Color.LIGHT_GRAY);
			cBirth.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					cBirth.selectAll();
					cBirth.setForeground(Color.BLACK);
				}
			});
			
			cBirth.setEnabled(false);
			cBirth.setBounds(260, 422, 156, 21);
			cBirth.setColumns(10);
			cBirth.setText("ex)yyyy-MM-dd");
		}
		return cBirth;
	}
	private JTextField getCDate() {
		if (cDate == null) {
			cDate = new JTextField();
			cDate.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cDate.setBorder(null);
			cDate.setHorizontalAlignment(SwingConstants.CENTER);
			cDate.setEnabled(false);
			cDate.setBounds(260, 554, 156, 21);
			cDate.setColumns(10);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date da = new Date();
			cDate.setText(sdf.format(da));
		}
		return cDate;
	}
	private JRadioButton getCMail() {
		if (cMail == null) {
			cMail = new JRadioButton("\uB0A8");
			cMail.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cMail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cMail.setBackground(new Color(255, 255, 255));
			cMail.setBounds(260, 390, 42, 23);
			cMail.setSelected(true);
			group.add(cMail);
		}
		return cMail;
	}
	private JRadioButton getCFemail() {
		if (cFemail == null) {
			cFemail = new JRadioButton("\uC5EC");
			cFemail.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cFemail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cFemail.setBackground(new Color(255, 255, 255));
			cFemail.setBounds(374, 390, 42, 23);
			group.add(cFemail);
		}
		return cFemail;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setIcon(new ImageIcon(UserCreate.class.getResource("/semi_icon2/체크.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cPwd.setBackground(Color.WHITE);//검색버튼을 다시누르면 텍스트필드 다시 흰색으로 변경
					cName.setBackground(Color.WHITE);
					cPhone.setBackground(Color.WHITE);
					cBirth.setBackground(Color.WHITE);
					cEmail.setBackground(Color.WHITE);
					if(cId.getText().contains(" ")||cPwd.getText().contains(" ")||cEmail.getText().contains(" ")||
						cBirth.getText().contains(" ")||cPhone.getText().contains(" ")||cDate.getText().contains(" ")||
						cName.getText().contains(" ")) {//문자사이 공백있으면 X
						status.setText("입력정보사이에 공백이있습니다");
						cPwd.requestFocus();
						return;
					}
					if(cId.getText().equals("")||cPwd.getText().equals("")||cEmail.getText().equals("")||
							cBirth.getText().equals("")||cPhone.getText().equals("")||cDate.getText().equals("")||
							cName.getText().equals("")) {//공백은 X
						status.setText("입력정보중에 공백이 있습니다");
						cPwd.requestFocus();
						return;
					}
					String nameCheck = "[가-휗a-zA-Z]{1,20}";//이름 무결성체크
					boolean flag = Pattern.matches(nameCheck, cName.getText());
					if(!flag) {
						status.setText("이름에는 숫자가올수 없습니다");
						cName.requestFocus();
						cName.selectAll();
						cName.setBackground(Color.MAGENTA);
						return;
					}
					
					String pWdCheck = "^[a-zA-Z0-9~!@#$%\\^&\\*\\(\\)]{8,16}";//비밀번호체크
					flag = Pattern.matches(pWdCheck, cPwd.getText());
					if(!flag) {
						status.setText("비밀번호는 숫자와문자,특수기호가포함된 8~16자리로 해주세요");
						cPwd.requestFocus();
						cPwd.selectAll();
						cPwd.setBackground(Color.MAGENTA);
						return;
					}
					
					String PhoneCheck = "01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}";//핸드폰번호체크
					flag = 	Pattern.matches(PhoneCheck, cPhone.getText());
					if(!flag) {
						status.setText("전화번호를000-0000-0000식으로 입력해주세요");
						cPhone.requestFocus();
						cPhone.selectAll();
						cPhone.setBackground(Color.MAGENTA);
						return;
					}
					String BirthCheck = "(\\d{4})-(\\d{2})-(\\d{2})";//생년월일체크
					flag = Pattern.matches(BirthCheck, cBirth.getText());
					if(!flag) {
						status.setText("생년월일은'yyyy-mm-dd'형식으로 입력해주세요");
						cBirth.requestFocus();
						cBirth.selectAll();
						cBirth.setBackground(Color.MAGENTA);
						return;
					}
					String EmailCheck = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+$";//이메일체크
					flag = Pattern.matches(EmailCheck, cEmail.getText());
					if(!flag) {
						status.setText("이메일은'~~~~@~~.~~'형식으로 입력해주세요");
						cEmail.requestFocus();
						cEmail.selectAll();
						cEmail.setBackground(Color.MAGENTA);
						return;
					}
					
					
					
					
					try {
						String uId = cId.getText();//아이디
						String uPwd = cPwd.getText();//비밀번호
						String uMail = cEmail.getText();//이메일
						Date uBirth = sdf.parse(cBirth.getText());//생년월일
						String uPhone = cPhone.getText();//핸드폰번호
						String uGender ="";//성별
						Date uDate = sdf.parse(cDate.getText());//가입날짜
						String uAdmin = "";//유저인지 관리자인지
						String uName = cName.getText();//이름
						if(cUser.isSelected()) {
							uAdmin ="u";//유저이면
						}else if(cAdmin.isSelected()) {
							uAdmin = "a";//관리자이면
						}
						if(cMail.isSelected()) {
							uGender = "남";
						}else if(cFemail.isSelected()) {
							uGender = "여";
						}
						UserVo vo = new UserVo();//dao에 보낼 vo에 정보입력
						vo.setcId(uId);
						vo.setcPwd(uPwd);
						vo.setcEmail(uMail);
						vo.setcBirth(uBirth);
						vo.setcPhone(uPhone);
						vo.setcGender(uGender);
						vo.setcDate(uDate);
						vo.setIs_Admin(uAdmin);
						vo.setcName(uName);
						int r = dao.insert(vo);
						if(r>0) {
							status.setText("회원가입이 완료되었습니다 로그인 해주세요");
						}else {
							status.setText("회원가입중 오류발생 관리자를 불러주세요");
						}
					}catch(Exception ex) {
						
					}
				}
			});
			btnNewButton.setBounds(260, 628, 42, 37);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(UserCreate.class.getResource("/semi_icon2/로그인하기.jpg")));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(new Color(255, 255, 255));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Login log = new Login();
				}
			});
			btnNewButton_1.setBounds(238, 704, 86, 29);
		}
		return btnNewButton_1;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setForeground(new Color(240, 128, 128));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(0, 584, 564, 15);
		}
		return status;
	}
	private JTextField getCEmail() {
		if (cEmail == null) {
			cEmail = new JTextField();
			cEmail.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cEmail.setBorder(null);
			cEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cEmail.setForeground(Color.LIGHT_GRAY);
			cEmail.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					cEmail.selectAll();
					cEmail.setForeground(Color.BLACK);
				}
			});
			
			cEmail.setEnabled(false);
			cEmail.setColumns(10);
			cEmail.setBounds(260, 484, 156, 21);
			cEmail.setText("ex)aaaa1111@aaaa.aaa");
		}
		return cEmail;
	}
	private JRadioButton getCUser() {
		if (cUser == null) {
			cUser = new JRadioButton("\uD68C\uC6D0");
			cUser.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cUser.setBackground(Color.WHITE);
			cUser.setBounds(228, 223, 53, 23);
			group2.add(cUser);
			cUser.setSelected(true);
		}
		return cUser;
	}
	private JRadioButton getCAdmin() {
		if (cAdmin == null) {
			cAdmin = new JRadioButton("\uAD00\uB9AC\uC790");
			cAdmin.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cAdmin.setEnabled(false);
			cAdmin.setBackground(Color.WHITE);
			cAdmin.setBounds(279, 223, 77, 23);
			group2.add(cAdmin);
		}
		return cAdmin;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setIcon(new ImageIcon(UserCreate.class.getResource("/semi_icon2/검색.jpg")));
			btnNewButton_2.setBackground(new Color(255, 255, 255));
			btnNewButton_2.setBorder(null);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cId.getText().contains(" ")) {
						status2.setText("아이디중간에 공백이있습니다");
						cId.requestFocus();
						cId.selectAll();
						return;
					}
					if(cId.getText().equals("")) {
						status2.setText("아이디가 공백입니다");
						cId.requestFocus();
						cId.selectAll();
						return;
					}
					String idcheck = "\\w{2,15}";
					boolean flag = Pattern.matches(idcheck, cId.getText());
					if(!flag) {
						status2.setText("아이디는 한글이 올수 없으며2~15자리입니다");
						cId.requestFocus();
						cId.selectAll();
						return;
					
					}
					
					String find = cId.getText();//dao에 보낼값
					String a  = dao.idCheck(find);//dao에서 값가져오기
					
					
					if(a == null) {//아이디중복이 아니라면
						status2.setText("아이디를 사용할수 있습니다");
						cPwd.requestFocus();
						cPwd.setEnabled(true);
						cName.setEnabled(true);
						cPhone.setEnabled(true);
						cBirth.setEnabled(true);
						cEmail.setEnabled(true);
					}else if(a != null){//아이디가 중복되었다면
						status2.setText("아이디가 중복되었습니다");
						cId.requestFocus();
						cPwd.setEnabled(false);
						cName.setEnabled(false);
						cPhone.setEnabled(false);
						cBirth.setEnabled(false);
						cEmail.setEnabled(false);
					}
				}
			});
			btnNewButton_2.setBounds(371, 252, 29, 29);
		}
		return btnNewButton_2;
	}
	private JLabel getStatus2() {
		if (status2 == null) {
			status2 = new JLabel("");
			status2.setFont(new Font("나눔스퀘어_ac ExtraBold", Font.PLAIN, 12));
			status2.setForeground(new Color(240, 128, 128));
			status2.setHorizontalAlignment(SwingConstants.CENTER);
			status2.setBounds(12, 326, 540, 15);
			status2.setText("아이디중복조회를 해주세요");
		}
		return status2;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(UserCreate.class.getResource("/semiIcon/UserCreate.jpg")));
			lblNewLabel.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel;
	}
}
