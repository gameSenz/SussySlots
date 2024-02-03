package com.example.SussySlots;

import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SussySlotsClientFX extends SussySlotsClient implements SussySlotsInterface
{
    //Variables to hold ImageView, and ImageID for checking for like images
    private final ImageView imageView;
    private int imageID=0;

    //references to all colors used in the slot game
    private final Image blue= new Image("/Blue.png");
    private final Image red = new Image("/Red.png");
    private final Image black = new Image("/Black.png");
    private final Image orange = new Image("/Orange.png");
    private final Image purple = new Image("/Purple.png");
    private final Image pink = new Image("/Pink.png");
    private final Image green= new Image("/Green.png");
    Random random = new Random();

    //overload constructor
    public SussySlotsClientFX(String imagePath, int ID)
    {
        imageView = new ImageView(new Image(imagePath));
        sizing();
    }
    //sets sizing to all images
    private void sizing()
    {
        imageView.setFitWidth(150); // Set width
        imageView.setFitHeight(150); // Set height
        // Any other common initialization can go here
    }
    //returns an Imageview
    public ImageView getImageView()
    {
        return imageView;
    }

    //applies a "animation" of showing the game randomizing the icons also checks for the Lever image
    public void shuffleImage(boolean isLever)
    {
        //amount of spins, plus time between changes, add up to 1s of animation
        int rotations = 20;
        Duration frameTime = Duration.millis(50);

        for (int i = 0; i < rotations; i++)
        {
            Timeline timeline = new Timeline(new KeyFrame(frameTime, event ->
            {
                if(!isLever)
                {
                    //using a random number to pick a image, and assigning an imageID based on the color
                    int randomInt = random.nextInt(8);
                    if (randomInt == 0)
                    {
                        imageView.setImage(red);
                        imageID=1;
                    }
                    else if (randomInt == 1)
                    {
                        imageView.setImage(blue);
                        imageID=2;
                    }
                    else if (randomInt == 2)
                    {
                        imageView.setImage(green);
                        imageID=3;
                    }
                    else if (randomInt == 3)
                    {
                        imageView.setImage(black);
                        imageID=4;
                    }
                    else if (randomInt == 4)
                    {
                        imageView.setImage(orange);
                        imageID=5;
                    }
                    else if (randomInt == 5)
                    {
                        imageView.setImage(purple);
                        imageID=6;
                    }
                    else if (randomInt == 6)
                    {
                        imageView.setImage(pink);
                        imageID=7;
                    }

                }
                //if a lever image is passed it will flip the lever down while the animation is going
                else if(isLever)
                    imageView.setScaleY(-1);
            }));
            timeline.setCycleCount(rotations);

            //once the animation is done flip the lever back up
            timeline.setOnFinished(event -> {
                if (isLever) {
                    imageView.setScaleY(1);
                }
            });
            timeline.play();

        }

    }
    //@return ImageID
    public int getImageID()
    {
        return imageID;
    }

}