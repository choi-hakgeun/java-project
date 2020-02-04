package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class FoodDao {
	Connection conn;
	
public FoodDao() {
	conn = DBConn.getConn();
}
public int Insert(FoodVo vo) { // ���� �߰�
	int r = 0;
	try {
		String sql = "insert into Food values(seq_Food.nextval,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getfName());
		ps.setInt(2, vo.getInPrice());
		ps.setInt(3, vo.getSalPrice());
		ps.setInt(4, vo.getfEa());
		ps.setString(5, vo.getSales());
		conn.setAutoCommit(false);
		
		r = ps.executeUpdate();
		if(r>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		ps.close();
		conn.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		return r;
	}
}

public int Delete(int fNo) {// ���� ����
	int r = 0;
	try {
		String sql = "delete from Food where FoodNo=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, fNo);
		
		r = ps.executeUpdate();
		conn.setAutoCommit(false);
		if(r>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		ps.close();
		conn.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		return r;
	}
}

public FoodVo Search(int fNo) { // ����/���� �Ҷ� �˻�
	FoodVo vo = null;
	try {
		String sql = "select FoodNo, FoodName, InComePrice, SalesPrice, Ea, Is_Sales from Food where FoodNo=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, fNo);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			vo = new FoodVo();
			vo.setfName(rs.getString("FoodName"));
			vo.setInPrice(rs.getInt("InComePrice"));
			vo.setSalPrice(rs.getInt("SalesPrice"));
			vo.setfEa(rs.getInt("Ea"));
			vo.setSales(rs.getString("Is_Sales"));
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		return vo;
	}
}

public int Update(FoodVo vo) {// ���� ����
	int r = 0;
	try {
		String sql = "update Food set FoodName=?, InComePrice=?, SalesPrice=?, Ea=?, Is_Sales=? where FoodNo=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getfName());
		ps.setInt(2, vo.getInPrice());
		ps.setInt(3, vo.getSalPrice());
		ps.setInt(4, vo.getfEa());
		ps.setString(5, vo.getSales());
		ps.setInt(6, vo.getfNo());
		
		r = ps.executeUpdate();
		conn.setAutoCommit(false);
		if(r>0) {
			conn.commit();
		}else {
			conn.rollback();
		}
		ps.close();
		conn.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		return r;
	}
}

public List<FoodVo> Select(String tFind){ // ���� ��ȸ
	List<FoodVo> list = new ArrayList<FoodVo>();
	try {
	String sql = " select FoodNo, FoodName, InComePrice, SalesPrice, Ea, Is_Sales from Food where FoodName like ? " +
				"ORDER BY FoodNo";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + tFind + "%");
		DefaultTableModel model = new DefaultTableModel();
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			FoodVo vo = new FoodVo();
			vo.setfNo(rs.getInt("foodNo"));
			vo.setfName(rs.getString("foodName"));
			vo.setInPrice(rs.getInt("InComePrice"));
			vo.setSalPrice(rs.getInt("SalesPrice"));
			vo.setfEa(rs.getInt("Ea"));
			vo.setSales(rs.getString("Is_Sales"));
			
			list.add(vo);
		}
		rs.close();
		ps.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
		return list;
	}
}

public List<FoodVo> list(){// ó�� ȭ�� ���ö� ��� ��� ��Ÿ���� 
	List<FoodVo> list = new ArrayList<FoodVo>();
	try {
		String sql = "select * from Food ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			FoodVo vo = new FoodVo();
			vo.setfNo(rs.getInt("foodNo"));
			vo.setfName(rs.getString("foodName"));
			vo.setInPrice(rs.getInt("InComePrice"));
			vo.setSalPrice(rs.getInt("SalesPrice"));
			vo.setfEa(rs.getInt("Ea"));
			vo.setSales(rs.getString("Is_Sales"));
			
			list.add(vo);
		}
	}catch(Exception ex) {
		ex.printStackTrace();
	} finally {
		return list;
	}
}

}
