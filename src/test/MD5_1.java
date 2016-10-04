package test;

import util.MD5Util;

public class MD5_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "tanpeng";
		s = MD5Util.getMD5(MD5Util.getMD5(s));
		System.out.println(s);
	}

}
