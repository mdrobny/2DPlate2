package prir.client;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;

import prir.api.*;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;
    
    public final static int N = 15;

    public static void main(String[] args) throws Exception {
        registry = LocateRegistry.getRegistry(HOST, PORT + 1);
        final Api remoteServer1 = (Api) registry.lookup("plate1");
        registry = LocateRegistry.getRegistry(HOST, PORT + 2);
        final Api remoteServer2 = (Api) registry.lookup("plate2");
        PlotFrame plot = new PlotFrame(N);
        plot.setVisible(true);
        
        /** main temperature array **/
        double p[][] = new double[N][N];
        final Plate plate = new Plate(N);
        
        plate.setEdgeTemp(p);

    	/*    main loop in plate array	*/
        Api tmpRemoteServerRef = remoteServer1;
        ArrayList<TempThread> threadList = new ArrayList<TempThread>();
    	int i,j;
    	for(i=1;i<N-1;i++){
    		for(j=1;j<N-1;j++){
    			TempThread tmpT = new TempThread(tmpRemoteServerRef, p, i, j, plot);
    			threadList.add( tmpT );
    			tmpT.start();
    			tmpRemoteServerRef = (tmpRemoteServerRef==remoteServer1) ? remoteServer2 : remoteServer1;
    			
    		}
    	}

    	for(TempThread t: threadList){
    		t.join();
    	}
    	
    	plate.showAndSavePlate(p, false);
    	System.out.print("\n");
//    	new Thread( new ShowAndSavePlate(plate, p) ).start();
    	
 
    }
    
    
}

/**
 * Calculates temperature in [x,y] point in individual thread on chosen server 
 * @author drobny
 *
 */
class TempThread extends Thread {
	
	private Api remoteServer;
	private double[][] p;
	private int x;
	private int y;
        PlotFrame plot;
	
	
	public TempThread(Api server,double[][] p, int x, int y,PlotFrame pl) {
		this.remoteServer = server;
		this.p = p;
		this.x = x;
		this.y = y;
                plot = pl;
	}

	@Override
	public void run() {
		try {
			
			p[x][y] = remoteServer.temperature(p,x,y);
			System.out.printf("[%d %d] %f ",x,y,p[x][y]);
                        plot.addValue(x, y, p[x][y]);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}

/**
 * Thread to save temperature array (p) to file
 * @author drobny
 *
 */
//class ShowAndSavePlate implements Runnable {
//	
//	private Plate plate;
//	private double[][] p;
//	
//	public ShowAndSavePlate(Plate plate, double[][] p){
//		this.plate = plate;
//		this.p = p;
//	}
//
//	@Override
//	public void run() {
//		try {
//			System.out.println("\n\nShowAndSaveThread");
//			plate.showAndSavePlate(p, false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//}