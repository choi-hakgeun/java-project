package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ARRAY;

public class BookDao {
	Connection conn;

	public BookDao() {
		conn = DBConn.getConn(); 
	}
		//insert 메소드
	public int insert(BookVo vo) { 
		int r = 0;

		try {
			String sql = "insert into Book values(seq_book.nextval,? ,? ,? ,? , ? )";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getmName());
			ps.setString(2, vo.getAuthor());
			ps.setString(3, vo.getCompany());
			ps.setInt(4, vo.getEa()); 
			ps.setInt(5, vo.getPrice());
			
			conn.setAutoCommit(false);

			r = ps.executeUpdate();
			if (r > 0)
				conn.commit();
			else 
				conn.rollback();

			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return r;
		}

	}

	// delete 메소드
	public int delete(String mName , int b) {
		int r = 0;

		try {
			String sql = "delete from Book where bookno=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, b);
			conn.setAutoCommit(false);
			r = ps.executeUpdate();
			if (r > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return r;
		}
	}

	// search 메소드 
	
	public List<BookVo> search(String mName) {
		BookVo vo = null;
		List<BookVo> list = new ArrayList<BookVo>();
		String sql = "select * from Book where bookname like ?"; 
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + mName + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vo = new BookVo();
				vo.setSeri(rs.getInt("BOOKNO"));
				vo.setmName(rs.getString("bookname"));
				vo.setAuthor(rs.getString("author"));
				vo.setCompany(rs.getString("publishing"));
				vo.setEa(rs.getInt("count"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}

			rs.close(); 
			ps.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return list;
		}
	}

	/// update메소드.
	
	public int update(BookVo vo) {
		int r = 0;
		try {
			
			String sql = "update book set  author = ?, publishing = ?, count =? , price = ? " + " where BOOKNO=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getAuthor());
			ps.setString(2, vo.getCompany());
			ps.setInt(3, vo.getEa());
			ps.setInt(4, vo.getPrice());
			ps.setInt(5, vo.getSeri());
			
			r = ps.executeUpdate();
			
			/*ps.close();
			conn.close();*/
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return r;
		}
	}

	//  select메소드

		
	public List<BookVo> select(String find) { 
		List<BookVo> list = new ArrayList<BookVo>();
	
		String sql = " select * from Book " + " where bookname like ? or author like ?";
																						
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + find + "%"); 
												
			ps.setString(2, "%" + find + "%");
			ResultSet rs = ps.executeQuery(); 

			while (rs.next()) {
				String mName = rs.getString("bookname");
				String author = rs.getString("author");
				String company = rs.getNString("publishing");
				int ea = rs.getInt("count");
				int price = rs.getInt("price");
				BookVo vo = new BookVo(mName, author, company, ea, price);
				list.add(vo);
			}

			rs.close(); 
			ps.close();
			conn.close();

		} catch (Exception ex) {

		} finally {
			return list;
		}

	}
		

}

