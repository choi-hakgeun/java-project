package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserModify extends JFrame {
	
	ButtonGroup group = new ButtonGroup();
	UserDao dao = new UserDao();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String lcId;//전달받고 보낼 아이디
	public Date logIn;
	public UserVo vos;

	private JPanel contentPane;
	public JTextField cId;
	public JPasswordField cPwd;
	public JTextField cName;
	public JTextField cPhone;
	public JTextField cBirth;
	public JTextField cDate;
	public JRadioButton cMail;
	public JRadioButton cFemail;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel status;
	private JButton btnMenu;
	public JTextField cEmail;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserModify frame = new UserModify();
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
	public UserModify() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UserMain um = new UserMain(lcId,logIn);
				um.cId.setText(lcId);
				SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
				um.lIn.setText(sdf2.format(logIn));
				setVisible(false);
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 792);
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
		contentPane.add(getCMail());
		contentPane.add(getCFemail());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getStatus());
		contentPane.add(getBtnMenu());
		contentPane.add(getCEmail());
		contentPane.add(getLblNewLabel_8());
	}
	
	public UserModify(UserVo vo ,String id,Date login) {//전달받은값 필드에 저장
		this();
		this.lcId = id;
		this.logIn = login;
		this.vos = vo;
	}
	public JTextField getCId() {
		if (cId == null) {
			cId = new JTextField();
			cId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cId.setBorder(null);
			cId.setEnabled(false);
			cId.setBounds(213, 257, 145, 19);
			cId.setColumns(10);
		}
		return cId;
	}
	public JPasswordField getCPwd() {
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
			cPwd.setBounds(213, 288, 175, 19);
			cPwd.setColumns(10);
		}
		return cPwd;
	}
	public JTextField getCName() {
		if (cName == null) {
			cName = new JTextField();
			cName.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cName.setBorder(null);
			cName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cName.setBounds(257, 358, 153, 21);
			cName.setColumns(10);
		}
		return cName;
	}
	public JTextField getCPhone() {
		if (cPhone == null) {
			cPhone = new JTextField();
			cPhone.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cPhone.setBorder(null);
			cPhone.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cPhone.setBounds(257, 453, 153, 21);
			cPhone.setColumns(10);
		}
		return cPhone;
	}
	public JTextField getCBirth() {
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
			cBirth.setBounds(257, 422, 153, 21);
			cBirth.setColumns(10);
		}
		return cBirth;
	}
	public JTextField getCDate() {
		if (cDate == null) {
			cDate = new JTextField();
			cDate.setBorder(null);
			cDate.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			cDate.setEnabled(false);
			cDate.setBounds(257, 555, 153, 21);
			cDate.setColumns(10);
		}
		return cDate;
	}
	public JRadioButton getCMail() {
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
			cMail.setBounds(265, 391, 42, 23);
			group.add(cMail);
		}
		return cMail;
	}
	public JRadioButton getCFemail() {
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
			cFemail.setBounds(329, 391, 42, 23);
			group.add(cFemail);
		}
		return cFemail;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(UserModify.class.getResource("/semi_icon2/체크.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cPwd.setBackground(Color.WHITE);//검색버튼 다시누르면 텍스트필드 색 초기화
					cName.setBackground(Color.WHITE);
					cPhone.setBackground(Color.WHITE);
					cBirth.setBackground(Color.WHITE);
					cEmail.setBackground(Color.WHITE);
					if(cPwd.getText().contains(" ")||cEmail.getText().contains(" ")||
						cBirth.getText().contains(" ")||cPhone.getText().contains(" ")||
						cName.getText().contains(" ")) {
							status.setText("입력항목 중간에 공백이 있습니다");
							cPwd.requestFocus();
							return;
						}
						if(cPwd.getText().equals("")||cEmail.getText().equals("")||
							cBirth.getText().equals("")||cPhone.getText().equals("")||
							cName.getText().equals("")) {
							status.setText("입력항목에 공백이있습니다");
							cPwd.requestFocus();
							return;
						}
						
						String nameCheck = "[가-힣a-zA-Z]{1,20}";//이름체크
						boolean flag = Pattern.matches(nameCheck, cName.getText());
						if(!flag) {
							status.setText("이름에는 숫자가 올수 없습니다");
							cName.requestFocus();
							cName.selectAll();
							cName.setBackground(Color.MAGENTA);
							return;
						}
						
						String pWdCheck = "^[a-zA-Z0-9~!@#$%\\^&\\*\\(\\)]{8,16}";//비밀번호체크
						flag = Pattern.matches(pWdCheck, cPwd.getText());
						if(!flag) {
							status.setText( "비밀번호는 숫자와영어특수가호가 포함된8~16자리로 해주세요");
							cPwd.requestFocus();
							cPwd.selectAll();
							cPwd.setBackground(Color.MAGENTA);
							return;
						}
						
						String PhoneCheck = "01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}";//폰번호체크
						flag = 	Pattern.matches(PhoneCheck, cPhone.getText());
						if(!flag) {
							status.setText("핸드폰번호는'000-0000-0000'형식으로 해주세요");
							cPhone.requestFocus();
							cPhone.selectAll();
							cPhone.setBackground(Color.MAGENTA);
							return;
						}
						String BirthCheck = "(\\d{4})-(\\d{2})-(\\d{2})";//생년월일
						flag = Pattern.matches(BirthCheck, cBirth.getText());
						if(!flag) {
							status.setText("생년월일은 yyyy-MM-dd형식으로 입력해 주세요");
							cBirth.requestFocus();
							cBirth.selectAll();
							cBirth.setBackground(Color.MAGENTA);
							return;
						}
						String EmailCheck = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+$";//이메일
						flag = Pattern.matches(EmailCheck, cEmail.getText());
						if(!flag) {
							status.setText("이메일은'~~~~@~~.~~'형식으로 입력해주세요");
							cEmail.requestFocus();
							cEmail.selectAll();
							cEmail.setBackground(Color.MAGENTA);
							return;
						}
						
					try {
						String uId = cId.getText();
						String uPwd = cPwd.getText();
						String uMail = cEmail.getText();
						Date uBirth = sdf.parse(cBirth.getText());
						String uPhone = cPhone.getText();
						String uGender ="";
						Date uDate = sdf.parse(cDate.getText());
						String uName = cName.getText();
					
						if(cMail.isSelected()) {
							uGender = "남";
						}else if(cFemail.isSelected()) {
							uGender = "여";
						}
						UserVo vo = new UserVo();
						vo.setcId(uId);
						vo.setcPwd(uPwd);
						vo.setcEmail(uMail);
						vo.setcBirth(uBirth);
						vo.setcPhone(uPhone);
						vo.setcGender(uGender);
						vo.setcDate(uDate);
						vo.setcName(uName);
						int r = dao.modify(vo);
						if(r>0) {
							status.setText("정보가 수정되었습니다");
						}else {
							status.setText("정보 수정중 오류발생 관리자를 호출하세요");
						}
						
					}catch(Exception ex) {
						
					}
				}
			});
			btnNewButton.setBounds(263, 629, 42, 38);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(UserModify.class.getResource("/semi_icon2/이전으로.jpg")));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					UserModifyCheck2 um = new UserModifyCheck2(vos,lcId,logIn);//뒤로갈때 정보들 전달
					UserDao dao = new UserDao();
					vos = dao.checkAndsearch(lcId);
				}
			});
			btnNewButton_1.setBounds(20, 711, 42, 31);
		}
		return btnNewButton_1;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setForeground(new Color(240, 128, 128));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setBounds(20, 598, 532, 15);
		}
		return status;
	}
	private JButton getBtnMenu() {
		if (btnMenu == null) {
			btnMenu = new JButton("");
			btnMenu.setIcon(new ImageIcon(UserModify.class.getResource("/semi_icon2/UserModify메뉴로.jpg")));
			btnMenu.setBorder(null);
			btnMenu.setBackground(Color.WHITE);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserMain um = new UserMain(lcId,logIn);
					um.cId.setText(lcId);
					SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
					um.lIn.setText(sdf2.format(logIn));
					dispose();
				}
			});
			btnMenu.setBounds(238, 711, 89, 31);
		}
		return btnMenu;
	}
	public JTextField getCEmail() {
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
			cEmail.setColumns(10);
			cEmail.setBounds(257, 484, 153, 21);
		}
		return cEmail;
	}
	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("");
			lblNewLabel_8.setIcon(new ImageIcon(UserModify.class.getResource("/semiIcon/UserModify.jpg")));
			lblNewLabel_8.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_8;
	}
}
