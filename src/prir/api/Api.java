package prir.api;

import java.rmi.*;

public interface Api extends Remote {

	public double temperature(double[][] p, int x, int y) throws RemoteException;

}
