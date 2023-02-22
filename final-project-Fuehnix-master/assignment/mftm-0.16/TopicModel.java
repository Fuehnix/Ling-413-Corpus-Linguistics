import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TopicModel {

	public void run(int iters, String filename) throws Exception {
		readDocs(filename);
		initialize();
		
		System.out.println("Sampling...");
		
		long startTime = System.nanoTime();
		for (int iter = 1; iter <= iters; iter++) {
			if(iter % 50 == 0){
				System.out.println("Iteration "+iter);
			}
			doSampling();
		}
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Time elapsed for ccLDA Java run time: " + TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS));
		// write variable assignments

		writeOutput(filename);

		System.out.println("...done.");
	}
	
	public abstract void initialize();
	
	public abstract void doSampling();
	
	public abstract void readDocs(String filename) throws Exception;
	
	public abstract void writeOutput(String filename) throws Exception;
}
