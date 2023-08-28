package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.BorderFactory;

import lms.model.Holding;
import lms.view.MainView;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class BookGridCell extends AbstractHoldingGridCell {

	public BookGridCell(Holding holding, MainView mainView) {
		super(holding, mainView);
	}
	
	// --Sets a blue border around the cell.
	@Override
	public void setBorderColor() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
	}
}
