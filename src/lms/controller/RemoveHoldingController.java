package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lms.view.MainView;
import lms.view.RemoveHoldingDialog;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class RemoveHoldingController implements ActionListener {

	// --Initiate class variables, currentlySelected holds the value of the
	// final static variables held in the RemoveHoldingDialog class to decide
	// which setting (either book or video) to start with when opening the
	// dialog box. Eg, if you decide to remove books, the book removal
	// menu will be displayed first, as opposed to the video one.
	private MainView view;
	private int currentlySelected;

	// --Class constructor
	public RemoveHoldingController(MainView mainView, int holdingType) {
		this.view = mainView;
		this.currentlySelected = holdingType;
	}

	// --This gets the RemoveHoldingDialog from the MainView, and then checks to
	// see whether a LibraryCollection has been initialized. If so then the
	// dialog is set to visible and the user can remove Holdings as they wish.
	// If not then a warning message is shown telling the user to create a new
	// Library Collection.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		RemoveHoldingDialog dialogBox = view.getRemoveHoldingDialog();

		if (view.isCollectionInitialised()) {
			dialogBox.setSelectedHolding(currentlySelected);
			dialogBox.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null,
					"No Library Collection has been created, please create a "
							+ "Collection before removing Holdings!");
		}
	}
}
