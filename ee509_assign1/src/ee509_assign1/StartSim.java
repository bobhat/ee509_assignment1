package ee509_assign1;

import java.util.*;



public class StartSim {
	
	public static double Batch, Clock,EndClock, TimeDelay, QueueDepartures, queueStatus, serviceTime;
	public static double packetCount, TotalDelay, MeanDelay, TotalArrived, batchMeanDelay;
	public static long NumPackets, QueueLength, TotalPackets, ID,  NumDepart;
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
		TotalTime = 1f;
		Batch = 1;
		TotalDelay = 0;
		MeanDelay = 0;
		ID = 1;
		
		
		Lamda = 0.001f;
		MU = 0.0008f;
		
		
		System.out.println("Lamda= " + Lamda + "MU= " + MU);
		TotalPackets = 0;
		
		long seed = 1;
		RDM = new Random(seed);
				
		Event event1 = new Event(negExpon(RDM, Lamda),ID,"Arrival");
		
		EventList = EventListManager.getInstance();
		EventList.removeAll();
		
		Clock = 0;				
		EventList.insertEvent(event1);
		
		
		//for(int i = 0; i<Batch;i++)
		//{
			//System.out.println("\nBatch Number: " + (i+1));
			//TotalTime = 20f;
			//QueueDepartures =0;
			//EndClock = 0;
			//serviceTime =0;
			//packetCount = 1; 
			//TotalDelay = 0;
			//MeanDelay = 0;
			///TotalArrived = 0;
			
			/*if(i>0){
				EventList.removeAll();
				EventList.setDropPackets();
				Event event2 = new Event(Clock + negExpon(RDM, Lamda), packetCount);
				Clock = event2.getEventClock();
				EventList.insertEvent(event2);
			}
		int n = 0;*/
		//TotalTime = TotalTime * (i+1);
		
		while(Clock < TotalTime)
		{
			Event event2 = EventList.poll();
			Clock = event2.getEventClock();
			
			
			if(event2.getEventType()== "Arrival")
			{
				ProcessPacket(event2);
				TotalArrived +=1;
				System.out.println("Arrival ID: " + event2.getEventNum() + " Clock:" + Clock);
			}
			else
			{
				DepartPacket(event2);
				System.out.println("Depart ID: " + event2.getEventNum() + " Clock:" + Clock);	
			}
			
			
			
		}
		//}
		
		//System.out.println("Clock: " + Clock + " Mean_Delay: " + TotalDelay/QueueDepartures);
		//System.out.println("EndClock after " + QueueDepartures + " Packets = " + EndClock);
		//System.out.println("Mean Time Delay after " + QueueDepartures + " Packets = " + TotalDelay/QueueDepartures);
		System.out.println("Totatl arrived packet: " + TotalArrived);
		System.out.println("Total Departed packets: " + NumDepart);
		System.out.println("Total dropped packets: " + EventList.getDropPackets());
		batchMeanDelay += TotalDelay/QueueDepartures;
		
		
		System.out.println("Batch Mean Delay over " + Batch + " Trials = " + batchMeanDelay/100);
	}
		

	
	public static void ProcessPacket(Event event)
	{
		double tempClock;
		
		if(EndClock == 0 )
		{
			packetCount++;
			tempClock = Clock + negExpon(RDM, MU);
			//tempClock = Clock + MU;
			event.setEventClock(tempClock);
			event.setType("Depart");
			EndClock = event.getEventClock();
			
			EventList.insertEvent(event);
			ID++;
			Event newEvent = new Event(Clock + negExpon(RDM, Lamda), ID, "Arrival");
			//Event newEvent = new Event(Clock + Lamda, ID, "Arrival");
			EventList.insertEvent(newEvent);
			QueueLength++;
			
		}
		else
		{
			packetCount++;
			tempClock = event.getEventClock() + negExpon2(RDM, MU);
			//tempClock = EndClock +  MU;
			event.setEventClock(tempClock);
			event.setType("Depart");
			EventList.insertEvent(event);
			EndClock = event.getEventClock();
			ID++;
			Event newEvent = new Event(Clock + negExpon(RDM, Lamda), ID, "Arrival");
			//Event newEvent = new Event(Clock + Lamda, ID, "Arrival");
			EventList.insertEvent(newEvent);
			QueueLength++;
		}	
	}
	
	public static void DepartPacket(Event event)
	{
		//Clock = event.getEventClock();
		QueueLength--;
		NumDepart++;
			
		
	}
	
		public static double negExpon(Random RDM, float Lamda1)
		{
			double temp;
			temp = RDM.nextDouble();
			//double temp2 = Clock + temp;
			double temp4F;
			
				temp4F = Math.exp((-Lamda1)*temp);	
			
			
			
			
			double temp1 = 1-temp4F;
			//System.out.println("\nTime: " + temp1 + " Lamda: " + Lamda1);
			return temp1;
				
		}
		
		public static double negExpon2(Random RDM, float MU)
		{
			double temp;
			temp = RDM.nextDouble();
			//double temp2; 
			//temp2 = temp + EndClock;
			
			double temp4F = Math.exp((-MU)*temp);
			double temp1 = 1-temp4F;
			//System.out.println("\nTime: " + temp1 + " Lamda: " + Lamda1);
			return temp1;
				
		}
	
}

