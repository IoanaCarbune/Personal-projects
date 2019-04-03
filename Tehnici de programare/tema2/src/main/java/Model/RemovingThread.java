package Model;

import View.MainView;
import javafx.application.Platform;

public class RemovingThread {

	private boolean threadDisabler;

	public void start(SimulationQueue currentQueue, MainView mainView) {
		threadDisabler = true;
		Thread thread = new Thread(() -> {
			try {
				while (threadDisabler) {
					if (currentQueue.getCustomerQ().peek() != null) {

						Thread.sleep(1000 * currentQueue.getCustomerQ().peek().getServiceTime());
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								if (currentQueue.getCustomerQ().isEmpty() == false) {
									currentQueue.getCustomerQ().remove();
									mainView.displayLogEventMessage(
											"Un client a plecat de la coada" + (currentQueue.getIndex() + 1) + "\n");
									mainView.removeCustomerIcon(currentQueue.getIndex());

								}
							}
						});

					}
				}
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			Thread.currentThread().interrupt();

		});

		thread.start();

	}

	public void stopCurrentThread() {
		this.threadDisabler = false;
	}

}
