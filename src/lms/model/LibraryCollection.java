package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

import java.util.*;

public class LibraryCollection {

	// --Initiate class variables
	// I used a LinkedHashMap to store the collection of Holdings because
	// it allows Holdings to be found using their holding code, and also
	// inserts them into the map in the order they are created.
	private Map<Integer, Holding> holdings;
	private String collectionCode;
	private String collectionName;

	// --Class constructor
	public LibraryCollection(String collectionCode, String collectionName) {
		holdings = new LinkedHashMap<Integer, Holding>();
		this.collectionCode = collectionCode;
		this.collectionName = collectionName;
	}

	// This method first checks to see whether the holding already exists and if
	// so returns false. If not then the holding is put into the collection.
	public boolean addHolding(Holding holding) {
		if (holdings.containsKey(holding.getHoldingCode()))
			return false;
		else {
			holdings.put(holding.getHoldingCode(), holding);
			return true;
		}
	}

	// This method first checks to see if the Holding exists and if not returns
	// false. It then checks to see if the holding is already on loan, if it
	// is then it cannot be removed. and a false boolean value is returned. In
	// any other case the holding is removed from the library collection
	public boolean removeHolding(int holdingID) {
		if (this.holdings.containsKey(holdingID)) {
			Holding holding = holdings.get(holdingID);

			if (holding.isOnLoan())
				return false;
			else {
				holdings.remove(holdingID);
				return true;
			}
		} else
			return false;
	}

	public Map<Integer, Holding> getAllHoldings() {
		return holdings;
	}

	// Finds the requested holding using its ID and returns it. If the holding
	// doesn't exist then a null value is returned.
	public Holding getHolding(int holdingID) {
		Holding holding;
		if (holdings.containsKey(holdingID)) {
			holding = holdings.get(holdingID);
			return holding;
		} else
			return null;
	}

	// This toString method varies depending whether or not there are Holdings
	// in it. If there are no holdings then the collections code and name are
	// returned. If there are holdings within, then a list of them is returned.
	@Override
	public String toString() {
		if (this.holdings.isEmpty()) {
			String collectionString = (this.collectionCode + ":" + this.collectionName);
			return collectionString;
		} else {
			String collectionString = (this.collectionCode + ":"
					+ this.collectionName + ":");
			int numberInSet = holdings.size();
			for (int holdingCode : holdings.keySet()) {
				collectionString += holdingCode;
				numberInSet--;
				if (numberInSet != 0)
					collectionString += ",";
			}
			return collectionString;
		}
	}
}
