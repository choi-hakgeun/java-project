package semi;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesVo {
	private int salesNo;
	private Date adjustmentDT;
	private int totalUser;
	private int totalPrice;
	private int totalOrder;
	private int profit;
	
	public SalesVo() {}
	public SalesVo(int totalUser, int totalPrice, int profit, Date adjustmentDT) {
		this.totalUser = totalUser;
		this.totalPrice = totalPrice;
		this.profit = profit;
		this.adjustmentDT = adjustmentDT;
	}
	
	// 엑셀 파일 저장을 위하여 지정해준 String format
	@Override
	public String toString() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		str = String.format("%s, %d, %d, %d", sdf.format(adjustmentDT), totalUser, totalPrice, profit);
		return str;
	}
	public int getTotalOrder() {
		return totalOrder;
	}
	
	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}
	
	public int getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(int salesNo) {
		this.salesNo = salesNo;
	}

	public Date getAdjustmentDT() {
		return adjustmentDT;
	}

	public void setAdjustmentDT(Date adjustmentDT) {
		this.adjustmentDT = adjustmentDT;
	}

	public int getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	
}
