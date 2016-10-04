package main;

import javax.swing.UnsupportedLookAndFeelException;

import view_ATM.FirstView_User;


public class Main_User {
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		javax.swing.UIManager
				.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
		FirstView_User firstView_User = new FirstView_User();
	}
}