package MySPSO;


import java.util.Collection;
import java.util.HashMap;

/**
 * A neighborhood of particles
 * 
 * @author Hernani Chantre
 */
public abstract class MySPNeighborhood {

	// All neighborhoods are stored here, so that we do not need to calculate them each time
	HashMap<MySPParticle, Collection<MySPParticle>> neighborhoods;
	// The best particle in the neighborhood is stored here
	HashMap<MySPParticle, MySPParticle> bestInNeighborhood;

	public MySPNeighborhood() {
		neighborhoods = new HashMap<MySPParticle, Collection<MySPParticle>>();
		bestInNeighborhood = new HashMap<MySPParticle, MySPParticle>();
	}

	/**
	 * Calculate all neighbors of particle 'p'
	 * 
	 * Note: The p's neighbors DO NOT include 'p'
	 * 
	 * @param p : a particle
	 * @return A collection with all neighbors
	 */
	public abstract Collection<MySPParticle> calcNeighbours(MySPParticle p);

	/**
	 * Get the best particle in the neighborhood
	 * @param p
	 * @return The best particle in the neighborhood of 'p'
	 */
	public MySPParticle getBestParticle(MySPParticle p) {
		return bestInNeighborhood.get(p);
	}

	/**
	 * Get the best position ever found by all the particles in the neighborhood of 'p'
	 * @param p
	 * @return The best position in the neighborhood of 'p'
	 */
	public double[] getBestPosition(MySPParticle p) {
		MySPParticle bestp = getBestParticle(p);
		if (bestp == null) return null;
		return bestp.getBestPosition();
	}

	/**
	 * Get all neighbors of particle 'p'
	 * @param p : a particle
	 * @return A collection with all neighbors
	 */
	public Collection<MySPParticle> getNeighbours(MySPParticle p) {
		Collection<MySPParticle> neighs = neighborhoods.get(p);
		if (neighs == null) neighs = calcNeighbours(p);
		return neighs;
	}

	/**
	 * Initialize neighborhood
	 * @param swarm
	 * @return 
	 */
	public void init(MySPSwarm swarm) {
		// Create neighborhoods for each MySPParticle
		for (MySPParticle p : swarm) {
			Collection<MySPParticle> neigh = getNeighbours(p);
			neighborhoods.put(p, neigh);
		}
	}

	/**
	 * Update neighborhood: This is called after each iteration
	 * @param swarm
	 * @return 
	 */
	public void update(MySPSwarm swarm, MySPParticle p) {
		// Find best fitness in this neighborhood
		MySPParticle pbest = getBestParticle(p);
		if ((pbest == null) || swarm.getFitnessFunction().isBetterThan(pbest.getBestFitness(), p.getBestFitness())) {
			// Particle 'p' is the new 'best in neighborhood' => we need to update all neighbors
			Collection<MySPParticle> neigh = getNeighbours(p);
			for (MySPParticle pp : neigh) {
				bestInNeighborhood.put(pp, p);
			}
		}
	}

	
}
