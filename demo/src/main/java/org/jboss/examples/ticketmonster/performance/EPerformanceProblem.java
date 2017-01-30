package org.jboss.examples.ticketmonster.performance;

/**
 * 
 * Enumeration which represents the implemented performance problems.
 * 
 * @author Marius Oehler
 *
 */
public enum EPerformanceProblem {

	SlowVenues("This problem slows down the request which is used to receive particular venues."),
	
	SlowBookings("This problem slows down the request that is used to get a list of all existing bookings in the system."),
	
	CreepingEvents("Increases the invocation sequence duration over a period of time."),
	
	ExcessiveDatabaseQueries("Using an excessive amount of database queries to calculate the total amount spent on tickets.");

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
