package lms.model.facade;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.*;

import lms.model.BorrowingHistory;
import lms.model.HistoryRecord;
import lms.model.Holding;
import lms.model.Library;
import lms.model.LibraryCollection;
import lms.model.Member;
import lms.model.exception.InsufficientCreditException;
import lms.model.exception.MultipleBorrowingException;
import lms.model.exception.OverdrawnCreditException;
import lms.model.util.DateUtil;
import lms.model.visitor.HoldingVisitor;

public class LMSFacade implements LMSModel {

	// --Initiate class variables
	private Library library;

	// --Class constructor
	public LMSFacade() {
		library = new Library();
	}

	// Most of the following methods are passed on to the Library class, after
	// which they are actioned accordingly.
	public void setDate(String currentDate) {
		DateUtil.getInstance().setDate(currentDate);
	}

	@Override
	public void addMember(Member member) {
		library.addMember(member);
	}

	@Override
	public void addCollection(LibraryCollection collection) {
		library.addCollection(collection);
	}

	@Override
	public Member getMember() {
		Member member = library.getMember();
		return member;
	}

	@Override
	public LibraryCollection getCollection() {
		LibraryCollection collection = library.getCollection();
		return collection;
	}

	@Override
	public boolean addHolding(Holding holding) {
		boolean holdingAdded = library.addHolding(holding);
		if (holdingAdded)
			return true;
		else
			return false;
	}

	@Override
	public boolean removeHolding(int holdingId) {
		boolean holdingRemoved = library.removeHolding(holdingId);
		return holdingRemoved;
	}

	@Override
	public Holding getHolding(int holdingId) {
		Holding holding = library.getHolding(holdingId);
		return holding;
	}

	// This method changes the LinkedHashMap into a Holding[] array. It creates
	// an array the same size as the LinkedHashMap and places the Holdings
	// within.
	@Override
	public Holding[] getAllHoldings() {
		Map<Integer, Holding> holdings = library.getAllHoldings();

		if (holdings.isEmpty())
			return null;
		else {
			Holding[] holdingsArray = holdings.values().toArray(
					new Holding[holdings.size()]);
			return holdingsArray;
		}
	}

	// -- UPDATE --
	// This method has been updated to use a Visitor design pattern to check how
	// many books are in the LibraryCollection.
	@Override
	public int countBooks() {
		int numberOfBooks = 0;
		Holding[] holdings = this.getAllHoldings();
		if (holdings == null)
			return numberOfBooks;
		else {
			HoldingVisitor visitor = new HoldingVisitor();

			for (Holding currentHolding : holdings) {
				currentHolding.accept(visitor);
			}

			return visitor.getBookCount();

			// The following is the original code from assignment 1, before
			// updating to the Visitor design pattern.

			// for (Holding currentHolding : holdings) {
			// if (currentHolding instanceof Book) {
			// numberOfBooks++;
			// }
			// }
		}
	}

	// -- UPDATE --
	// This method has been updated to use a Visitor design pattern to check how
	// many videos are in the LibraryCollection.
	@Override
	public int countVideos() {
		int numberOfVideos = 0;
		Holding[] holdings = this.getAllHoldings();
		if (holdings == null)
			return numberOfVideos;
		else {
			HoldingVisitor visitor = new HoldingVisitor();

			for (Holding currentHolding : holdings) {
				currentHolding.accept(visitor);
			}

			return visitor.getVideoCount();

			// The following is the original code from assignment 1, before
			// updating to the Visitor design pattern.

			// for (Holding currentHolding : holdings) {
			// if (currentHolding instanceof Video) {
			// numberOfVideos++;
			// }
			// }
		}
	}

	@Override
	public void borrowHolding(int holdingId)
			throws InsufficientCreditException, MultipleBorrowingException {
		library.borrowHolding(holdingId);
	}

	@Override
	public void returnHolding(int holdingId) throws OverdrawnCreditException {
		library.returnHolding(holdingId);
	}

	// This method changes the borrowing history LinkedList into a
	// HistoryRecord[] array. It creates an array the same size as the
	// LinkedList and places the HistoryRecords within.
	@Override
	public HistoryRecord[] getBorrowingHistory() {
		BorrowingHistory history = library.getBorrowingHistory();
		List<HistoryRecord> historyRecords = history.getAllHistoryRecords();

		if (historyRecords.isEmpty())
			return null;
		else {
			HistoryRecord[] historyRecordsArray = history
					.getAllHistoryRecords().toArray(
							new HistoryRecord[historyRecords.size()]);
			return historyRecordsArray;
		}
	}

	@Override
	public HistoryRecord getHistoryRecord(int holdingId) {
		return library.getHistoryRecord(holdingId);
	}

	// This method changes the borrowed holdings LinkedList into a
	// Holding[] array. It creates an array the same size as the
	// LinkedList and places the Holdings within.
	@Override
	public Holding[] getBorrowedHoldings() {
		List<Holding> holdingList = library.getBorrowedHoldings();

		if (holdingList.isEmpty())
			return null;
		else {
			Holding[] holdingArray = holdingList
					.toArray(new Holding[holdingList.size()]);
			return holdingArray;
		}
	}

	@Override
	public void resetMemberCredit() {
		library.resetCredit();
	}

	@Override
	public int calculateRemainingCredit() {
		return library.calculateRemainingCredit();
	}

	@Override
	public int calculateTotalLateFees() {
		return library.calculateTotalLateFees();
	}
}