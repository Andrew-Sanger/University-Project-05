package lms.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lms.model.Book;
import lms.model.Holding;
import lms.model.Video;
import lms.model.facade.LMSModel;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class CreateHoldingDialog extends JDialog {

	// --These final static variables can be used to decide which create holding
	// options will be shown when an option is pressed. If the user clicks the
	// create book button, then CreateHoldingDialog.NEW_BOOK will be passed to
	// the setSelectedHolding method and the bookSelected boolean value will be
	// changed to suit.
	final static int NEW_BOOK = 0;
	final static int NEW_VIDEO = 1;

	private boolean bookSelected = true;

	// --Initiates variables to hold references to the model and view.
	private LMSModel model;
	private MainView view;

	// --Initiates all swing components used in the creation of this Dialog box.
	// This dialog box uses a cardLayout to either show the Book creation menu
	// or the Video creation menu, depending on what the user selects. A
	// JComboBox<String> is used to hold the two possible values of a video fee
	// to choose from.
	private JPanel helpPanel, radioButtonPanel, mainPanel, bookPanel,
			videoPanel, bookCodePanel, bookNamePanel, videoCodePanel,
			videoNamePanel, videoFeePanel, buttonPanel;
	private JLabel helpText, bookCodeText, bookNameText, videoCodeText,
			videoNameText, videoFeeText;
	private JRadioButton createBook, createVideo;
	private JTextField bookCodeInput, bookNameInput, videoCodeInput,
			videoNameInput;
	private JButton confirmButton, cancelButton;
	private JComboBox<String> videoFee;
	private CardLayout mainLayout;

	public CreateHoldingDialog(JFrame parent, boolean modal, MainView mainView) {
		super(parent, modal);
		this.view = mainView;
		model = mainView.getModel();

		// --Creates the JDialog itself, and centers it in the screen.
		setTitle("Create new Holding");
		setSize(400, 250);
		setLocationRelativeTo(null);
		setResizable(false);

		// --Creates the help text at the top of the box
		helpPanel = new JPanel(new BorderLayout());
		helpText = new JLabel("Please choose Holding you wish to create: ");
		helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// --Creates the JPanel containing the Book and Video radioButtons. This
		// allows the user to switch between CardLayouts with ease.
		radioButtonPanel = new JPanel();
		createBook = new JRadioButton("Book");
		createVideo = new JRadioButton("Video");
		ButtonGroup holdingGroup = new ButtonGroup();
		holdingGroup.add(createBook);
		holdingGroup.add(createVideo);
		radioButtonPanel.add(createBook);
		radioButtonPanel.add(createVideo);
		createBook.setSelected(true);
		helpPanel.add(helpText, BorderLayout.NORTH);
		helpPanel.add(radioButtonPanel, BorderLayout.SOUTH);

		// --Create the CardLayout panel displaying Book input information
		bookCodePanel = new JPanel();
		bookCodeText = new JLabel("Code: ");
		bookCodeInput = new JTextField("0", 20);
		bookCodePanel.add(bookCodeText);
		bookCodePanel.add(bookCodeInput);

		bookNamePanel = new JPanel();
		bookNameText = new JLabel("Name: ");
		bookNameInput = new JTextField("", 20);
		bookNamePanel.add(bookNameText);
		bookNamePanel.add(bookNameInput);

		bookPanel = new JPanel(new GridLayout(3, 0));
		bookPanel.add(bookCodePanel);
		bookPanel.add(bookNamePanel);

		// --Create the CardLayout panel displaying Video input information
		videoCodePanel = new JPanel();
		videoCodeText = new JLabel("Code: ");
		videoCodeInput = new JTextField("0", 20);
		videoCodePanel.add(videoCodeText);
		videoCodePanel.add(videoCodeInput);

		videoNamePanel = new JPanel();
		videoNameText = new JLabel("Name: ");
		videoNameInput = new JTextField("", 20);
		videoNamePanel.add(videoNameText);
		videoNamePanel.add(videoNameInput);

		videoFeePanel = new JPanel();
		videoFeeText = new JLabel("Fee: ");
		videoFee = new JComboBox<String>(new String[] { "$4.00", "$6.00" });
		videoFeePanel.add(videoFeeText);
		videoFeePanel.add(videoFee);

		videoPanel = new JPanel(new GridLayout(3, 0));
		videoPanel.add(videoCodePanel);
		videoPanel.add(videoNamePanel);
		videoPanel.add(videoFeePanel);

		// --Adds the Book and Video panels to the mainPanel of the Dialog.
		mainLayout = new CardLayout(20, 10);
		mainPanel = new JPanel(mainLayout);
		mainPanel.add(bookPanel, "book");
		mainPanel.add(videoPanel, "video");

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

		add(helpPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// --Whenever the createBook RadioButton is pressed, the card layout is
		// changed to show the Book options.
		createBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedHolding(NEW_BOOK);
			}
		});

		// --Whenever the createVideo RadioButton is pressed, the card layout is
		// changed to show the Video options.
		createVideo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setSelectedHolding(NEW_VIDEO);
			}
		});

		// --When the confirm button is clicked it first checks which
		// RadioButton is pressed and decides whether to create a Book or Video
		// Holding. It then runs the entered inputs through the isCodeValid and
		// isNameValid methods to see whether they are correct input. If they
		// are correct, then a holding is attempted to be created. If
		// successful, and appropriate message is displayed, and if not then the
		// holding number must already exist, so another appropriate message is
		// displayed warning the user.
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bookSelected == true) {
					int bookCode = Integer.parseInt(bookCodeInput.getText());
					String bookName = bookNameInput.getText();

					if (isCodeValid(bookCode) && isNameValid(bookName)) {
						Holding newBook = new Book(bookCode, bookName);
						boolean isCreated = model.addHolding(newBook);

						view.resetView();

						// Checks whether Holding was successfully created
						if (isCreated) {
							JOptionPane.showMessageDialog(null,
									"New Book Holding successfully created!");
						} else
							JOptionPane
									.showMessageDialog(null,
											"Holding code already exists, please try a different code");

						// Calls the resetHoldingInputs method which resets
						// inputs so Dialog starts fresh.
						resetHoldingInputs();
						setVisible(false);
					} else if (isCodeValid(bookCode) == false)
						JOptionPane.showMessageDialog(null,
								"Invalid Book code entered, must be 7 digits!");
					else
						JOptionPane
								.showMessageDialog(null,
										"Invalid Book name entered, must be a minimum of 3 characters!");
				} else {
					int videoCode = Integer.parseInt(videoCodeInput.getText());
					String videoName = videoNameInput.getText();
					int videoFeeAmount;

					if (videoFee.getSelectedIndex() == 0)
						videoFeeAmount = 4;
					else
						videoFeeAmount = 6;

					if (isCodeValid(videoCode) && isNameValid(videoName)) {
						Holding newVideo = new Video(videoCode, videoName,
								videoFeeAmount);
						boolean isCreated = model.addHolding(newVideo);

						view.resetView();

						if (isCreated)
							JOptionPane.showMessageDialog(null,
									"New Video Holding successfully created!");
						else
							JOptionPane
									.showMessageDialog(null,
											"Holding code already exists, please try a different code");

						resetHoldingInputs();
						setVisible(false);
					} else if (isCodeValid(videoCode) == false) {
						JOptionPane
								.showMessageDialog(null,
										"Invalid Video code entered, must be 7 digits!");
					} else
						JOptionPane
								.showMessageDialog(null,
										"Invalid Video name entered, must be a minimum of 3 characters!");
				}
			}
		});

		// --When the cancel button is pressed, all inputs are reset, and the
		// visibility of the JDialog is turned off.
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetHoldingInputs();
				setVisible(false);
			}
		});
	}

	// --This method takes the user entered holdingCode and checks whether it is
	// a valid number, then returns the result
	public boolean isCodeValid(int holdingCode) {
		if (holdingCode >= 1000000 && holdingCode <= 9999999)
			return true;
		else
			return false;
	}

	// --This method takes the user entered holdingName and checks whether it
	// has the right number of characters, and returns the results.
	public boolean isNameValid(String holdingName) {
		if (holdingName.length() >= 3)
			return true;
		else
			return false;
	}

	// --This method resets the inputs on the Dialog so they no longer hold the
	// values entered by the user.
	public void resetHoldingInputs() {
		bookCodeInput.setText("0");
		bookNameInput.setText("");
		videoCodeInput.setText("0");
		videoNameInput.setText("");
	}

	// --This method changes which CardLayout panel is displayed, either the
	// Book or Video panel depending on which final static variable is passed to
	// it.
	public void setSelectedHolding(int currentlySelected) {
		if (currentlySelected == NEW_BOOK) {
			mainLayout.show(mainPanel, "book");
			createBook.setSelected(true);
			bookSelected = true;
		} else {
			mainLayout.show(mainPanel, "video");
			createVideo.setSelected(true);
			bookSelected = false;
		}
	}
}
