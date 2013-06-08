package prir.client;

import java.rmi.RemoteException;
import java.rmi.registry.*;

import prir.api.*;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    
    public final static int N = 10;

    public static void main(String[] args) throws Exception {
        registry = LocateRegistry.getRegistry(HOST, PORT);
        final Api remoteApi = (Api) registry.lookup("plate");
        
        double p[][] = new double[N][N];
        Plate plate = new Plate(N);
        
        plate.setEdgeTemp(p);

    	/*    main loop in plate array	*/
    	int i,j;
    	for(i=1;i<N-1;i++){
    		for(j=1;j<N-1;){
    			TempThread t1 = new TempThread(remoteApi, p, i, j++);
    			t1.run();
    			TempThread t2 = new TempThread(remoteApi, p, i, j++);
    			t2.run();
//    			Thread.sleep(2000);
    			
//    			p[i][j] = remoteApi.temperature(p,i,j);
//    			System.out.printf("[%d %d] %f ",i,j,p[i][j]);
    		}
    		System.out.printf("\n");
    	}

    	plate.showAndSavePlate(p, false);
 
    }
    
    
}

class TempThread extends Thread {
	
	private Api remoteApi;
	private double[][] p;
	private int x;
	private int y;
	
	
	public TempThread(Api remoteApi,double[][] p, int x, int y) {
		this.remoteApi = remoteApi;
		this.p = p;
		this.x = x;
		this.y = y;
	}

	@Override
	public void run() {
		try {
//			System.out.print("Thread, ["+ this.x +"]["+ this.y +"]");
			
			p[x][y] = remoteApi.temperature(p,x,y);
			System.out.printf("[%d %d] %f ",x,y,p[x][y]);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}