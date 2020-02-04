package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;

public class UserModifyCheck2 extends JFrame {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public UserVo vos;// 값전달해주고 전달 받기위해 필드에저장
	public String lcId;
	public Date logIn;
	Date logOut;// 삭제버튼을 누를시 필요한 로그아웃시간

	private JPanel contentPane;
	private JButton button;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel status;
	private JLabel lblNewLabel_2;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserModifyCheck2 frame = new UserModifyCheck2();
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
	public UserModifyCheck2() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UserModifyCheck um = new UserModifyCheck(vos, lcId, logIn);
				UserDao dao = new UserDao();
				vos = dao.checkAndsearch(lcId);
				setVisible(false);
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
		contentPane.add(getTextField());
		contentPane.add(getButton());
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getStatus());
		contentPane.add(getLblNewLabel_2());
	}

	public UserModifyCheck2(String id, Date login) {// 지워도 될지말지는 미지수
		this();
		this.lcId = id;
		this.logIn = login;
	}

	public UserModifyCheck2(UserVo vos, String id, Date login) {
		this();
		this.vos = vos;
		this.lcId = id;
		this.logIn = login;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(UserModifyCheck2.class.getResource("/semi_icon2/이전으로.jpg")));
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserModifyCheck um = new UserModifyCheck(vos, lcId, logIn);
					UserDao dao = new UserDao();
					vos = dao.checkAndsearch(lcId);
					setVisible(false);
				}
			});
			button.setBounds(32, 699, 32, 31);
		}
		return button;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBorder(null);
			btnNewButton.setIcon(new ImageIcon(UserModifyCheck2.class.getResource("/semi_icon2/UserModify수정.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					UserModify um = new UserModify(vos, lcId, logIn); // 수정창을 띄워줄것입
					// 수정창의 텍스트필드에 현재상태의 유저아이디의 vos를 수정창의 텍스트필드에 올려줄것임
					um.cId.setText(vos.getcId());// 아이디
					um.cPwd.setText(vos.getcPwd());// 비밀번호
					um.cEmail.setText(vos.getcEmail());// 이메일
					um.cBirth.setText(sdf.format(vos.getcBirth()));// 생년월일
					um.cPhone.setText(vos.getcPhone());// 전화번호
					um.cDate.setText(sdf.format(vos.getcDate()));// 생성날짜
					um.cName.setText(vos.getcName());// 이름
					char gen = vos.getcGender().charAt(0);// 성별
					if (gen == '남') {
						um.cMail.setSelected(true);
					} else if (gen == '여') {
						um.cFemail.setSelected(true);
					}
				}
			});
			btnNewButton.setBounds(184, 214, 193, 159);
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setIcon(new ImageIcon(UserModifyCheck2.class.getResource("/semi_icon2/UserModify삭제.jpg")));
			btnNewButton_1.setBorder(null);
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// 삭제후 로그아웃 시킴--------------------------------------
					logOut = new Date();// 현재시간 로그아웃필드에 담기

					LoginLogVo lvo = new LoginLogVo(lcId, logIn, logOut);// log 값저장을위한 lvo
					LoginLogDao ldao = new LoginLogDao();// log값 저장을위한 ldao
					int lr = ldao.insert(lvo);// LoginLog테이블에서 입력

					if (lr > 0) {

						String find = vos.getcId();
						UserDao dao = new UserDao();
						System.out.println(find);
						int r = dao.delete(find);// member테이블에 삭제
						
						if (r > 0) {
							// 로그아웃 시킨후 삭제창에 사용한시간을 뿌려줄것임----------------------------------------------
//							LoginLogVo ovo = ldao.logOut(lcId);
							// 삭제창
							// 띄워줄것임----------------------------------------------------------------------------

							setVisible(false);
							DeleteFinish df = new DeleteFinish(lcId, logIn); // 삭제완료창에 매개변수를 던져주며 실행

//							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//							df.cUid.setText(ovo.getcId());
//							df.cLogin.setText(sdf.format(ovo.getlIn()));
//							df.cLogout.setText(sdf.format(ovo.getlOut()));
//							df.cTime.setText(ovo.getlTime()+"");
						} else {
							status.setText("삭제중 오류발생");
						}

					} else {
						status.setText("로그아웃중에 오류발생");
					}

				}
			});
			btnNewButton_1.setBounds(184, 455, 193, 159);
		}
		return btnNewButton_1;
	}

	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setForeground(new Color(240, 128, 128));
			status.setBounds(12, 402, 540, 15);
		}
		return status;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(UserModifyCheck2.class.getResource("/semiIcon/UserModifyCheck2.jpg")));
			lblNewLabel_2.setBounds(0, -1, 564, 754);
		}
		return lblNewLabel_2;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBorder(null);
			textField.setBounds(-37, 101, 116, 21);
			textField.setColumns(10);
		}
		return textField;
	}
}
