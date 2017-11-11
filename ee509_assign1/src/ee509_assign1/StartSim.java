package ee509_assign1;

import java.util.*;


public class StartSim {
	
	public static double Batch, Clock,EndClock, TimeDelay, QueueDepartures, queueStatus, serviceTime ;
	public static double packetCount, TotalDelay, MeanDelay, TotalArrived, batchMeanDelay;
	public static long NumPackets, QueueLength, TotalPackets,  NumDepart;
	public static float Lamda, MU, TotalTime;

	public static Random RDM;
	public static EventListManager EventList;
	public static PacketQueue PQueue;
	
	public static void main(String[] args) {
		
		QueueDepartures =0;
		Clock = 0;
		EndClock = 0;
		serviceTime =0;
		packetCount = 1;
		TotalTime = 10f;
		Batch = 100;
		TotalDelay = 0;
		MeanDelay = 0;
		
		Lamda = 0.0001f;
		MU = 0.00000008f;
		
		
		System.out.println("Lamda= " + Lamda + "MU= " + MU);
		TotalPackets = 0;
		
		long seed = 1;
		RDM = new Random(seed);
		
		//EventList = EventListManager.getInstance();
		Event event1 = new Event(negExpon(RDM, Lamda),packetCount);
		
			
		EventList = EventListManager.getInstance();
		EventList.removeAll();
		
		Clock = 0;				
		Event event33 = new Event(negExpon(RDM, Lamda),packetCount);
		Clock = event33.getEventClock();
		EventList.insertEvent(event33);
		
		
		for(int i = 0; i<Batch;i++)
		{
			//System.out.println("\nBatch Number: " + (i+1));
			TotalTime = 10f;
			QueueDepartures =0;
			EndClock = 0;
			serviceTime =0;
			packetCount = 1; 
			TotalDelay = 0;
			MeanDelay = 0;
			TotalArrived = 0;
			
			if(i>0){
				EventList.removeAll();
				EventList.setDropPackets();
				Event event34 = new Event(Clock + negExpon(RDM, Lamda), packetCount);
				Clock = event34.getEventClock();
				EventList.insertEvent(event1);
			}
		int n = 0;
		TotalTime = TotalTime * (i+1);
		
		while(Clock < TotalTime)
		{
			Event event2 = new Event();
			event2 = EventList.poll();
			TotalArrived +=1;
			ProcessPacket(event2);
		}
		
		//System.out.println("Clock: " + Clock + " Mean_Delay: " + TotalDelay/QueueDepartures);
		//System.out.println("EndClock after " + QueueDepartures + " Packets = " + EndClock);
		//System.out.println("Mean Time Delay after " + QueueDepartures + " Packets = " + TotalDelay/QueueDepartures);
		System.out.println("Totatl arrived packet: " + TotalArrived);
		System.out.println("Total Departed packets: " + QueueDepartures);
		System.out.println("Total dropped packets: " + EventList.getDropPackets());
		batchMeanDelay += TotalDelay/QueueDepartures;
		
		}
		System.out.println("Batch Mean Delay over " + Batch + " Trials = " + batchMeanDelay/100);
	}
		

	
	public static void ProcessPacket(Event event)
	{
		queueStatus = Clock - EndClock;
		if(queueStatus>=0)
		{
			ServicePacket(event);
			//System.out.println("PacketNum: " + event.getEventNum()+ " Gone" );
			if(QueueLength <= 0)
			{
				QueueLength = 0;
			}
			else
			{
				QueueLength--;
			}
			
			
		}
		else
		{
			EventList.insertEvent(event);
			if(QueueLength < 0)
			{
				QueueLength = 0;
			}
			QueueLength++;
			TimeDelay += Clock - EndClock;
		}
						
			packetCount++;
			
			double interArrivalTime1 = negExpon(RDM, Lamda);
			Event event1 = new Event(Clock + interArrivalTime1, packetCount);
			Clock = event1.getEventClock();
			EventList.insertEvent(event1);
			
			
	}
	
	public static void ServicePacket(Event event)
	{
		double pDelay1;
		serviceTime = (negExpon(RDM, MU));
		EndClock = Clock + serviceTime;
		
		pDelay1 = EndClock - event.getEventClock();
		QueueDepartures++;
		TotalDelay += pDelay1;
		//MeanDelay = TotalDelay/QueueDepartures;
		//System.out.println("Delay: " + TotalDelay + " MeanDelay: " + MeanDelay);
		//System.out.println("QueueDepartures" + QueueDepartures);
	}
	
		public static double negExpon(Random RDM, float Lamda1)
		{
			double temp;
			temp = RDM.nextDouble();
			
			double temp4F = Math.exp((-Lamda1)*temp);
			double temp1 = 1-temp4F;
			return temp1;
				
		}
	
}

