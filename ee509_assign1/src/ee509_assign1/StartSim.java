package ee509_assign1;

import java.util.*;

public class StartSim {
	
	public static double Clock,  LastEventT, MaxQueueL, NumberInService;
	public static long NumPackets, QueueLength, TotalPackets, MU, NumDepart;
	public static float Lamda;

	public static Random RDM;
	public static EventListManager EventList, PacketQueue;
	
	public static void main(String[] args) {
		
		Clock = 0;
		Lamda = (8000f / 10000000f);
		MU = 8000;
		
		System.out.println("Lamda= " + Lamda + "MU= " + MU);
		TotalPackets = 100;
		
		long seed = 78789090;
		RDM = new Random(seed);
		
		EventList = EventListManager.getInstance();
		PacketQueue = EventListManager.getInstance();
		Event event1 = new Event(negExpon(RDM, Lamda));
		Clock = event1.getEventClock();
		EventList.insertEvent(event1);
		
		
		Integer n = 0;
		while(n < TotalPackets)
		{
			n++;
			
			Event event2 = new Event();
			event2 = EventList.poll();
			
			if((event2.getEventType()) == "Arrival"){
				
				ProcessArrival(event2);
			}
			else{
				
				ProcessDeparture(event2);
			}
			
			Clock  = Clock + negExpon(RDM, Lamda);
			
			//EventList.insertEvent(event2);
			
		}
		System.out.println("Clock after " + TotalPackets + " Packets = " + Clock);
		System.out.println(10000000f/Clock);

		
	}
	
	public static void ProcessArrival(Event event)
	{
		PacketQueue.insertEvent(event);
				
		Event event2 = new Event(negExpon(RDM, Lamda));
		Clock = Clock + event2.getEventClock();
		EventList.insertEvent(event2);
		
	}
	
	public static void ProcessDeparture(Event event)
	{
		 
		
	}
	
	public static double negExpon(Random RDM, float Lamda1)
	{
		double temp = RDM.nextDouble();
		//System.out.println(1-Math.exp(-Lamda1*temp));
		
		return 1-Math.exp(-Lamda1*temp);
		
	}
	
}

