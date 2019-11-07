package ee509_assign1;

import java.io.File;

public class StatisticFiles {
	
	File queueArrival;
	File queueDepart;
	File batchDelay;
	
	public StatisticFiles()
	{
		queueArrival = new File("C:/Users/Server/Documents/queueArrival.txt");
		queueDepart = new File("C:/Users/Server/Documents/queueDepart.txt");
		batchDelay = new File("C:/Users/Server/Documents/batchDelay.txt");
	}
	public void resetFiles()
	{
		queueArrival.delete();
		queueArrival = new File("C:/Users/Server/Documents/queueArrival.txt");
		queueDepart.delete();
		queueDepart = new File("C:/Users/Server/Documents/queueDepart.txt");
		batchDelay.delete();
		batchDelay = new File("C:/Users/Server/Documents/batchDelay.txt");
	}
	
}
