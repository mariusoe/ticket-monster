package org.jboss.examples.ticketmonster.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.jboss.examples.ticketmonster.model.Event;
import org.jboss.examples.ticketmonster.performance.EPerformanceProblem;
import org.jboss.examples.ticketmonster.performance.PerformanceProblemService;

/**
 * <p>
 * A JAX-RS endpoint for handling {@link Event}s. Inherits the actual methods from
 * {@link BaseEntityService}, but implements additional search criteria.
 * </p>
 *
 * @author Marius Bogoevici
 */
@Path("/events")
/**
 * <p>
 * This is a stateless service, we declare it as an EJB for transaction demarcation
 * </p>
 */
@Stateless
public class EventService extends BaseEntityService<Event> {

	@Inject
	private PerformanceProblemService problemService;

	public EventService() {
		super(Event.class);
	}

	/**
	 * <p>
	 * We override the method from parent in order to add support for additional search criteria for
	 * events.
	 * </p>
	 * 
	 * @param queryParameters
	 *            - the HTTP query parameters received by the endpoint
	 * @param criteriaBuilder
	 *            - @{link CriteriaBuilder} used by the invoker
	 * @param root
	 * @{link Root} used by the invoker
	 * @return
	 */
	@Override
	protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters, CriteriaBuilder criteriaBuilder, Root<Event> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (queryParameters.containsKey("category")) {
			String category = queryParameters.getFirst("category");
			predicates.add(criteriaBuilder.equal(root.get("category").get("id"), category));
		}

		return predicates.toArray(new Predicate[] {});
	}

	private boolean active = false;

	private long start = 0L;

	@Override
	public List<Event> getAll(UriInfo uriInfo) {
		if (problemService.isActive(EPerformanceProblem.CreepingEvents)) {
			if (!active) {
				active = true;
				start = System.currentTimeMillis();
			}

			// in min
			int duration = ((int) ((System.currentTimeMillis() - start) / 1000L)) / 60;

			double lower = Math.min(duration * 0.04D, 0.6D);
			double upper = lower + 0.2D;

			try {
				ThreadLocalRandom random = ThreadLocalRandom.current();

				// dynamic delay
				if (random.nextDouble() < random.nextDouble(lower, upper)) {
					Thread.sleep(random.nextLong(25L, 50L));
				}
			} catch (InterruptedException e) {
			}
		}
		return super.getAll(uriInfo);
	}
}
