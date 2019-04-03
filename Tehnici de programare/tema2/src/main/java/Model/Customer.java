package Model;

public class Customer {
	private int waitingTime;
	private int serviceTime;
	private int arrivingTime;
	
	
	public int getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	public int getArrivingTime() {
		return arrivingTime;
	}
	public void setArrivingTime(int arrivingTime) {
		this.arrivingTime = arrivingTime;
	}
}
