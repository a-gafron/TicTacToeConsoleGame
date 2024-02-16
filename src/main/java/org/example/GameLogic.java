package org.example;

import java.util.Scanner;

public class GameLogic {
    static Player playerX = new Player("x");
    static Player playerO = new Player("o");
    public static String chosenField = "";
    public static String playBoard[][] = {
            {"0","1","2"},
            {"3","4","5"},
            {"6","7","8"}
    };

    public static void showTheField(){
        for (int i = 0; i < GameLogic.playBoard.length; i++){
            for (int i1 = 0; i1 < GameLogic.playBoard.length; i1++ ) {
                System.out.print(GameLogic.playBoard[i][i1] + "\t");
            }
            System.out.println();
        }
    }
    public static void chooseAField() {
        Scanner forChosenField = new Scanner(System.in);
        String hasChosenField = "";
        System.out.println("Chose a field number!");
        hasChosenField = forChosenField.nextLine();
        chosenField = hasChosenField;
    }

    public static void manipulateTheBoard (Player playerXorO){
        for (int i = 0; i < playBoard.length; i++) {
            for (int i1 = 0; i1 < playBoard[i].length; i1++) {
                if (playBoard[i][i1].equals(chosenField)) {
                    playBoard[i][i1] = playerXorO.getWhichPlayer();
                }
            }
        }
    }

    public static void aPlayerManipulatesTheBoard (){
        if (Player.itsTheTurnOfPlayerX){
            System.out.println("Player x");
            chooseAField();
            manipulateTheBoard(playerX);
        }
        else if (!Player.itsTheTurnOfPlayerX) {
            System.out.println("Player o");
            chooseAField();
            manipulateTheBoard(playerO);
        }
    }

    public static String [] change2DArrayIn1DArray(String wasPlayBoard [][]){
        String a1DArray [] = new String[9];
        int counterIndex = 0;
        for (int i = 0; i < wasPlayBoard.length; i++){
            for (int i1 = 0; i1 < wasPlayBoard.length; i1++ ) {
                a1DArray[counterIndex] = wasPlayBoard[i][i1];
                counterIndex++;
            }
        }
        return a1DArray;
    }

    public static boolean checkWinner(String was1DArray[], Player whichPlayerXorO){

        int winCombinations [][] = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}};

        for (int i = 0; i < winCombinations.length; i++){
            int counter = 0;
            for (int i1 = 0; i1 < winCombinations[i].length; i1++ ){
                if (was1DArray[winCombinations[i][i1]] == (whichPlayerXorO.getWhichPlayer())){
                    counter++;
                    if (counter == 3){
                        return true;
                    }
                }
            }
        }
        return false;
    }
     public static boolean gameOn (String wasPlayBoard [][]){
        if (checkWinner(change2DArrayIn1DArray(wasPlayBoard),playerX) || checkWinner(change2DArrayIn1DArray(wasPlayBoard),playerO)
        ){
            return false;
        }
        else if (Player.numberOfTurns == 9){
            return false;
        }
        else {
            return true;
        }
     }

    public static String whoWon (String wasPlayBoard [][]){

        if (checkWinner(change2DArrayIn1DArray(wasPlayBoard),playerX)){
            return "Player X won!";
        }
        else if (checkWinner(change2DArrayIn1DArray(wasPlayBoard),playerO)){
            return "Player O won!";
        }
        else if (Player.numberOfTurns == 9) {
            return "Draw!";
        }
        else
            return "What ever, we broke down."; //todo exception handling
    }

    public static void whoShallStart(){
        System.out.println("Hello, and welcome to Alex TicTacToe Game! \n Who shall start the game? Please enter either 'x' or 'o'.");
    }

    public static void chooseStartingPlayer(){
        Scanner startingPlayer = new Scanner(System.in);
        try {
            char thisIsTheStartingPlayer = 'a';
            whoShallStart();
            thisIsTheStartingPlayer = startingPlayer.nextLine().charAt(0);

            if (thisIsTheStartingPlayer == 'x'){
                Player.itsTheTurnOfPlayerX = true;
                System.out.println("Player-" + thisIsTheStartingPlayer + " starts.");
            }
            else if (thisIsTheStartingPlayer == 'o') {
                Player.itsTheTurnOfPlayerX = false;
                System.out.println("Player" + thisIsTheStartingPlayer + " starts.");
            }
            else {
                throw new IllegalArgumentException("Please insert either 'x' or 'o'.");
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e);  //new input advice
        }
    }

    public static void changePlayer (){
        if (Player.itsTheTurnOfPlayerX){
            Player.itsTheTurnOfPlayerX = false;
        }
        else if (!Player.itsTheTurnOfPlayerX) {
            Player.itsTheTurnOfPlayerX = true;
        }
    }

    public static void theGame(){
        chooseStartingPlayer();
        do {
            showTheField();
            aPlayerManipulatesTheBoard();
            changePlayer();
            Player.numberOfTurns++;
        }while (gameOn(playBoard));
        System.out.println(whoWon(playBoard));
    }
}
