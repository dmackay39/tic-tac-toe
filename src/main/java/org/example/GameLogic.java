package org.example;

import java.util.Scanner;

public class GameLogic {
    private String[][] gameBoard = new String[3][3];

    private void initialGameBoardSetUp(){
        String[][] gameBoardInitial = getGameBoard();
        for (int i=0;i<3;i++){
            for (int j=0; j<3;j++){
                gameBoardInitial[i][j] = " " + i + "," + j + " ";
            }
        }
        setGameBoard(gameBoardInitial);
    }

    private String[][] getGameBoard() {
        return gameBoard;
    }

    private void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    private void setGameBoardElement(int row, int column, String xoChoice){
        String[][] updatedGameBoard = getGameBoard();
        updatedGameBoard[row][column] = "  " + xoChoice + "  ";
        setGameBoard(updatedGameBoard);
    }

    private boolean isGameWon(){
        return ((gameBoard[0][0].equalsIgnoreCase(gameBoard[0][1])
                && gameBoard[0][0].equalsIgnoreCase(gameBoard[0][2]))
        || (gameBoard[0][0].equalsIgnoreCase(gameBoard[1][0])
                && gameBoard[0][0].equalsIgnoreCase(gameBoard[2][0]))
        || (gameBoard[0][1].equalsIgnoreCase(gameBoard[1][1])
                && gameBoard[0][1].equalsIgnoreCase(gameBoard[2][1]))
        || (gameBoard[0][2].equalsIgnoreCase(gameBoard[1][2])
                && gameBoard[0][2].equalsIgnoreCase(gameBoard[2][2]))
        || (gameBoard[1][0].equalsIgnoreCase(gameBoard[1][1])
                && gameBoard[1][0].equalsIgnoreCase(gameBoard[1][2]))
        || (gameBoard[2][0].equalsIgnoreCase(gameBoard[2][1])
                && gameBoard[2][0].equalsIgnoreCase(gameBoard[2][2]))
        || (gameBoard[0][0].equalsIgnoreCase(gameBoard[1][1])
                && gameBoard[0][0].equalsIgnoreCase(gameBoard[2][2]))
        || (gameBoard[0][2].equalsIgnoreCase(gameBoard[1][1])
                && gameBoard[0][2].equalsIgnoreCase(gameBoard[2][0])));
    }

    private void displayBoard(){
        System.out.println(gameBoard[0][0] + "|" + gameBoard[0][1] + "|" + gameBoard[0][2]);
        System.out.println("-----------------");
        System.out.println(gameBoard[1][0] + "|" + gameBoard[1][1] + "|" + gameBoard[1][2]);
        System.out.println("-----------------");
        System.out.println(gameBoard[2][0] + "|" + gameBoard[2][1] + "|" + gameBoard[2][2]);
    }

    private void playerTurn(int player){
        String xoChoice;
        if (player==1){
            xoChoice = "x";
        }
        else {
            xoChoice = "o";
        }
        Scanner myScanner = new Scanner(System.in);
        boolean validInput = false;
        int row;
        int column;
        while (!validInput) {
            System.out.println("Player " + player + " Enter a row for " + xoChoice);
            row = myScanner.nextInt();
            myScanner.nextLine();
            System.out.println("Player " + player + " Enter a column for " + xoChoice);
            column = myScanner.nextInt();
            myScanner.nextLine();
            if ((row < 0 || row > 2)||(column<0 || column >2)){
                System.out.println("Invalid row/column number try again");
            }
            else if ((getGameBoard()[row][column].equalsIgnoreCase("  x  "))){
                System.out.println("Square already occupied, try again");
            }
            else if (getGameBoard()[row][column].equalsIgnoreCase("  o  ")){
                System.out.println("Square already occupied, try again");
            }
            else{
                setGameBoardElement(row,column,xoChoice);
                validInput = true;
            }
        }
    }

    public void playGame(){
        initialGameBoardSetUp();
        boolean stopPlaying = false;
        while (!stopPlaying){
            for (int i=0; i<10;i++){
                if (isGameWon()){
                    displayBoard();
                    System.out.println("Player " + ((i-1)%2 + 1) + " wins");
                    stopPlaying = true;
                    break;

                } else if (i==9 && !isGameWon()) {
                    displayBoard();
                    System.out.println("Draw");
                    stopPlaying = true;
                    break;
                }
                else{
                    displayBoard();
                    playerTurn((i%2 + 1));
                }
            }
        }
    }

}