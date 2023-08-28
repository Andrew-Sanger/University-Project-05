package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.*;

public class BorrowingHistory {

	// --Initiate Class Variables
	// I used a LinkedList so that all records are kept in the order they are
	// created and not randomly arranged.
	private List<HistoryRecord> history = new LinkedList<HistoryRecord>();

	// --Class constructor
	public BorrowingHistory() {
	}

	public void addHistoryRecord(HistoryRecord record) {
		history.add(record);
	}

	// This method goes through all records in the borrowing history and
	// retrieves the late fees. These late fees are added together and returned.
	public int calculateTotalLateFees() {
		int totalLateFees = 0;
		for (HistoryRecord record : this.history)
			totalLateFees += record.getLateFeePayed();
		return totalLateFees;
	}

	public List<HistoryRecord> getAllHistoryRecords() {
		return this.history;
	}

	public HistoryRecord getHistoryRecord(int holdingCode) {
		// Returns null if no such history record exists.
		HistoryRecord recordFound = null;
		// The equals method only works on the Integer wrapper class, so holding
		// code is changed from a primitive int to a wrapper Integer.
		Integer code = (Integer) holdingCode;

		// Looks through all records and finds the history record that matches
		// the passed holding code.
		for (HistoryRecord record : this.history) {
			if (code.equals(record.getHolding().getHoldingCode()))
				recordFound = record;
		}

		return recordFound;
	}
}
