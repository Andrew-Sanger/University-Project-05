package lms.view.grid;

import java.awt.GridLayout;

import javax.swing.JPanel;

import lms.model.Holding;
import lms.model.facade.LMSModel;
import lms.model.visitor.HoldingVisitor;
import lms.view.MainView;
import lms.view.ToolBar;
import lms.view.grid.cells.*;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class HoldingGrid extends JPanel {

	// --Initiates variables to hold a reference to both the view and the model,
	// and also an array to hold the Holdings being added to the grid.
	MainView view;
	LMSModel model;
	Holding[] originalHoldings;

	// --Initates a GridLayout, this layout will be changed a lot depending on
	// how many Holdings are in the Library Collection.
	GridLayout holdingGridLayout;

	public HoldingGrid(MainView mainView) {
		this.view = mainView;
		this.model = view.getModel();

		// --Calls the setGridLayout method so that the GridLayout is properly
		// set. (Because this constructor is called before any holdings are in
		// the model, this is important to do.
		setGridLayout();
	}

	// --This method returns how many holdings are to be displayed in the grid
	// in total.
	public int getTotalHoldings() {
		return (model.countBooks() + model.countVideos());
	}

	// --This method sets the GridLayout depending on how many holdings are in
	// the model, and whether or not a Library Collection has actually been
	// initialised yet or not.
	public void setGridLayout() {
		if (view.isCollectionInitialised() == false) {
			// Sets the Holding Grid to one big empty square if the Library
			// Collection hasnt been initialised yet.
			holdingGridLayout = new GridLayout(1, 1);
			setLayout(holdingGridLayout);
			return;
		} else if (getTotalHoldings() < 4) {
			// If there are less that 4 holdings then will show either 1, 2, or
			// 3 cells.
			holdingGridLayout = new GridLayout(1, 0);
			setLayout(holdingGridLayout);
		} else {
			// If there are 4 or more holdings then there will always be 4
			// columns
			holdingGridLayout = new GridLayout(0, 4);
			setLayout(holdingGridLayout);
		}
	}

	// --This method creates either BookGridCells, VideoGridCells or
	// EmptyGridCells depending on how many Holdings are in the
	// LibraryCollection and what type they are. First it calls setGridLayout()
	// to ensure the GridLayout is set up correctly. It then removes all current
	// GridCells from the display. After this it gets an array of all holdings,
	// and checks first that the array isn't null, if it is null, then there are
	// no holdings and it display a single EmptyGridCell. It then calls
	// sortGridItems so the array is properly sorted. A Visitor class is created
	// to check all holdings in the array and GridCells are created depending on
	// what sort of Holding it is. After this the remaining empty grid cells are
	// counted and EmptyGridCells are created to fill the gaps.
	public void createGrid() {
		setGridLayout();

		this.removeAll();

		originalHoldings = model.getAllHoldings();

		if (originalHoldings == null)
			add(new EmptyGridCell());
		else {
			originalHoldings = sortGridItems(originalHoldings);

			for (Holding currentHolding : originalHoldings) {
				HoldingVisitor visitor = new HoldingVisitor();
				currentHolding.accept(visitor);

				if (visitor.isHoldingBook())
					add(new BookGridCell(currentHolding, view));
				else if (visitor.isHoldingVideo())
					add(new VideoGridCell(currentHolding, view));
			}
		}

		if (getTotalHoldings() >= 4) {
			// --Finds out how many empty GridCells come after the Holding grid
			// cells
			int emptySquares = 4 - (getTotalHoldings() % 4);

			if (emptySquares != 0 && emptySquares != 4) {
				for (int i = 1; i <= emptySquares; i++)
					add(new EmptyGridCell());

			}
		}

	}

	// --This method sorts the array depending on what sort radio button is
	// pressed in the custom toolBar class. If noSort is selected then the
	// original array is returned. If code sort is selected, then the array is
	// sorted by Holding code, from smallest to largest. If type sort is
	// selected, then a Visitor object is created that visits each holding and
	// sorts them into Books and then Videos.
	public Holding[] sortGridItems(Holding[] holdingArray) {
		ToolBar toolBar = view.getToolBar();

		if (toolBar.getNoSort().isSelected()) {
			return holdingArray;
		} else if (toolBar.getCodeSort().isSelected()) {
			int currentSmallest, swapPos;
			Holding tempHolding;

			for (int i = 0; i < holdingArray.length - 1; i++) {
				currentSmallest = holdingArray[i].getHoldingCode();
				swapPos = i;

				for (int j = i + 1; j < holdingArray.length; j++) {
					if (currentSmallest > holdingArray[j].getHoldingCode()) {
						swapPos = j;
						currentSmallest = holdingArray[j].getHoldingCode();
					}
				}

				tempHolding = holdingArray[i];
				holdingArray[i] = holdingArray[swapPos];
				holdingArray[swapPos] = tempHolding;
			}

			return holdingArray;
		} else {
			Holding[] typeSortedArray = new Holding[holdingArray.length];
			int currentArrayPos = 0;

			for (int i = 0; i < holdingArray.length; i++) {
				HoldingVisitor visitor = new HoldingVisitor();
				holdingArray[i].accept(visitor);

				if (visitor.isHoldingBook()) {
					typeSortedArray[currentArrayPos] = holdingArray[i];
					currentArrayPos++;
				}
			}

			for (int i = 0; i < holdingArray.length; i++) {
				HoldingVisitor visitor = new HoldingVisitor();
				holdingArray[i].accept(visitor);

				if (visitor.isHoldingVideo()) {
					typeSortedArray[currentArrayPos] = holdingArray[i];
					currentArrayPos++;
				}
			}

			return typeSortedArray;
		}
	}
}
