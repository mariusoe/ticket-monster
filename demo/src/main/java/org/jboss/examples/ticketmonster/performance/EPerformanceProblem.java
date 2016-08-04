package org.jboss.examples.ticketmonster.performance;

/**
 * 
 * Enumeration which represents the implemented performance problems.
 * 
 * @author Marius Oehler
 *
 */
public enum EPerformanceProblem {

	TestProblem("This is a test problem. Nothing happens if this is active."),

	SlowVenues("This problem slows down the request which is used to receive particular venues."),
	
	SlowBookings("This problem slows down the request that is used to get a list of all existing bookings in the system.");

	/**
	 * The description of the performance problem. This will be shown in the administration
	 * interface.
	 */
	private final String description;

	/**
	 * Constructor.
	 * 
	 * @param description
	 *            the description
	 */
	private EPerformanceProblem(String description) {
		this.description = description;
	}

	/**
	 * Returns the description.
	 * 
	 * @return returns {@link #description}
	 */
	public String getDescription() {
		return description;
	}
}
