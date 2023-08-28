package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.*;

import lms.model.exception.*;

public class Library {

	// --Initiates class variables
	private LibraryCollection libraryCollection;
	private Member member;

	// --Class constructor
	public Library() {
	}

	// Nearly all methods in this class pass variables on to methods in either
	// libraryCollection or member
	public void addCollection(LibraryCollection collection) {
		this.libraryCollection = collection;
	}

	public boolean addHolding(Holding holding) {
		boolean addHolding = libraryCollection.addHolding(holding);
		return addHolding;
	}

	public boolean removeHolding(int holdingId) {
		boolean holdingRemoved = libraryCollection.removeHolding(holdingId);
		return holdingRemoved;
	}

	public void addMember(Member member) {
		this.member = member;
	}

	public void borrowHolding(int holdingId)
			throws InsufficientCreditException, MultipleBorrowingException {
		Holding holdingToBeBorrowed = libraryCollection.getHolding(holdingId);

		member.borrowHolding(holdingToBeBorrowed);
	}

	public int calculateRemainingCredit() {
		return member.calculateRemainingCredit();
	}

	public int calculateTotalLateFees() {
		return member.getBorrowingHistory().calculateTotalLateFees();
	}

	public Map<Integer, Holding> getAllHoldings() {
		Map<Integer, Holding> holdings = libraryCollection.getAllHoldings();
		return holdings;
	}

	public List<Holding> getBorrowedHoldings() {
		List<Holding> holdingList = member.getCurrentHoldings();
		return holdingList;
	}

	public BorrowingHistory getBorrowingHistory() {
		return member.getBorrowingHistory();
	}

	public LibraryCollection getCollection() {
		return this.libraryCollection;
	}

	public HistoryRecord getHistoryRecord(int holdingId) {
		return member.getBorrowingHistory().getHistoryRecord(holdingId);
	}

	public Holding getHolding(int holdingID) {
		Holding holding = libraryCollection.getHolding(holdingID);
		return holding;
	}

	public Member getMember() {
		return this.member;
	}

	public void resetCredit() {
		member.resetCredit();
	}

	public void returnHolding(int holdingID) throws OverdrawnCreditException {
		Holding returnedHolding;
		Map<Integer, Holding> totalHoldings = libraryCollection
				.getAllHoldings();

		returnedHolding = totalHoldings.get(holdingID);

		// Checks first to see if the holding that is wanting to be returned is
		// not in the library, and not on loan, and then continues.
		if (returnedHolding != null && returnedHolding.isOnLoan()) {
			member.returnHolding(returnedHolding);
		}
	}
}
