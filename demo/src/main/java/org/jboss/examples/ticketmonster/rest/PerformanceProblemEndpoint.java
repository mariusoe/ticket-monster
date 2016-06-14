package org.jboss.examples.ticketmonster.rest;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.examples.ticketmonster.performance.PerformanceProblem;
import org.jboss.examples.ticketmonster.performance.PerformanceProblemService;

/**
 * Endpoint class that provides the REST interface to manage the performance
 * problems.
 * 
 * @author Marius Oehler
 *
 */
@Path("/problems")
@Stateless
public class PerformanceProblemEndpoint {

	@Inject
	private PerformanceProblemService problemService;

	/**
	 * Service to get the current status of all injected performance problems.
	 * 
	 * @return the current status of all performance problems
	 */
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PerformanceProblem> getProblemStatus() {
		return problemService.getPerformanceProblems();
	}

	/**
	 * Service to update the active state of the specified performance problem.
	 * 
	 * @param updateProblem
	 *            the problem to update and the new value for the
	 *            <code>active</code> property
	 * @return the current status of all performance problems
	 */
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<PerformanceProblem> updateProblemStatus(PerformanceProblem updateProblem) {
		PerformanceProblem realProblem = problemService.getPerformanceProblem(updateProblem.toEPerformanceProblem());
		realProblem.setActive(updateProblem.isActive());

		return problemService.getPerformanceProblems();
	}
}
