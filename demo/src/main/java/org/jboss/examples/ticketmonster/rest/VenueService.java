package org.jboss.examples.ticketmonster.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;

import org.jboss.examples.ticketmonster.model.Venue;
import org.jboss.examples.ticketmonster.performance.EPerformanceProblem;
import org.jboss.examples.ticketmonster.performance.PerformanceProblemService;

/**
 * <p>
 * A JAX-RS endpoint for handling {@link Venue}s. Inherits the actual methods from
 * {@link BaseEntityService}.
 * </p>
 *
 * @author Marius Bogoevici
 */
@Path("/venues")
/**
 * <p>
 * This is a stateless service, we declare it as an EJB for transaction demarcation
 * </p>
 */
@Stateless
public class VenueService extends BaseEntityService<Venue> {

	@Inject
	private PerformanceProblemService problemService;

	public VenueService() {
		super(Venue.class);
	}

	private void delay() {
		// injected performance problem
		if (problemService.isActive(EPerformanceProblem.SlowVenues)) {
			if (problemService.propability(0.9)) {
				problemService.randomSleep(2000L, 3000L);
			} else {
				problemService.randomSleep(4000L, 6000L);
			}
		}
	}

	@Override
	public List<Venue> getAll(UriInfo uriInfo) {
		delay();

		return super.getAll(uriInfo);
	}

	@Override
	public Venue getSingleInstance(Long id) {
		delay();

		return super.getSingleInstance(id);
	}
}