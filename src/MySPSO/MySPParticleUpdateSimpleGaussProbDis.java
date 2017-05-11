package MySPSO;

import MySP.VectorGenerator;

/**
 * Particle update strategy
 * 
 * Every Swarm.evolve() itereation the following methods are called
 * 		- begin(Swarm) : Once at the begining of each iteration
 * 		- update(Swarm,Particle) : Once for each particle
 * 		- end(Swarm) : Once at the end of each iteration
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class MySPParticleUpdateSimpleGaussProbDis extends MySPParticleUpdate {

	/** Random vector for local update */
	double rlocal[];
	/** Random vector for global update */
	double rglobal[];
	/** Random vector for neighborhood update */
	double rneighborhood[];

	/**
	 * Constructor 
	 * @param particle : Sample of particles that will be updated later
	 */
	public MySPParticleUpdateSimpleGaussProbDis(MySPParticle particle) {
		super(particle);
		rlocal = new double[particle.getDimension()];
		rglobal = new double[particle.getDimension()];
		rneighborhood = new double[particle.getDimension()];
	}

	/** 
	 * This method is called at the begining of each iteration
	 * Initialize random vectors use for local and global updates (rlocal[] and rother[])
	 */
	@Override
	public void begin(MySPSwarm swarm) {
		int i, dim = swarm.getSampleParticle().getDimension();
		for (i = 0; i < dim; i++) {
			/*rlocal[i] = Math.random();
			rglobal[i] = Math.random();
			rneighborhood[i] = Math.random();*/

			/*
			 *
			 * */
			rlocal[i] = swarm.getGaussProbDis();
			rglobal[i] = swarm.getGaussProbDis();
			rneighborhood[i] =swarm.getGaussProbDis();
		}
	}

	/** This method is called at the end of each iteration */
	@Override
	public void end(MySPSwarm swarm) {
	}

	/** Update particle's velocity and position */
	@Override
	public void update(MySPSwarm swarm, MySPParticle particle) {
		double position[] = particle.getPosition();
		double velocity[] = particle.getVelocity();
		double globalBestPosition[] = swarm.getBestPosition();
		double particleBestPosition[] = particle.getBestPosition();
		double neighBestPosition[] = swarm.getNeighborhoodBestPosition(particle);

		// Update velocity and position
		for (int i = 0; i < position.length; i++) {
			// Update velocity

			velocity[i] = swarm.getInertia() * velocity[i] // Inertia
					+rlocal[i] * (particleBestPosition[i] - position[i]) // Local best
					+ rneighborhood[i] *(neighBestPosition[i] - position[i]) // Neighborhood best					
					+  rglobal[i] * (globalBestPosition[i] - position[i]); // Global best
			// Update position
			position[i] = Math.round(position[i]) + Math.round(velocity[i]);



		}
	}
}
