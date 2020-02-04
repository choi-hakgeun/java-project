package semi;

public class FoodVo {
	int fNo; // 음식 번호	
	String fName; // 음식 이름
	int InPrice; // 입고 금액
	int SalPrice; // 판매 금액
	int fEa; // 재고 수량
	String sales; // 판매/미판매
	
	public FoodVo ( ) {		}
	
    public FoodVo(String fName, int SalPrice) {//주문하기 프레임에 음식명, 가격 보여주기
	   this.fName = fName;
	   this.SalPrice = SalPrice;
	   }
	
	public FoodVo(int fNo, String fName, int InPrice, int SalPrice, int fEa, String sales) {
		this.fNo = fNo;
		this.fName = fName;
		this.InPrice = InPrice;
		this.SalPrice = SalPrice;
		this.fEa = fEa;
		this.sales = sales;
	}
	
	public String toString() {
		String temp = String.format("%10d\t%15s\t%10d\t%10d\t%10d\t%10s\n", fNo, fName, InPrice, SalPrice, fEa, sales);
		return temp;
	}

	public int getfNo() {
		return fNo;
	}

	public void setfNo(int fNo) {
		this.fNo = fNo;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public int getInPrice() {
		return InPrice;
	}

	public void setInPrice(int inPrice) {
		InPrice = inPrice;
	}

	public int getSalPrice() {
		return SalPrice;
	}

	public void setSalPrice(int salPrice) {
		SalPrice = salPrice;
	}

	public int getfEa() {
		return fEa;
	}

	public void setfEa(int fEa) {
		this.fEa = fEa;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

}
