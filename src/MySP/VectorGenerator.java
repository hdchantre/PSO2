package MySP;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class VectorGenerator  {

	public static Integer N;
	public static Integer K;


	public static double[] vector; 



	public static double[] getVector() {
		N = MySPDataset.numSubSystem;
		K =MySPDataset.numComp;
		int dim = N* K;


		double[] vector = new double[dim];
		for ( int i = 0; i < vector.length; ++i){
			vector[i] = Math.random();
		}
		return vector;
	}

	// Returns a random number between min (included) and max (excluded) for MME (2)
	public static double setDelay1() {


		return  Math.random() * (MySPDataset.maxMME - MySPDataset.minMME ) + MySPDataset.minMME;

	}

	// Returns a random number between min (included) and max (excluded) for MCE (1)
	public static double setDelay0() {


		return Math.random() * (MySPDataset.maxMCE - MySPDataset.minMCE) + MySPDataset.minMCE;

	}

	// Returns a random number between min (included) and max (excluded) for BMSC (4)
	public static double setDelay3() {


		return  Math.random() * (MySPDataset.maxBC- MySPDataset.minBC ) + MySPDataset.minBC;

	}

	// Returns a random number between min (included) and max (excluded) for MBMSGW (3)
	public static double setDelay2() {


		return  Math.random() * (MySPDataset.maxGW - MySPDataset.minGW) + MySPDataset.minGW;

	}


	public static double[] setCost4Elem() {

		double[] vector = new double[4];

		vector [0] = setDelay0();
		vector [1] = setDelay1();
		vector [2] = setDelay2();
		vector [3] = setDelay3();

		return vector;
	}


	//Create a vector with the same cost delay  for each k component of a subsystem element

	public static double[] getCostDelay() {
		N = 4;
		K =MySPDataset.numComp;
		int dim = N* K;


		double[] vector = new double[dim];

		double [] v = setCost4Elem();



		int i = 0;


		do {

			for (int l = 0 ; l< v.length ; l++)
			{ for (int j = 0 ;j < K; j++)
			{


				vector[i+j] = v[1];

			}
			i +=K;
			}


		}
		while (i<dim);




		return vector;
	}


	// Create a vector cost delay within the same range for th k component of a subsystem element  
	public static double[] getDifCostDelay() {
		N = 4;
		K =MySPDataset.numComp;
		int dim = N* K;

		double[] vector = new double[dim];


		int i = 0;


		do {

			for (int l = 0 ; l< setCost4Elem().length ; l++)
			{ 
				for (int j = 0 ;j < K; j++)
				{


					vector[i+j] =setCost4Elem()[l] ;

				}
				i +=K;
			}


		}
		while (i<dim);

		// Arrays.sort(vector);


		return vector;
	}



	//Create a vector with the same cost delay  for each k component of a subsystem element
	//This constraint check if Sum_{k}x_{ik}>=1 returning true if the summ is less then 1 

	public static void setConstraint(double position[]) {

		N = MySPDataset.numSubSystem;
		K =MySPDataset.numComp;
		//int dim = N* K;
		
		//boolean value = false; 
		double [] v = new double [N];
		double [] v1 = position;
		
		
		for (int l = 0 ; l< v.length ; l++)
		{ 
			for (int j = 0 ;j < K; j++)
			{

				v[l] += v1[K*l+j];

			}
		
	
		}
		
		for (int m=0;m<v.length;m++){
			System.out.print(+v[m]+"\t");
		}
		
	/*	for(double z: v){
			if (z < 1) return true;

	
		}
		
		

		return false;      
*/	}


	
	public static boolean setConstraint1(double position[]) {

		N = MySPDataset.numSubSystem;
		K =MySPDataset.numComp;
		//int dim = N* K;
		
		//boolean value = false; 
		double [] v = new double [N];
		double [] v1 = position;
		
		
		for (int l = 0 ; l< v.length ; l++)
		{ 
			for (int j = 0 ;j < K; j++)
			{

				v[l] += v1[K*l+j];

			}
		
	
		}
		/*
		for (int m=0;m<v.length;m++){
			System.out.print(+v[m]+"\t");
		}
		*/
		for(double z: v){
			if (z < 1) return true;

	
		}
		
		

		return false;      
	}	
	
	
	
	
	public static double constrain(double position[], double pr[]) {
		double f = 1;
		//double[]pr = VectorGenerator.getVector();



			for (int i = 0; i < (position.length - 1); i++) {
				
			f *= 1- Math.pow(1-pr[i], position[i])*(Math.pow(1-pr[i+1], position[i+1]));
		}
		
		return f;
	}
	
	// Poisson Distribution  Reliability
	
	 public static double getRandom(double lambda) { 
         //double L = Math.exp(-lambda);
         Random r = new Random ();
         
         return -(Math.log(r.nextDouble()) / lambda);
        /* int k = 0; 
         double p = 1.0; 
         do { 
             k++; 
             p = p *r.nextDouble() ; 
         } while (p > L); 

         return k - 1; */
     }
	 
	 
	/*	public static double[] getReliability() {
			N = 4;
			K =MySPDataset.numComp;
			int dim = N* K;
			Random r = new Random ();

			double[] vector = new double[dim];

			
			// for each paramenter lammbdaMCE,lammbdaMME,lammbdaGM,lammbdaBC;
			
			double [] v = new double[N] ;
			
			//doulammbdaMCE,lammbdaMME,lammbdaGM,lammbdaBC;
			
			v [0] = -(Math.log(r.nextDouble()) / MySPDataset.lammbdaMCE);
			v [1] = -(Math.log(r.nextDouble()) / MySPDataset.lammbdaMME);
			v [2] = -(Math.log(r.nextDouble()) / MySPDataset.lammbdaGW);
			v [3] = -(Math.log(r.nextDouble()) / MySPDataset.lammbdaBC);



			int i = 0;


			do {

				for (int l = 0 ; l< v.length ; l++)
				{ for (int j = 0 ;j < K; j++)
				{


					vector[i+j] = v[l];

				}
				i +=K;
				}


			}
			while (i<dim);




			return vector;
		}	*/
	 //commented on 2k160905 due to the fact the reliability for all components are equal
	 /*public static double[] setRel4Elem() {

			double[] vector = new double[4];

			vector [0] = Math.random() * (0.997 - 0.992) + 0.992; //MCE
			vector [1] = Math.random() * (0.992 - 0.988) + 0.988; //MME
			vector [2] = Math.random() * (0.988 - 0.983) + 0.983;// GW
			vector [3] = Math.random() * (0.983 - 0.980) + 0.980; //SC

			return vector;
		}*/
		
		public static double[] getReliabilityDCR() {
			N = 4;
			K =MySPDataset.numComp;
			int dim = N* K;
			//Random r = new Random ();

			double[] vector = new double[dim];

			
			// for each paramenter lammbdaMCE,lammbdaMME,lammbdaGM,lammbdaBC;
			
			//double [] v = new double[N] ;
			
			//doulammbdaMCE,lammbdaMME,lammbdaGM,lammbdaBC;
			// 0.48 % and 2%
			/*v [0] = Math.random() * (0.9952 - 0.98) + 0.98;
			v [1] = Math.random() * (0.9952 - 0.98) + 0.98;
			v [2] = Math.random() * (0.9952 - 0.98) + 0.98;
			v [3] = Math.random() * (0.9952 - 0.98) + 0.98;*/
			
		//	v [0] = 0.9815;
		//	v [1] = 0.9882;
		//	v [2] = 0.9905;
		//	v [3] = 0.9950;



			int i = 0;


			do {

				for (int l = 0 ; l< N; l++)
				{ for (int j = 0 ;j < K; j++)
				{

					vector[i+j] = MySPDataset.reliabi;
					//vector[i+j] = v[l];
					//setRel4Elem()[l]

				}
				i +=K;
				}


			}
			while (i<dim);
			
			/*for (int left=0, right=vector.length-1; left<(vector.length/2); left++, right--) {
			    // exchange the first and last
			    double temp = vector[left];
			    vector[left]  = vector[right];
			    vector[right] = temp;
			}*/
		//	Arrays.sort(vector);
			//Collections.sort(vector, Collections.reverseOrder());
			//Arrays.sort(vector, Collections.reverseOrder());

			return vector;
		}	 

		

}
