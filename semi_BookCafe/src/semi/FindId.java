package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import java.awt.RenderingHints.Key;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class FindId extends JFrame {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	UserDao dao = new UserDao();

	private JPanel contentPane;
	private JTextField cId;
	private JTextField cEmail;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblNewLabel_4;
	private JButton button;
	private JTextField cPhone;
	private JLabel status;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindId frame = new FindId();
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
	public FindId() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Login lo = new Login();
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 793);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCId());
		contentPane.add(getCEmail());
		contentPane.add(getBtnNewButton());
		contentPane.add(getScrollPane());
		contentPane.add(getButton());
		contentPane.add(getCPhone());
		contentPane.add(getStatus());
		contentPane.add(getLblNewLabel_5());
	}
	private JTextField getCId() {
		if (cId == null) {
			cId = new JTextField();
			cId.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 12));
			cId.setBorder(null);
			cId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cId.setBounds(252, 280, 134, 19);
			cId.setColumns(10);
		}
		return cId;
	}
	private JTextField getCEmail() {
		if (cEmail == null) {
			cEmail = new JTextField();
			cEmail.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 12));
			cEmail.setBorder(null);
			cEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cEmail.setBounds(252, 312, 134, 21);
			cEmail.setColumns(10);
		}
		return cEmail;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(FindId.class.getResource("/semi_icon2/FindID검색.jpg")));
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			
				
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(cId.getText().contains(" ")||cEmail.getText().contains(" ")||cPhone.getText().contains(" ")) {
						status.setText("입력항목중간에 공백이 있습니다");
						return;
					}
					if(cId.getText().equals("")||cEmail.getText().equals("")||cPhone.getText().equals("")) {
						status.setText("입력항목에 공백이 있습니다");
						return;
					}
					
					String uId = cId.getText();//아이디
					String uEmail = cEmail.getText();//비밀번호
					String uPhone = cPhone.getText();//전화번호
					UserVo vo = new UserVo(uId,uEmail,uPhone);//유저정보를 담을 vo
					List<UserVo> list = dao.idPwdSearch(vo);
					for(int i = 0; i<list.size() ; i++) {
		                  UserVo vo2 = list.get(i);
		                  if(vo2 !=null) {
		                     StringBuilder bu = new StringBuilder(vo2.getcPwd());
		                     bu.setCharAt(0, '*');
		                     bu.setCharAt(1, '*');
		                     textArea.append(vo2.getcId()+"\t"+bu);
		                     status.setText("정보를 찾았습니다 로그인해주세요");
		                  }else{
		                     status.setText("정보를 찾지 못했습니다");
		                  }
		               }
				}
			});
			btnNewButton.setBounds(252, 386, 55, 49);
		}
		return btnNewButton;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setBounds(161, 447, 246, 152);
			scrollPane.setViewportView(getTextArea());
			scrollPane.setColumnHeaderView(getLblNewLabel_4());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 13));
		}
		return textArea;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel(" \uC544\uC774\uB514               \uBE44\uBC00\uBC88\uD638");
			lblNewLabel_4.setForeground(Color.GRAY);
			lblNewLabel_4.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 15));
		}
		return lblNewLabel_4;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(FindId.class.getResource("/semi_icon2/로그인하기.jpg")));
			button.setBorder(null);
			button.setBackground(Color.WHITE);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login lo = new Login();
					dispose();
				}
			});
			button.setBounds(237, 696, 88, 38);
		}
		return button;
	}
	private JTextField getCPhone() {
		if (cPhone == null) {
			cPhone = new JTextField();
			cPhone.setFont(new Font("나눔스퀘어OTF_ac Light", Font.PLAIN, 12));
			cPhone.setBorder(null);
			cPhone.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			cPhone.setColumns(10);
			cPhone.setBounds(252, 345, 134, 21);
		}
		return cPhone;
	}
	private JLabel getStatus() {
		if (status == null) {
			status = new JLabel("");
			status.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 12));
			status.setHorizontalAlignment(SwingConstants.CENTER);
			status.setForeground(new Color(240, 128, 128));
			status.setBounds(-1, 371, 563, 15);
		}
		return status;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setIcon(new ImageIcon(FindId.class.getResource("/semiIcon/FindId.jpg")));
			lblNewLabel_5.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_5;
	}
}
