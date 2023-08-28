package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import lms.model.exception.*;

public interface Borrower {

	// Abstract methods passed to Member --> AbstractMember and finally to both
	// StandardMember and PremiumMember
	public abstract void borrowHolding(Holding holding)
			throws InsufficientCreditException, MultipleBorrowingException;

	public abstract void returnHolding(Holding returnedHolding)
			throws OverdrawnCreditException;
}
