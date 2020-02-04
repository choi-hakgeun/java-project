package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDao {
	Connection conn;

	public GuestDao() {
		conn = DBConn.getConn();
	}

	public List<GuestVo> select(String find) { //select占쌨소드를 占쏙옙占쏙옙臼占� 占쏙옙회占싸븝옙 占쌜쇽옙
		List<GuestVo> list = new ArrayList<GuestVo>();

		String sql = " select * from Book " + " where mName like ? or author like ?";

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
				GuestVo vo = new GuestVo(mName, author, company, ea);
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
