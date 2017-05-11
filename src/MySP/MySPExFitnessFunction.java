package MySP;

import MySPSO.MySPFitnessFunction;
import MySPSO.MySPParticle;

/**
 * Min Series-Parallel System
 * 
 * 	General form
 * 
 * 		f( x ) = \sum_{t\in (T_1,T_2,T_3,T_4)}\sum_{i=1}^{k_i}{c_i^t}{x_i^t}
 * 		
 * @author Hernani Chantre <hdchantre@gmail.com>
 */
public class MySPExFitnessFunction extends MySPFitnessFunction {

	//double[] cost= {18,19,13,14,16,17};
	double[] cost= VectorGenerator.getDifCostDelay();
	double[] pr=  VectorGenerator.getReliabilityDCR();
	//double[] pr= {0.95,0.96,0.90,0.98,0.95,0.93};
	double Ro = MySPDataset.reliabobj;
	//double Ro = 0.9998;
	//public double penaltyFactor = 1e100;
	//public double penaltyFactor = 1e-06;
	public double dp = 1e-04;
	public double penaltyFactor = 1000000*(dp*dp) ;
	
	//public double penaltyFactor =10000*(Math.cosh(4*dp)-1);

	/** Should this funtion be maximized or minimized */
	boolean maximize;

	public double factor;

	/** Default constructor */
	public MySPExFitnessFunction() {
		super(false); // Minimize this function
	}

	//-------------------------------------------------------------------------
	// Methodsy
	//-------------------------------------------------------------------------


	/**
	 * Update on 22/07/2016 extending MySPParticle
	 * adding factor to the particle
	 * Evaluates a particles at a given position
	 * @param 
	 * @return 
	 */
	@Override
	public double evaluate(MySPParticle particle) {
		double position[] = particle.getPosition();

		double fit =0;
		boolean  value =  constrain1(position);

		//double factor = particle.getFactor();
		if (value == false ){

			if (constrain(position) == 0.0)
			{ return evaluate(position);
			}else{ 
				

				
					particle.setFactor(constrain(position));
					
					fit = evaluate(position);

					particle.setFitness(fit, maximize);

					return fit; 
				
				 }

		} else {
			return evaluate(position);


		}


	}



	/**
	 * Constraint Factor
	 * @param position : Particle's position
	 * @return Factor function for a particle
	 */
	public double constrain(double position[]) {
		double f = 1;
		//double[]pr = VectorGenerator.getVector();



		for (int i = 0; i < (position.length - 1); i++) {

			f *= 1- Math.pow(1-pr[i], position[i])*(Math.pow(1-pr[i+1], position[i+1]));
		}

		return f;
	}

	/**
	 * This constraint check if Sum_{k}x_{ik}>=1 returning true if the summ is less then 1 
	 * @param position : Particle's position
	 * @return Factor function for a particle
	 */

	public boolean constrain1(double position []){

		return VectorGenerator.setConstraint1(position);
	}


	/**
	 * Evaluates a particles at a given position
	 * @param position : Particle's position
	 * @return Fitness function for a particle
	 */
	@Override
	public double evaluate(double position[]) {


		//	boolean value = constrain1(position);
		MySPExParticle particle = new  MySPExParticle ();
		double factor = particle.getFactor();
		//double[]cost = VectorGenerator.getVector();		

		double penalty = 0;
		double f = 0;
		double  y = 0 ;

		for (int i = 0; i < position.length ; i++) {

			f += cost[i]*position[i];

		}

        

		// double restr = factor - Ro;
		if (factor == 0.0 && factor < Ro  ) { 
			penalty = penaltyFactor * factor;
			y = f-penalty;
			return y; 

		} else {  return f ;}

	}
}
