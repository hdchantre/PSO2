package MySP;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import MySPSO.*;

/**
 * Min Series-Parallel System
 * 
 * 	General form
 * 
 * 		f( x ) = \sum_{t\in (T_1,T_2,T_3,T_4)}\sum_{i=1}^{k_i}{c_i^t}{x_i^t}
 * 		
 * @author Hernani Chantre <hdchantre@gmail.com>
 */
public class MySPExample {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) throws IOException {
		System.out.println("Begin: SPR\n");

		int firstArg, secndArg,totalcmp,mulfactor;
		double minMME,minMCE,minBC,minGW,maxMME,maxMCE,maxBC,maxGW,reliabi,robj;
		//	double lMCE, lMME, lGW, lBC;
		//GaussianRandomGenerator rg ;

		if (args.length > 0) {
			try {

				firstArg = Integer.parseInt(args[0]); // number of subsystem n

				secndArg = Integer.parseInt(args[1]); // number of components kn

				minMCE = Double.parseDouble(args[3]); // min value of delay for MCE
				maxMCE = Double.parseDouble(args[4]); // max value of delay for MCE
				minMME = Double.parseDouble(args[5]); // min value of delay for  MME
				maxMME = Double.parseDouble(args[6]); // max value of delay for  MME

				minGW= Double.parseDouble(args[7]); // min value of delay for GW
				maxGW = Double.parseDouble(args[8]); // max value of delay for GW
				minBC = Double.parseDouble(args[9]); // min value of delay for  BC
				maxBC = Double.parseDouble(args[10]); // max value of delay for  BC
				// lMCE = Double.parseDouble(args[11]);  // Lambbda for  MCE
				// lMME = Double.parseDouble(args[12]);  // Lambbda for  MME
				// lGW  = Double.parseDouble(args[13]); // Lambbda for  GW
				//lBC = Double.parseDouble(args[14]); // Lambbda for  BC
				
				
				
				totalcmp = Integer.parseInt(args[12]); // total of cmp
				mulfactor = Integer.parseInt(args[13]); // increase by 
				reliabi = Double.parseDouble(args[14]); // level of reliability
				
				robj = Double.parseDouble(args[15]); // Min required reliability
				
				

			//	for (int r = 0 ; r <4 ; r++){
			//	for (double r = reliabi ; r <=0.9 ; r+=0.05){
					
					for (int j = secndArg  ; j <=totalcmp; j+=mulfactor)
					{ 
						
					
						
						for (int m = 0 ; m <10; m++)
						{
							
							MySPDataset.buildDataset(firstArg,j,minMCE,maxMCE,minMME,maxMME,minGW,maxGW,minBC,maxBC,reliabi,robj);	// vector stage n*kn
							//MySPDataset.buildDataset(firstArg,j,minMCE,maxMCE,minMME,maxMME,minGW,maxGW,minBC,maxBC,lMCE, lMME, lGW, lBC);	// vector stage n*kn
							//   VectorGenerator.getVector(); // Generator vectors for delay and relaibility 

							// args[2] sets the type of ParctileUpdate  to be used 0 UpdateSimple  -- 1 UpdateSimpleGaussProbDis
						//	Double[]pr = VectorGenerator.getReliabilityDCR();
							//Double[]pr1 = VectorGenerator.getReliabilityDCR();
							// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as fitness function)
							MySPSwarm swarm = new MySPSwarm(MySPSwarm.DEFAULT_NUMBER_OF_PARTICLES, new MySPExParticle(), new MySPExFitnessFunction(),Integer.parseInt(args[2]));
							//MySPSwarm swarm = new MySPSwarm(MySPSwarm.DEFAULT_NUMBER_OF_PARTICLES, new MySPExParticle(), new MySPExFitnessFunction());
							// SwarmRepulsive swarm = new SwarmRepulsive(100, new MyParticle(), new MyFitnessFunction());
							//		swarm.setOtherParticleIncrement(0.1);
							//		swarm.setRandomIncrement(0.1);

							//	swarm.setGaussProbDis(0.9, 1);



							// Use neighborhood
							//MySPNeighborhood neigh = new MySPNeighborhood1D(MySPSwarm.DEFAULT_NUMBER_OF_PARTICLES / 5, true);
							//swarm.setNeighborhood(neigh);
							//swarm.setNeighborhoodIncrement(0.95);

							// Set position (and velocity) constraints. I.e.: where to look for solutions
							swarm.setInertia(0.4);
							swarm.setGlobalIncrement(2.05);
							swarm.setParticleIncrement(2.05);

							swarm.setMaxPosition(4);
							swarm.setMinPosition(0);
							//	swarm.setMaxVelocity(maxVelocity);
							swarm.setMaxMinVelocity(0.80);

							int numberOfIterations = Integer.parseInt(args[11]); // number of iterations
							//C:\Users\Linka\Documents\Phd\Disciplinas\AlgorithmsExamples\output
						//	FileWriter f0 = new FileWriter("output"+j+"_"+r+".dat",true);
							//File dir = new File (""+j);
							//dir.mkdir();
						//	File file = new File(dir,"output_"+j+"_"+reliabi+".dat");
							FileWriter f0 = new FileWriter("output_"+j+"_"+reliabi+".dat",true);
							//FileWriter f0 = new FileWriter(file,true);
							PrintWriter out = new PrintWriter(f0);
							//FileWriter f01 = new FileWriter("output.csv",true);
							//PrintWriter out1 = new PrintWriter(f01);
							//String newLine = System.getProperty("line.separator");


							for (int i = 0; i < numberOfIterations; i++) 
							{
								swarm.evolve();
								/*if (i % 1000 == 0) {
						int idxBest = swarm.getBestParticleIndex();
						System.out.println("Iteration: " + i + "\tBest particle (" + idxBest + "):\t" + swarm.getParticle(idxBest).toString());
					}*/

								//	int idxBest = swarm.getBestParticleIndex();
								//System.out.println( swarm.getParticle(idxBest).toString());
								//f0.write(swarm.getParticle(idxBest).toString() +newLine);
								//out.println( j + ",\t" +swarm.getParticle(idxBest).toString());

							}
							out.println(swarm.getBestParticle());
							/*
							 * for (int i = 0; i < pr1.length; i++) {
								System.out.println("\n Pr[ " + i + "]=" + pr1[i]);
							}
							
							for ( int i = 0; i < pr1.length; i++) {
								out1.println("\n Pr1[ " + i + "]=" + pr1[i]);
							}*/
							
							
							//out1.println( j + "\t" +swarm.getBestParticle());
							
							out.flush();
							out.close();
							f0.close();
							//out1.flush();
							//out1.close();
							//f01.close();
						}

						//	System.out.println(swarm.getBestParticle());

						

						// Print en results
						//System.out.println(swarm.toStringStats());
						//	for (int i=0;i<6;i++){
						//	System.out.println( swarm.getBestPosition()[i]+"\t");



						}
					
					//}
			//	}
				System.out.println("End: SPR" ); 

				//	double[]pr = VectorGenerator.getReliability();
				//	double[]pr1 = VectorGenerator.getReliabilityDCR();
				//double[]cost = VectorGenerator.getDifCostDelay();
				//		double[] pr = {0.95,0.96,0.90,0.98,0.95,0.93};
				//		double [] position = {0.0,	1.0	,1.0	,2.0,	1.0,	0.0};
				//	double[]cost1 = VectorGenerator.getCost4Elem();


				/*for (int i = 0; i < pr.length; i++) {
					System.out.println("\n Pr[ " + i + "]=" + pr[i]);
				}*/


				/*	for (int i = 0; i < pr1.length; i++) {
				System.out.println("\n Pr1[ " + i + "]=" + pr1[i]);
			}
				 */	


				//System.out.println("\n Value =" + VectorGenerator.setConstraint1(swarm.getBestPosition()) +"\n factor ="+ VectorGenerator.constrain( swarm.getBestPosition(), pr)  );	
				//System.out.println("\n factor ="+ VectorGenerator.getRandom(0.4)  );	



				/*double lambda = 9.69; 
        System.out.println("Testing stat:   with lambda: " + lambda);
        for (int i = 0; i < 1000; i++){ 
            System.out.println(VectorGenerator.getRandom(lambda)); 
        }	*/


			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		}



	}
}
