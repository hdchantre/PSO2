package MySPSO;


/**
 * Swarm variables update
 * Every Swarm.evolve() iteration, update() is called 
 * 
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public class MySPVariablesUpdate {

	/**
	 * Default constructor 
	 */
	public MySPVariablesUpdate() {
		super();
	}

	/**
	 * Update Swarm parameters here
	 * @param swarm : Swarm to update
	 */
	public void update(MySPSwarm swarm) {
		// Nothing updated in this case (build your own VariablesUpdate class)
		// e.g. (exponential update): 
	 swarm.setInertia( 0.95 * swarm.getInertia() );
	//	swarm.setMaxVelocity(swarm.getMaxVelocity());
	// swarm.setMinVelocity(swarm.getMinVelocity());
		 swarm.setParticleIncrement(0.98*swarm.getParticleIncrement());
		 swarm.setGlobalIncrement(0.98*swarm.getGlobalIncrement());
		 
		
	}
}
