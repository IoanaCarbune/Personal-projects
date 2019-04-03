package Model;

import java.util.concurrent.ConcurrentLinkedQueue;

import View.MainView;

public class SimulationQueue {
		private ConcurrentLinkedQueue<Customer> customersQueue= new ConcurrentLinkedQueue<Customer>();
		private RemovingThread removingThread=new RemovingThread();
		private int index;
		private int totalServiceTime=0;
		private int totalWaitingTime=0;
		private int numberOfClients=0;
		public SimulationQueue(MainView mainView) {
			super();
			removingThread.start(this,mainView);
		}
		public void addCustomer(Customer customer) {
			customersQueue.add(customer);
			numberOfClients++;
			totalWaitingTime+=customer.getWaitingTime();
			totalServiceTime+=customer.getServiceTime();
		}
		
		public Integer getCurrentWaitingTime() {
			Integer waitingTime=0;
			for(Customer currentCustomer:customersQueue) {
				waitingTime+=currentCustomer.getServiceTime();
			}
			return waitingTime;
		}
		public void setIndex(int index) {
			this.index=index;
		}
		
		public int getIndex() {
			return this.index;
		}
		public int getNumberOfClients() {
			return this.numberOfClients;
		}
		
		public RemovingThread getRemovingThread() {
			return this.removingThread;
		}
		public ConcurrentLinkedQueue<Customer> getCustomerQ(){
			return this.customersQueue;
		}
		
		public int getServiceTime() {
			
//			for(Customer currentCustomer:this.customersQueue) {
//				this.totalServiceTime+=currentCustomer.getServiceTime();
//			}
			return this.totalServiceTime;
		}
		
		
		public int getTotalWaitingTime() {
		
//			for(Customer currentCustomer:this.customersQueue) {
//				int currentCustomerWaitingTime=0;
//				for(Customer frontCustomer: this.customersQueue) {
//					if(currentCustomer.equals(frontCustomer))
//						break;
//					else {
//						int frontCustomerServ=frontCustomer.getServiceTime();
//						currentCustomerWaitingTime+=frontCustomerServ;
//					}
//				}
//				//currentCustomer.setWaitingTime(currentCustomerWaitingTime);
//				System.out.println(currentCustomerWaitingTime);
//				this.totalWaitingTime+=currentCustomerWaitingTime;
//			}
			return this.totalWaitingTime;
		}
}
