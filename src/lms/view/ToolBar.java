package lms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lms.controller.CreateHoldingController;
import lms.controller.InitialiseResetLibraryController;
import lms.controller.RemoveHoldingController;
import lms.controller.SortArrayController;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class ToolBar extends JPanel {

	// --Initiates a variable to hold a reference to the view.
	private MainView view;

	// --Initiates all swing components used in the creation of this ToolBar.
	private JPanel buttonPanel, sortPanel, sortPanelTop, sortPanelBottom;
	private JButton resetLibrary, addBook, removeBook, addVideo, removeVideo;
	private ImageIcon resetLibraryIcon, addBookIcon, removeBookIcon,
			addVideoIcon, removeVideoIcon;
	private JLabel sortLabel;
	private JRadioButton noSort, codeSort, typeSort;

	public ToolBar(MainView mainView) {
		this.view = mainView;

		setLayout(new BorderLayout());

		// --Creates the buttonPanel, which holds all buttons. Creates the
		// buttons which have icons and toolTipText.
		buttonPanel = new JPanel(new FlowLayout());

		resetLibraryIcon = new ImageIcon("images/lms-icons/collection.png");
		addBookIcon = new ImageIcon("images/lms-icons/book_add.png");
		removeBookIcon = new ImageIcon("images/lms-icons/book_delete.png");
		addVideoIcon = new ImageIcon("images/lms-icons/film_add.png");
		removeVideoIcon = new ImageIcon("images/lms-icons/film_delete.png");

		resetLibrary = new JButton(resetLibraryIcon);
		resetLibrary
				.setToolTipText("Create new Library Collection, or Reset created Library Collection");
		addBook = new JButton(addBookIcon);
		addBook.setToolTipText("Add a new Book Holding");
		removeBook = new JButton(removeBookIcon);
		removeBook.setToolTipText("Remove a Book Holding");
		addVideo = new JButton(addVideoIcon);
		addVideo.setToolTipText("Add a new Video Holding");
		removeVideo = new JButton(removeVideoIcon);
		removeVideo.setToolTipText("Remove a Video Holding");

		buttonPanel.add(resetLibrary);
		buttonPanel.add(addBook);
		buttonPanel.add(removeBook);
		buttonPanel.add(addVideo);
		buttonPanel.add(removeVideo);

		// --Creates the sortPanel, which holds the sort radio buttons and a
		// description above. Also adds the radio buttons to a button group so
		// that two buttons aren't pressed at the same time.
		sortPanel = new JPanel(new GridLayout(2, 1));

		sortPanelTop = new JPanel(new BorderLayout());
		sortLabel = new JLabel("SORT ORDER:");
		sortPanelTop.add(sortLabel, BorderLayout.WEST);

		sortPanelBottom = new JPanel(new GridLayout(1, 0));
		noSort = new JRadioButton("NONE");
		codeSort = new JRadioButton("CODE");
		typeSort = new JRadioButton("TYPE");
		ButtonGroup sortGroup = new ButtonGroup();
		noSort.setSelected(true);
		sortGroup.add(noSort);
		sortGroup.add(codeSort);
		sortGroup.add(typeSort);
		sortPanelBottom.add(noSort);
		sortPanelBottom.add(codeSort);
		sortPanelBottom.add(typeSort);

		sortPanel.add(sortPanelTop);
		sortPanel.add(sortPanelBottom);

		// --Adds the buttonPanel to the left side of the screen, and the
		// sortPanel to the right side of the screen using BorderLayout.
		add(buttonPanel, BorderLayout.WEST);
		add(sortPanel, BorderLayout.EAST);

		// --Adds ActionListeners to both the buttons and the sort radio
		// buttons.
		resetLibrary.addActionListener(new InitialiseResetLibraryController(
				view));
		addBook.addActionListener(new CreateHoldingController(view,
				CreateHoldingDialog.NEW_BOOK));
		addVideo.addActionListener(new CreateHoldingController(view,
				CreateHoldingDialog.NEW_VIDEO));
		removeBook.addActionListener(new RemoveHoldingController(view,
				RemoveHoldingDialog.REMOVE_BOOKS));
		removeVideo.addActionListener(new RemoveHoldingController(view,
				RemoveHoldingDialog.REMOVE_VIDEOS));
		noSort.addActionListener(new SortArrayController(view));
		codeSort.addActionListener(new SortArrayController(view));
		typeSort.addActionListener(new SortArrayController(view));
	}

	// --Accessors for the sort radio buttons, which allow other parts of the
	// view to know which button is currently pressed.
	public JRadioButton getTypeSort() {
		return typeSort;
	}

	public JRadioButton getCodeSort() {
		return codeSort;
	}

	public JRadioButton getNoSort() {
		return noSort;
	}

}
