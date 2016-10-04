package test;

import java.io.IOException;

public class Test1 {
	public static void main(String[] args) {

		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://money.eastmoney.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
