package lms.view.main;

import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class LMSDriver {

	public static void main(String[] args) {
		MainView mainView = new MainView();
		mainView.setVisible(true);
	}
}

// --This driver class initiates the GUI MVC and is the only part of the program
// that needs to be run to get it to work.