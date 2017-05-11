package MySPSO;

/**
 * Base Fitness Function
 * @author Pablo Cingolani <pcingola@users.sourceforge.net>
 */
public abstract class MySPFitnessFunction {

	/** Should this funtion be maximized or minimized */
	boolean maximize;

	//-------------------------------------------------------------------------
	// Constructors
	//-------------------------------------------------------------------------

	/** Default constructor */
	public MySPFitnessFunction() {
		maximize = true; // Default: Maximize
	}

	/**
	 * Constructor 
	 * @param maximize : Should we try to maximize or minimize this function?
	 */
	public MySPFitnessFunction(boolean maximize) {
		this.maximize = maximize;
	}

	//-------------------------------------------------------------------------
	// Methods
	//-------------------------------------------------------------------------

	//public abstract double constrain(double position[]);
	
	/**
	 * Evaluates a particles at a given position
	 * NOTE: You should write your own method!
	 * 
	 * @param position : Particle's position
	 * @return Fitness function for a particle
	 */
	public abstract double evaluate(double position[]);

	/**
	 * Evaluates a particles 
	 * @param particle : Particle to evaluate
	 * @return Fitness function for a particle
	 */
	public double evaluate(MySPParticle particle) {
		double position[] = particle.getPosition();
		double fit = evaluate(position);
		particle.setFitness(fit, maximize);
		return fit;
	}

	/**
	 * Is 'otherValue' better than 'fitness'?
	 * @param fitness
	 * @param otherValue
	 * @return true if 'otherValue' is better than 'fitness'
	 */
	public boolean isBetterThan(double fitness, double otherValue) {
		if (maximize) {
			if (otherValue > fitness) return true;
		} else {
			if (otherValue < fitness) return true;
		}
		return false;
	}

	/** Are we maximizing this fitness function? */
	public boolean isMaximize() {
		return maximize;
	}

	public void setMaximize(boolean maximize) {
		this.maximize = maximize;
	}

}