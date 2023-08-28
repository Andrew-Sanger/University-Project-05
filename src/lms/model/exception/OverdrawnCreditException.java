package lms.model.exception;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class OverdrawnCreditException extends LMSException {

	// When this exception is thrown, a warning message is displayed.
	public OverdrawnCreditException() {
		super("-- Warning - Credit is overdrawn --");
	}
}
