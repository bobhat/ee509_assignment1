package ee509_assign1;
import java.lang.Math;
import java.util.*;

public class REDController {
	
	private double avgQ;
	private double idleTime;
	public double count;
	private double probA;
	private double probB;
	private boolean processPacket = true;
	public double ran1;
	public Random rdm = new Random();
	
	
	
	public REDController()
	{
		this.setAvgQ(0);
		this.setCount(-1);
	}
	
	public void calAvg(double queueLenght, Event event, double clock)
	{
		if(queueLenght > 0)
		{
			//double tempCal = ;
			this.setAvgQ((1-0.005)*this.getAvgQ() + (0.005*queueLenght));
		}
		else
		{
			double s = 0.2/0.0008;
			double m = (event.getEventClock()- idleTime)*s;
			this.setAvgQ((Math.pow((1-0.005), m)*this.getAvgQ()));
		}
		
		processAvg(event, queueLenght);
		if(queueLenght == 0)
		{
			this.setIdleTime(event.getEventClock());
		}
	}
	
	private void processAvg(Event event, double QL)
	{
		if(this.getAvgQ()>=5 && this.getAvgQ()<15)
		{
			
			count++;
			this.setProbB((0.005*(this.getAvgQ()-5))/(15-5));
			this.setProbA(this.getProbB()/(1-this.getCount()*this.getProbB()));
			double negDis = negExpon(rdm, 0.0006666666667);
		
			if(count > 0 && negDis < this.getProbA())
			{
					this.setProcessPacket(false);
					count =0;
			}
			if(count == 0)
			{
				ran1 = Math.random();
				this.setProcessPacket(true);
			}
		
		}
		else if (this.getAvgQ()>15)
		{
			this.setProcessPacket(false);			
		}
		else
		{
			this.setCount(-1);
			this.setProcessPacket(true);
		}
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
	public double getAvgQ() {
		return avgQ;
	}


	public void setAvgQ(double avgQ) {
		this.avgQ = avgQ;
	}


	public double getCount() {
		return count;
	}


	public void setCount(double count) {
		this.count = count;
	}

	public double getProbA() {
		return probA;
	}

	public void setProbA(double probA) {
		this.probA = probA;
	}

	public double getProbB() {
		return probB;
	}

	public void setProbB(double probB) {
		this.probB = probB;
	}

	public boolean isProcessPacket() {
		return processPacket;
	}

	public void setProcessPacket(boolean processPacket) {
		this.processPacket = processPacket;
	}

	public double getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(double idleTime) {
		this.idleTime = idleTime;
	}
	
	
	
	
	

}
