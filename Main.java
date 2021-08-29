package com.company;


import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);



        while (true) {
            Scanner scanner = new Scanner(System.in);
            //Player turn
            System.out.println("Enter your placement (1-9)");
            int playerPos = scanner.nextInt();
            //To check position is taken or not.
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position taken! please choose again!");
                playerPos = scanner.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
            System.out.println("");
            // Cpu turn and generate random number between (0 to 8) +1
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
            if(result.length() > 0){
                System.out.println(result);
                break;
            }


        }
    }
    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            //Store the value in player/cpu positions
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        // Check player / cpu decision and place piece at selected index
        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;

        }
        printGameBoard(gameBoard);

    }
    public static String checkWinner(){
        //To set the winning conditions.
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winning = new ArrayList<>();

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(rightCol);
        winning.add(midCol);
        winning.add(cross1);
        winning.add(cross2);
        //Run this to loop through the list of winning to check winning
        for( List l : winning) {
            if(playerPositions.containsAll(l)){
                return "Congratulations you won !";
            }else if (cpuPositions.containsAll(l)){
                return "Cpu wins, Sorry! :(";
            }else if(playerPositions.size() + cpuPositions.size() == 9){
                return "cat";
            }
        }

        return  "";
    }
}