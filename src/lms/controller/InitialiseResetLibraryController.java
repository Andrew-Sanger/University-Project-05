package lms.controller;

import java.awt.event.*;

import javax.swing.JOptionPane;

import lms.model.Holding;
import lms.view.InitialiseDialog;
import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class InitialiseResetLibraryController implements ActionListener {

	// --Initiate class variables
	private MainView view;

	// --Class Constructor
	public InitialiseResetLibraryController(MainView mainView) {
		this.view = mainView;
	}

	// --This gets the InitialiseDialog from the MainView, and then checks to
	// see whether a LibraryCollection has been initialized. If not, it sets the
	// InitialiseDialog to visible and allows user to initialize a new Library
	// Collection. If a collection has been initialized, then it shows a prompt
	// asking the user whether they want to reset the Collection with no
	// Holdings. If the user wishes to do so, then all Holdings are removed from
	// the LMSModel and the MainView is reset, to show these changes.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		InitialiseDialog dialogBox = view.getInitialiseDialog();

		if (view.isCollectionInitialised() == false) {
			dialogBox.setVisible(true);
		} else {
			Object resetLibraryPrompt = JOptionPane.showConfirmDialog(null,
					"Are you sure you wish to reset the Library Collection",
					"Reset Library Collection?", JOptionPane.YES_NO_OPTION);
			if (resetLibraryPrompt.equals(JOptionPane.YES_OPTION)) {
				Holding[] allHoldings = view.getModel().getAllHoldings();

				for (Holding currentHolding : allHoldings) {
					view.getModel().removeHolding(
							currentHolding.getHoldingCode());
				}

				view.resetView();
			}
		}
	}
}
