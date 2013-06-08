package prir.api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Plate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public double[][] p;
	private int N;
	
	public Plate(int N){
		 this.N = N;
	}
	
	/**
	*	start conditions - temperature at edges of plate
	*/
	public void setEdgeTemp(double p[][]){
		int i,j;

		for(i=0;i<N;i++){
			for(j=0;j<N;j++){
				p[i][j] = -1.0;
			}
		}
		for(i = 0 ; i < N ; ++i){
			p[0][i] = 0.0;
			p[i][0] = 0.0;
			p[i][N-1] = 0.0;
		}
		
		for(j=0;j<N;j++)
			p[N-1][j] = 100.0;
	}
	
	/**
	*	if out==true shows plate at std output
	*	save plate in file in format to generate pm3d map
	 * @throws IOException 
	*/
	public void showAndSavePlate(double p[][], boolean out) throws IOException{
		FileWriter file = new FileWriter(new File("proj2.txt"));
		int i,j;
		if(out) {
			for(i=0;i<N;i++){
				for(j=0;j<N;j++) System.out.printf("%3.2f ", p[i][j]);
				System.out.printf("\n");
			}
		}

		//file output, used to create grid image
		String o = new String();
		for(i=0;i<N;i++){
			for(j=0;j<N;j++) {
				o = String.format("%d %d %3.6f\n",i,j,p[i][j]);
				file.write(o);
			}
			file.write("\n");
		}
		file.close();
	}

}
