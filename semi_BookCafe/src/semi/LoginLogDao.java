package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class LoginLogDao {
	Connection conn;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public LoginLogDao() {
		conn = DBConn.getConn();
	}
	
	//���ݾ� �����ϱ�
	public int inputPrice(int p,String cId,int in) {
		int r =0;
		try {
			String sql = "update  loginlog set UserPrice=? where userId=? and UseTime=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p);
			ps.setString(2, cId);
			ps.setInt(3, in);
			
			conn.setAutoCommit(false);
			r = ps.executeUpdate();
			if(r>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception ex) {
			
		}finally {
			return r;
		}
	}
	
	//���� ��������
	public LoginLogVo price() {
		LoginLogVo vo = new LoginLogVo();
		try {
			String sql = "select initprice , addprice from charge";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vo = new LoginLogVo(rs.getInt("initprice"),rs.getInt("addprice"));
			}
		}catch(Exception ex) {
			
		}finally {
			return vo;
		}
	}
	
	//�α��ι�ư Ŭ���� �α׾ƿ��� lvo�� ����
	public int insert(LoginLogVo lvo) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		

		int r =0;
		try {
			
			String sql = "insert into LoginLog(LoginNo,UserId,LoginDT,LogoutDT,UseTime) values(seq_loginlog.nextval,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, lvo.getcId());
			ps.setTimestamp(2, new java.sql.Timestamp(lvo.getlIn().getTime() ));
			ps.setTimestamp(3, new java.sql.Timestamp(lvo.getlOut().getTime()));
			ps.setInt(4, lvo.getlTime());
			
			
			conn.setAutoCommit(false);
			
			r = ps.executeUpdate();
			if(r>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			
		}finally {
			return r;
		}
	}
	//�α׾ƿ��� ������ �ѷ��� �޼ҵ�
	public LoginLogVo logOut(String id) {
		LoginLogVo lvo = new LoginLogVo();
		int tot =0;
		try {
			String sql = "select UserID,LoginDT,LogoutDT,UseTime from LoginLog where UserID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lvo.setcId(rs.getString("UserID"));
				lvo.setlIn(rs.getTimestamp("LoginDT"));
				lvo.setlOut(rs.getTimestamp("LogoutDT"));
				tot += rs.getInt("UseTime");
				lvo.setTotTime(tot);
				lvo.setlTime(rs.getInt("UseTime"));
			}
		}catch(Exception ex) {
			
		}finally {
			return lvo;
		}
	}

	public List<LoginLogVo> select() {
		List<LoginLogVo> list = new ArrayList<>();
		
		try {
			String sql = " select * from LoginLog order by loginNo ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				LoginLogVo vo = new LoginLogVo();
				vo.setlNo(rs.getInt("loginno"));
				vo.setcId(rs.getString("userid"));
				vo.setlIn((rs.getTimestamp("logindt")));
				vo.setlOut(rs.getTimestamp("logoutdt"));
				vo.setlTime(rs.getInt("usetime"));
				vo.setUsePrice(rs.getInt("userprice"));
				
				list.add(vo);
			}
			
			ps.close();
			rs.close();
			conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {				
				return list;
			}
		}
}
