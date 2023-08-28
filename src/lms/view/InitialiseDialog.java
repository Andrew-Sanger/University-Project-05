package lms.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lms.model.LibraryCollection;
import lms.model.facade.LMSModel;
import lms.view.util.Tester;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class InitialiseDialog extends JDialog {

	// --Initiates variables to hold references to the model and view.
	private LMSModel model;
	private MainView view;

	// --Initiates all swing components used in the creation of this Dialog box.
	private JPanel codePanel, namePanel, mainPanel, buttonPanel;
	private JLabel helpText, codeText, nameText;
	private JTextField codeInput, nameInput;
	private JButton confirmButton, cancelButton;

	public InitialiseDialog(JFrame parent, boolean modal, MainView mainView) {
		super(parent, modal);
		this.view = mainView;
		this.model = mainView.getModel();

		// --Creates the JDialog itself, and centers it in the screen.
		setTitle("Initialise Library Collection");
		setSize(400, 180);
		setLocationRelativeTo(null);
		setResizable(false);

		// --Creates the help text at the top of the box.
		helpText = new JLabel(
				"Please enter code and name for new Library Collection: ");
		helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// --Creates a JPanel containing the Collection Code inputs.
		codePanel = new JPanel();
		codeText = new JLabel("Collection Code: ");
		codeInput = new JTextField("Code", 20);
		codePanel.add(codeText);
		codePanel.add(codeInput);

		// --Creates a JPanel containing the Collection Name inputs.
		namePanel = new JPanel();
		nameText = new JLabel("Collection Name: ");
		nameInput = new JTextField("Name", 20);
		namePanel.add(nameText);
		namePanel.add(nameInput);

		// --Adds the code and name input panels into the mainPanel
		mainPanel = new JPanel(new GridLayout(2, 0));
		mainPanel.add(codePanel);
		mainPanel.add(namePanel);

		// --Creates the Confirm/Cancel buttons at the bottom of the JDialog.
		buttonPanel = new JPanel();
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);

		// --Sets the layout of the entire Dialog box to BorderLayout. And then
		// adds all panels to it.
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		add(helpText, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// --When the confirm button is clicked, the user submitted input is
		// used to create a new LibraryCollection in the LMSModel. It also
		// changes the isCollectionInitialised boolean variable to true, which
		// affects most other Dialog boxes in what they display. A new tester
		// class is created and used to set up Test data in the model. Then the
		// view is reset and this Dialog box is set to invisible.
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCollectionCode(codeInput.getText());
				view.setCollectionName(nameInput.getText());
				view.setCollectionInitialised(true);

				model.addCollection(new LibraryCollection(codeInput.getText(),
						nameInput.getText()));
				Tester tester = new Tester();
				tester.setupTestData(model);

				view.resetView();

				setVisible(false);
			}
		});

		// --When the cancel button is pressed, the text inputs are reset, and
		// the Dialog box is made invisible.
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				codeInput.setText("Code");
				nameInput.setText("Name");
				setVisible(false);
			}
		});
	}
}
