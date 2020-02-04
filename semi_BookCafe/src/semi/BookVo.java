package semi;

public class BookVo {
	String mName;   //å�̸�
	String author;  //����
	String company; //���ǻ�
	int ea;         //����
	int price;		//���Ű���
	int seri;

	
	public BookVo() {} 
	public BookVo(String mName, String author,String company ,int  ea, int price , int seri) { 
			this.mName= mName;
			this.author=author;
			this.company = company;
			this.ea =ea;
			this.price = price;
			this.seri = seri;
		}
	public BookVo(String mName, String author,String company ,int  ea, int price) { 
		this.mName= mName;
		this.author=author;
		this.company = company;
		this.ea =ea;
		this.price = price;

	}		
	
			public String toString() {
				String str = String.format("%15s\t%15s\t%15s\t%4d\t%4d\t%4d\n", mName ,author, company, ea, price,seri);
				return str;
			}
			
			public int getSeri() {
				return seri;
			}
			public void setSeri(int seri) {
				this.seri = seri;
			}
		
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
}
