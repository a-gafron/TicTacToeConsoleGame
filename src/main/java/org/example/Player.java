package org.example;



public class Player {
    public static boolean itsTheTurnOfPlayerX = true;
    public static int numberOfTurns = 0;

    private String whichPlayer = "";
    public Player (String xOrO) {
        whichPlayer = xOrO;
    }

    public String getWhichPlayer() {
        return whichPlayer;
    }

    public void setWhichPlayer(String whichPlayer) {
        this.whichPlayer = whichPlayer;
    }






}
