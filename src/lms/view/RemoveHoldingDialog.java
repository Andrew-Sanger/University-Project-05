package lms.view;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.model.visitor.HoldingVisitor;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class RemoveHoldingDialog extends JDialog {

	// --These final static variables can be used to decide which remove holding
	// options will be shown when an option is pressed. If the user clicks the
	// remove book button, then RemoveHoldingDialog.REMOVE_BOOK will be passed
	// to
	// the setSelectedHolding method and the bookSelected boolean value will be
	// changed to suit.
	final static int REMOVE_BOOKS = 0;
	final static int REMOVE_VIDEOS = 1;

	private boolean bookSelected = true;

	// --Initiates variables to hold references to the model and view.
	private LMSModel model;
	private MainView view;

	// --Creates a new JList<String> to hold a list of all available
	// Books/Videos that can be removed.
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> holdingList = new JList<String>(listModel);

	// --Initiates all swing components used in the creation of this Dialog box.
	private JPanel helpPanel, radioButtonPanel, buttonPanel;
	private JLabel helpText;
	private JRadioButton removeBook, removeVideo;
	private JButton confirmButton, cancelButton;

	public RemoveHoldingDialog(JFrame parent, boolean modal, MainView mainView) {
		super(parent, modal);
		this.view = mainView;
		model = mainView.getModel();

		// --Creates the JDialog itself and centers it in the middle of the
		// screen.
		setTitle("Remove Holding");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		// --Creates the help text at the top of the box.
		helpPanel = new JPanel(new BorderLayout());
		helpText = new JLabel("Please choose Holdings you wish to remove: ");
		helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// --Creates the JPanel containing the Book and Video radioButtons. This
		// allows the user to switch between the JList of Books, or Videos.
		radioButtonPanel = new JPanel();
		removeBook = new JRadioButton("Book");
		removeVideo = new JRadioButton("Video");
		ButtonGroup holdingGroup = new ButtonGroup();
		holdingGroup.add(removeBook);
		holdingGroup.add(removeVideo);
		radioButtonPanel.add(removeBook);
		radioButtonPanel.add(removeVideo);
		removeBook.setSelected(true);
		helpPanel.add(helpText, BorderLayout.NORTH);
		helpPanel.add(radioButtonPanel, BorderLayout.SOUTH);

		// --Sets the selection mode of the JList so that multiple Holdings can
		// be selected at once.
		holdingList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// --Creates the Confirm/Cancel buttons at the bottom of the JDialog.
		buttonPanel = new JPanel();
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);

		// --Sets the layout of the entire JDialog and adds all panels to it.
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		add(helpPanel, BorderLayout.NORTH);
		add(new JScrollPane(holdingList), BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// --Adds an actionListener to the removeBook and removeVideo radio
		// buttons that changes which Holding is shown. Then calls
		// populateList(), which updates the list with the new holdings.
		removeBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookSelected = true;
				populateList();
			}
		});
		removeVideo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bookSelected = false;
				populateList();
			}
		});

		// --When the confirm button is pressed, a message dialog pops up to
		// ensure the user really wishes to delete the holdings. If so, then it
		// first makes another List which contains the Strings of all selected
		// Holdings that the user wishes to delete. It then goes through the
		// list and converts the String into an integer and removes the Holdings
		// from the model. Finally the view is reset and the JDialog is set to
		// invisible.
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object removeHoldingConfirm = JOptionPane
						.showConfirmDialog(
								null,
								"Are you sure you wish to delete the selected Holdings?",
								"Remove Holdings", JOptionPane.YES_NO_OPTION);
				if (removeHoldingConfirm.equals(JOptionPane.YES_OPTION)) {
					List<String> deletionList = holdingList
							.getSelectedValuesList();

					for (String holdingID : deletionList) {
						int holdingIDInteger = Integer.parseInt(holdingID);
						model.removeHolding(holdingIDInteger);
					}

					JOptionPane.showMessageDialog(null,
							"Holdings successfully removed!");

					view.resetView();

					setVisible(false);
				}
			}
		});

		// --If the cancel button is pressed then the JDialog is set to
		// invisible.
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	// --This method creates a JList of the selected type of Holdings, eg.
	// either Books or Videos. First it creates a array of all holdings in the
	// Model. It then removes all elements from the JList so that it starts off
	// empty. After this, it goes through all Holdings in the array and calls
	// the Visitor class to visit the holding. Depending on whether Books or
	// Videos are being displayed, it calls the appropriate Visitor method
	// (isHoldingBook() or isHoldingVideo()) to see what type the holding is. If
	// the Holding is of the correct type, then its holdingCode is added to the
	// JList and displayed.
	public void populateList() {
		Holding[] holdings = model.getAllHoldings();
		listModel.removeAllElements();

		for (Holding currentHolding : holdings) {
			HoldingVisitor visitor = new HoldingVisitor();
			currentHolding.accept(visitor);

			if (bookSelected) {
				if (visitor.isHoldingBook()) {
					String holdingCode = Integer.toString(currentHolding
							.getHoldingCode());
					listModel.addElement(holdingCode);
				}
			} else {
				if (visitor.isHoldingVideo()) {
					String holdingCode = Integer.toString(currentHolding
							.getHoldingCode());
					listModel.addElement(holdingCode);
				}
			}
		}
	}

	// --This method changes whether Books or Videos are shown in the list. It
	// then calls populateList() to update the JList itself.
	public void setSelectedHolding(int currentlySelected) {
		if (currentlySelected == RemoveHoldingDialog.REMOVE_BOOKS) {
			bookSelected = true;
			populateList();
		} else {
			bookSelected = false;
			populateList();
		}
	}
}
