package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lms.view.CreateHoldingDialog;
import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class CreateHoldingController implements ActionListener {

	// --Initiate class variables, currentlySelected holds the value of the
	// final static variables held in the CreateHoldingDialog class to decide
	// which setting (either book or video) to start with when opening the
	// dialog box. Eg, if you decide to create a new book, the book creation
	// menu will be displayed first, as opposed to the video one.
	private MainView view;
	private int currentlySelected;

	// --Class constructor
	public CreateHoldingController(MainView mainView, int holdingType) {
		this.view = mainView;
		this.currentlySelected = holdingType;
	}

	// --This gets the createHoldingDialog from the MainView, then sets the
	// creation menu to either book or video depending on the value of
	// currentlySelected. It then checks to see if a LibraryCollection has been
	// Initialized and if not shows a warning message, and will not allow
	// Holdings to be created. If one has been created then the
	// CreateHoldingDialog will be set to visible and the user will be able to
	// create a new Holding.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreateHoldingDialog dialogBox = view.getCreateHoldingDialog();
		dialogBox.setSelectedHolding(currentlySelected);

		if (view.isCollectionInitialised()) {
			dialogBox.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null,
					"No Library Collection has been created, please create a "
							+ "Collection before creating a Holding!");
		}
	}
}
