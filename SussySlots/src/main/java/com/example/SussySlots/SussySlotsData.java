package com.example.SussySlots;

public class SussySlotsData
{
    //instance variables to hold user cash, total winnings, won per spin, and running total of plays
    private double cash;
    private double totalCash;
    private double winnings=0;
    private double won=0;
    private int plays=0;

    //overload contructor to set cash
    public SussySlotsData(double input)
    {
        cash=input;
    }
    //setter to set cash equal to userInput
    public void setCash(double input)
    {
        cash=input;
        totalCash+=cash;
    }

    //references ImageID to determine a 2/3 of a kind, jackpot, or bust
    public int checkWin(int slot1,int slot2,int slot3)
    {
        //variable to hold result of spin
        int winType;

        //checks for 3 of a kind
        if(slot1==slot2 && slot1==slot3)
        {
            //if ImageIDs are all 1 which is Red set winType to jackpot
            if (slot1 == 1) {
                won = (cash * 10);
                winnings+=won;
                incPlay();
                winType=3;

            }
            //sets winType to 3 of a kind
            else{
                won = (cash * 6);
                winnings+=won;
                incPlay();
                winType=2;

            }
        }
        //checks for two of a kind and sets winType
        else if(slot1==slot2 || slot1==slot3 || slot2==slot3)
        {
            won = (cash * 2);
            winnings+=won;
            incPlay();
            winType=1;
        }
        //sets winType to a lose
        else
        {
            incPlay();
            winType=0;
        }

        //returns isWin variable to determine what message to send to the user
        return winType;
    }

    //returns a String holding a message based on the winType
    public String getData(int winType)
    {
        String data="";

        if (winType==0)
            data = "You have won nothing this play, in total you have won $"+winnings+" in total winnings\n You have made "+plays+" total plays, and have spent $"+totalCash+" in total";
        if (winType==1)
            data = "Two of a Kind, You have won $"+won+" this play, in total you have won $"+winnings+" in total winnings.\n You have made "+plays+" total plays, and have spent $"+totalCash+" in total";
        if (winType==2)
            data = "Three of a Kind, You have won $"+won+" this play, and have won $"+winnings+" in total winnings.\n You have made "+plays+" total plays, and have spent $"+totalCash+" in total";
        if (winType==3)
            data = "YOU HIT THE JACKPOT! You have won $"+won+" this play, and have won $"+winnings+" in total winnings.\n You have made "+plays+" total plays, and have spent $"+totalCash+" in total";
        return data;
    }

    //increments the amount of plays
    private void incPlay()
    {
        plays++;
    }
}
