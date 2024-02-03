package com.example.SussySlots;

import javafx.animation.PauseTransition;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class SussySlotsClient extends Application
{
    //Textfield so user can input cash
    private TextField moneyDrop;

    //constructor for the Data tracking class
    private SussySlotsData bagAlert = new SussySlotsData(0);

    //Label to explain rules to user
    private final Label rules = new Label("Pull the lever to start your spin!\n2 or 3 Matches is a win, and 3 Reds is a Jackpot\nPlease type the cash you want to insert");

    //invisible labels to hold result of spin when done, and to hold an error code if user input invalid response to cash
    private final Label result = new Label();
    private final Label Error = new Label();


    public void start(Stage SussySlots)
    {

        //title of game based on amongus
        SussySlots.setTitle("Sussy Slots");
        //moneyDrop to hold user input for cash
        moneyDrop = new TextField();

        //sets font size, and alignment
        rules.setFont(new Font("Arial", 24)); // Sets font to Arial with a size of 24
        rules.setAlignment(Pos.CENTER);

        result.setFont(new Font("Arial", 18)); // Sets font to Arial with a size of 20
        result.setAlignment(Pos.CENTER);

        //overload constructor to set up slot objects with a default white color
        SussySlotsClientFX slot1 = new SussySlotsClientFX("/White.png",1);
        SussySlotsClientFX slot2 = new SussySlotsClientFX("/White.png",2);
        SussySlotsClientFX slot3 = new SussySlotsClientFX("/White.png",3);
        SussySlotsClientFX lever = new SussySlotsClientFX("/Lever.png",0);

        //variables to hold imageViews
        ImageView slotView1 = slot1.getImageView();
        ImageView slotView2 = slot2.getImageView();
        ImageView slotView3 = slot3.getImageView();
        ImageView leverView = lever.getImageView();


        //new gridpane to hold slots
        GridPane gridPane = new GridPane();

        gridPane.add(slotView1, 0, 0); // row 0, col 1
        gridPane.add(slotView2, 1, 0); // row 0, col 2
        gridPane.add(slotView3, 2, 0); // row 0, col 3
        gridPane.add(leverView, 3, 0); // row 0, col 4


        //event handler for when lever field is pressed to run the animations and check for win
        leverView.setOnMousePressed(event ->
        {
            try {
                // Set cash based on user input, handles NumberFormatException
                bagAlert.setCash(Double.parseDouble(moneyDrop.getText()));
            } catch (NumberFormatException e) {
                //sets Error label with text informing the user of an invalid input
                Error.setText("Invalid input, insert numbers only");
                return; // Exit the event handler early if the input is invalid
            }
            //keeps Error empty if correct input, or to remove the error once user addresses their error
            Error.setText("");

            // Trigger the slot changes, and looks for the lever object to flip
            slot1.shuffleImage(false);
            slot2.shuffleImage(false);
            slot3.shuffleImage(false);
            lever.shuffleImage(true);

            //A delay to allow for image changes to complete, since the animation is 1s long our delay is 1.1s
            PauseTransition delay = new PauseTransition(Duration.seconds(1.1));
            delay.setOnFinished(delayEvent -> {
                // Check for a win after the delay
                int winResult = bagAlert.checkWin(slot1.getImageID(), slot2.getImageID(), slot3.getImageID());
                //sets result label to the corresponding text based on a loss or win or jackpot
                result.setText(bagAlert.getData(winResult));
            });
            delay.play();
        });

        //aligns gridPane
        gridPane.setAlignment(Pos.CENTER);

        //Hbox to hold a small textfield that holds userInput
        HBox userInput = new HBox(10,moneyDrop);
        userInput.setAlignment(Pos.CENTER);

        //Vbox to hold rules, userInput, error label, gridpane, and the result
        VBox gameWindow = new VBox(10.0, rules, userInput,Error, gridPane,result);

        gameWindow.setAlignment(Pos.CENTER);

        //sets scene
        Scene scene = new Scene(gameWindow,900,400);
        SussySlots.setScene(scene);
        SussySlots.show();
    }

    public static void main(String[] args)
    {
        // Launchs app
        launch(args);
    }
}