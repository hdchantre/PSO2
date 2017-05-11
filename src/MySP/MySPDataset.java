package MySP;

public class MySPDataset {
	
	public static Integer numSubSystem;
	public static Integer numComp;
	public static Double maxMME,maxMCE,maxBC,maxGW;
	public static Double minMME,minMCE,minBC,minGW;
	public static Double reliabi;
	public static Double reliabobj;
	
	//public static Double lammbdaMCE, lammbdaMME, lammbdaGW, lammbdaBC; 
	
	
	//public static void buildDataset(int n, int m, int miMCE, int mxMCE, int miMME, int mxMME, int miGW, int mxGW,  int miBC,int mxBC, double lMCE, double lMME, double lGW, double lBC ){
	public static void buildDataset(int n, int m, double miMCE, double mxMCE, double miMME, double mxMME, double miGW, double mxGW,  double miBC,double mxBC,double reliab, double reob){
		numSubSystem = n;
		numComp = m;
		
		minMME =miMME;
		minMCE = miMCE;
		minBC = miBC;
		minGW = miGW;
		
		maxMCE =mxMCE;
		maxMME=mxMME;
		maxMCE=mxMCE;
		maxGW = mxGW;
		maxBC= mxBC;
		reliabi = reliab;
		reliabobj = reob;
		/*lammbdaMCE = lMCE;
		lammbdaMME = lMME;
		lammbdaGW = lGW;
		lammbdaBC = lBC;*/ 
		
		
	}

}
