package lms.view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lms.model.facade.LMSModel;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class StatusBar extends JPanel {

	// --Initiates variables to hold references to the model and view.
	private MainView view;
	private LMSModel model;

	// --This variable contains the status text.
	private JLabel currentStatus;

	public StatusBar(MainView mainView) {
		this.view = mainView;
		this.model = view.getModel();

		setLayout(new BorderLayout());

		// --Creates the status JLabel, and gives it an empty border so that it
		// has some space around it and is properly readable. It then calls the
		// createStatus() method which updates the text inside the JLabel.
		currentStatus = new JLabel();
		currentStatus.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		createStatus();

		// --Adds the status text to the bottom left of the MainView window.
		add(currentStatus, BorderLayout.WEST);
	}

	// --This class updates the status text by creating a new string depending
	// on whether a Library Collection is initialized or not.
	public void createStatus() {
		if (view.isCollectionInitialised() == true) {
			currentStatus.setText("Collection Code: "
					+ view.getCollectionCode() + " || Collection Name: "
					+ view.getCollectionName() + " || Total Books: "
					+ model.countBooks() + " || Total Videos: "
					+ model.countVideos());
		} else {
			currentStatus
					.setText("No Library Collection has been created, please " +
							"initialise new Library Collection.");
		}
	}
}
