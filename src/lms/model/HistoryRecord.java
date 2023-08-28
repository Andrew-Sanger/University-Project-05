package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class HistoryRecord {

	// --Initiate class variables
	private Holding holding;
	private int totalPayedFee;

	// --Class constructor
	public HistoryRecord(Holding holding, int totalPayedFee) {
		this.holding = holding;
		this.totalPayedFee = totalPayedFee;
	}

	// --Accessors and Mutators
	public Holding getHolding() {
		return this.holding;
	}

	public int getFeePayed() {
		return this.totalPayedFee;
	}

	// This method gets the total payed fee for the holding and subtracts the
	// standard loan fee. This becomes any late fee payed for the holding.
	public int getLateFeePayed() {
		return (this.totalPayedFee - this.holding.getStandardLoanFee());
	}

	@Override
	public String toString() {
		return (this.holding.getHoldingCode() + ":" + this.totalPayedFee);
	}

}
