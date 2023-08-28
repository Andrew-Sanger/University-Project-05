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
public class VideoGridCell extends AbstractHoldingGridCell {

	public VideoGridCell(Holding holding, MainView mainView) {		
		super(holding, mainView);
	}

	// --Sets a red border around the cell.
	@Override
	public void setBorderColor() {
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
	}

}
