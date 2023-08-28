package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public abstract class AbstractHolding implements Holding {

	// --Initiate class variables
	private int holdingCode;
	private String holdingTitle;
	private int standardLoanFee;
	private int maximumLoanPeriod;
	private boolean onLoan;
	private String borrowDate;

	// --Class constructor
	public AbstractHolding(int holdingCode, String holdingTitle, int loanFee,
			int maxLoanPeriod) {
		this.holdingCode = holdingCode;
		this.holdingTitle = holdingTitle;
		this.standardLoanFee = loanFee;
		this.maximumLoanPeriod = maxLoanPeriod;
		this.onLoan = false;
	}

	// --Accessors and Mutators
	@Override
	public int getHoldingCode() {
		return this.holdingCode;
	}

	@Override
	public String getHoldingTitle() {
		return this.holdingTitle;
	}

	@Override
	public int getStandardLoanFee() {
		return this.standardLoanFee;
	}

	@Override
	public int getMaxLoanPeriod() {
		return this.maximumLoanPeriod;
	}

	// This method returns true if the Holding is on loan, and if it isn't on
	// loan, puts it on loan and returns false
	@Override
	public boolean isOnLoan() {
		if (this.onLoan) {
			return true;
		} else {
			this.onLoan = true;
			return false;
		}
	}

	@Override
	public String getBorrowDate() {
		return this.borrowDate;
	}

	@Override
	public void setBorrowDate(String newDate) {
		this.borrowDate = newDate;
	}

	@Override
	public String toString() {
		return (this.holdingCode + ":" + this.holdingTitle + ":"
				+ this.standardLoanFee + ":" + this.maximumLoanPeriod);
	}
}
