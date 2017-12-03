package ee509_assign1;

import java.io.IOException;
import java.util.*;



public class StartSim {
	
	public static double Batch, Clock,EndClock, TimeDelay, QueueDepartures, queueStatus, serviceTime,departInservice;
	public static double maxQueue, Queue;
	public static double packetCount,MeanDelay,MU, Lambda,totalPacketCount, TotalTime,NumDepart, TotalDelay, totalBatchDelay;
	public static long NumPackets, QueueLength, TotalPackets, ID, TotalArrived;
	public static double batchMeanDelay, delay3DevPlus, delay3DevMinus, totalLargeDelay, countLargeDelay, countSmallDelay, totalSmallDelay;
	public static double batchArrival, batchDepart, batchDrop, totalDrop, throughPut;
	public static double[] batchArray;
	public static double percent95;
	public static double plus95, percent95plus;
	public static double minus95, percent95minus;
	
	public static Random RDM;
	public static EventListManager EventList;
	public static PacketQueue PQueue;
	
	public static WriteFile writeData = new WriteFile();
	public static REDController REDCon = new REDController();
	
	public static void main(String[] args) {
		
		StatisticFiles statisticFile = new StatisticFiles();
		statisticFile.resetFiles();
		
		percent95 = 0;
		plus95 =0;
		percent95plus= 0;
		minus95 =0;
		percent95minus=0;
		QueueDepartures =0;
		Clock = 0;
		EndClock = 0;
		serviceTime =0;
		packetCount = 1;
		TotalTime = 1;
		Batch = 1;
		TotalDelay = 0;
		MeanDelay = 0;
		ID = 1;
		totalBatchDelay =0;
		delay3DevPlus = 0.0004143376996;
		delay3DevMinus = 0.0004143376996;
		countLargeDelay = 0;
		totalLargeDelay = 0;
		countSmallDelay = 0;
		totalSmallDelay = 0;
		Queue = 31;
		throughPut = 0;
		batchArray  = new double[100];
		
		totalDrop = 0;
		
		//Lambda: 0.001
		//MU: 0.0008
		
		Lambda = 0.0006666666667;
		MU = 0.0008;
		
		
		
		System.out.println("Lambda= " + Lambda + " MU= " + MU);
		TotalPackets = 0;
		
		Clock = 0;				
		double totalPacketCount = 0;
		double ratePerBatch = TotalTime;
		ID=1;
		for(int i=0; i<Batch; i++)
		{
			
			TotalTime = ratePerBatch*(i+1);
			QueueDepartures =0;
			EndClock = 0;
			serviceTime =0;
			packetCount = 1; 
			MeanDelay = 0;
			TotalArrived = 0;
			maxQueue=0;
			QueueLength=0;
			departInservice=0;
			batchArrival = 0;
			batchDepart = 0;
			batchDrop = 0;			
						
			RDM = new Random(i+5);
		
			EventList = EventListManager.getInstance();
			EventList.removeAll();
			EventList.setDropPackets();
			Event event1 = new Event(Clock + negExpon(RDM, Lambda), ID,"Arrival");
			Clock = event1.getEventClock();
			EventList.insertEvent(event1);
			
			Event event2 = new Event();
		while(Clock <= TotalTime /*|| batchDepart < batchArrival-1*/)
		{
			
			if(Clock== 1.0118544921326826)
			{
				System.out.println("Stop" + batchArrival);
			}
			
				
			if(EventList.isEmpty()== false)
			{
				event2 = EventList.poll();
				Clock = event2.getEventClock();
			}
			
				
						
			if(event2.getEventType()== "Arrival")
			{
				ProcessPacket(event2);
				
				if(Clock <= TotalTime)
				{
					newArrival();
					TotalArrived++;
					batchArrival++;
					totalPacketCount++;
				}							
			}
			else
			{
				DepartPacket(event2);
			}
			
		}
		batchMeanDelay = TotalDelay/NumDepart;
		System.out.println(batchMeanDelay );
		totalBatchDelay +=  batchMeanDelay;
		totalDrop += batchDrop;
		batchArray[i] = batchMeanDelay;
		
		String printText = "Batch" + i + batchMeanDelay ;
			try{
				writeData.writeToFile(printText, "C:/Users/Server/Documents/batchDelay.txt", true);
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
					
		
		}
		
		System.out.println("\nLambda= " + Lambda + " MU= " + MU);
		System.out.println("\nArrived packet per Trial: " + TotalArrived);
		System.out.println("Mean Large Delay of " + countLargeDelay + " Packets:" + totalLargeDelay/countLargeDelay);
		System.out.println("Mean Small Delay of " + countSmallDelay + " Packets:" + totalSmallDelay/countSmallDelay);
		System.out.println("Total Packet Count: " + totalPacketCount);
		System.out.println("Total Departed packets: " + NumDepart);
		System.out.println("Total Dropped packets: " + totalDrop);
		System.out.println("Total Batch Mean Delay: " + Batch + " Trials = " + totalBatchDelay/Batch);
		System.out.println("Throughput: Mbits Per Second : " + throughPut/TotalTime);
		
		@SuppressWarnings("unused")
		double test4 = 0;
		for(int i=1; i<Batch; i++)
		{
			double test1 = batchArray[i];
			double test2 = totalBatchDelay/Batch;
			double test3 = test1 - test2;
			
			test4 += (test3 * test3);
		}
		
		test4 = test4/Batch;
		test4 = Math.sqrt(test4);
		System.out.println("Standard Dev: " + test4);
				
		percent95 = ((1.96*test4)/(Math.sqrt(Batch)));
		plus95 = (totalBatchDelay/Batch)+ percent95;
		minus95 = (totalBatchDelay/Batch)- percent95;
		
		System.out.println("Standard Dev: " + test4);
		System.out.println("95% Convedence: " + percent95);
		System.out.println("Erlang =" + (Lambda/MU) +  "95% Plus: " + plus95 + " 95% Minus: " + minus95 );
		System.out.println("MAX Queue: " + maxQueue);
		
		for(int i=1; i<Batch; i++)
		{
			double test1 = batchArray[i];
			if(test1 <= plus95 && test1 > minus95 )
			{
				percent95plus ++;
			}
		}
		System.out.println("test" + percent95plus);
			
		
	}
	

	
	public static void ProcessPacket(Event event)
	{
		double tempClock;
		
		if(EndClock == 0 )
		{
			packetCount++;
			tempClock = Clock + negExpon(RDM, MU);
					
			event.setArrivalClock(event.getEventClock());
			event.setEventClock(tempClock);
			event.setType("Depart");
			EndClock = event.getEventClock();
			EventList.insertEvent(event);
							
		}
		else
		{	
			if(Clock == 0.0047096348521287225)
			{
				System.out.println("STOP");
			}
			
			//Question:2.2
			//If QueueLength is greater than the set Queue of 31 the Arrival Packet is not processed
			
			/*if(QueueLength >= Queue)
			{
				batchDrop++;
			}*/
				
			REDCon.calAvg(QueueLength, event, Clock);
			
			if(REDCon.isProcessPacket() == false || QueueLength >= Queue)
			{
				batchDrop++;
			}
			else
			{
				event.setArrivalClock(event.getEventClock());
				event.setType("Depart");
					
				if(EndClock < event.getEventClock())
				{
					double negE = negExpon(RDM, MU);
					event.setEventClock(event.getEventClock() + negE);	
					event.setPacketLen(negE);	
					EndClock = event.getEventClock();
				}
				else
				{
					double negE = negExpon(RDM, MU);
					event.setEventClock(EndClock + negE);	
					event.setPacketLen(negE);
					EndClock = event.getEventClock(); 
				}
							
				EventList.insertEvent(event);
				QueueLength++;
				
				/*Used to write out the queuelength for Question 2.2 and 3.2 */
				String printText = event.getEventClock()+ "Queue Lenght Arrive: " + QueueLength;
				try{
					writeData.writeToFile(printText, "C:/Users/Server/Documents/queueArrival.txt", true);
				}
				catch (IOException e)
				{
					System.out.println(e.getMessage());
				}
				//System.out.println(event.getEventClock()+ "Queue Lenght Arrive: " + QueueLength);
			}
			
									
		}	
	}
	
	public static void newArrival()
	{
			ID++;
			Event newEvent = new Event(Clock + negExpon(RDM, Lambda), ID, "Arrival");
			EventList.insertEvent(newEvent);
			
									
			if(maxQueue < QueueLength )
			{
				maxQueue = QueueLength;
								
			}
		
	}
	
	public static void DepartPacket(Event event)
	{
		double delay1 = (event.getEventClock() - event.getArrivalClock());
		 
		TotalDelay += (event.getEventClock() - event.getArrivalClock());
		
		if (delay1 > 0.025)
		{
			countLargeDelay++;
			totalLargeDelay += delay1; 
		}
		
		if (delay1 <= delay3DevMinus)
		{
			countSmallDelay++;
			totalSmallDelay += delay1; 
		}
								
		QueueLength--;
		if(QueueLength <=0)
		{
			QueueLength = 0;
		}
		NumDepart++;
		departInservice=0;
		batchDepart++;
		/*Used to write out the queuelength for Question 2.2 and 3.2 */
		String printText = event.getEventClock()+ " " + QueueLength;
		try{
			writeData.writeToFile(printText, "C:/Users/Server/Documents/queueDepart.txt", true);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		throughPut += event.getPacketLen();
		
	}
	
		public static double negExpon(Random RDM, double mean1)
		{
			double expNum;
			double ranJavaNum;
			ranJavaNum = RDM.nextDouble();
			double exponent = (-mean1)*ranJavaNum;
			
			expNum = 1-(Math.exp(exponent));	
			//double expNum2 = 1-(Math.exp(-exponent));
			
			//System.out.println(expNum);
			return expNum;
			
		}
		
}

