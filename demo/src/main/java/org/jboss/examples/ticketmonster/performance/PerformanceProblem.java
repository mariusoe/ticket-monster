package org.jboss.examples.ticketmonster.performance;

/**
 * This class is a container for the state of the performance problems. Also, it
 * will be used as model for the JSON object that are send to the front-end.
 * 
 * @author Marius Oehler
 *
 */
public class PerformanceProblem {

	/**
	 * Name of the performance problem.
	 */
	private String name;

	/**
	 * The description of the performance problem.
	 */
	private String description;

	/**
	 * Spevifies whether the problem is active.
	 */
	private boolean isActive;

	/**
	 * Default constructor. Necessary for deserialization.
	 */
	public PerformanceProblem() {
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the problem
	 * @param description
	 *            the problem description
	 */
	public PerformanceProblem(String name, String description) {
		this.name = name;
		this.description = description;
		this.isActive = false;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the related {@link EPerformanceProblem} of this instance.
	 * 
	 * @return
	 */
	public EPerformanceProblem toEPerformanceProblem() {
		return EPerformanceProblem.valueOf(name);
	}

	@Override
	public String toString() {
		return "PerformanceProblem [name=" + name + ", description=" + description + ", isActive=" + isActive + "]";
	}
}
