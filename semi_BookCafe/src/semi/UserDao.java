package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class UserDao {
	Connection conn;
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy_MM_dd");
	public UserDao() {
		conn = DBConn.getConn();
	}
	//ȸ�� ����
	public int delete(String sId) {
		int r = 0;
		try {
			String sql = "delete from member where UserId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(sId);
			ps.setString(1, sId);
			
			conn.setAutoCommit(false);
			r = ps.executeUpdate();
			System.out.println(r);
			if(r>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			return r;
		}
	}
	//ȸ�� ����
	public int modify(UserVo vo) {
		int r =0;
		try {
			String sql = "update member set password=?,Email=?,BirthDay=?,Phone=?,Gender=?,UserName=? where UserId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getcPwd());
			ps.setString(2, vo.getcEmail());
			ps.setString(3, sdf.format(vo.getcBirth()));
			ps.setString(4, vo.getcPhone());
			ps.setString(5, vo.getcGender());
			ps.setString(6, vo.getcName());
			ps.setString(7, vo.getcId());
			
			conn.setAutoCommit(false);
			r = ps.executeUpdate();
			if(r>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			return r;
		}
	}
	//���������� ���̵�,��й�ȣ�� Ȯ���ϰ� ���̵� �ش��ϴ� ������vo�� �����ϴ� �޼ҵ�
	public UserVo checkAndsearch(String cId,String cPwd) {
		UserVo vo = new UserVo();
		try {
			String sql = "select UserNo,UserId,password,Email,BirthDay,Phone,Gender,CreateDT,is_Admin,UserName from member where UserId= ? and password= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cId);
			ps.setString(2, cPwd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int uNo = rs.getInt("UserNo");
				String uid = rs.getString("UserId");
				String uPwd = rs.getString("password");
				String uEmail = rs.getString("Email");
				Date uBirth = rs.getDate("BirthDay");
				String uPhone = rs.getString("Phone");
				String uGender = rs.getString("Gender");
				Date uDate = rs.getDate("CreateDT");
				String uAdmin = rs.getString("is_Admin");
				String uName = rs.getString("UserName");
				vo = new UserVo(uNo,uid,uPwd,uEmail,uBirth,uPhone,uGender,uDate,uAdmin,uName);
			}
			
			ps.close();
			rs.close();
		}catch(Exception ex) {
			
		}finally {
			return vo;
		}
	}
	
	//����ȭ�鿡�� �ڷΰ��⸦ Ŭ���� ������vo�������� �������ֱ����� �޼���
	public UserVo checkAndsearch(String cId) {
		UserVo vo = new UserVo();
		try {
			String sql = "select UserNo,UserId,password,Email,BirthDay,Phone,Gender,CreateDT,is_Admin,UserName from member where UserId= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int uNo = rs.getInt("UserNo");
				String uid = rs.getString("UserId");
				String uPwd = rs.getString("password");
				String uEmail = rs.getString("Email");
				Date uBirth = rs.getDate("BirthDay");
				String uPhone = rs.getString("Phone");
				String uGender = rs.getString("Gender");
				Date uDate = rs.getDate("CreateDT");
				String uAdmin = rs.getString("is_Admin");
				String uName = rs.getString("UserName");
				vo = new UserVo(uNo,uid,uPwd,uEmail,uBirth,uPhone,uGender,uDate,uAdmin,uName);
			}
			
			ps.close();
			rs.close();
		}catch(Exception ex) {
			
		}finally {
			return vo;
		}
	}
	
	//�α��� �� ���� �޼ҵ�
	public char login(String cId,String cPwd) {
		char c = 0;
		try {
		String sql = "select is_Admin from member where UserId= ? and password= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cId);
		ps.setString(2, cPwd);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String r = rs.getString("is_Admin");
			c = r.charAt(0);
			
			
		}
		
		ps.close();
		rs.close();
		}catch(Exception ex) {
			
		}finally {
			return c;
		}
	}
	
	//ȸ�� ���̵�,��й�ȣ�� ã������ �żҵ�
	public List<UserVo> idPwdSearch(UserVo vo) {
		List<UserVo> list = new ArrayList<UserVo>();
		try {
			String sql = "select UserID , password from member where UserName=? and Email=? and Phone=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getcName());
			ps.setString(2, vo.getcEmail());
			ps.setString(3, vo.getcPhone());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String uId = rs.getString("UserId");
				String uPwd = rs.getString("password");
				UserVo vo2 = new UserVo(uId,uPwd);
				list.add(vo2);
			}
			
			ps.close();
			rs.close();
		}catch(Exception e) {
			
		}finally {
			return list;
		}
	}
	//ȸ������
	public int insert(UserVo vo) {
		int r = 0;
		try {
			String sql = "insert into member values(seq_member.nextval ,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getcId());
			ps.setString(2, vo.getcPwd());
			ps.setString(3, vo.getcEmail());
			ps.setString(4, sdf.format(vo.getcBirth()));
			ps.setString(5, vo.getcPhone());
			ps.setString(6, vo.getcGender());
			ps.setString(7, sdf.format(vo.getcDate()));
			ps.setString(8, vo.getIs_Admin());
			ps.setString(9, vo.getcName());
			
			conn.setAutoCommit(false);
			r = ps.executeUpdate();
			if(r>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			ps.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			return r;			
		}
	}
	//ȸ�����Խ� ���̵� �ߺ�üũ
	public String idCheck(String find){
		String a = null;
		try {
			String sql = "select UserId from member where UserId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, find);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = rs.getString("UserId");
				
				
			}
			
			ps.close();
			rs.close();
		}catch(Exception ex) {
			
		}finally {
			return a;
		}
	}
}
































