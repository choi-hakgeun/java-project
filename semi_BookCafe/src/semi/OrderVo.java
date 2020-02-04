package semi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderVo {
	int oNum;
	Date oDate;
	String oName;
	int oea;
	int oPrice;
	String mId;
	
	public OrderVo() {}
	public OrderVo(int on, String oName, int oea, Date od, int op, String id) {
		this.oNum = on;
		this.oName = oName;
		this.oea = oea;
		this.oDate = od;
		this.oPrice = op;
		this.mId = id;		
	}
	
	public String toString() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = String.format("%d, %s, %d, %s, %d, %s", oNum, oName, oea, sdf.format(oDate), oPrice, mId);
		return str;	
	}
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}	
	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public int getOea() {
		return oea;
	}
	public void setOea(int oea) {
		this.oea = oea;
	}
	public int getoPrice() {
		return oPrice;
	}
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}


}
