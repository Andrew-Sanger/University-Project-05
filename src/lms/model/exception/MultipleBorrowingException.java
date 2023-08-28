package lms.model.exception;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

@SuppressWarnings("serial")
public class MultipleBorrowingException extends LMSException {

	// When this exception is thrown, a warning message is displayed.
	public MultipleBorrowingException() {
		super("-- Warning - Trying to borrow a Holding that "
				+ "is already on loan --");
	}
}
