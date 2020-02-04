package semi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CreateFile {
	String separator = File.separator;
	String filePath = "c:" + separator + "txtBackUp";
	String currentDate = "";
	
	public void createLoginLog() throws IOException {
		// LoginLog 백업
		File dirLogin = new File(filePath + separator + "LoginLog");
		if (!dirLogin.exists())
			dirLogin.mkdir();

		// true면 파일 이어쓰기를 함
		BufferedWriter lbw = new BufferedWriter(new FileWriter(
				filePath + separator + "LoginLog" + separator + "LoginLog_" + currentDate + ".csv", true));

		// 컬럼명 설정
		lbw.write("No, 사용자ID, 로그인 시간, 로그아웃 시간, 총 사용시간, 총 이용금액");
		lbw.newLine();

		// 데이터 저장
		LoginLogDao lDao = new LoginLogDao();
		List<LoginLogVo> llist = lDao.select();
		for (LoginLogVo vo : llist) {
			lbw.write(vo.toString());
			lbw.newLine();
		}

		lbw.flush();
		lbw.close();
	}

	public void createOrderLog() throws IOException {
		// OrderLog 백업
		File dirOrder = new File(filePath + separator + "OrderLog");
		if (!dirOrder.exists())
			dirOrder.mkdir();

		// true면 파일 이어쓰기를 함
		BufferedWriter obw = new BufferedWriter(new FileWriter(
				filePath + separator + "OrderLog" + separator + "OrderLog_" + currentDate + ".csv", true));

		// 컬럼명 설정
		obw.write("No, 음식명, 주문 수량, 주문 시간, 주문 금액, 주문자ID");
		obw.newLine();

		// 데이터 저장
		OrderDao oDao = new OrderDao();
		List<OrderVo> olist = oDao.OrderList();
		for (OrderVo vo : olist) {
			obw.write(vo.toString());
			obw.newLine();
		}

		obw.flush();
		obw.close();
	}

	public void createSalesLog() throws IOException {
		// SaleseLog 백업
		File dirSalse = new File(filePath + separator + "SalseLog");
		if (!dirSalse.exists())
			dirSalse.mkdir();

		// true면 파일 이어쓰기를 함
		BufferedWriter sbw = new BufferedWriter(new FileWriter(
				filePath + separator + "SalseLog" + separator + "SalseLog_" + currentDate + ".csv", true));

		// 컬럼명 설정
		sbw.write("날짜, 이용자 수, 총 매출액, 순이익");
		sbw.newLine();

		// 데이터 저장
		SalesDao sDao = new SalesDao();
		List<SalesVo> slist = sDao.select();
		for (SalesVo vo : slist) {
			sbw.write(vo.toString());
			sbw.newLine();
		}

		sbw.flush();
		sbw.close();
	}

	public boolean createFile(String currentDate) {
		boolean result = true; // 저장 결과를 알려줌
		this.currentDate = currentDate;
		
		// 백업파일 저장할 경로 - 있으면 생성 X
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdir();

		try {
			createLoginLog();
			createOrderLog();
			createSalesLog();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			return result;
		}
	}
}
