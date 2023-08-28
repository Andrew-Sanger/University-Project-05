package lms.model;

/*
 * -- Programming 2 - Assignment 1 --
 * 
 * Author - Andrew Sanger
 * 			S3440468
 */

public class PremiumMember extends AbstractMember {

	// --Initiate class variables
	private static final int FIXED_MAXIMUM_CREDIT = 45;
	private static final String MEMBER_TYPE = "PREMIUM";

	// --Class constructor
	public PremiumMember(String premiumMemberId, String premiumMemberName) {
		super(premiumMemberId, premiumMemberName, FIXED_MAXIMUM_CREDIT);
	}

	@Override
	public String toString() {
		return (super.toString() + ":" + MEMBER_TYPE);
	}
}
