package semi;

import java.util.Date;

public class UserVo {
	int    serial;	//�ø����ȣ
	String cId;		//���̵�
	String cPwd;	//�н�����
	String cEmail;	//�̸���
	Date   cBirth;	//�������
	String cPhone;	//�ڵ�����ȣ
	String cGender;	//����
	Date   cDate;	//���Գ�¥
	String is_Admin;//������üũ
	String cName;	//�̸�
	

	
	public UserVo() {
		
	}
	//ȸ�� ���̵� �н����� ã�⸦ ���� ������
	public UserVo(String cName,String cEmail , String cPhone) {
		this.cName = cName;
		this.cEmail = cEmail;
		this.cPhone = cPhone;
	}
	
	
	//ȸ�� ������ ���̵� �ߺ���ȸ �� ������
	public UserVo(String cId) {
		this.cId = cId;
	}
	//ȸ�� ���̵��й�ȣ ��ȸ�� ������ + ȸ�� ���̵� �н�����ã�⸦ ���� ������
	public UserVo(String cId,String cPwd) {
		this.cId = cId;
		this.cPwd = cPwd;
	}
	//ȸ�� ���Կ� ������
	public UserVo(int serial, String cId, String cPwd, String cEmail, Date cBirth,String cPhone,String cGender,Date cDate,String is_Admin,String cName) {
		this.serial = serial;
		this.is_Admin = is_Admin;
		this.cId = cId;
		this.cPwd = cPwd;
		this.cName = cName;
		this.cPhone = cPhone;
		this.cBirth = cBirth;
		this.cEmail = cEmail;
		this.cGender = cGender;
		this.cDate = cDate;
	}
	public String toString() {
		String temp = String.format("%15s\t%15s\n", cId , cPwd);
		return temp;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getIs_Admin() {
		return is_Admin;
	}
	public void setIs_Admin(String is_Admin) {
		this.is_Admin = is_Admin;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcPwd() {
		return cPwd;
	}
	public void setcPwd(String cPwd) {
		this.cPwd = cPwd;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public Date getcBirth() {
		return cBirth;
	}
	public void setcBirth(Date cBirth) {
		this.cBirth = cBirth;
	}
	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	public String getcGender() {
		return cGender;
	}
	public void setcGender(String cGender) {
		this.cGender = cGender;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
}
