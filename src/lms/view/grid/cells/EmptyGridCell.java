package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * -- Programming 2 - Assignment 2 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class EmptyGridCell extends JPanel implements GridCell {

	// --Due to the fact that EmptyGridCell contains nothing besides its border
	// color and background color, it implements GridCell
	public EmptyGridCell() {
		setBorderColor();
		setBackgroundColor();
	}

	@Override
	public void setBorderColor() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	@Override
	public void setBackgroundColor() {
		setBackground(Color.WHITE);
	}

}
