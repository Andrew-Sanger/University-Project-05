package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.List;

public interface Member extends Borrower {

	// Abstract methods passed on to AbstractMember, StandardMember and
	// PremiumMember
	public abstract String getMemberID();

	public abstract String getMemberName();

	public abstract int getMaxCredit();

	public abstract BorrowingHistory getBorrowingHistory();

	public abstract List<Holding> getCurrentHoldings();

	public abstract int calculateRemainingCredit();

	public abstract void resetCredit();
}
