package semi;


import java.util.Date;

public class ManagerVo {

	int cSerial;
	String cId;
	String cPwd;
	String cEmail;
	Date cBirth;
	String cPhone;
	char cGender;
	Date cDate;
	char cCheck;
	String cName;
	
	
	
	public ManagerVo() {}
	
	public ManagerVo(int cSerial, String cId, String cPwd, String cEmail, Date cBirth, String cPhone, char cGender, Date cDate, char cCheck, String cName ) {
		this.cSerial=cSerial;
		this.cId=cId;
		this.cPwd=cPwd;
		this.cEmail=cEmail;
		this.cBirth=cBirth;
		this.cPhone=cPhone;
		this.cGender=cGender;
		this.cDate=cDate;
		this.cCheck=cCheck;
		this.cName= cName;
	}
				
		
		

	public int getcSerial() {
		return cSerial;
	}

	public void setcSerial(int cSerial) {
		this.cSerial = cSerial;
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

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public Date getcBirth() {
		return cBirth;
	}

	public void setcBirth(Date cBirth) {
		this.cBirth = cBirth;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public char getcGender() {
		return cGender;
	}

	public void setcGender(char cGender) {
		this.cGender = cGender;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public char getcCheck() {
		return cCheck;
	}

	public void setcCheck(char cCheck) {
		this.cCheck = cCheck;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
	
}
