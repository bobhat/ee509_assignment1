package ee509_assign1;

public class Event {
	
	/**
	 * The clock value of the event.
	 */
	private double eventClock;
	private String eventType;
	private double eventNum;

	/**
	 * Constructor of the class: to be developed by the student...
	 * 
	 */
	public Event(double eventClock) { 
		this.setEventClock(eventClock);
		
	}
	
	public Event(double eventClock, double eventNum) { 
		this.setEventClock(eventClock);
		this.setEventNum(eventNum);
	
		
	}
	
	public Event(double eventClock, double eventNum, String type) { 
		this.setEventClock(eventClock);
		this.setEventNum(eventNum);
		this.setType(type);
	
		
	}
	
	public Event(double eventClock, String Type) { 
		this.setEventClock(eventClock);
	}
	
	
	public Event(){
		//System.out.println("Con: Event()");
		
		double test = 0;
		//ystem.out.println(test);
		this.setEventClock(test);
	}

	/**
	 * Returns the event clock value.
	 * 
	 * @return (double) eventClock : the even clock value (double).
	 */
	public double getEventClock() {
		//System.out.println("met: getEventClock()");
		return eventClock;
	}
	
	public String getEventType()
	{
		return eventType;
	}
	
	public double getEventNum()
	{
		return eventNum;
	}

	/**
	 * Modifies the event clock value.
	 * 
	 * @param (double) eventClock
	 *            : the new event clock value (double).
	 */
	public void setEventClock(double eventClock) {
		//System.out.println("met: setEventClock(double eventClock)");
		this.eventClock = eventClock;
	}
	
	public void setType(String eventType) {
		//System.out.println("met: setEventClock(double eventClock)");
		this.eventType = eventType;
	}
	
	public void setEventNum(double eventNum) {
		//System.out.println("met: setEventClock(double eventClock)");
		this.eventNum = eventNum;
	}
	
	//Add attributes that are necessary to uniquely define the event object.
	// ....

}
