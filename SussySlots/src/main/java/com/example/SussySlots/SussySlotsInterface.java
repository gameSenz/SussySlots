package com.example.SussySlots;
import javafx.scene.image.ImageView;


public interface SussySlotsInterface
{
    //method to get imageView from an image
    ImageView getImageView();

    //method to change images
    void shuffleImage(boolean isLever);

    //method to return an images's ID
    int getImageID();

}
