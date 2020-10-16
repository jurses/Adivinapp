package adivinapp;

import javafx.application.Application; 
import javafx.scene.text.Text;
import javafx.stage.Stage; 
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Main extends Application {
	private static int rn;
	private static int bounded_a;
	private static int bounded_b;
	private static int attemps;

	public int checkAnswer(int answer)
	{
		attemps++;
		if (answer == rn)
			return 0;
		else
		{
			if (answer > rn && answer <= bounded_b)
				bounded_b = answer;
			else if (answer < rn && answer >= bounded_a)
				bounded_a = answer;
			else
				return 2;
			
			return 1;
		}
	}
	
	public void start(Stage s)
	{
		s.setTitle("AdivinApp");
		Text intro = new Text("Introduce un número del 1 al 100.");
		TextField inputNumber = new TextField();
		Button check = new Button("check");
		VBox r = new VBox();
		
		Alert byLittle;
		Alert badAnswer;
		Alert theAnswer;
		byLittle = new Alert(AlertType.INFORMATION);
		byLittle.setTitle("Casi casi");
		byLittle.setHeaderText("Casi lo consigues.");
		byLittle.setContentText("El número está entre " + bounded_a + " y " + bounded_b + ".");
		
		badAnswer = new Alert(AlertType.ERROR);
		badAnswer.setTitle("Mala respuesta");
		badAnswer.setHeaderText("Está mal.");
		badAnswer.setContentText("¡No!, Tiene que ser un número entre " + bounded_a + " y " + bounded_b + ".");
		
		theAnswer = new Alert(AlertType.CONFIRMATION);
		theAnswer.setTitle("Adivinado");
		theAnswer.setHeaderText("Lo adivinaste.");
		theAnswer.setContentText("Adivinaste a la " + attemps + " vez.");
		
		check.setOnAction((event)->{
			int error = checkAnswer(Integer.parseInt(inputNumber.getText()));
			switch (error)
			{
			case 0:
				theAnswer.show();
				theAnswer.setContentText("Adivinaste a la " + attemps + " vez.");
				break;
			case 1:
				byLittle.show();
				byLittle.setContentText("El número está entre " + bounded_a + " y " + bounded_b + ".");
				break;
			case 2:
				badAnswer.show();
				badAnswer.setContentText("¡No!, Tiene que ser un número entre " + bounded_a + " y " + bounded_b + ".");
				break;
			}
		});
		
		r.setAlignment(Pos.CENTER);
		r.getChildren().add(intro);
		r.getChildren().add(inputNumber);
		r.getChildren().add(check);
		
		Scene sc = new Scene(r, 400, 200);
		s.setScene(sc);
		s.show();
	}
	
	public static void main(String args[])
	{
		rn = (int)(Math.random() * 100 + 1);
		bounded_a = 1;
		bounded_b = 100;
		attemps = 1;
		launch(args);
	}
} 
