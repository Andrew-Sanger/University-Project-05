package lms.model;

import lms.model.visitor.Visitable;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

// -- This interface was changed for Assignment 2, to allow all Holdings to 
// be visited by a visitor class. The only change made is that it now extends 
// the Visitable interface.

public interface Holding extends Visitable {

	// Abstract methods passed to AbstractHolding and then Book and Video.
	public abstract int getHoldingCode();

	public abstract String getHoldingTitle();

	public abstract int getStandardLoanFee();

	public abstract int getMaxLoanPeriod();

	public abstract String getBorrowDate();

	public abstract void setBorrowDate(String newDate);

	public abstract int calculateLateFee(int numberOfDaysLate);

	public abstract boolean isOnLoan();
}
