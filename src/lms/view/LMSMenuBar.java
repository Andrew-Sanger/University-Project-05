package lms.view;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lms.controller.CreateHoldingController;
import lms.controller.InitialiseResetLibraryController;
import lms.controller.RemoveHoldingController;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class LMSMenuBar extends JMenuBar {

	// --Initiates a view variable to hold reference to the view.
	private MainView view;

	// --Initiates all swing components used in the creation of this JMenuBar.
	private JMenu fileMenu, libraryMenu, holdingsMenu, helpMenu;
	private JMenuItem fileExit, libraryInitReset, holdingAddBook,
			holdingRemoveBook, holdingAddVideo, holdingRemoveVideo, helpAbout;

	public LMSMenuBar(MainView mainView) {
		this.view = mainView;

		// --Creates the File, Library, Holdings and Help menus and their menu
		// items, and sets their mnemonics to appropriate characters.
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileExit = new JMenuItem("Exit");
		fileExit.setMnemonic('E');
		fileMenu.add(fileExit);

		libraryMenu = new JMenu("Library");
		libraryMenu.setMnemonic('L');
		libraryInitReset = new JMenuItem("Initialise/Reset Library");
		libraryInitReset.setMnemonic('I');
		libraryMenu.add(libraryInitReset);

		holdingsMenu = new JMenu("Holdings");
		holdingsMenu.setMnemonic('H');
		holdingAddBook = new JMenuItem("Add Book");
		holdingAddVideo = new JMenuItem("Add Video");
		holdingRemoveBook = new JMenuItem("Remove Book");
		holdingRemoveVideo = new JMenuItem("Remove Video");
		holdingsMenu.add(holdingAddBook);
		holdingsMenu.add(holdingRemoveBook);
		holdingsMenu.add(holdingAddVideo);
		holdingsMenu.add(holdingRemoveVideo);

		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('P');
		helpAbout = new JMenuItem("About");
		helpAbout.setMnemonic('A');
		helpMenu.add(helpAbout);

		// --Adds all menus and their items to the menuBar
		add(fileMenu);
		add(libraryMenu);
		add(holdingsMenu);
		add(helpMenu);

		// --All menus are given the appropriate ActionListeners.
		libraryInitReset
				.addActionListener(new InitialiseResetLibraryController(view));
		holdingAddBook.addActionListener(new CreateHoldingController(view,
				CreateHoldingDialog.NEW_BOOK)); // Initiates with the Book menu
		holdingAddVideo.addActionListener(new CreateHoldingController(view,
				CreateHoldingDialog.NEW_VIDEO)); // Initiates with the Video
													// menu
		holdingRemoveBook.addActionListener(new RemoveHoldingController(view,
				RemoveHoldingDialog.REMOVE_BOOKS)); // Initiates with the Book
													// menu
		holdingRemoveVideo.addActionListener(new RemoveHoldingController(view,
				RemoveHoldingDialog.REMOVE_VIDEOS)); // Initiates with the Video
														// menu

		// --Asks the user if they are sure they wish to exit the program. And
		// if so, closes the program.
		fileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object programClosingPrompt = JOptionPane.showConfirmDialog(
						null, "Are you sure you wish to exit?",
						"Exit program?", JOptionPane.YES_NO_OPTION);

				if (programClosingPrompt.equals(JOptionPane.YES_OPTION))
					System.exit(0);
			}
		});

		// --Creates a small JPanel using a gridLayout that displays information
		// about this program.
		helpAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel aboutPanel = new JPanel(new GridLayout(0, 1));
				aboutPanel.add(new JLabel("CPT221 - Programming 2",
						JLabel.CENTER));
				aboutPanel.add(new JLabel("Assignment 2", JLabel.CENTER));
				aboutPanel
						.add(new JLabel("--------------------", JLabel.CENTER));
				aboutPanel.add(new JLabel("LMS Graphical User Interface",
						JLabel.CENTER));
				aboutPanel
						.add(new JLabel("--------------------", JLabel.CENTER));
				aboutPanel.add(new JLabel("Created by:", JLabel.CENTER));
				aboutPanel.add(new JLabel("Andrew Sanger", JLabel.CENTER));
				aboutPanel.add(new JLabel("s3440468", JLabel.CENTER));
				aboutPanel.add(new JLabel("", JLabel.CENTER));
				aboutPanel.add(new JLabel("Enjoy :)", JLabel.CENTER));
				JOptionPane.showMessageDialog(null, aboutPanel,
						"About this program", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
}
