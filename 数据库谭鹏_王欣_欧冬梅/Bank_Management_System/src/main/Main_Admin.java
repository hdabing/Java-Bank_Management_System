package main;

import javax.swing.UnsupportedLookAndFeelException;

import view_Admin.LoginView_Admin;


public class Main_Admin {

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		javax.swing.UIManager
				.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		LoginView_Admin loginView_Admin = new LoginView_Admin();
	}

}