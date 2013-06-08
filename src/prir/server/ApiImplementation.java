package prir.server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

import prir.api.*;

public class ApiImplementation extends UnicastRemoteObject implements Api {
    private static final long serialVersionUID = 1L;
    
    private Random rnd = new Random();

    public ApiImplementation() throws RemoteException {
        super();
    }

	@Override
	public double temperature(double[][] p, int x, int y) throws RemoteException {
		int i;
		final double EPS = 0.0001;
		long n = 0;
		double accu = 0.0;
		double temp = 0.0;
		double oldTemp = -1.0;

		while(Math.abs(temp - oldTemp) > EPS){
			oldTemp = temp;
			for( i = 0 ; i < 500 ; ++i, ++n){
				accu += walk2(p,x,y);
			}
			temp = accu/n;
		}
//		plate.p[x][y] = temp;
//		System.out.printf("temp: [%d %d] %f\n",x,y,temp);
		return temp;
	}
	
	
	/**
	*	1-north, 2-east, 3-south, 4-west
	*/
	private int randDirection(){
//		Random rnd = new Random();
//		int r = rnd.nextInt(4) + 1;
//		System.out.print(" "+ r);
		return rnd.nextInt(4) + 1;
	}
	
	/**
	*	recursive walk
	*	ends after reaching counted temperature
	*/
	private double walk2(double p[][],int x, int y){
		switch(this.randDirection()){
			case 1:	if(p[--x][y] < 0) break;
				else return p[x][y]; 
			case 2:  if(p[x][++y] < 0) break;
				else return p[x][y];
			case 3:  if(p[++x][y] < 0) break;
				else return p[x][y]; 
			case 4:  if(p[x][--y] < 0) break;
				else return p[x][y]; 
		}
		return this.walk2(p,x,y);
	}
}
