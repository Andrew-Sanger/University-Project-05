package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class SortArrayController implements ActionListener {

	// --Initiate class variables
	private MainView view;

	// --Class constructor
	public SortArrayController(MainView view) {
		this.view = view;
	}

	// --Whenever the sort options are changed, the view is reset. This sorts
	// the grid. It only attempts to reset the view if a library collection has
	// been created though.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (view.isCollectionInitialised())
			view.resetView();
	}
}
