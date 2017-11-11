package ee509_assign1;

import java.util.ArrayList;

public class PacketQueue {

	private ArrayList<Object> packetQ;
	
	//private static PacketQueue instance;
	
	public PacketQueue()
	{
		this.packetQ = new ArrayList<Object>();
		
	}
	
	public void addPacket(Object p)
	{
		this.packetQ.add(p);
		
	}
}
