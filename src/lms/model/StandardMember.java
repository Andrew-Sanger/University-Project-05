package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class StandardMember extends AbstractMember {

	// --Initiate class variables
	private static final int FIXED_MAXIMUM_CREDIT = 30;
	private static final String MEMBER_TYPE = "STANDARD";

	// --Class constructor
	public StandardMember(String standardMemberId, String standardMemberName) {
		super(standardMemberId, standardMemberName, FIXED_MAXIMUM_CREDIT);
	}

	@Override
	public String toString() {
		return (super.toString() + ":" + MEMBER_TYPE);
	}
}
