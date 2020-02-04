package semi;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {//모듈화를 위해 만든 클래스
	
	
	public static Connection getConn() {//정적으로 만들지 않았다면 Connection conn = new DBConn.getConn();으로 BDConn을 새롭게 생성해서 사용해도 되지만 정적으로 만들어서 굳이 DBConn을 새롭게 만들지않고 쓰겠다 
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.27:1521:xe";
		String dbUser ="semi";
		String dbPwd ="1234";
		Connection conn = null;
		try {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url,dbUser,dbPwd);
		}catch(Exception ex) {
			
		}
		return conn;
	}
}
