package ee509_assign1;


import java.util.*;

public class Queue  {
	
	private ArrayList<Object> packetQ;
		
	public Queue()
	{
		this.packetQ = new ArrayList<Object>();
		/*
		 * tesetes
		 */
		
	}
	
	public void addPacket(Object p)
	{
		this.packetQ.add(p);
		
	}
	
}
