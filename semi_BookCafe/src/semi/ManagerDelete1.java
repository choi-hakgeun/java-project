package semi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ManagerDelete1 extends JFrame {

	private JPanel contentPane;
	private JTextField MasterId;
	private JTextField MasterPwd;
	private JButton btnNewButton;
	private JLabel lblNewLabel_3;
	private JButton button;
	ManagerFrame frame;
    String id;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerDelete1 frame = new ManagerDelete1();
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
	 public char delete() {
	    	//ManagerFrame�� table���� Ŭ���� userid�� ���̶� frmae�� �� �����ڷ� ���� (1)�α������Ͽ�
	    	String mid = MasterId.getText();
			String pwd = MasterPwd.getText();
			ManagerVo vo = new ManagerVo();
			ManagerDao dao = new ManagerDao();
			vo.setcId(mid);
			vo.setcPwd(pwd);
			dao.insert(vo);
			
			char cnt = dao.insert(vo);//(2)�α��� ���ϰ��� u(�ϴ� ������) �̸� dao.delete�� ManagerFrame���� �޾ƿ� id���� �ִ´� 
			if(cnt == 'm') {
				int result =JOptionPane.showConfirmDialog(ManagerDelete1.this,id+" 삭제하시겠습니까?","message",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION) {//�� �ƴϿ� ��ư ����
					
				}else if(result == JOptionPane.YES_OPTION) {
					if(!id.isEmpty()) {
					dao.delete(id);
					
					frame.id ="";
					frame.refresh();
					frame.staa.setText("");
					frame.nPwd.setText("");
					JScrollBar vertical = frame.scrollPane.getVerticalScrollBar();
					vertical.setValue(vertical.getMaximum());
					}else {
						JOptionPane.showMessageDialog(ManagerDelete1.this, "아이디를 다시 선택해주세요.", "message", JOptionPane.ERROR_MESSAGE);
					}
					
					dispose();
					ManagerFrame a = new ManagerFrame();
				}else {
					
				}
			}else {//�α��� ���ϰ��� u�� �ƴϸ� �޽�����
				JOptionPane.showMessageDialog(ManagerDelete1.this, "권한이 없습니다.", "message", JOptionPane.ERROR_MESSAGE);
			}
			return cnt;
			
	 }
				
				
				
				
				
				
			
				
	    	
	    	
	    	
	    	
			
	
	
	public ManagerDelete1() {
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 790);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLabel_2());
		contentPane.add(getMasterId());
		contentPane.add(getMasterPwd());
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getButton());
		contentPane.add(getLabel_1());
	}
	public ManagerDelete1(String id,ManagerFrame frame) {
		this();
		this.id=id;
		this.frame=frame;
	}
	private JTextField getMasterId() {
		if (MasterId == null) {
			MasterId = new JTextField();
			MasterId.setFont(new Font("나눔스퀘어_ac Bold", Font.PLAIN, 12));
			MasterId.setBorder(null);
			MasterId.setBounds(227, 279, 153, 21);
			MasterId.setColumns(10);
		}
		return MasterId;
	}
	private JTextField getMasterPwd() {
		if (MasterPwd == null) {
			MasterPwd = new JTextField();
			MasterPwd.setBorder(null);
			MasterPwd.setBounds(227, 338, 153, 21);
			MasterPwd.setColumns(10);
		}
		return MasterPwd;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setBorder(null);
			btnNewButton.setIcon(new ImageIcon(ManagerDelete1.class.getResource("/semi_icon2/다음으로2.jpg")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					delete();
					
				}
			});
			btnNewButton.setBounds(239, 472, 85, 81);
		}
		return btnNewButton;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(12, 163, 283, 27);
		}
		return lblNewLabel_3;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setBackground(Color.WHITE);
			button.setBorder(null);
			button.setIcon(new ImageIcon(ManagerDelete1.class.getResource("/semi_icon2/이전으로.jpg")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ManagerFrame panel = new ManagerFrame();
					panel.toFront();
					setVisible(false);
				}
			});
			button.setBounds(27, 699, 35, 32);
		}
		return button;
	}
	private JLabel getLabel_1() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(ManagerDelete1.class.getResource("/semiIcon/UserModifyCheck.jpg")));
			lblNewLabel_2.setBounds(0, 0, 564, 754);
		}
		return lblNewLabel_2;
	}
	private JLabel getLabel_2() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID 삭제");
			lblNewLabel.setForeground(SystemColor.inactiveCaptionText);
			lblNewLabel.setFont(new Font("나눔스퀘어OTF_ac ExtraBold", Font.PLAIN, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setOpaque(true);
			lblNewLabel.setBounds(225, 200, 116, 32);
		}
		return lblNewLabel;
	}
}
