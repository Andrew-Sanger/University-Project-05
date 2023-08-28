package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.*;

import lms.model.exception.*;
import lms.model.util.*;

public abstract class AbstractMember implements Member {

	// --Initiate class variables
	private String memberID;
	private String memberName;
	private int maximumCredit;
	private int remainingCredit;
	private BorrowingHistory history = new BorrowingHistory();
	private List<Holding> currentHoldings = new LinkedList<Holding>();

	// --Class constructor
	public AbstractMember(String memberID, String memberName, int maximumCredit) {
		this.memberID = memberID;
		this.memberName = memberName;
		this.maximumCredit = maximumCredit;
		this.remainingCredit = maximumCredit;
	}

	// --Accessors and Mutators
	@Override
	public String getMemberID() {
		return this.memberID;
	}

	@Override
	public String getMemberName() {
		return this.memberName;
	}

	@Override
	public int getMaxCredit() {
		return this.maximumCredit;
	}

	@Override
	public BorrowingHistory getBorrowingHistory() {
		return this.history;
	}

	@Override
	public List<Holding> getCurrentHoldings() {
		return this.currentHoldings;
	}

	/*
	 * This method allows a member to borrow a holding, and throws exceptions if
	 * it is unable to. First it checks if the Member has sufficient remaining
	 * credit to borrow, and if not throws an InsufficientCreditException. Next
	 * it checks if the Holding is on loan already, and if so, throws a
	 * MultipleBorrowingException. If the Holding is able to be borrowed, then
	 * it changes the Holdings status to on loan, adds the Holding to the
	 * Members current holdings, subtracts the Holdings loan fee out of
	 * remaining credit, and sets the Holdings borrow date to the current date.
	 */
	@Override
	public void borrowHolding(Holding holding)
			throws InsufficientCreditException, MultipleBorrowingException {
		if (holding.getStandardLoanFee() > this.remainingCredit) {
			throw new InsufficientCreditException();
		} else {
			if (holding.isOnLoan())
				throw new MultipleBorrowingException();
			else {
				holding.isOnLoan();
				currentHoldings.add(holding);
				this.remainingCredit -= holding.getStandardLoanFee();
				holding.setBorrowDate(DateUtil.getInstance().getDate());
			}
		}
	}

	/*
	 * This method allows a member to return a holding, and throws exceptions if
	 * it is unable to. First it sets the day it was borrowed to a variable and
	 * works out how long the Holding is overdue (if it is in fact overdue at
	 * all). If the Holding isn't overdue, then the Holding is returned and a
	 * new History Record of the Holding is added to the Members info. If it is
	 * overdue, first it works out the late fee. Then it decided whether the
	 * member is either a standard member or a premium member. If the member is
	 * a standard member and doesn't have enough credit remaining to return the
	 * Holding, then an OverdrawnCreditException is thrown, and the Holding is
	 * not returned. If the member is a premium member, or a standard member
	 * that has sufficient credit remaining to return the Holding, then the
	 * Holding is removed from the members current holdings, and a new History
	 * Record of the Holding is created. Finally the late fee is subtracted from
	 * the members remaining credit.
	 */
	@Override
	public void returnHolding(Holding returnedHolding)
			throws OverdrawnCreditException {
		int timeBorrowed = DateUtil.getInstance().getElapsedDays(
				returnedHolding.getBorrowDate());
		int timeOverdue = timeBorrowed - returnedHolding.getMaxLoanPeriod();
		if (timeOverdue <= 0) {
			currentHoldings.remove(returnedHolding);
			this.history.addHistoryRecord(new HistoryRecord(returnedHolding,
					returnedHolding.getStandardLoanFee()));
		} else {
			int lateFee = returnedHolding.calculateLateFee(timeOverdue);

			if (this instanceof StandardMember) {
				if ((this.calculateRemainingCredit() - lateFee) < 0) {
					throw new OverdrawnCreditException();
				} else {
					currentHoldings.remove(returnedHolding);
					this.history.addHistoryRecord(new HistoryRecord(
							returnedHolding, (returnedHolding
									.getStandardLoanFee() + lateFee)));
					this.remainingCredit -= lateFee;
				}
			} else if (this instanceof PremiumMember) {
				currentHoldings.remove(returnedHolding);
				this.history.addHistoryRecord(new HistoryRecord(
						returnedHolding,
						(returnedHolding.getStandardLoanFee() + lateFee)));
				this.remainingCredit -= lateFee;
			}
		}
	}

	@Override
	public int calculateRemainingCredit() {
		return this.remainingCredit;
	}

	@Override
	public void resetCredit() {
		this.remainingCredit = this.maximumCredit;
	}

	@Override
	public String toString() {
		return (this.memberID + ":" + this.memberName + ":" + this.remainingCredit);
	}
}
