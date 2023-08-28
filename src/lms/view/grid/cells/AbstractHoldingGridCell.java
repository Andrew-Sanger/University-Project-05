package lms.view.grid.cells;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public abstract class AbstractHoldingGridCell extends JPanel implements
		GridCell {

	// --Initiates variables to hold references to model, view and the Holding
	// that this Grid holds information for.
	private MainView view;
	private LMSModel model;
	private Holding linkedHolding;

	// --Initiates all swing components used in the creation of this GridCell.
	private JPanel mainContent;
	private JLabel holdingID, title, loanFee, loanPeriod;

	public AbstractHoldingGridCell(Holding holding, MainView mainView) {
		this.linkedHolding = holding;
		this.view = mainView;
		this.model = view.getModel();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// --Creates a JPanel that contains all information from the Holding and
		// then inserts it into a JScrollPane to allow scrolling capability.
		mainContent = new JPanel();
		mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(mainContent);

		// --Calls the inherited methods in either BookGridCell or VideoGridCell
		// to make the correct background or border for the cell type.
		setBackgroundColor();
		setBorderColor();

		// --Creates JLabels containing information from the Holdings and adds
		// them to the mainContent JPanel.
		holdingID = new JLabel("Holding ID: "
				+ Integer.toString(holding.getHoldingCode()));
		holdingID.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		title = new JLabel("Title: " + holding.getHoldingTitle());
		title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		loanFee = new JLabel("Standard Loan Fee: "
				+ Integer.toString(holding.getStandardLoanFee()));
		loanFee.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		loanPeriod = new JLabel("Loan Period: "
				+ Integer.toString(holding.getMaxLoanPeriod()));
		loanPeriod.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		mainContent.add(holdingID);
		mainContent.add(title);
		mainContent.add(loanFee);
		mainContent.add(loanPeriod);

		// --ScrollPane containing everything is added to the GridCell.
		add(scrollPane);

		// --A mouseListener is added to the GridCell which waits for the used
		// to click the mouse. And when this happens a prompt comes up asking
		// whether the user really want to delete the Holding from the Library
		// Collection. And if so, the holding is removed. After this the
		// mainView is reset.
		mainContent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object removeHoldingConfirm = JOptionPane
						.showConfirmDialog(
								null,
								"Are you sure you wish to delete the selected Holding?",
								"Remove Holding", JOptionPane.YES_NO_OPTION);
				if (removeHoldingConfirm.equals(JOptionPane.YES_OPTION)) {
					model.removeHolding(linkedHolding.getHoldingCode());

					view.resetView();

					JOptionPane.showMessageDialog(null,
							"Holding successfully removed!");
				}
			}
		});
	}

	// --The backgrounds of both the book and video cell are lightGrey, so this
	// method is implemented here.
	@Override
	public void setBackgroundColor() {
		setBackground(Color.LIGHT_GRAY);
	}

}
