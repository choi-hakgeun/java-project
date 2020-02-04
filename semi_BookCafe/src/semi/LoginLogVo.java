package semi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginLogVo {
	int lNo;	//�α��� �ε���/������
	String cId;	//���� ���̵�
	Date lIn;	//�α��� �ð�
	Date lOut;	//�α׾ƿ� �ð�
	int lTime;	//���û��ð�
	int totTime;//�� ���ð�
	int usePrice;
	//long min; // �α׾ƿ� �ð� - �α��� �ð��� �д����� ȯ��
	
	int initprice;//�⺻���
	int addprice;//�߰����
	
	
	
	
	public LoginLogVo() {
		
	}
	
	public LoginLogVo(int ini,int add) {
		this.initprice = ini;
		this.addprice = add;
		
		
		

		
	}
	
	@Override
	public String toString() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str = String.format("%d, %s, %s, %s, %d, %d", lNo, cId, lIn, lOut, lTime, usePrice);
		return str;
	}
	public LoginLogVo(String cId, Date lIn , Date lOut) {
		this.cId = cId;
		this.lIn = lIn;
		this.lOut = lOut;
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
		int hIn = Integer.parseInt(sdf.format(lIn));//�α��� �ð�
		int mIn = Integer.parseInt(sdf2.format(lIn));//�α��� ��
		
		int hOut = Integer.parseInt(sdf.format(lOut));//�α��� �ð�
		int mOut = Integer.parseInt(sdf2.format(lOut));//�α��� ��
		
		//int sOut = lOut.getTime();//�α׾ƿ� �ð�
		//int mOut = sdf2.format(lOut);//�α׾ƿ� ��
		
		//long diff = lOut.getTime() - lIn.getTime();
		//this.min = diff / (1000*60);
		
		
		//System.out.println(diff);
		//System.out.println(min);
		this.lTime = ((hOut*60)+mOut)-((hIn*60)+mIn); // 
	}
	public int getInitprice() {
		return initprice;
	}

	public void setInitprice(int initprice) {
		this.initprice = initprice;
	}

	public int getAddprice() {
		return addprice;
	}

	public void setAddprice(int addprice) {
		this.addprice = addprice;
	}



	public int getUsePrice() {
		return usePrice;
	}

	public void setUsePrice(int usePrice) {
		this.usePrice = usePrice;
	}

	public int getTotTime() {
		return totTime;
	}
	public void setTotTime(int totTime) {
		this.totTime = totTime;
	}
	public int getlNo() {
		return lNo;
	}
	public void setlNo(int lNo) {
		this.lNo = lNo;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public Date getlIn() {
		return lIn;
	}
	public void setlIn(Date lIn) {
		this.lIn = lIn;
	}
	public Date getlOut() {
		return lOut;
	}
	public void setlOut(Date lOut) {
		this.lOut = lOut;
	}
	public int getlTime() {
		return lTime;
	}
	public void setlTime(int lTime) {
		this.lTime = lTime;
	}
}
