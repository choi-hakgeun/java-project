package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.SystemColor;

public class UserPrice extends JFrame {
	
	LoginLogDao ldao;
	LoginLogVo ovo; 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public String lcId; //전달받기위한 필드

	private JPanel contentPane;
	private JLabel cUId;
	private JButton btnNewButton;
	private JLabel cLogin;
	private JLabel cLogout;
	private JLabel cTime;
	private JLabel ctot;
	private JLabel cPrice;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPrice frame = new UserPrice();
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
	public UserPrice() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setVisible(false);
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 794);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getCUId());
		contentPane.add(getBtnNewButton());
		contentPane.add(getTextField());
		contentPane.add(getCLogin());
		contentPane.add(getCLogout());
		contentPane.add(getCTime());
		contentPane.add(getCtot());
		contentPane.add(getCPrice());
		contentPane.add(getLblNewLabel_1());
	}
	public UserPrice(String id) {//창떠지면 바로 입력될수있게 생성자에 실행문 작성
		this();
		this.lcId = id;
		
		this.ldao = new LoginLogDao();
		this.ovo = this.ldao.logOut(lcId);	
		this.cUId.setText(ovo.getcId());
		this.cLogin.setText(sdf.format(ovo.getlIn()));
		this.cLogout.setText(sdf.format(ovo.getlOut()));
		
		String shour ="";
		String smin ="";
		String stothour="";
		String stotmin="";
		
		
		int hour = (int) Math.floor((ovo.getlTime()/(double)60));
		int min = ovo.getlTime()%60;
		if(min<10) {
			smin = "0"+min;
		}else if(min==0) {
			smin = "00"+min;
		}else {
			smin = ""+min;
		}
		shour = hour+"";
		int tothour = (int) Math.floor((ovo.getTotTime()/(double)60));
		int totmin = ovo.getTotTime()%60;
		if(totmin<10) {
			stotmin = "0"+totmin;
		}else if(totmin==0){
			stotmin = "00"+totmin;
		}else {
			stotmin = ""+totmin;
		}
		stothour = tothour+"";
		
		
		
		this.cTime.setText(shour+"시간"+smin+"분");
		this.ctot.setText(stothour+"시간"+stotmin+"분");
		
		System.out.println(ovo.getlTime());
		LoginLogVo vo = this.ldao.price();
		int p = 0;
		if(ovo.getlTime()<= 120 && ovo.getlTime()>=0){
			p = vo.getInitprice();
			System.out.println("if :" + p);
		}else if(ovo.getlTime()>120) {
			double i = Math.ceil((ovo.getlTime()-120)/(double)60);
			
				p =	(int)(vo.getInitprice()+(vo.getAddprice()*i));
				System.out.println("else :" + p);
		}
		
		DecimalFormat df = new DecimalFormat("##,###,###");
		this.cPrice.setText(df.format(p)+"원");
		LoginLogDao dao = new LoginLogDao();
		dao.inputPrice(p,lcId,ovo.getlTime());
		
		
	
	}
	private JLabel getCUId() {
		if (cUId == null) {
			cUId = new JLabel("??");
			cUId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cUId.setForeground(SystemColor.inactiveCaptionText);
			cUId.setHorizontalAlignment(SwingConstants.CENTER);
			cUId.setBounds(176, 152, 88, 23);
		}
		return cUId;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(UserPrice.class.getResource("/semi_icon2/닫기.jpg")));
			btnNewButton.setBorder(null);
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						btnNewButton.doClick();
					}
				}
			});
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			btnNewButton.setBounds(464, 113, 41, 42);
		}
		return btnNewButton;
	}
	private JLabel getCLogin() {
		if (cLogin == null) {
			cLogin = new JLabel("??");
			cLogin.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cLogin.setForeground(SystemColor.inactiveCaptionText);
			cLogin.setHorizontalAlignment(SwingConstants.LEFT);
			cLogin.setBounds(285, 361, 171, 22);
		}
		return cLogin;
	}
	private JLabel getCLogout() {
		if (cLogout == null) {
			cLogout = new JLabel("??");
			cLogout.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cLogout.setForeground(SystemColor.inactiveCaptionText);
			cLogout.setHorizontalAlignment(SwingConstants.LEFT);
			cLogout.setBounds(285, 386, 181, 22);
		}
		return cLogout;
	}
	private JLabel getCTime() {
		if (cTime == null) {
			cTime = new JLabel("??");
			cTime.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cTime.setForeground(SystemColor.inactiveCaptionText);
			cTime.setHorizontalAlignment(SwingConstants.LEFT);
			cTime.setBounds(285, 411, 181, 22);
		}
		return cTime;
	}
	private JLabel getCtot() {
		if (ctot == null) {
			ctot = new JLabel("??");
			ctot.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			ctot.setForeground(SystemColor.inactiveCaptionText);
			ctot.setHorizontalAlignment(SwingConstants.LEFT);
			ctot.setBounds(285, 436, 181, 22);
		}
		return ctot;
	}
	private JLabel getCPrice() {
		if (cPrice == null) {
			cPrice = new JLabel("??");
			cPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cPrice.setForeground(SystemColor.inactiveCaptionText);
			cPrice.setHorizontalAlignment(SwingConstants.CENTER);
			cPrice.setBounds(190, 219, 181, 23);
		}
		return cPrice;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(UserPrice.class.getResource("/semiIcon/UserPrice.jpg")));
			lblNewLabel_1.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setBounds(86, 300, 189, 42);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBorder(null);
			textField.setBounds(-22, 10, 116, 21);
			textField.setColumns(10);
		}
		return textField;
	}
}
