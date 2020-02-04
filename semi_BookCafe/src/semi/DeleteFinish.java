package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class DeleteFinish extends JFrame {

	public String lcId;//전달받은 아이디값 필드에저장
	public Date logIn;
	LoginLogDao ldao;
	LoginLogVo ovo;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private JPanel contentPane;
	private JButton btnNewButton;
	public JLabel cPrice;
	public JLabel ctot;
	public JLabel cTime;
	public JLabel cLogout;
	public JLabel cLogin;
	public JLabel cUid;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteFinish frame = new DeleteFinish();
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
	public DeleteFinish() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Login log = new Login();
				setVisible(false);
			}
		});
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 770);
		contentPane = new JPanel();
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnNewButton.doClick();
				}
			}
		});
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getCPrice());
		contentPane.add(getCtot());
		contentPane.add(getCTime());
		contentPane.add(getCLogout());
		contentPane.add(getCLogin());
		contentPane.add(getCUid());
		contentPane.add(getLblNewLabel_1());
	}
	
	public DeleteFinish(String id,Date logIn) {
		this();
		this.lcId = id;
		
		this.ldao = new LoginLogDao();
		this.ovo = this.ldao.logOut(lcId);	
		this.cUid.setText(ovo.getcId()+"님");
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
		
		LoginLogVo vo = this.ldao.price();
		int p = 0;
		if(ovo.getlTime()<= 120||ovo.getlTime()>=0){
			p = vo.getInitprice();
		}else if(ovo.getlTime()>120) {
			double i = Math.ceil((ovo.getlTime()-120)/(double)60);
			
				p =	(int)(vo.getInitprice()+(vo.getAddprice()*i));
			
		}
		
		DecimalFormat df = new DecimalFormat("##,###,###");
		this.cPrice.setText(df.format(p)+"원");
		LoginLogDao dao = new LoginLogDao();
		dao.inputPrice(p,lcId,ovo.getlTime());
		
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(DeleteFinish.class.getResource("/semi_icon2/닫기.jpg")));
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
					Login log = new Login();//로그인화면 띄워줌
					setVisible(false);
				}
			});
			btnNewButton.setBounds(459, 117, 45, 37);
		}
		return btnNewButton;
	}
	private JLabel getCPrice() {
		if (cPrice == null) {
			cPrice = new JLabel("??");
			cPrice.setForeground(SystemColor.inactiveCaptionText);
			cPrice.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cPrice.setBounds(275, 309, 165, 25);
		}
		return cPrice;
	}
	private JLabel getCtot() {
		if (ctot == null) {
			ctot = new JLabel("??");
			ctot.setForeground(SystemColor.inactiveCaptionText);
			ctot.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			ctot.setBounds(275, 435, 165, 23);
		}
		return ctot;
	}
	private JLabel getCTime() {
		if (cTime == null) {
			cTime = new JLabel("??");
			cTime.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cTime.setForeground(SystemColor.inactiveCaptionText);
			cTime.setBounds(275, 411, 165, 23);
		}
		return cTime;
	}
	private JLabel getCLogout() {
		if (cLogout == null) {
			cLogout = new JLabel("??");
			cLogout.setForeground(SystemColor.inactiveCaptionText);
			cLogout.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cLogout.setBounds(275, 386, 165, 23);
		}
		return cLogout;
	}
	private JLabel getCLogin() {
		if (cLogin == null) {
			cLogin = new JLabel("??");
			cLogin.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 14));
			cLogin.setForeground(SystemColor.inactiveCaptionText);
			cLogin.setBounds(274, 361, 165, 23);
		}
		return cLogin;
	}
	private JLabel getCUid() {
		if (cUid == null) {
			cUid = new JLabel("??");
			cUid.setForeground(SystemColor.inactiveCaptionText);
			cUid.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 15));
			cUid.setHorizontalAlignment(SwingConstants.CENTER);
			cUid.setBounds(202, 215, 158, 25);
		}
		return cUid;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(DeleteFinish.class.getResource("/semiIcon/DeleteFinish.jpg")));
			lblNewLabel_1.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_1;
	}
}
