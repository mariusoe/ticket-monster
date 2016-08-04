package org.jboss.examples.ticketmonster.rest;

import java.util.Random;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

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

	private Random random = new Random();

	public VenueService() {
		super(Venue.class);
	}

	@Override
	public Venue getSingleInstance(Long id) {
		// injected performance problem
		if (problemService.isActive(EPerformanceProblem.SlowVenues)) {
			try {
				long baseline = 850L;
				Thread.sleep(baseline + random.nextInt(150));
			} catch (InterruptedException e) {
			}
		}

		return super.getSingleInstance(id);
	}
}