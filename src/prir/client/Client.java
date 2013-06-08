package prir.client;

import java.rmi.registry.*;

import prir.api.*;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    
    public final static int N = 10;

    public static void main(String[] args) throws Exception {
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup("plate");
        
        double p[][] = new double[N][N];
        Plate plate = new Plate(N);
        
        plate.setEdgeTemp(p);

    	/*    main loop in plate array	*/
    	int i,j;
//    	double temp;
    	for(i=1;i<N-1;i++){
    		for(j=1;j<N-1;j++){
    			p[i][j] = remoteApi.temperature(p,i,j);
    			
    			System.out.printf("[%d %d] %f ",i,j,p[i][j]);
    		}
    		System.out.printf("\n");
    	}

    	plate.showAndSavePlate(p, false);
 
    }
}
