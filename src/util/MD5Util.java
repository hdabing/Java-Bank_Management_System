package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static void main(String[] args) {
		String pwd = MD5Util.getMD5(MD5Util.getMD5("tanpeng"));
		System.out.println(pwd);
	}

	private static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	// 字符串加�?
	public static String getMD5(String source) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.update(source.getBytes());
		byte[] b = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byteHEX(b[i]));
		}

		// sb.setCharAt(sb.length()-1, (char)(sb.charAt(sb.length()-1)+1));
		return sb.toString();
	}
}
