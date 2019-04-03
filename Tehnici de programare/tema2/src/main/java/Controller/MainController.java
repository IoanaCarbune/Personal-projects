package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Customer;
import Model.SimulationQueue;
import View.MainView;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainController {

	MainView mainView;
	private int peakTime=1;
	private int maxNoOfClients=0;
	private int time=1;
	private int emptyQTime=0;
	public void start(Stage primaryStage) {
		mainView=new MainView(primaryStage);
		mainView.show();
		initializeButtonListeners();
	}

	private void initializeButtonListeners() {
		mainView.addStartButtonActionListener(e->{
			if(mainView.getNumberOfQueues().length()==0 ||mainView.getMinimArrivingTime().length()==0 ||mainView.getMaximArrivingTime().length()==0||
					mainView.getMinimServiceTime().length()==0||mainView.getMaximServiceTime().length()==0||mainView.getSimulationTime().length()==0) {
				mainView.errorLabel("Completati toate campurile!");
			}else {
			mainView.creatAnimationSpace(Integer.valueOf(mainView.getNumberOfQueues()));
			Integer minArrivingTime=Integer.valueOf(mainView.getMinimArrivingTime());
			Integer maxArrivingTime=Integer.valueOf(mainView.getMaximArrivingTime());
			Integer minServiceTime=Integer.valueOf(mainView.getMinimServiceTime());
			Integer maxServiceTime=Integer.valueOf(mainView.getMaximServiceTime());
			Integer numberOfQueues=Integer.valueOf(mainView.getNumberOfQueues());
			Integer simulationTime=Integer.valueOf(mainView.getSimulationTime());
			mainView.setAverageEmptyQueueTimeTxt("");
			mainView.setAverageServiceTimeTxt("");
			mainView.setAverageWaitingTimeTxt("");
			mainView.setPeakHourTxt("");
			mainView.emptyLog();
			
			//System.out.println(minArrivingTime+" "+maxArrivingTime);
			//System.out.println(minServiceTime+" "+maxServiceTime);
			List <SimulationQueue> currentList=createListOfQueues(numberOfQueues);
			Thread.currentThread().interrupt();
			Thread thread=new Thread(()-> {
				try {
					time=1;
					emptyQTime=0;
					peakTime=1;
					maxNoOfClients=0;
					while(true) {
					Integer arrivalTime=new Random().nextInt((maxArrivingTime-minArrivingTime +1))+minArrivingTime;
					Integer serviceTime=new Random().nextInt((maxServiceTime-minServiceTime +1))+minServiceTime;
					Customer currentCustomer=new Customer();
					currentCustomer.setArrivingTime(arrivalTime);
					currentCustomer.setServiceTime(serviceTime);
				
					time+=arrivalTime;
					if(time>simulationTime) {
						break;
					}
					int i=1;
					
					while(i<=arrivalTime) {
						Thread.sleep(1000);
						if(emptyQ(currentList)) {
							emptyQTime++;
						}
						i++;
					}
					
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							
							Integer index=getShortestQueue(currentList);
							currentCustomer.setWaitingTime(currentList.get(index).getCurrentWaitingTime());
							currentList.get(index).addCustomer(currentCustomer);
							mainView.addCustomerIcon(index);
							int totalNoOfClients=getCurrentCustomerNumber(currentList);
						//	System.out.println("Max"+maxNoOfClients+"---"+"peakH"+peakTime);
							if(totalNoOfClients>maxNoOfClients) {
								peakTime=time;
								maxNoOfClients=totalNoOfClients;
							}
							mainView.displayLogEventMessage("Un client a venit la coada "+(index+1)+" timp serv "+serviceTime+" waiting time "+currentCustomer.getWaitingTime()+"\n");
							
						}
					});
					}
					Thread.currentThread().interrupt();
					stopRemovingThreads(currentList);
					mainView.setAverageWaitingTimeTxt(String.format("%.2f", averageWaitingTime(currentList)));
					mainView.setAverageServiceTimeTxt(String.format("%.2f",averageServiceTime(currentList)));
					double avgEmpty=(double)emptyQTime/numberOfQueues;
					mainView.setAverageEmptyQueueTimeTxt(String.format("%.2f",avgEmpty));
					mainView.setPeakHourTxt(String.valueOf(peakTime));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		});
			
		thread.start();
			}
	});
	
	}
	
	private boolean emptyQ(List<SimulationQueue> currentList) {
		for(SimulationQueue currentQ: currentList) {
			if(currentQ.getCustomerQ().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private void stopRemovingThreads(List<SimulationQueue> currentList) {
		for(SimulationQueue currentQ: currentList) {
			currentQ.getRemovingThread().stopCurrentThread();
		}
	}

	private List <SimulationQueue> createListOfQueues(Integer numberOfQueues) {
		List <SimulationQueue> listOfQueues=new ArrayList<SimulationQueue>();
		for(int i=0;i<numberOfQueues;i++) {
			SimulationQueue currentQueue=new SimulationQueue(mainView);
			currentQueue.setIndex(i);
			listOfQueues.add(currentQueue);
		}
		return listOfQueues;
	}
	
	private int getShortestQueue(List <SimulationQueue> qList) {
		int index=0;
		
		int minimWaitingTime=qList.get(0).getCurrentWaitingTime();
		for(SimulationQueue currentQueue:qList) {
			if(currentQueue.getCurrentWaitingTime()<minimWaitingTime)
			{
				minimWaitingTime=currentQueue.getCurrentWaitingTime();
				index=currentQueue.getIndex();
				//System.out.println(index+"---->"+minimWaitingTime);
			}
			
		}
		//System.out.println(index+" "+minimWaitingTime);
		return index;
	}
	
	private Double averageWaitingTime(List<SimulationQueue> qList) {
		int totalWaitingTime=0;
		int totalNoOfClients=0;
		Double averageWaitingTime=(double) 0;
		for(SimulationQueue currentQ: qList) {
			totalWaitingTime+=currentQ.getTotalWaitingTime();
			totalNoOfClients+=currentQ.getNumberOfClients();
			
		}
		System.out.println(totalWaitingTime+"--"+totalNoOfClients);
		averageWaitingTime=(double)totalWaitingTime/totalNoOfClients;
		return averageWaitingTime;
		
	}
	
	private Double averageServiceTime(List<SimulationQueue> qList) {
		int totalServiceTime=0;
		int totalNoOfClients=0;
		Double averageServiceTime=(double) 0;
		for(SimulationQueue currentQ: qList) {
			totalServiceTime+=currentQ.getServiceTime();
			totalNoOfClients+=currentQ.getNumberOfClients();
		}
		averageServiceTime=(double)totalServiceTime/(double)totalNoOfClients;
		return averageServiceTime;
		
	}
	
	private int getCurrentCustomerNumber(List<SimulationQueue>currentList) {
		int totalNoOfClients=0;
		for(SimulationQueue currentQueue: currentList) {
			totalNoOfClients+=currentQueue.getCustomerQ().size();
		}
		return totalNoOfClients;
	}
	
}
