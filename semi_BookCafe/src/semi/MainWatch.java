package semi;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class MainWatch extends Thread{
	JLabel watch;
	JLabel day;
	boolean a;
	
	MainWatch(JLabel watch,JLabel day,boolean a){
		this.watch = watch;
		this.day = day;
		this.a = a;
	}
	
	@Override
	public void run() {
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		while(a) {
			watch.setText(sdf.format(new Date()));
			day.setText(sdf2.format(new Date()));
		}
	}
}
