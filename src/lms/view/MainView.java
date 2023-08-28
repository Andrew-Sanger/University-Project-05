package lms.view;

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lms.model.facade.LMSFacade;
import lms.model.facade.LMSModel;
import lms.view.grid.HoldingGrid;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class MainView extends JFrame {

	// --Initiates variables to hold a reference to the LMSModel that is used
	// throughout the entire GUI, the collections Code and Name, and a boolean
	// value that shows whether a Library Collection has been initiated or not.
	private LMSModel model;
	private String collectionCode, collectionName = "";
	private boolean collectionInitialised;

	// --Initiates variables to hold all custom GUI components used in the
	// MainView.
	private LMSMenuBar menuBar;
	private ToolBar toolBar;
	private HoldingGrid holdingGrid;
	private StatusBar statusBar;

	private InitialiseDialog initialiseDialog;
	private CreateHoldingDialog createHoldingDialog;
	private RemoveHoldingDialog removeHoldingDialog;

	public MainView() {
		// --Creates a new model to use in this view.
		model = new LMSFacade();

		// --Sets title of MainView and sets layout
		setTitle("OUA CPT221 - SP1 2014 - LMS GUI - s3440468");
		setLayout(new BorderLayout());

		// --Creates instances of all Dialogs and custom GUIs, sets all dialogs
		// to invisible, and passes references to the MainView to all.
		initialiseDialog = new InitialiseDialog(this, false, this);
		initialiseDialog.setVisible(false);

		createHoldingDialog = new CreateHoldingDialog(this, false, this);
		createHoldingDialog.setVisible(false);

		removeHoldingDialog = new RemoveHoldingDialog(this, false, this);
		removeHoldingDialog.setVisible(false);

		menuBar = new LMSMenuBar(this);
		toolBar = new ToolBar(this);
		holdingGrid = new HoldingGrid(this);
		statusBar = new StatusBar(this);

		// --Adds the custom GUI components to the JFrame.
		setJMenuBar(menuBar);
		add(toolBar, BorderLayout.NORTH);
		add(holdingGrid, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);

		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// --Adds a windowListener to the JFrame which asks the user to confirm
		// whether they wish to close the window or not. This makes sure that
		// the window is not accidentally closed.
		(this).addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Object programClosingPrompt = JOptionPane.showConfirmDialog(
						null, "Are you sure you wish to exit?",
						"Exit program?", JOptionPane.YES_NO_OPTION);

				if (programClosingPrompt.equals(JOptionPane.YES_OPTION))
					System.exit(0);
			}
		});
	}

	// --Accessors and mutators.
	public LMSModel getModel() {
		return model;
	}

	public InitialiseDialog getInitialiseDialog() {
		return initialiseDialog;
	}

	public CreateHoldingDialog getCreateHoldingDialog() {
		return createHoldingDialog;
	}

	public RemoveHoldingDialog getRemoveHoldingDialog() {
		return removeHoldingDialog;
	}

	public String getCollectionCode() {
		return collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public HoldingGrid getHoldingGrid() {
		return holdingGrid;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public boolean isCollectionInitialised() {
		return collectionInitialised;
	}

	public void setCollectionInitialised(boolean collectionInitialized) {
		this.collectionInitialised = collectionInitialized;
	}

	// --This next method updates the entire display. It does this by first
	// updating the statusBar and holdingGrid (the only two GUI components that
	// change) so their information is up to date. And then revalidates them.
	public void resetView() {
		statusBar.createStatus();
		statusBar.revalidate();
		holdingGrid.createGrid();
		holdingGrid.revalidate();
	}

}
