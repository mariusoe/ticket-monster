package org.jboss.examples.ticketmonster.performance;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Service class to manage the performance problems.
 * 
 * @author Marius Oehler
 *
 */
@Singleton
@Startup
public class PerformanceProblemService {

	/**
	 * Map containing the status of all performance problems.
	 */
	private Map<EPerformanceProblem, PerformanceProblem> problemMap;

	/**
	 * Returns the existing performance problems which are stored in the
	 * {@link #problemMap}.
	 * 
	 * @return returns all existing performance problems
	 */
	public Collection<PerformanceProblem> getPerformanceProblems() {
		return problemMap.values();
	}

	/**
	 * Initialization method.
	 */
	@PostConstruct
	public void onStartup() {
		problemMap = new HashMap<EPerformanceProblem, PerformanceProblem>();

		for (EPerformanceProblem problem : EPerformanceProblem.values()) {
			problemMap.put(problem, new PerformanceProblem(problem.toString(), problem.getDescription()));
		}
	}

	/**
	 * Returns the {@link PerformanceProblem} related to the given
	 * {@link EPerformanceProblem}.
	 * 
	 * @param problem
	 *            {@link EPerformanceProblem} of the desired
	 *            {@link PerformanceProblem}
	 * @return the {@link PerformanceProblem} related to the given
	 *         {@link EPerformanceProblem}
	 */
	public PerformanceProblem getPerformanceProblem(EPerformanceProblem problem) {
		return problemMap.get(problem);
	}

	/**
	 * Returns whether the specified performance problem is active.
	 * 
	 * @param problem
	 *            {@link EPerformanceProblem} of the desired
	 *            {@link PerformanceProblem}
	 * @return <code>true</code> if the problem is active
	 */
	public boolean isActive(EPerformanceProblem problem) {
		return problemMap.get(problem).isActive();
	}
}
