package ee509_assign1;

public class Event {
	
	/**
	 * The clock value of the event.
	 */
	private double eventClock;
	private String eventType;

	/**
	 * Constructor of the class: to be developed by the student...
	 * 
	 */
	public Event(double eventClock) { 
		this.setEventClock(eventClock);
		
	}
	
	public Event(double eventClock, String Type) { 
		this.setEventClock(eventClock);
	}
	
	
	public Event(){
		//System.out.println("Con: Event()");
		
		double test = 999;
		System.out.println(test);
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
	
	
	//Add attributes that are necessary to uniquely define the event object.
	// ....

}
