package semi;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;




public class ManagerDao {
	
	Connection conn;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ResultSetMetaData meta;
	DefaultTableModel model;
	Vector row ;
	public ManagerDao() {
		conn = DBConn.getConn();
	}

	public ManagerDao(Vector row,DefaultTableModel model) {
		this();
		this.row=row;
		this.model=model;
	}
	public char insert(ManagerVo vo) {//FirstFrame_1 �α��ι�ư ������ �α���
		char r=0;

		try {
			String sql = " select is_admin from member where userid =  ? and password = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getcId());
			ps.setString(2, vo.getcPwd());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String c =rs.getString("IS_ADMIN");
				
				r=c.charAt(0);
				
				rs.close();
				ps.close();
			}else {
				r=0;
			}
		}catch (Exception ex){
			
			
		}
		
		return r;
		
	}
		
		
		
		
		

	
	
	public int update(ManagerVo vo) {//ManagerFrame�� ��й�ȣ ���� ��ư ������ ��й�ȣ ����
		int r = 0;
		
		try {
			String sql = " update member set PASSWORD = ? where userid = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
		    ps.setString(1, vo.getcPwd());
		    ps.setString(2,vo.getcId());
		    r = ps.executeUpdate();
		    
		    
	    
	    r = ps.executeUpdate();
	    ps.close();
	    
	    if(r>0) conn.commit();
	    else conn.rollback();
		}catch(Exception ex) {
			
		}finally {

		return r;
		}
	}
	
	public int Minsert(ManagerVo vo) {//ManagerFrame�� ������ư������ �Ŵ��� ���� ����
		int a=0;
		try {
		
		String sql = " insert into member(userno,userid,password,is_admin,username) values(seq_member.nextval,?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getcId());
	    ps.setString(2,vo.getcPwd());
	    ps.setString(3,String.valueOf(vo.getcCheck()));
	    ps.setString(4, vo.getcName());
		a =ps.executeUpdate();
		
        ps.close();
	} catch (Exception ex) {

	}
	return a;
	
	
	
	
	
	
	
	
	
	}
	
	
	
	public void delete(String id){
		//ManagerFrame�� ���� ��ư ������ ManagerDelete1 �������� ����ǰ� ManagerDelete�� �α��� ��ư������ �� �Լ��� �����
		
		try {

			System.out.println(id);
			String sql = " delete from member where userid=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			ps.executeUpdate();
			

		} catch (Exception ex) {

		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void search(String find) {
    	
		String sql = " select * from member where userid like ? ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + find + "%");
			
			
			
			meta = ps.getMetaData();
			model.setRowCount(0);
			
			int cnt = meta.getColumnCount();
			for(int i=1; i<=cnt; i++) {
				model.addColumn( meta.getColumnName(i));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				row = new Vector();
				for(int i=1; i<=cnt; i++) {
					row.add(rs.getString(i));
				}
				model.addRow(row);
			}
			rs.close();
			ps.close();
            
		} catch (Exception ex) {
			
		}
			
		
		
	}
			
}			
			
			
			
			
		
	
	
	
	
	
	
	
	
	
	
	


