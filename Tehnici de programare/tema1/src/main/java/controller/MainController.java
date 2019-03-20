package controller;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Polinom;
import view.MainView;

public class MainController {

	MainView mainView;
	Polinom polinom1 = new Polinom();
	Polinom polinom2 = new Polinom();

	public void start(Stage primaryStage) {
		mainView = new MainView(primaryStage);
		mainView.show();
		initializeButtonListeners();

	}

	private void initializeButtonListeners() {
		mainView.addSumButtonActionListener(e -> {
			if (polinom1.validatePolynomial(mainView.getPolinom1()) && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom1.addition(polinom2));
				mainView.error1("Format corect", Color.GREEN);
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
		});
		mainView.addSubButtonActionListener(e -> {
			if (polinom1.validatePolynomial(mainView.getPolinom1()) && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom1.subtraction(polinom2));
				mainView.error1("Format corect", Color.GREEN);
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
		});
		mainView.addDivButtonActionListener(e -> {
			if (polinom1.validatePolynomial(mainView.getPolinom1()) && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom1.division(polinom2));
				mainView.error1("Format corect", Color.GREEN);
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
		});
		mainView.addMultButtonActionListener(e -> {
			if (polinom1.validatePolynomial(mainView.getPolinom1()) && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom1.multiplication(polinom2));
				mainView.error1("Format corect", Color.GREEN);
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
		});
		mainView.addDerivButtonActionListener(e -> {
			if (mainView.radio1IsSelected() && polinom1.validatePolynomial(mainView.getPolinom1())) {
				mainView.showResult(polinom1.derivative());
				mainView.error1("Format corect", Color.GREEN);
			} else if (mainView.radio2IsSelected() && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom2.derivative());
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
			mainView.resetFields();
		});
		mainView.addIntegrButtonActionListener(e -> {

			if (mainView.radio1IsSelected() && polinom1.validatePolynomial(mainView.getPolinom1())) {
				mainView.showResult(polinom1.integration());
				mainView.error1("Format corect", Color.GREEN);
			} else if (mainView.radio2IsSelected() && polinom2.validatePolynomial(mainView.getPolinom2())) {
				mainView.showResult(polinom2.integration());
				mainView.error2("Format corect", Color.GREEN);
			} else {
				mainView.error1("Format invalid", Color.RED);
				mainView.error2("Format invalid", Color.RED);
			}
			polinom1 = new Polinom();
			polinom2 = new Polinom();
			mainView.resetFields();
		});

		mainView.addRadio1ButtonActionListener(e -> {
			mainView.radio1IsSelected();
		});
		mainView.addRadio2ButtonActionListener(e -> {
			mainView.radio2IsSelected();
		});

		mainView.addResetButtonActionListener(e -> {
			mainView.showResult("");
			mainView.resetFields();
			polinom1 = new Polinom();
			polinom2 = new Polinom();
		});

	}

}
