package ee509_assign1;

import java.util.*;

public class StartSim {
	
	public static double Clock,EndClock, TimeDelay, QueueDepartures, queueStatus, serviceTime, LastEventT, MaxQueueL, NumberInService, packetCount;
	public static long NumPackets, QueueLength, TotalPackets, TotalTime, NumDepart;
	public static float Lamda, MU ;

	public static Random RDM;
	public static EventListManager EventList, PacketQueue;
	public static Queue QueueList;
	
	public static void main(String[] args) {
		
		QueueDepartures =0;
		Clock = 0;
		EndClock = 0;
		serviceTime =0;
		packetCount = 1;
		TotalTime = 200;
		Lamda = (8000f / 10000000f);
		MU = (8000f / 10000000f);
		//MU = (float) 0.0008;
		
		System.out.println("Lamda= " + Lamda + "MU= " + MU);
		TotalPackets = 0;
		
		long seed = 78789090;
		RDM = new Random(seed);
		
		EventList = EventListManager.getInstance();
		PacketQueue = EventListManager.getInstance();
		
		Event event1 = new Event(negExpon(RDM, Lamda),packetCount);
		Clock = event1.getEventClock();
		
		
		EventList.insertEvent(event1);
		
			
		int n = 0;
		//while(QueueDepartures < TotalPackets)
		while(Clock < TotalTime)
		{
			n++;
			
			
			Event event2 = new Event();
			event2 = EventList.poll();
			//System.out.println("PacketNum about to be services: " + event2.getEventNum() );
			//System.out.println("MAIN:About to Call ProcessPacket: PacketNum: " + event2.getEventNum());
			
			ProcessPacket(event2);
			//System.out.println(Clock + " QueuLen: " + QueueLength);
			
			//Clock  = Clock + negExpon(RDM, Lamda);
			
			
									
		}
		System.out.println("Clock after " + QueueDepartures + " Packets = " + Clock);
		System.out.println("EndClock after " + QueueDepartures + " Packets = " + EndClock);
		System.out.println("TimeDaly after " + QueueDepartures + " Packets = " + TimeDelay/QueueDepartures);
		//System.out.println(QueueLength);
		
		
	}
	
	public static void ProcessPacket(Event event)
	{
		//event.setEventClock(negExpon(RDM, Lamda));
		//Clock = Clock + event.getEventClock();
		queueStatus = Clock - EndClock;
		
		//System.out.println("CLOCK = " + Clock + " EndClock= " + EndClock);
		
		
		if(queueStatus>=0)
		{
			//System.out.println("About to Start Servicing Packet: PacketNum" + event.getEventNum());
			ServicePacket(event);
			//Event eventService = new Event();
			//eventService = PacketQueue.poll();
			//System.out.println("PacketNum: " + event.getEventNum()+ " Gone" );
			if(QueueLength < 0)
			{
				QueueLength = 0;
			}
			else
			{
				QueueLength--;
			}
			QueueDepartures++;
			
		}
		else
		{
			EventList.insertEvent(event);
			//System.out.println("Queue Busy");
			//PacketQueue.insertEvent(event);
			if(QueueLength < 0)
			{
				QueueLength = 0;
			}
			QueueLength++;
			TimeDelay += Clock - EndClock;
		}
		
		//System.out.println("Adding new arrival");
		//if(packetCount<TotalPackets){
			
			packetCount++;
			Event event1 = new Event(Clock + negExpon(RDM, Lamda), packetCount);
			Clock = event1.getEventClock();
			EventList.insertEvent(event1);
		//}
		//else {
		//	Clock = Clock + negExpon(RDM, Lamda);
		//}
		
	//	System.out.println("New arrival Added: PacketNum: " + event1.getEventNum());
		
	}
	
	public static void ServicePacket(Event event)
	{
		//System.out.println("Servicing Packet");
		serviceTime = (negExpon(RDM, MU));
		//System.out.println("Servicing Time:" + serviceTime);
		EndClock = Clock + serviceTime;
		//System.out.println("EndClock:" + EndClock);
		//System.out.println("");
	}
	
	public static double negExpon(Random RDM, float Lamda1)
	{
		double temp;
		temp = RDM.nextDouble();
		double temp5 = -Lamda1*temp;
		double temp4F = Math.exp(-Lamda1*temp);
				
		//System.out.println(1-Math.exp(-Lamda1*temp));
		//double temp1 = 1-(Math.exp(-Lamda1*temp));
		double temp1 = 1-temp4F;
		//return temp4F;
		return temp1;
		//return 1-Math.exp(-Lamda1*temp);
		
	}
	
}

