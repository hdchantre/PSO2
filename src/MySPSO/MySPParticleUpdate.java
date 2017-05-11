package MySPSO;


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
public abstract class MySPParticleUpdate {

	/**
	 * Constructor 
	 * @param particle : Sample of particles that will be updated later
	 */
	public MySPParticleUpdate(MySPParticle particle) {
	}

	/** 
	 * This method is called at the begining of each iteration
	 * Initialize random vectors use for local and global updates (rlocal[] and rother[])
	 */
	public void begin(MySPSwarm swarm) {
	}

	/** This method is called at the end of each iteration */
	public void end(MySPSwarm swarm) {
	}

	/** Update particle's velocity and position */
	public abstract void update(MySPSwarm swarm, MySPParticle particle);
}
